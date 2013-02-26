/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package breed;

import BodyParts.BodyPart;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Polygon;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 *
 * @author xx
 */
public class OrganismInfoBox {
    double x, y;
    double height, width;
    Graph graph;
    Organism organism;
    InfoScreen screen;
    Lable selectedGraph;
    ArrayList<Lable> lables = new ArrayList<Lable>();
    ArrayList<Lable> parts = new ArrayList<Lable>();
    int lY=0;
    boolean highlighted = false;
    Polygon area = new Polygon();
    Polygon locationEffect =  new Polygon();
    boolean locatingUnit = false;
    double locationEffectRadius=650;
    double scale = .9;
    Lable statsT, partsT;
    Lable tabs[];
    Lable locateUnit;
    public final static int TAB_STATS = 0, TAB_PARTS = 1;
    int yC;
    
    public OrganismInfoBox(){

    }
    public OrganismInfoBox(Organism o, InfoScreen f, double x1, double y1){
        screen = f;
        organism = o;
        x= x1; y = y1;
        testSettings();
    }

    public void testSettings(){
        locateUnit = new Lable("Locate", 0, (int)(x+280 - 80), (int)(y-10));
        locateUnit.setDisplayValue(false);
        locateUnit.setWidth(50);
        tabs = new Lable[2];
        statsT = new Lable("FaF", 0, (int)(x), (int)(y-10)); //facts and figures
        tabs[0] = statsT;
        tabs[0].setSelected(false);
        tabs[0].setWidth(50);
        tabs[0].setDisplayValue(false);
        tabs[0].setIsInt(true);
        tabs[0].setIsDouble(false);
        partsT = new Lable("Parts", 0, (int)(x+ 50), (int)(y-10));
        tabs[1] = partsT;
        tabs[1].setSelected(false);
        tabs[1].setWidth(50);

        tabs[screen.defaultTab].setSelected(true); //Sets displayed tab based on infoScreen //Change?
        double lableWidth = 10*scale;
        lables.add(new Lable("Age",organism.age, (int)(x), (int)(y+lY)));//0
        lY+=lableWidth;
        lables.add(new Lable("Generation",organism.generation, (int)(x), (int)(y+lY)));//1
        lY+=lableWidth;
        lables.add(new Lable("Sex",organism.sex, (int)(x), (int)(y+lY)));//2
        lY+=lableWidth;
        lables.add(new Lable("Energy",organism.energy, (int)(x), (int)(y+lY))); //3
        lables.get(3).setSelected(true); //Preset Graph value
        lY+=lableWidth;
        if(organism.fats.size()>0){
            lables.add(new Lable("Stored Energy (Fat)",organism.fats.get(0).getStoredEnergy(), (int)(x), (int)(y+lY)));
            lY+=lableWidth;
        }
        lables.add(new Lable("Weight",organism.weight, (int)(x), (int)(y+lY)));//2
        lY+=lableWidth;
        lables.add(new Lable("Speed",organism.speed, (int)(x), (int)(y+lY)));
        lY+=lableWidth;

        for(Lable l : lables){
            if(l.isSelected()){
                selectedGraph = l;
                break;
            }
        }

        height = lables.size()*10*scale +6; //6 for tabs

//            if(o.fats.size()>0){
//                g.drawString("Stored Energy (Fat) : " + (float)o.fats.get(0).getStoredEnergy(), selectedOrganismX, selectedOrganismY+yC);
//                yC+=10;
//            }
//            g.drawString("Speed: " + (float)o.speed, selectedOrganismX, selectedOrganismY+yC);
        
    }
    public void draw(Graphics g){
        height = lables.size()*10*scale +6;
        area.reset();
//        area.addPoint((int)x-5, (int)y-11);
        area.addPoint((int)x-5, (int)y-19);
//        area.addPoint((int)(x+280*scale), (int)y-11);
        area.addPoint((int)(x+280*scale), (int)y-19);

        g.setColor(Color.WHITE);
        //Different Displays
        if(tabs[TAB_STATS].isSelected()){
            DrawStatsTab(g);
        }else
        if(tabs[TAB_PARTS].isSelected()){
            DrawPartsTab(g);
        }

        //Tabs
        g.setColor(Color.DARK_GRAY.darker());
        //g.drawLine((int)x-5, (int)y-19+10, (int)(x+280*scale), (int)y-19+10);
        g.fillRect((int)x-5, (int)y-19, (int)(5+280*scale), (int)10);
        for(int i = 0; i < tabs.length; i ++){
                tabs[i].draw(g);
                tabs[i].update(organism.parts.size());
        }
        locateUnit.draw(g);


        area.addPoint((int)(x+280*scale), (int)(y+height)-6);
            area.addPoint((int)x-5, (int)(y+height)-6);
               //draw box
            g.setColor(Color.DARK_GRAY);
            g.drawPolygon(area);
            
            if(!organism.isAlive()){
                g.setColor(new Color(0,0,0,100));
                g.fillPolygon(area);
            }

         

            
    }

