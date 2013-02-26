/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package breed;

import BodyParts.BodyPart;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xx
 */
public class Organism implements Movable {
    Call call; //Change this to noise
    ArrayList<BodyPart> parts = new ArrayList<BodyPart>();
//    ArrayList<Mutable> mutables = new ArrayList<Mutable>();
    ArrayList<Brain> brains = new ArrayList<Brain>();
    ArrayList<Eye> eyes = new ArrayList<Eye>();
    ArrayList<Fat> fats = new ArrayList<Fat>();
    ArrayList<Genital> genitals = new ArrayList<Genital>();
    ArrayList<Leg> legs = new ArrayList<Leg>();
    ArrayList<Leaf> leafs = new ArrayList<Leaf>();
    ArrayList<Mouth> mouths = new ArrayList<Mouth>();
    ArrayList<Point> movementQue = new ArrayList<Point>();
    ArrayList<Seed> seeds = new ArrayList<Seed>();
    ArrayList<Stem> stems = new ArrayList<Stem>();
    ArrayList<Stomach> stomachs = new ArrayList<Stomach>();
    ArrayList<Womb> wombs = new ArrayList<Womb>();
   // ArrayList<Task> tasks = new ArrayList<Task>();
    ArrayList<Entity> entities = new ArrayList<Entity>();
    Tasks tasks = new Tasks(this);
    Mutables mutables = new Mutables(this);
    boolean wander;
    double speed;
    double direction =0;
    double x;
    double y;
    double age=0;
    double energy=1000;
    double energyMax = 1500;
    double puberty;
    double expiration;
    double speedMax;
    double energyChange=0;
    double previousEnergy;
    double weight;
    
    String species = "";
    Color baseColor;
    //Turn rate?
    boolean alive = true;
    int generation;
    int sex;
    Polygon sight = new Polygon();
    Organism mother;
    Organism father;
//
//    ArrayList<Graph> graphs = new ArrayList<Graph>();


    Genital genital;
    Mouth mouth;
    Brain brain = null;

    double size;
    Environment environment;

    public Organism(){
       
    }
    public Organism(Environment environ){
        this();
        setEnvironment(environ);
    }
    public Organism(Organism parent){
        
    }
    public Organism(Environment environ, String speci){
            this();
        setEnvironment(environ);
        setSpecies(speci);
         testSettings();
    }

    public void testSettings(){
        if(species == "Test1"){ //Test type 1 : herbavorous mammal
            generation = 1;
            puberty = 4.9;
            x=500;
            y=500;
            direction=Math.toRadians(Math.random()*360);
            legs.add(new Leg(this));
            legs.add(new Leg(this));
            brains.add(new Brain(this));
             brain = brains.get(0);
            genitals.add(new Genital(this));
            eyes.add(new Eye(this));
            stomachs.add(new Stomach(this));
            mouths.add(new Mouth(this));
            fats.add(new Fat(this));
            mouth = mouths.get(0);

            sex=(int)(Math.random()*2);
            if(sex == 0){
                baseColor = Color.RED;
            }
            if(sex == 1){
                baseColor = Color.YELLOW;
                wombs.add(new Womb(this));
                parts.addAll(wombs);
            }
            parts.addAll(legs);
            parts.addAll(genitals);
            parts.addAll(eyes);
            parts.addAll(stomachs);
            parts.addAll(brains);
            parts.addAll(mouths);
            parts.addAll(fats);
            expiration = 34;
            size = 5;
            brain.getPreySpecies().add("Test2");

          
        }
        if(species == "Test2"){ //Test type 2 : photosynthetic plant
            generation = 1;
            puberty = 2;
            x=Math.random()*environment.getWidth();
            y=Math.random()*environment.getHeight();
            direction=Math.toRadians(Math.random()*360);

            leafs.add(new Leaf(this));
            leafs.add(new Leaf(this));
            leafs.add(new Leaf(this));
            leafs.add(new Leaf(this));
            leafs.add(new Leaf(this));
            seeds.add(new Seed(this));
            stems.add(new Stem(this));
             legs.add(new Leg(this));
            sex=(int)(Math.random()*2);
            baseColor = Color.GREEN;
            parts.addAll(leafs);
            parts.addAll(stems);
            parts.addAll(seeds);
            expiration = 400;
            size = 9;
        }
        if(species == "Test3"){ //Test type 1 : carnivorous mammal
            generation = 1;
            puberty = 5;
            energyMax = 3750;
            x=Math.random()*environment.getWidth();;
            y=Math.random()*environment.getHeight();
            direction=Math.toRadians(Math.random()*360);
            legs.add(new Leg(this));
            legs.add(new Leg(this));
            legs.add(new Leg(this));
            legs.add(new Leg(this));
            brains.add(new Brain(this));
            genitals.add(new Genital(this));
            eyes.add(new Eye(this));
            stomachs.add(new Stomach(this));
            mouths.add(new Mouth(this));
            fats.add(new Fat(this));
            mouth = mouths.get(0);
            brain = brains.get(0);

            sex=(int)(Math.random()*2);
            if(sex == 0){
                baseColor = Color.MAGENTA;
            }
            if(sex == 1){
                baseColor = new Color(150,0,255,255);
            }
            parts.addAll(legs);
            parts.addAll(genitals);
            parts.addAll(eyes);
            parts.addAll(stomachs);
            parts.addAll(brains);
            parts.addAll(mouths);
            parts.addAll(fats);
            expiration = 35;
            size = 7;

            

            brain.getPreySpecies().add("Test1");
        }


        
    }

