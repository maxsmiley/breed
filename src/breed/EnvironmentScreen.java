/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package breed;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

/**
 *
 * @author xx
 */
public class EnvironmentScreen extends Panel{
    MainFrame frame;
    double mouseX, mouseY, selectionX, selectionY, viewX, viewY, newX=0, newY=0;
    double viewHeight, viewWidth, newHeight=0, newWidth=0;
    Environment environment;
    Polygon selection = new Polygon();
    AffineTransform transform = new AffineTransform();
    boolean initialAdjustment = true;
    boolean adjust= true, adjusted = false; //Ajust is a boolean for whether or not the screen SHOULD ajust
    boolean keys[] = new boolean[128];
    double viewPanSpeed = 7;

//    Timer timer;

    Organism selectedOrganism; //More detailed on info screen
    ArrayList<Organism> selectedOrganisms = new ArrayList<Organism>();

        Runtime r = Runtime.getRuntime();
    long memory = r.freeMemory();
    int gcDelay = 150;
    int gcCount = 0;


    public EnvironmentScreen(){
        for(int i = 0 ; i < keys.length; i ++){
            keys[i] = false;
        }
        
       
        addMouseMotionListener(this);
        addMouseListener(this);
        addMouseWheelListener(this);
    }

    public EnvironmentScreen(MainFrame frame1){
        this();
        setFrame(frame1);
        //environment = new Environment(this);
                //environment.setPaintDelay(frame.timer.getTime()/50);

    }
public void garbageCollection(){
         if(gcCount >= gcDelay ){
        //memory = r.freeMemory();
        //System.out.println(memory + " free space " + this.getClass().getSimpleName());
         System.gc();
       // memory = r.freeMemory();
        //System.out.println(memory + " free space after garbace collector " + this.getClass().getSimpleName());
        }else{
            gcCount++;
        }
    }
    @Override
    public void paint(Graphics g2){
            garbageCollection();
        //Initial tranformation settings
        if(initialAdjustment){
            newHeight= this.getHeight();
            newWidth= this.getWidth();
            viewHeight= this.getHeight();
            viewWidth= this.getWidth();
            viewX= this.getX();
            viewY= this.getY();
            newX= 0;
            newY= 0;
            initialAdjustment=false;
        }

          Graphics2D g = (Graphics2D)g2;
        double wid = this.getWidth();
        double hei = this.getHeight();

        //Backround
        g.setColor(Color.BLACK);
        g.fillRect(0,0,this.getWidth(),this.getHeight());


        if(keys[KeyEvent.VK_UP]){
            viewY+=viewPanSpeed;
            adjusted = false;
        }
        if(keys[KeyEvent.VK_DOWN]){
            viewY-=viewPanSpeed;
            adjusted = false;
        }
        if(keys[KeyEvent.VK_RIGHT]){
            viewX-=viewPanSpeed;
            adjusted = false;
        }
        if(keys[KeyEvent.VK_LEFT]){
            viewX+=viewPanSpeed;
            adjusted = false;
        }

        if(adjust){
            transform.scale((viewWidth/wid) ,((viewHeight)/(hei+0)));
            transform.translate(viewX-this.getX(),viewY-this.getY());
             newWidth *= viewWidth/this.getWidth();
             newHeight *= viewHeight/this.getHeight();
            g.transform(transform);
            viewWidth=wid;
            viewHeight=hei+0;
            newX -= (viewX-this.getX())/(this.getWidth()/newWidth);
            newY -= (viewY-this.getY())/(this.getHeight()/newHeight);
            viewX=this.getX();
            viewY=this.getY();
            adjusted=false;
        }else{
            if(adjusted==false){ //Note that the adjust boolean is different than the adjusted boolean
                    transform.scale((viewWidth/this.getWidth()) ,(viewHeight/this.getHeight()));
                    transform.translate(viewX-this.getX(),viewY-this.getY());
                    newWidth *= viewWidth/this.getWidth();
                    newHeight *= viewHeight/this.getHeight();


                g.transform(transform);
//                viewWidth=this.getWidth();
//                viewHeight=this.getHeight();
                adjusted=true;
            }

        }



        //Environment
        environment.paint(g);

        //Selection
        g.setColor(new Color(20,255,80,40));
        if(selection.npoints>1)
        g2.fillPolygon(selection);
        g.setColor(new Color(0,255,0,150));
        if(selectedOrganisms.size()>0){
            selectedOrganism = selectedOrganisms.get(0); //Change this to user selectable
         for(Organism o : selectedOrganisms){
             g.drawOval((int)(o.getX()-(o.size/2)-5), (int)(o.getY()-(o.size/2)-5), (int)o.size+10, (int)o.size+10);
         }
        }

        if(frame.infoScreen!= null){
            for(int i = 0; i < frame.infoScreen.infoBoxs.size(); i ++){
                 //Locate Unit
                if(frame.infoScreen.infoBoxs.get(i).isLocatingUnit()){
                frame.infoScreen.infoBoxs.get(i).locateUnit(g);
                }
            }
        }

    }

