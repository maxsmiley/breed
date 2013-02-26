/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package breed;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author xx
 */
public class Environment extends Screen {
    Panel panel;
    Screen display;
    Terrain terrain[][];
    Color gridColor = Color.BLACK;
    double gameSpeed=5;
    double gridWidth = 15, gridHeight=15; //20,20
    ArrayList<Organism> organisms = new ArrayList<Organism>();
    ArrayList<Organism> deadOrganisms = new ArrayList<Organism>();
    ArrayList<Entity> entities = new ArrayList<Entity>();
    ArrayList<Call> calls = new ArrayList<Call>();
    double paintDelay = 0;
    double paintCount = 0;
    double time=0; //in hours:minutes
    double timeDays=0;
    double secondLength= .5;
    boolean showdaylight = false;
    boolean daytime = true;
    double DAY = 1200, NIGHT= 2400;
    boolean displayVision = true;
    boolean displaySound = true;
    boolean displayGrid = true;
    boolean displayGridLines = false;
    boolean displayTasks = true;
    double displayMaxOrganisms = 80;




    //User interface

 

    public Environment(){
        
        display = new Screen();
        setX(0);
        setY(0);
        setWidth(1100);
        setHeight(900);
        testSettings();
    }

    public Environment(Panel panel1){
        this();
        setPanel(panel1);
        setX(0);
        setY(0);
        display.setX(panel.getX());
        display.setY(panel.getY());
        display.setWidth(panel.getWidth());
        display.setHeight(panel.getHeight());
        setHeight((int)((panel.getHeight()-50)/gridHeight)*gridHeight);
        setWidth((int)((panel.getWidth())/gridWidth)*gridWidth);
        setTerrainGrid();
       
        
    }
    

     public Environment(Panel panel1, double x1, double y1, double w1, double h1){
        this();
        setPanel(panel1);
        display.setX(panel.getX());
        display.setY(panel.getY());
        display.setWidth(panel.getWidth());
        display.setHeight(panel.getHeight());

        setX(x1);
        setY(y1);
        setWidth(w1);
        setHeight(h1);
        setTerrainGrid();

    }

     public void testSettings(){
        organisms.add(new Organism(this,"Test1"));
        organisms.add(new Organism(this,"Test1"));
        organisms.add(new Organism(this,"Test1"));
        organisms.add(new Organism(this,"Test1"));
        organisms.add(new Organism(this,"Test1"));
        organisms.add(new Organism(this,"Test1"));
        organisms.add(new Organism(this,"Test1"));
        organisms.add(new Organism(this,"Test1"));
        organisms.add(new Organism(this,"Test1"));
        organisms.add(new Organism(this,"Test1"));
        organisms.add(new Organism(this,"Test1"));
        organisms.add(new Organism(this,"Test1"));
        organisms.add(new Organism(this,"Test2"));
        organisms.add(new Organism(this,"Test2"));
        organisms.add(new Organism(this,"Test2"));
        organisms.add(new Organism(this,"Test2"));
        organisms.add(new Organism(this,"Test2"));
        organisms.add(new Organism(this,"Test2"));
        organisms.add(new Organism(this,"Test2"));
        organisms.add(new Organism(this,"Test2"));
        organisms.add(new Organism(this,"Test2"));
        organisms.add(new Organism(this,"Test2"));
        organisms.add(new Organism(this,"Test2"));
        organisms.add(new Organism(this,"Test2"));
        organisms.add(new Organism(this,"Test2"));
        organisms.add(new Organism(this,"Test2"));
        organisms.add(new Organism(this,"Test2"));
        organisms.add(new Organism(this,"Test2"));
        organisms.add(new Organism(this,"Test2"));
        organisms.add(new Organism(this,"Test2"));
        organisms.add(new Organism(this,"Test2"));
        entities.add(new Entity(this, 100,200, new Leaf()));
//        organisms.add(new Organism(this,"Test3"));
//        organisms.add(new Organism(this,"Test3"));
//        organisms.add(new Organism(this,"Test3"));
//        organisms.add(new Organism(this,"Test3"));
//        organisms.add(new Organism(this,"Test3"));
 


       
     
     }