    public void DrawStatsTab(Graphics g){
        yC=0;
         g.setFont(new Font("HELVETICA", Font.PLAIN, (int)(10*scale)));
        for(Lable l : lables){
            l.draw(g);
            if(l.getName()=="Age"){
                l.update(organism.age);
            }
            if(l.getName()=="Generation"){
                l.update(organism.generation);
            }
            if(l.getName()=="Stored Energy (Fat)" && organism.fats.size() > 0){
                l.update(organism.fats.get(0).getStoredEnergy());
            }
            if(l.getName()=="Speed"){
                l.update(organism.speed);
            }
            if(l.getName()=="Energy"){
                l.update(organism.energy);
                l.setAnotation(" ("+(float)organism.getEnergyChange()+")");
            }
            if(l.getName()=="Weight"){
                l.update(organism.weight);
            }
            yC+=10*scale;
        }



            boolean graphExists = false;
            for(int i = 0 ; i < screen.graphs.size(); i ++){
                if(screen.graphs.get(i).getOrganism()==organism){
                    graph.setLable(selectedGraph);
                    screen.graphs.get(i).addValue(selectedGraph.getValue());
                    screen.graphs.get(i).draw(g);
                    screen.graphs.get(i).setValueName(selectedGraph.getName());
                    graphExists = true;
                    break;
                }

            }

            if(!graphExists){
                graph = new Graph(organism, organism.getEnergy(), (int)x+150*scale, (int)(y), 10);
                graph.setHeight(height-(10*scale));
                graph.setWidth(100*scale);
                screen.graphs.add(graph);
            }

            for(Lable l : lables){
            if(l.isSelected()){
                if(l != selectedGraph){
                    graph.reset();
                    graph.setLable(selectedGraph);
                 }
                selectedGraph = l;
                if(l.hasExtremes){
                    graph.setMax(l.getMax());
                    graph.setMin(l.getMin());
                }


                break;
            }
             }

//            area.addPoint((int)(x+280*scale), (int)y+yC-6);
//            area.addPoint((int)x-5, (int)y+yC-6);

     

                 //Inventory
             g.setFont(new Font("HELVETICA", Font.PLAIN, (int)(8*scale)));
            int count = 0;
            int xShift =(int)(290*scale);
            int maxCollumns = 3;
            int collumns=0;
            if(organism.mouth != null){


              while(count < organism.entities.size() && collumns < maxCollumns){
                   for(int i = 0 ; count < organism.entities.size() && i < organism.entities.size() && i*10<height; i ++, count++){
                     String s = organism.entities.get(count).getBodyPart().getClass().getName().replaceAll("breed.", "");
                     g.drawString(s,(int)x+xShift, (int)y+(int)(10*i*scale));
                   }
                   xShift+=(int)(25*scale);
                   collumns++;
              }
//            while(count < organism.entities.size() && collumns < maxCollumns){
//                   for(int i = 0 ; count < organism.entities.size() && i < organism.entities.size() && i*10<height; i ++, count++){
//                     String s = organism.entities.get(count).getBodyPart().getClass().getName().replaceAll("breed.", "");
//                     g.drawString(s,(int)x+xShift, (int)y+(int)(10*i*scale));
//                   }
//                   xShift+=(int)(25*scale);
//                   collumns++;
//                }
            }
    }