    public void createEnvironment(){
        environment = new Environment(this);
    }

    public void selectionShift(){
        
    }


    public MainFrame getFrame() {
        return frame;
    }

    public void setFrame(MainFrame frame) {
        this.frame = frame;
    }


    public void mouseClicked(MouseEvent me) {
        mouseX = me.getX()+(newX);
        mouseY = me.getY()+(newY);
    }

    public void mousePressed(MouseEvent me) {
       mouseX = me.getX()+(newX);
        mouseY = me.getY()+(newY);
//         System.out.println(viewWidth + " " + this.getWidth() + " "+newWidth);
         selection.reset();
         selectionX = mouseX*(this.getWidth()/newWidth);
         selectionY = mouseY*(this.getHeight()/newHeight);
         

    }

    public void mouseReleased(MouseEvent me) {
        selection.reset();
    }

    public void mouseEntered(MouseEvent me) {
    }

    public void mouseExited(MouseEvent me) {
    }

    public void mouseDragged(MouseEvent me) {
        mouseX = me.getX()+(newX);
        mouseY = me.getY()+(newY);

      //Selection
        selection.reset();
        selection.addPoint((int)(selectionX), (int)(selectionY));
        selection.addPoint((int)(mouseX*(this.getWidth()/newWidth)), (int)(selectionY));
        selection.addPoint((int)(mouseX*(this.getWidth()/newWidth)), (int)(mouseY*(this.getHeight()/newHeight)));
        selection.addPoint((int)(selectionX), (int)(mouseY*(this.getHeight()/newHeight)));


        selectedOrganisms.clear();
              for(Organism o : environment.getOrganisms()){
                  if(selection.contains(o.getX(), o.getY())){
                      selectedOrganisms.add(o);
                  }

              }
              
       frame.infoScreen.graphs.clear();
       frame.infoScreen.infoBoxs.clear();



    }

    public void mouseMoved(MouseEvent me) {
    }

    public Organism getSelectedOrganism() {
        return selectedOrganism;
    }

    public void setSelectedOrganism(Organism selectedOrganism) {
        this.selectedOrganism = selectedOrganism;
    }

    public ArrayList<Organism> getSelectedOrganisms() {
        return selectedOrganisms;
    }

    public void setSelectedOrganisms(ArrayList<Organism> selectedOrganisms) {
        this.selectedOrganisms = selectedOrganisms;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent mwe) {
        int notches = mwe.getUnitsToScroll();
       if (notches < 0) { //Zooming in
            if(newWidth < this.getWidth()*4){ //Limited to 4x zoom in

         double oldHeight = viewHeight;
         double oldWidth = viewWidth;
               viewHeight-=(notches*10);
               viewWidth-=(notches*10);
               viewX-=(viewWidth-oldWidth)/2;
               viewY-=(viewHeight-oldHeight)/2;

                   
           }
       } else { //Zooming out
            if(newWidth > this.getWidth()/1.5 ){ //Limited to 1.5x zoom out

         double oldHeight = viewHeight;
         double oldWidth = viewWidth;
                 viewHeight-=(notches*10);
                 viewWidth-=(notches*10);
                viewX-=(viewWidth-oldWidth)/2;
                viewY-=(viewHeight-oldHeight)/2;
                   adjusted=false;
            }
            
       }
     
       if (mwe.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {

       }
    }

    public void keyReleased(KeyEvent e) {
                 if(e.getKeyCode() < keys.length){
                keys[e.getKeyCode()] = false;
         }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {

         if(e.getKeyCode() < keys.length){
                keys[e.getKeyCode()] = true;
         }
      
    }


}