    public void setTerrainGrid(){
        terrain = new Terrain[(int)(this.getWidth()/gridWidth)][(int)(this.getHeight()/gridHeight)];
//        for(int i = 0; i < terrain.length; i++){
//            for(int i2 = 0; i2 < terrain[i].length; i2++){
//                terrain[i][i2]=new Terrain(Terrain.TYPE_DIRT);
//            }
//        }
        for(int i = 0; i < terrain.length; i++){
            for(int i2 = 0; i2 < terrain[i].length; i2++){
                terrain[i][i2]=new Terrain(-2);
            }
        }
        terrain[0][0] = new Terrain(Terrain.TYPE_DIRT);
        generateSpot(terrain[0][0],1,1);
    }

    public void generateSpot(Terrain t, int x1, int y1){
        if(x1 < terrain.length && x1 >= 0){
            if(y1 < terrain[x1].length && y1 >= 0){
                if(terrain[x1][y1].getType() == -2){
                    int type=0;
                    int roll = (int)(Math.random()*100);
                    // redo this to be  a system
                    if(t.getType() == Terrain.TYPE_DIRT){
                            if(roll < 2 ){
                                type = Terrain.TYPE_ROCK;
                            }else{
                                if(roll < 99 ){
                                    type = Terrain.TYPE_DIRT;
                                }
                                else{
                                    type = Terrain.TYPE_GRASS;

                                }
                            }
                    }
                        if(t.getType() == Terrain.TYPE_ROCK){
                            if(roll < 2 ){
                                type = Terrain.TYPE_GRASS;
                            }else{
                                if(roll < 55 ){
                                    type = Terrain.TYPE_ROCK;
                                }
                                else{
                                    type = Terrain.TYPE_DIRT;

                                }
                            }
                    }
                    if(t.getType() == Terrain.TYPE_GRASS){
                            if(roll < 95 ){
                                type = Terrain.TYPE_GRASS;
                            }
                            else{
                                type = Terrain.TYPE_DIRT;

                            }
                    }
                    terrain[x1][y1] = new Terrain(type);

                    int roll2 = (int)(Math.random()*100);
                    if(roll2 < 25){
                    generateSpot(terrain[x1][y1], x1+1, y1+1);
                    generateSpot(terrain[x1][y1], x1-1, y1+1);
                    generateSpot(terrain[x1][y1], x1-1, y1-1);
                    generateSpot(terrain[x1][y1], x1+1, y1-1);
                    
                        generateSpot(terrain[x1][y1], x1-1, y1);
                    generateSpot(terrain[x1][y1], x1+1, y1);
                    generateSpot(terrain[x1][y1], x1, y1+1);
                    generateSpot(terrain[x1][y1], x1, y1-1);
                    generateSpot(terrain[x1][y1], x1+1, y1);
                    }else{
                        if( roll < 50){
                            generateSpot(terrain[x1][y1], x1-1, y1);
                        
                        generateSpot(terrain[x1][y1], x1, y1-1);
                        generateSpot(terrain[x1][y1], x1+1, y1);
                        generateSpot(terrain[x1][y1], x1+1, y1);
                        generateSpot(terrain[x1][y1], x1, y1+1);

                        generateSpot(terrain[x1][y1], x1-1, y1-1);
                        generateSpot(terrain[x1][y1], x1+1, y1-1);
                        generateSpot(terrain[x1][y1], x1+1, y1+1);
                        generateSpot(terrain[x1][y1], x1-1, y1+1);
                        }else{
                        if( roll < 75){
                             generateSpot(terrain[x1][y1], x1-1, y1-1);
                        generateSpot(terrain[x1][y1], x1+1, y1-1);
                        generateSpot(terrain[x1][y1], x1+1, y1+1);
                        generateSpot(terrain[x1][y1], x1-1, y1+1);
                        
                        generateSpot(terrain[x1][y1], x1, y1-1);
                        generateSpot(terrain[x1][y1], x1+1, y1);
                        generateSpot(terrain[x1][y1], x1+1, y1);
                        generateSpot(terrain[x1][y1], x1, y1+1);
                            generateSpot(terrain[x1][y1], x1-1, y1);

                        }else{
                        if( roll < 100){
                            generateSpot(terrain[x1][y1], x1-1, y1);
                         generateSpot(terrain[x1][y1], x1-1, y1+1);

                        generateSpot(terrain[x1][y1], x1, y1-1);
                        generateSpot(terrain[x1][y1], x1+1, y1);
                             generateSpot(terrain[x1][y1], x1-1, y1-1);
                        generateSpot(terrain[x1][y1], x1+1, y1-1);
                           generateSpot(terrain[x1][y1], x1+1, y1);
                        generateSpot(terrain[x1][y1], x1, y1+1);
                        generateSpot(terrain[x1][y1], x1+1, y1+1);
                       
                     
                        }

                    }
                        
                    }

                    }


                    

                }
            }
        }

        

    }

    
    public void paint(Graphics g){
       //garbagCollection();
        
        //Paint Background
        g.setColor(Color.BLACK);
        g.fillRect(0,0,(int)this.getWidth(),(int)this.getHeight());
        //Paint Terrain and Grid
        if(displayGrid){
        paintGrid(g);
        }
        g.setColor(Color.WHITE);
            g.drawRect((int)x, (int)y, (int)this.getWidth(), (int)this.getHeight());
        

for(int r = 0; r < gameSpeed ; r ++){
        for(int i = 0; i < organisms.size(); i++){
            if(organisms.get(i).isAlive()){
            organisms.get(i).function();

            }else{
                organisms.get(i).decay();
                deadOrganisms.add(organisms.get(i));
                organisms.remove(i);
                
                
            }
            


        }


        //Time
       time(g);

}

     if(paintCount >= paintDelay){
         //Painting Dead Organisms
         for(int i = 0; i < deadOrganisms.size() && i < displayMaxOrganisms*.5; i++){

                deadOrganisms.get(i).draw(g);
                if(i >75){ //Ensures maximum corpses. - 90 for now
                    deadOrganisms.remove(i);

                    organisms.remove(deadOrganisms.get(i));// Dec14,2010
                }

            }
         //Painting Living Organisms
            for(int i = 0; i < organisms.size() && i < displayMaxOrganisms; i++){
                    
                organisms.get(i).draw(g);

                //Connect families - temporary indicator
//                 for(int i2 = 0; i2 < organisms.size(); i2++){
//                     if(organisms.get(i2) == organisms.get(i).mother){
//                         g.setColor(Color.PINK);
//                         g.drawLine((int)organisms.get(i2).getX(), (int)organisms.get(i2).getY(),(int)organisms.get(i).getX(), (int)organisms.get(i).getY());
//                     }
//                 }

            }

         for(int i = 0; i < entities.size(); i ++){
             entities.get(i).draw(g);
         }
            
                    paintCount = 0;
       }

        paintCount++;
        
    }