    public void function(){
        previousEnergy = energy;
     if(alive){
         if(call != null){
             call.run();
         }
         if(genitals.size()>0)
                 genital = genitals.get(0);
            age+=.01; //Aging
            //Energy Maximum
            energy = Math.min(energy, energyMax); //Energy
            //Calculating Weight
            weight=0;
            for(int b = 0; b < parts.size(); b ++){
//            for(BodyPart b : parts){ //Calculate the total weight from all body parts
               weight+=parts.get(b).getWeight();
            }
            
            sight();
            thought();
            movement();
            
            //Functions
            for(int i = 0; i < parts.size(); i++){
             //Run all basic body function - uses energy
                if(parts.get(i).getEnergy() > 0){
                parts.get(i).function();
                }
            }
            //Basic Energy Tax
            for(int b = 0; b < parts.size(); b ++){
//            for(BodyPart b : parts){ //Apply the tax of all body parts
                parts.get(b).basicEnergyTax();
            }
            if(age > expiration){ //If late of age - Die.
                die();
            }
            if(energy <= 0){ //If out of energy - Die.
                die();
            }
            //Update bodyparts
            for(int l = 0; l < leafs.size(); l ++){
//            for(Leaf l : leafs){
                if(!parts.contains(leafs.get(l))){
                    parts.add(leafs.get(l));
                }
            }
            //Update Entities
            entities.clear();
            for(int b = 0 ; b < parts.size(); b ++){
//            for(BodyPart b : parts){ //Apply the tax of all body parts
                if(parts.get(b).isContainer()){
                    entities.addAll(parts.get(b).getEntities());
                }
            }

            //Tasks
            tasks.runTasks();

            energyTax();

     }else{
            clearCall();
           
            
     }

        energyChange = energy-previousEnergy;
    }

    public void decay(){
        
        //Update Entities
            entities.clear();
            for( int b = 0 ; b < parts.size() ; b ++){
           // for(BodyPart b : parts){ //Apply the tax of all body parts
                if(parts.get(b).isContainer()){
                    entities.addAll(parts.get(b).getEntities());
                }
            }
         if(entities.size()>0){ //If corpse has something on its person - i.e. leafs (food), etc.
             for( int e = 0 ; e < entities.size() ; e ++){
               // for(Entity e : entities){
                    if(entities.get(e).getBodyPart() != null){
                        entities.get(e).getBodyPart().decay();

                    }
                }
            }

    }

