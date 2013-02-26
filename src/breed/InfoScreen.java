/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package breed;

import BodyParts.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author xx
 */
public class InfoScreen extends Panel {
    MainFrame frame;
    ArrayList<Lable> lables = new ArrayList<Lable>();
    ArrayList<Button> buttons = new ArrayList<Button>();
    double lablesX= 10, lablesY = 10;
    int selectedOrganismX=10,selectedOrganismY=450, infoBoxsMaxHeight = 600;
    int yC;
    Button gameSpeedb, resetb, tab1b, tab2b;
    Graph animalsPop, traits;
    boolean setup = true;
    int defaultTab = 0;
   

    ArrayList<Graph> graphs = new ArrayList<Graph>();
    ArrayList<OrganismInfoBox> infoBoxs = new ArrayList<OrganismInfoBox>();
    public InfoScreen(MainFrame f){
        testSettings();
        setFrame(f);
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    public void testSettings(){

           gameSpeedb = new Button("Game Speed",(int)lablesX+150,100,100,25,true){
             public void pressEvent(){
                frame.environmentScreen.environment.gameSpeed=(-4+
                         (int)(32*(gameSpeedb.getSliderRect().getX()-(gameSpeedb.getX()))/(gameSpeedb.getWidth()-20)));
             }
         };
         gameSpeedb.setShowCenterText(false);
         buttons.add(gameSpeedb);

         tab1b = new Button("Fact/Figures",(int)selectedOrganismX,selectedOrganismY-95,62,20){
             public void selectEvent(){
                 defaultTab = 0;
             }
         };
         tab1b.setBorderColor(Color.GRAY);
         tab1b.setHighlightBorderColor(Color.WHITE);
         tab1b.setSelectedBorderColor(Color.GRAY);
         tab1b.setPressedBorderColor(Color.GRAY);
         tab1b.setTextColor(Color.WHITE);
         tab1b.setSelectedTextColor(Color.ORANGE);
         tab1b.setCenterFont(new Font("FUTURA", Font.PLAIN, 10)) ;
         buttons.add(tab1b);
          tab2b = new Button("Body Parts",(int)selectedOrganismX+69,selectedOrganismY-95,62,20){
             public void selectEvent(){
                 defaultTab = 1;
             }
         };
           tab2b.setBorderColor(Color.GRAY);
         tab2b.setHighlightBorderColor(Color.WHITE);
         tab2b.setSelectedBorderColor(Color.GRAY);
         tab2b.setPressedBorderColor(Color.GRAY);
         tab2b.setTextColor(Color.WHITE);
         tab2b.setSelectedTextColor(Color.ORANGE);
         tab2b.setCenterFont(new Font("FUTURA", Font.PLAIN, 10)) ;
         
         buttons.add(tab2b);

         resetb = new Button("Reset",(int)lablesX+18,132,50,20){
             public void selectEvent(){
                 frame.environmentScreen.createEnvironment();
             }
         };
         resetb.setBorderColor(Color.GRAY);
         resetb.setHighlightBorderColor(Color.WHITE);
         resetb.setSelectedBorderColor(Color.GRAY);
         resetb.setPressedBorderColor(Color.GRAY);
         resetb.setTextColor(Color.WHITE);
         resetb.setCenterFont(new Font("FUTURA", Font.PLAIN, 15)) ;
         buttons.add(resetb);

          
    }

    public void paint(Graphics g){
        if(setup){
            animalsPop = new Graph(frame.environmentScreen.environment.getSpecies("Test1").size(), 220, 200, 50, 2 );
//            graphs.add(animalsPop);
            animalsPop.setDelay(10);
            animalsPop.setHeight(40);
            animalsPop.setStyle(2);
            animalsPop.getGraphValue(0).setValueName("Animals");
            animalsPop.getGraphValue(0).setDelay(1);
            animalsPop.getGraphValue(1).setValueName("Plants");
            animalsPop.getGraphValue(1).setDelay(1);
            animalsPop.setEnvironment(frame.environmentScreen.environment);

             traits = new Graph(frame.environmentScreen.environment.getSpecies("Test1").size(), 220, 255, 50, 2 );
            traits.setDelay(10);
            traits.setHeight(40);
            traits.setStyle(2);
            traits.getGraphValue(0).setValueName("LegStrength");
            traits.getGraphValue(0).setDelay(1);
            traits.getGraphValue(1).setValueName("Delivery ");
            traits.getGraphValue(1).setDelay(1);
            traits.setEnvironment(frame.environmentScreen.environment);
            traits.setSeperateExtremes(true);
            setup=false;
        }
        
        //Background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 100000, 100000);

        //Lables
        g.setColor(Color.WHITE);
//        for(Lable l : lables){
//            g.drawString(l.getValueName()+" "+l.getValue(), (int)lablesX,(int)(lablesY*lables.indexOf(l)));
//        }
        g.drawString( "Animals Living Population: "+frame.environmentScreen.environment.getSpecies("Test1").size(),(int)lablesX, 200);
        g.drawString( "Plant Living Population: "+frame.environmentScreen.environment.getSpecies("Test2").size(),(int)lablesX, 220);
        //Clock
        g.drawString( "Time: "+(int)(frame.environmentScreen.environment.getTime()/100)%12 +":"+ 
                (int)((60./100.)*(frame.environmentScreen.environment.getTime()%100)) +
                (frame.environmentScreen.environment.getTime()<
                frame.environmentScreen.environment.DAY ? " pm" : " am"),(int)lablesX, 100);
        g.drawString( "Days: "+(int)(frame.environmentScreen.environment.getTimeDays()),(int)lablesX+20, 115);
        g.drawString( "GameSpeed: x" + (int)frame.environmentScreen.environment.getGameSpeed(),(int)lablesX+150, 100);

        //SelectedOrganism
        
        yC=0;
       OrganismInfoBox  currentbox=null;
       for(int o = 0; o < frame.environmentScreen.selectedOrganisms.size(); o ++){
//        for(Organism o: frame.environmentScreen.selectedOrganisms){
            if(yC < infoBoxsMaxHeight){
                g.setFont(new Font("HELVETICA", Font.PLAIN, 10));
                boolean infoBoxExists = false;
                for(int i = 0 ; i < infoBoxs.size(); i ++){
                    if(infoBoxs.get(i).getOrganism()==frame.environmentScreen.selectedOrganisms.get(o)){
                        infoBoxs.get(i).draw(g);
                        infoBoxExists = true;
                        currentbox = infoBoxs.get(i);
                        break;
                    }

                }
                if(!infoBoxExists){
                infoBoxs.add( currentbox = new OrganismInfoBox(frame.environmentScreen.selectedOrganisms.get(o), this, selectedOrganismX, selectedOrganismY+yC-50));
               // currentbox = infoBoxs.get(infoBoxs.size()-1);
               // currentbox.draw(g);
                }

                yC+=currentbox.getHeight()+16;
                if(yC > infoBoxsMaxHeight){
                    //If size over limite - shave off a box
                    infoBoxs.remove(infoBoxs.size()-1);
                    
                }
//                //Locate Unit
//                if(currentbox.isLocatingUnit()){
//                currentbox.locateUnit(frame.getEnvironmentScreen().getGraphics());
//                }

  
          }
        }
       
        if(frame.environmentScreen.selectedOrganism != null){
            

        }
        //Buttons
        for(Button b : buttons){
            b.draw(g);
        }

           //Graphs

              //animalsPop.addValue(frame.environmentScreen.environment.getSpecies("Test1").size());
              animalsPop.getGraphValue(0).addValue(frame.environmentScreen.environment.getSpecies("Test1").size());
              animalsPop.getGraphValue(1).addValue(frame.environmentScreen.environment.getSpecies("Test2").size());
              animalsPop.draw(g);

              double value = 0;
              double count = 0;
              for(int i = 0; i < frame.environmentScreen.environment.getOrganisms().size(); i ++){
                  if(frame.environmentScreen.environment.getOrganisms().get(i).getMutables().getMutable("strength")!= null){
                value+= frame.environmentScreen.environment.getOrganisms().get(i).getMutables().getMutable("strength").getValue();
                count++;
                  }
              }
              value/=count;
              traits.getGraphValue(0).addValue(value);

              value = 0;
              count = 0;
              for(int i = 0; i < frame.environmentScreen.environment.getOrganisms().size(); i ++){
                  if(frame.environmentScreen.environment.getOrganisms().get(i).getMutables().getMutable("delivery")!= null){
                value+= frame.environmentScreen.environment.getOrganisms().get(i).getMutables().getMutable("delivery").getValue();
                count++;
                  }
              }
              value/=count;
              traits.getGraphValue(1).addValue(value);
              traits.draw(g);
        

    }

    public MainFrame getFrame() {
        return frame;
    }

    public void setFrame(MainFrame frame) {
        this.frame = frame;
    }

    public ArrayList<Lable> getLables() {
        return lables;
    }

    public void setLables(ArrayList<Lable> lables) {
        this.lables = lables;
    }



    public void mouseClicked(MouseEvent me) {
        mouseX = me.getX();
        mouseY = me.getY();
        for(int i = 0; i < infoBoxs.size(); i ++){
            if(infoBoxs.get(i).getArea().contains(mouseX,mouseY)){
                infoBoxs.get(i).MouseClicked(mouseX,mouseY);
                 if(infoBoxs.get(i).getLocateUnit().getArea().contains(mouseX, mouseY)){
                        infoBoxs.get(i).setLocatingUnit(true);
                 }
            }
        }
//        for(OrganismInfoBox b : infoBoxs){
//            if(b.getArea().contains(mouseX,mouseY)){
//                  for(Lable l : b.lables){
//                     if(l.isHighlighted()){
//                         l.setSelected(true);
//                          for(Lable l2 : b.lables){
//                              if(l2 != l){
//                                  l2.setSelected(false);
//                              }
//                          }
//                              break;
//                     }else{
//
//                     }
//                 }
//
//                 //Locating Unit
//                 b.setLocatingUnit(true);
//
//
//            //**Make a locate button
//                }
//
//
//        }
    }

    public void mousePressed(MouseEvent me) {
         mouseX = me.getX();
         mouseY = me.getY();
         for(Button b : buttons){
             b.registerPress(mouseX, mouseY);
         }


    }

    public void mouseReleased(MouseEvent me) {
        mouseX = me.getX();
         mouseY = me.getY();
        for(Button b : buttons){
             b.registerSelect(mouseX, mouseY);
         }
        
    }

    public void mouseEntered(MouseEvent me) {
    }

    public void mouseExited(MouseEvent me) {
    }

    public void mouseDragged(MouseEvent me) {
         mouseX = me.getX();
         mouseY = me.getY();
         for(Button b : buttons){
             b.registerPress(mouseX, mouseY);
         }

    }

    public void mouseMoved(MouseEvent me) {
         mouseX = me.getX();
         mouseY = me.getY();
         //For organism information boxes

         for(int i = 0; i < infoBoxs.size(); i ++){
            if(infoBoxs.get(i).getArea().contains(mouseX,mouseY)){
                infoBoxs.get(i).MouseMoved(mouseX,mouseY);
            }
        }
//        for(OrganismInfoBox b : infoBoxs){
//
//            if(b.getArea().contains(mouseX,mouseY)){
//             for(Lable l : b.lables){
//                 if(l.getArea().contains(mouseX, mouseY)){
//                     l.setHighlighted(true);
//                 }else{
//                     l.setHighlighted(false);
//                 }
//             }
//            }
//        }

        for(Button b : buttons){
             b.registerHighlight(mouseX, mouseY);
         }
    }



}