    public void time(Graphics g){
         if(daytime){
            time+=secondLength;
            if(showdaylight){
                    if(time > NIGHT){ timeDays++; time =0; }
                    if(time < DAY){ g.setColor(new Color(0,0,5,(int)(80*(time/NIGHT))));
                     g.fillRect(0,0,(int)this.getWidth(),(int)this.getHeight());
                    }else{
                    if(time > DAY){
                        g.setColor(new Color(0,0,5,(int)(80*((NIGHT-time)/NIGHT))));
                     g.fillRect(0,0,(int)this.getWidth(),(int)this.getHeight());
                    }
                    }
            }else{
                if(time > NIGHT){ timeDays++; time =0; }
            }
        }
    }

    public void function(){
        for(int i = 0; i < organisms.size(); i++){
            organisms.get(i).function();
        }
//        for(int i = 0; i < deadOrganisms.size(); i++){
//                deadOrganisms.get(i).decay();
//                 System.out.println("HAS ENTITIES");
//
//        }
    }
    public void paintGrid(Graphics g){
         for(int i = 0; i < terrain.length; i++){
            for(int i2 = 0; i2 < terrain[i].length; i2++){
                g.setColor(terrain[i][i2].getColor());
                g.fillRect((int)this.getX()+(int)(i*gridWidth), (int)this.getY()+(int)(i2*gridHeight), (int)gridWidth, (int)gridHeight);
                if(displayGridLines){
                g.setColor(gridColor);
                g.drawRect((int)this.getX()+(int)(i*gridWidth), (int)this.getY()+(int)(i2*gridHeight), (int)gridWidth, (int)gridHeight);
                }
            }
        }
    }
    public Screen getDisplay() {
        return display;
    }