    public void draw(Graphics g){
        if(call != null){
            //Display Sounds
            if(environment.isDisplaySound()){
             call.draw(g);
            }
             if(call.radius >= call.radiusMax){
                 clearCall();
             }
         }
        if(brains.size()>0){
            brains.get(0).draw(g);
        }

        if(alive){
              if(eyes.size()>0){ //Eyesight
              eyes.get(0).generateVision();
              }
               //Display vision
             if(getEnvironment().isDisplayVision()){
             g.setColor(new Color(255,255,0,7));//7
             if(getSight().npoints > 0)
             g.fillPolygon(getSight());
             }

            //Tasks
                    int tasksCount = 0;
            for(int i = 0; i < tasks.getTaskCount(); i ++){
                if(!tasks.getTask(i).isComplete()){
                     g.setColor(Color.GRAY);
                    g.fillRect((int)(x-7), (int)(y-5+tasksCount*3), (int)(15), (int)(3));
                    g.setColor(tasks.getTask(i).getColor());
                    g.fillRect((int)(x-7), (int)(y-5+tasksCount*3), (int)(15*tasks.getTask(i).getProgress()/tasks.getTask(i).getDuration()), (int)(3));
                    g.setColor(Color.BLACK);
                    g.drawRect((int)(x-7), (int)(y-5+tasksCount*3), (int)(15*tasks.getTask(i).getProgress()/tasks.getTask(i).getDuration()), (int)(3));
                    tasksCount++;
                }

            }
//        if(wombs.size() > 0){
//            if(tasks.getTask("pregnant") != null){
//                if(!tasks.getTask("pregnant").isComplete()){
//                    g.setColor(Color.GRAY);
//                    g.fillRect((int)(x-7), (int)(y-5), (int)(15), (int)(3));
//                    g.setColor(Color.CYAN);
//                    g.fillRect((int)(x-7), (int)(y-5), (int)(15*tasks.getTask("pregnant").getProgress()/tasks.getTask("pregnant").getDuration()), (int)(3));
//                }
//            }
//        }
        }
       
        g.setColor(baseColor);
        if(!alive){
            g.setColor(Color.GRAY);
        }

          g.fillOval((int)(x-(int)(size+7*(age/expiration))/2),(int)(y-(int)(size+7*(age/expiration))/2),(int)(size+7*(age/expiration)),(int)(size+7*(age/expiration)));

          g.setColor(Color.BLACK);
          g.drawOval((int)(x-(int)(size+7*(age/expiration))/2),(int)(y-(int)(size+7*(age/expiration))/2),(int)(size+7*(age/expiration)),(int)(size+7*(age/expiration)));
          if(alive){
//               g.setColor(new Color(0,0,0,20));
          //Age


          //Draw Spotted Organisms
//                  if(eyes.size() > 0){
//                        for(Organism o: eyes.get(0).getSightOrganisms()){
//                         // g.drawLine((int)getX(), (int)getY(), (int)o.getX(), (int)o.getY());
//                      }
//                      }
                  }


        
        //Apendages
          if(leafs.size() >0 ){
              for(int i = 0; i < leafs.size(); i ++){
                  g.setColor(Color.GREEN.darker());
                  g.fillOval((int)(getX()+5*Math.cos(Math.toRadians(360/leafs.size()*i))), (int)(getY()+5*Math.sin(Math.toRadians(360/leafs.size()*i))), 2, 2);
              }
          }

    }

    public void thought(){
        if(brains.size()>0)
        brains.get(0).thought();

    }

    public void sight(){
//        for(int i = 0; i <environment.getOrganisms().size();i++){
//            Organism o = environment.getOrganisms().get(i);
        for(int e = 0; e < eyes.size(); e ++){
//        for(Eye e : eyes){
        eyes.get(e).getSightOrganisms().clear();
            for(int o = 0; o <  environment.getOrganisms().size() ; o ++){
//            for(Organism o : environment.getOrganisms()){
                if(environment.getOrganisms().get(o) != this){
                    if(sight.contains(environment.getOrganisms().get(o).getX(), environment.getOrganisms().get(o).getY())){
                        eyes.get(e).getSightOrganisms().add(environment.getOrganisms().get(o));
                    }

                }
            }
        }

        for(int e = 0; e < eyes.size(); e ++){
//        for(Eye e : eyes){
        eyes.get(e).getSightEntities().clear();
        for(int en = 0; en < environment.getEntities().size(); en ++){
//            for(Entity en : environment.getEntities()){

                    if(sight.contains(environment.getEntities().get(en).getX(), environment.getEntities().get(en).getY())){
                        eyes.get(e).getSightEntities().add(environment.getEntities().get(en));
                    }


            }
        }
    }

    public double getSpeedMax(){
        speedMax = 0;
        for(int l = 0; l < legs.size(); l ++){
//         for(Leg l : legs ){
             if(legs.get(l).getEnergy() > 0 && legs.get(l).isFunctioning()){
             speedMax+=legs.get(l).getStrength()/2; //Ratio = 1:2
             }
         }
         return speedMax;

    }
    public void movement(){
       getSpeedMax();


         for(int i = 0; i< speed; i++){
             if(!environment.outsideBounds(x+Math.cos(direction),y+Math.sin(direction)) &&
                     !environment.terrain[(int)((getX()+Math.cos(direction))/environment.gridWidth)][(int)((getY()+Math.sin(direction))/environment.gridHeight)].isImpathable()){
                 x+=Math.cos(direction);
                 y+=Math.sin(direction);
             }
             
         }
         
    }