     public void DrawPartsTab(Graphics g){
         int yShift = 0;
//         for(BodyPart b: organism.getParts()){
//                g.drawString(b.getClass().getSimpleName(), (int)(x), (int)(y+yShift));
//                yShift+=10;
//         }

               //Inventory
             g.setFont(new Font("HELVETICA", Font.PLAIN, (int)(8*scale)));
            int count = 0;
            int xShift = 0;
            int maxCollumns = 2;
            int collumns=0;
//            if(organism.mouth != null){

            while(count < organism.parts.size() && collumns < maxCollumns){
                   for(int i = 0 ; count < organism.parts.size() && i < organism.parts.size() && i*10<height; i ++, count++){
                    // String s = organism.parts.get(count).getClass().getSimpleName() + " " + (float)organism.parts.get(count).getEnergy();//getName().replaceAll("breed.", "");
                      String s = organism.parts.get(count).getClass().getSimpleName() + " " + (float)organism.parts.get(count).getEnergy();//getName().replaceAll("breed.", "");
                     g.drawString(s,(int)x+xShift, (int)y+(int)(10*i*scale));
                   }
                   xShift+=(int)(55*scale);
                   collumns++;
                }
//
//
//            while(count < organism.parts.size() && collumns < maxCollumns){
//                   for(int i = 0 ; count < organism.parts.size() && i < organism.parts.size() && i*10<height; i ++, count++){
//                     String s = organism.parts.get(count).getClass().getSimpleName();//getName().replaceAll("breed.", "");
//                     g.drawString(s,(int)x+xShift, (int)y+(int)(10*i*scale));
//                   }
//                   xShift+=(int)(35*scale);
//                   collumns++;
//                }
//            }


         
     }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Organism getOrganism() {
        return organism;
    }

    public void setOrganism(Organism organism) {
        this.organism = organism;
    }

    public Lable getSelectedGraph() {
        return selectedGraph;
    }

    public void setSelectedGraph(Lable selectedGraph) {
        this.selectedGraph = selectedGraph;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Polygon getArea() {
        return area;
    }

    public void locateUnit(Graphics g2){
          for(double i = 0; i <= 100; i +=10){
              g2.setColor(new Color(0,255,255,100-(int)i));
//          g.drawOval((int)(organism.getX()-locationEffectRadius/2),
//                  (int)(organism.getY()-locationEffectRadius/2),(int)(locationEffectRadius), (int)(locationEffectRadius));
          g2.drawOval((int)(organism.getX()-(locationEffectRadius+i)/2.0), (int)(organism.getY()-
          (locationEffectRadius+i)/2.0),(int)((locationEffectRadius+i)), (int)(locationEffectRadius+i));
          }
        locationEffectRadius-=75;
        if(locationEffectRadius <=0){
            locatingUnit = false;
            locationEffectRadius = 650;
        }
    }

    public boolean isHighlighted() {
        return highlighted;
    }

    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }

    public boolean isLocatingUnit() {
        return locatingUnit;
    }

    public void setLocatingUnit(boolean locatingUnit) {
        this.locatingUnit = locatingUnit;
    }

    public Polygon getLocationEffect() {
        return locationEffect;
    }

    public void setLocationEffect(Polygon locationEffect) {
        this.locationEffect = locationEffect;
    }

    public double getLocationEffectRadius() {
        return locationEffectRadius;
    }

    public void setLocationEffectRadius(double locationEffectRadius) {
        this.locationEffectRadius = locationEffectRadius;
    }

    public Lable getLable(String name){
        for(Lable l : lables){
            if(l.getName()==name){
                return l;
            }
        }
        return null;
    }


    public void MouseClicked(double x1, double y1){

         for(int t = 0; t < tabs.length; t ++){
             if(tabs[t].isHighlighted()){
                tabs[t].setSelected(true);
                    for(int t2 = 0; t2 < tabs.length; t2 ++){
                      if(tabs[t2] != tabs[t]){
                          tabs[t2].setSelected(false);
                      }
                  }
                      break;
             }else{

             }
         }
        for(int l = 0; l < lables.size(); l ++){
                     if(lables.get(l).isHighlighted()){
                         lables.get(l).setSelected(true);
                            for(int l2 = 0; l2 < lables.size(); l2 ++){
                              if(lables.get(l2) != lables.get(l)){
                                  lables.get(l2).setSelected(false);
                              }
                          }
                              break;
                     }else{

                     }
                 }
       
      
    }

    public void MouseMoved(double x1, double y1){
        if(getArea().contains(x1,y1)){
             for(int l = 0; l < lables.size(); l ++){
                 if(lables.get(l).getArea().contains(x1, y1)){
                     lables.get(l).setHighlighted(true);
                 }else{
                     lables.get(l).setHighlighted(false);
                 }
             }

             for(int t = 0; t < tabs.length; t ++){
                 if(tabs[t].getArea().contains(x1, y1)){
                     tabs[t].setHighlighted(true);
                 }else{
                     tabs[t].setHighlighted(false);
                 }
             }
             if(locateUnit.getArea().contains(x1,y1)){
                 locateUnit.setHighlighted(true);
             }else{
                 locateUnit.setHighlighted(false);
             }
            }

    }

    public Lable getLocateUnit() {
        return locateUnit;
    }

    public void setLocateUnit(Lable locateUnit) {
        this.locateUnit = locateUnit;
    }


}