    public void setDisplay(Screen display) {
        this.display = display;
    }

    public Panel getPanel() {
        return panel;
    }

    public void setPanel(Panel panel) {
        this.panel = panel;
    }

    public boolean outsideBounds(double x1, double y1){
        if(x1 > getX() +getWidth() || x1 < getX() || y1 > getY() + getHeight() || y1 < getY() ){
            return true;
        }
        return false;
    }

    public Color getGridColor() {
        return gridColor;
    }

    public void setGridColor(Color gridColor) {
        this.gridColor = gridColor;
    }

    public double getGridHeight() {
        return gridHeight;
    }

    public void setGridHeight(double gridHeight) {
        this.gridHeight = gridHeight;
    }

    public double getGridWidth() {
        return gridWidth;
    }

    public void setGridWidth(double gridWidth) {
        this.gridWidth = gridWidth;
    }

    public ArrayList<Organism> getOrganisms() {
        return organisms;
    }

    public void setOrganisms(ArrayList<Organism> organisms) {
        this.organisms = organisms;
    }

    public Terrain[][] getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain[][] terrain) {
        this.terrain = terrain;
    }

    public ArrayList<Organism> getLivingOrganisms(){
        ArrayList<Organism> list = new ArrayList<Organism>();
        for(int o = 0; o < getOrganisms().size(); o ++){
//        for(Organism o : organisms){
            if(getOrganisms().get(o).isAlive()){ list.add(getOrganisms().get(o));}
        }
        return list;
    }
    
    public ArrayList<Organism> getSpecies(String spec){
        ArrayList<Organism> list = new ArrayList<Organism>();
        for(int o = 0; o < getOrganisms().size(); o ++){
//        for(Organism o : organisms){
            if(getOrganisms().get(o).getSpecies()==spec){
                if(getOrganisms().get(o).isAlive()){ list.add(getOrganisms().get(o));}

            }
        }
        return list;
    

    }

    public double getPaintDelay() {
        return paintDelay;
    }

    public void setPaintDelay(double paintDelay) {
        this.paintDelay = paintDelay;
    }

    public double getGameSpeed() {
        return gameSpeed;
    }

    public void setGameSpeed(double gameSpeed) {
        this.gameSpeed = gameSpeed;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

    public boolean isDisplayVision() {
        return displayVision;
    }

    public void setDisplayVision(boolean displayVision) {
        this.displayVision = displayVision;
    }

    public boolean isDisplaySound() {
        return displaySound;
    }

    public void setDisplaySound(boolean displaySound) {
        this.displaySound = displaySound;
    }

    public double getTimeDays() {
        return timeDays;
    }

    public void setTimeDays(double timeDays) {
        this.timeDays = timeDays;
    }

    public boolean isDisplayGrid() {
        return displayGrid;
    }

    public void setDisplayGrid(boolean displayGrid) {
        this.displayGrid = displayGrid;
    }
    


}