    public void energyTax(){
        //Leg energy tax
        for(int l = 0; l < legs.size(); l ++){
//         for(Leg l : legs ){
             //energy-=Math.max(0,((((speed*l.getWeight()))*(l.getWeightSpeedEnergyRatio()))));
             legs.get(l).setEnergy( legs.get(l).getEnergy()-Math.max(0,((((speed* legs.get(l).getWeight()))*( legs.get(l).getWeightSpeedEnergyRatio())))));
         }
    }

    public void applyMutations(){
        mutables.applyMutations();
//        baseColor = new Color((int)Math.min(250,Math.max(0,(mother.baseColor.getRed()+father.baseColor.getRed())/2 + Math.random()*15 - 30)),
//                (int)Math.min(250,Math.max(0,(mother.baseColor.getBlue()+father.baseColor.getBlue())/2 + Math.random()*15 - 30)),
//                (int)Math.min(250,Math.max(0,(mother.baseColor.getGreen()+father.baseColor.getGreen())/2 + Math.random()*15 - 30)));


//        if(sex == 0){
//        baseColor = new Color((int)Math.min(250,Math.max(0,(mother.baseColor.getRed()*.25+father.baseColor.getRed()*.75) + Math.random()*15 - 30)),
//                (int)Math.min(250,Math.max(0,(mother.baseColor.getBlue()*.25+father.baseColor.getBlue()*.75) + Math.random()*15 - 30)),
//                (int)Math.min(250,Math.max(0,(mother.baseColor.getGreen()*.25+father.baseColor.getGreen()*.75) + Math.random()*15 - 30)));
//        }
//        if(sex ==1){
//            baseColor = new Color((int)Math.min(250,Math.max(0,(mother.baseColor.getRed()*.75+father.baseColor.getRed()*.25) + Math.random()*15 - 30)),
//                (int)Math.min(250,Math.max(0,(mother.baseColor.getBlue()*.75+father.baseColor.getBlue()*.25) + Math.random()*15 - 30)),
//                (int)Math.min(250,Math.max(0,(mother.baseColor.getGreen()*.75+father.baseColor.getGreen()*.25) + Math.random()*15 - 30)));
//        }


//        for(BodyPart b : parts){
//           Mutable m = (Mutable)b;
//            if(m.getMutables()!= null){
//                for(int i = 0; i < m.getMutables().length; i ++){
//                    if(m.getMutables()[i]!= null){
//                        try {
//                            if(m.getMutables()[i].toString().contains("double")){
////                                    System.out.println(m.getMutables()[i].toString());
//                                double value = 0;
//
//                            m.getMutables()[i].setDouble((Object)b, (m.getMutables()[i].getDouble((Object)b)+Math.random()*1-.5));
//                            }
//                        } catch (IllegalArgumentException ex) {
//                            Logger.getLogger(Organism.class.getName()).log(Level.SEVERE, null, ex);
//                        } catch (IllegalAccessException ex) {
//                            Logger.getLogger(Organism.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                    }
//
//
//                }
//            }
//        }

//        if(legs.size()>0){
//           // System.out.println(legs.get(0).getStrength()+"");
//        }
    }
    public int countParts(String type){
        int count=0;

        for(int p = 0; p < parts.size(); p ++){
//        for(BodyPart p : parts ){
             System.out.println(parts.get(p).getClass().getName());
            if(parts.get(p).getClass().getName()==type){
                count++;
            }
        }
        return count;
    }

    public void die(){
        alive = false;
    }

    public void setX( double x1) {
        x = x1;
    }

    public double getX() {
        return x;
    }

    public void setY(double y1) {
        y=y1;
    }

    public double getY() {
        return y;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public int getSex() {
        return sex;
    }

    public Polygon getSight() {
        return sight;
    }

    public void setSight(Polygon sight) {
        this.sight = sight;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Mutables getMutables() {
        return mutables;
    }

    public void setMutables(Mutables mutables) {
        this.mutables = mutables;
    }

    

    public void mate(){
        boolean impregnate = false;
        Organism partner = null;
        for(int o = 0; o < environment.getOrganisms().size(); o ++){
//        for(Organism o : environment.getOrganisms()){
            if(environment.getOrganisms().get(o).getSpecies () == getSpecies()){
            if(environment.getOrganisms().get(o) != this){
                  if(Math.hypot(environment.getOrganisms().get(o).getX()-getX(), environment.getOrganisms().get(o).getY()-getY())<30){
                      if(shouldMate(environment.getOrganisms().get(o))){
                          impregnate = true;
                          partner=environment.getOrganisms().get(o);
                          if(environment.getOrganisms().get(o).wombs.size()>0){
                               environment.getOrganisms().get(o).wombs.get(0).impregnate(this);

                          }
                          break;

                      }
                  }
            }

        }
        }
        if(impregnate && partner != null){
            if(wombs.size()>0){
                wombs.get(0).impregnate(partner);
            }
//         birthOffspring();
        }
    }

    public boolean shouldMate(Organism mate){
        if(mate.getSex()!=getSex()){ //If opposite sex
            if( age >= puberty && mate.getAge() >= puberty){ //If of age of sexual maturity
                if(mate.getSex() == 1 && genitals.size() >0 && mate.wombs.size() > 0 ){
                    if(genital.getSemenCount()>0 && mate.wombs.get(0).isPregnant() != true){ //If has sufficient genetic material and other not pregant
                        return true;
                    }
                }
                if(mate.getSex() == 0 && genitals.size() >0 && mate.wombs.size() > 0){
                    if(mate.genital.getSemenCount()>0 && wombs.get(0).isPregnant() != true){ //If has sufficient genetic material and other not pregant
                        return true;
                    }
                }
                
            }
        }
        return false;
    }

    public void birthOffspring(){
        if(sex == 1){
            //**** still needs to pass stats somehow ****
            Organism child = new Organism(environment, species);
            child.setX(getX());
            child.setY(getY());
            child.setGeneration(getGeneration()+1);
            child.setMother(this);
            child.setFather(wombs.get(0).getFather());
            child.applyMutations();
        
                          environment.getOrganisms().add(child);
        }
        genital.setSemenCount(genital.getSemenCount()-1);
    }

    public boolean goodPartner(Organism partner){
        if(partner.getSpecies() == getSpecies()){ //Must be of same species
            if(partner.getSex()!=getSex()){ //Must be of opposite sex
                if(partner.getAge() > puberty){ //Must be past puberty
                    return true;
                }

            }
        }
        return false;
    }

    public void eat(){
        boolean eat = false;
        Organism food = null;
        for(int o = 0; o < environment.getOrganisms().size(); o ++){
//        for(Organism o : environment.getOrganisms()){
            if(environment.getOrganisms().get(o).getSpecies () != getSpecies()){
            if(environment.getOrganisms().get(o) != this){
                  if(Math.hypot(environment.getOrganisms().get(o).getX()-getX(), environment.getOrganisms().get(o).getY()-getY())<mouth.getReach()){ //30
                      if(shouldEat(environment.getOrganisms().get(o))){
                          eat = true;
                          food=environment.getOrganisms().get(o);
                          break;

                      }
                  }
            }

        }
        }
        if(eat){
         //consumeFood(food);
            //If has a mouth - attempt to bite off some food
            if(mouth != null){
            mouth.bite(food);
            }
        }

        //if mouth holds some food - attempt to swallow it to tranfer to the stomach
        if(mouth.getEntities().size() > 0){
            mouth.swallow();
        }
    }

    public void consumeFood(Organism food){
        if(getEnergy()+100 < energyMax){
            if(food.getEnergy()-100 > 0){
            food.setEnergy(food.getEnergy()-100);
            setEnergy(getEnergy()+100);
            }else{
                setEnergy(getEnergy()+food.getEnergy());
                food.setEnergy(0);

            }
        }else{
            if(food.getEnergy()-(energyMax - getEnergy()) > 0){
            food.setEnergy(food.getEnergy()-(energyMax - getEnergy()));
            setEnergy(getEnergy()+(energyMax - getEnergy()));
            }else{
                setEnergy(getEnergy()+food.getEnergy());
                food.setEnergy(0);

            }
        }
    }
    public void consumeFood(Entity entity, Mouth container){
        BodyPart food = entity.getBodyPart();
        if(container != null){ //If food came from the organisms body; i.e. its mouth, hands, our pouch
            if(food.getEnergyCost()==0){
                        container.getEntities().remove(entity);
                        container.getEntities().add(entity);
            }else{
                if(getEnergy()+Math.abs(food.getEnergyCost()*1600) <= energyMax){
                    setEnergy(getEnergy()+Math.abs(food.getEnergyCost()*1600));
                        container.getEntities().remove(entity);
                        if(entity.getBodyPart().getClass().getName().contains("Leaf")){
                            // container.getEntities().add(new Entity(new Seed(this)));
                        }


                }else{

                }
            }
        }
    }

    public boolean shouldEat(Organism food){
        
                if(energy < energyMax){
                    if(food.getEnergy()>0){ //If has sufficient genetic material
                        return true;
                    }
                }
            
      
        return false;
    }
    public boolean goodMeal(Organism food){
        if(food.getSpecies() != getSpecies()){ //Must be of another species
            if(brain.getPreySpecies().contains(food.getSpecies())){
                if(food.leafs.size() > 0){
                    return true;
                }else{
                    if(food.getEnergy() >=100){
                            return true;
                    }
                }
                

            }
        }
        return false;
    }

    public boolean shouldFindMate(){
        if(sex == 0){ //If male
            if(genitals.size()>0){
                if(genitals.get(0).getSemenCount() > 0){
                    return true;
                }
            }
        }
        if(sex == 1){
            if(wombs.size()>0){
                if(wombs.get(0).isPregnant()!= true){
                    return true;
                }
            }
        }
        return false;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public double getPuberty() {
        return puberty;
    }

    public void setPuberty(double puberty) {
        this.puberty = puberty;
    }

    public double getDirection() {
        return direction;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }
    //*//
    public void call(){
        if(call == null){
            call = new Call(x,y,135, this);
        }
        
    }
    public void call(String content1){
        if(call == null){
            call = new Call(x,y,135, this, content1);
        }

    }
    //*//
    public void clearCall(){
        call = null;
    }

    public void hear(Call call, double x1, double y1){
        if(call.getContent()=="Food"){
            if(energy < energyMax*.6 ){
            setDirection(Math.atan2(y1-getY(),x1-getX()));
            }
        }
        if(call.getContent()=="Mate"){
            if(energy > energyMax*.2 ){
                if(age >= puberty ){
                    if(genitals.size()>0){
                        if(genitals.get(0).getSemenCount() > 0 && call.getOrganism().getSex() != sex && call.getOrganism().getSpecies()==species ){
                            setDirection(Math.atan2(y1-getY(),x1-getX()));
                        }
                    }
                }
            }
        }
        
    }

    public Organism getFather() {
        return father;
    }

    public void setFather(Organism father) {
        this.father = father;
    }

    public Organism getMother() {
        return mother;
    }

    public void setMother(Organism mother) {
        this.mother = mother;
    }

    public Tasks getTasks() {
        return tasks;
    }

    public void setTasks(Tasks tasks) {
        this.tasks = tasks;
    }
    


    public double getEnergyChange() {
        return energyChange;
    }

    public void setEnergyChange(double energyChange) {
        this.energyChange = energyChange;
    }

    public double getEnergyMax() {
        return energyMax;
    }

    public void setEnergyMax(double energyMax) {
        this.energyMax = energyMax;
    }

    public void stop(){
        speed = 0;
    }

    @SuppressWarnings("element-type-mismatch")
    public void removePart(int i){
        if(mouths.contains(parts.get(i))){
            mouths.remove(parts.get(i));
        }
        if(eyes.contains(parts.get(i))){
            eyes.remove(parts.get(i));
        }
        if(genitals.contains(parts.get(i))){
            genitals.remove(parts.get(i));
        }
        if(legs.contains(parts.get(i))){
            legs.remove(parts.get(i));
        }
        if(fats.contains(parts.get(i))){
            fats.remove(parts.get(i));
        }
        if(brains.contains(parts.get(i))){
            brains.remove(parts.get(i));
        }
        if(stomachs.contains(parts.get(i))){
            stomachs.remove(parts.get(i));
        }
        if(stems.contains(parts.get(i))){
            stems.remove(parts.get(i));
        }
        if(seeds.contains(parts.get(i))){
            seeds.remove(parts.get(i));
        }
        if(leafs.contains(parts.get(i))){
            leafs.remove(parts.get(i));
        }
        parts.remove(i);

    }

    public ArrayList<BodyPart> getParts() {
        return parts;
    }

    public void setParts(ArrayList<BodyPart> parts) {
        this.parts = parts;
    }


//        protected void removePermanently() throws Throwable {
//            try {
//                close();        // close open files
//            } finally {
//                super.finalize();
//            }
//    }



}
