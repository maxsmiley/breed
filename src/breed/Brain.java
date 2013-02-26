 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package breed;

import BodyParts.BodyPart;
import BodyParts.BodyPart;
import breed.Mutable;
import breed.Organism;
import breed.Receiver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 *
 * @author xx
 */
public class Brain extends BodyPart{
    Field mutables[];
    Personality personality = new Personality();
    double weight =1.5;
    double energyCost =.05;
    Organism organism;
    ArrayList<Organism> memoryOrganisms = new ArrayList<Organism>();
    public Brain(){}
    Organism partner;
    Organism foodSource;
    boolean waiting= false;
    ArrayList<String> preySpecies = new ArrayList<String>();
    ArrayList<String> predatorSpecies = new ArrayList<String>();
    ArrayList<String> preyParts = new ArrayList<String>();

    //List idea - needs refining - something in the brain that can categorize objects into ideas
    //such as a list for edible entities

    int seen = 0;
    int seenExpire = 35;
    int seenCount = 0;
    


    public Brain(Organism org){
        organism = org;
        
    }
    public void function() {
      

//        organism.setEnergy(organism.getEnergy()-energyCost);
    }

    public void draw(Graphics g){
        if(organism.isAlive()){
            g.setColor(new Color(255,255,255,150));
            if(foodSource != null){
           // g.fillOval((int)foodSource.getX()-10, (int)foodSource.getY()-10, 20, 20);
            }
        }

        //g.drawString(seen + "", (int)organism.getX(), (int)organism.getY());
    }

    public void thought(){
            seenCount++;
            
        if(seenCount > seenExpire && seen >0){
            seen-=1;
        }
        seen+= organism.eyes.get(0).getSightOrganisms().size()/3;

        organism.getSpeedMax();
          organism.wander = true;
        //Movement
           
            //Wander


        //Barrier
//        if(organism.environment.outsideBounds(organism.getX()+Math.cos(organism.direction),organism.getY()+Math.sin(organism.direction))){
//                organism.direction+=(Math.toRadians(60.0-(Math.random()*120.0)));
//             }

          if(organism.environment.outsideBounds(organism.getX()+Math.cos(organism.direction),organism.getY()+Math.sin(organism.direction)) ||
                  
                     organism.environment.terrain[(int)((organism.getX()+Math.cos(organism.direction))/organism.environment.gridWidth)][(int)((organism.getY()+Math.sin(organism.direction))/organism.environment.gridHeight)].isImpathable()){
                organism.direction+=(Math.toRadians(60.0-(Math.random()*120.0)));
             }

        //MovementQue
        if(organism.movementQue.size()<10){
            if(organism.movementQue.size()>0){
            organism.movementQue.add(new Point((int)(organism.movementQue.get(organism.movementQue.size()-1).getX()+10-Math.random()*20),
                   (int)(organism.movementQue.get(organism.movementQue.size()-1).getY()+10-Math.random()*20)));
            }
        }

        //Social Interaction
            //Mate Finding
            if(organism.shouldFindMate() && seen < 100){
                //Visual Recognition
                if(organism.eyes.size()>0){
                    if(organism.eyes.get(0).getSightOrganisms().size() > 0){

                        for(int i = 0; i < organism.eyes.get(0).getSightOrganisms().size(); i ++){
                            if(organism.goodPartner(organism.eyes.get(0).getSightOrganisms().get(i))){
                                //addition discretion past basic requirements
                                    // - if not same mother (avoid incest)
                                    if(organism.eyes.get(0).getSightOrganisms().get(i).brain.seen < 100 && ((organism.eyes.get(0).getSightOrganisms().get(i).father != organism.father && organism.eyes.get(0).getSightOrganisms().get(i).mother != organism.mother) || organism.mother == null)){

                                    partner = organism.eyes.get(0).getSightOrganisms().get(i);
                                    organism.direction = Math.atan2(organism.eyes.get(0).getSightOrganisms().get(i).getY()- organism.getY(),organism.eyes.get(0).getSightOrganisms().get(i).getX()-organism.getX());
                                    organism.wander = false;

                                    break;
                                }
                            }
                        }


                    }
                }
            }
          //Calling
          if(foodSource != null){ //Found foodsource
              if(Math.hypot(organism.getX()-foodSource.getX(), organism.getY()-foodSource.getY())<organism.mouth.getReach()+15){
                //organism.call();
                  organism.call("Food");
              }
          }

          if(partner != null){ //Found mate
              if(Math.hypot(organism.getX()-partner.getX(), organism.getY()-partner.getY())<30){
                  organism.call("Mate");
              }
          }


           //Wander
        if(organism.wander == true){
            //Check stress level
            if(organism.energy < organism.energyMax/4){ //Critically low energy
                organism.direction+=(Math.toRadians(10.0-(Math.random()*30.0)));
            }else{
                if(organism.energy < organism.energyMax/2){ //Low energy
                organism.direction+=(Math.toRadians(10.0-(Math.random()*20.0)));
            }else{ //Normal energy
                organism.direction+=(Math.toRadians(10.0-(Math.random()*20.0)));
            }
            }

        }

        //Mating
        organism.mate();


       //Food Aquisition
        
                if(organism.eyes.size()>0){ //If has eyes
                            for(Organism o :organism.eyes.get(0).getSightOrganisms()){
                                if(organism.goodMeal(o)){ // if edible
                                    foodSource = o;
                                    if(organism.energy <= organism.energyMax/2){
                                    organism.direction = Math.atan2(o.getY()- organism.getY(),o.getX()-organism.getX());
                                    organism.wander = false;
                                    }

                                    break;
                                }
                            }
                 }
        
        //Eating
        if(organism.stomachs.size()>0){
        organism.eat();
        }

        //Semi-Autonomous Processes:

            //Speed
            speed();

            //Nutriment (Eating / Handling food)
            nutriment();

        //Reset Signals
            waiting = false;

    }

    public void speed(){
         //Deciding Speed
        if(organism.getSpeedMax() > 0){
                //Based on energy
                    if(organism.energy > organism.energyMax/2 ){ //If at healthy energy, use full output
                        organism.speed = organism.speedMax;
                    }
                    if(organism.energy < organism.energyMax/2 ){ //If at healthy energy, use full output
                        organism.speed = organism.speedMax/2;
                    }
                //Based on encounter
                    //Partner
                    if(partner !=null){ //If organism has a partner
                         if(organism.eyes.size()>0){
                            if(organism.eyes.get(0).getSightOrganisms().indexOf(partner)!= -1){//If partner is still in sight
                                organism.speed = organism.speedMax; //Increase speed to catch up to partner
                              
                            }
                         }
                    }
                    //Food
                    if(foodSource !=null){
                         if(organism.eyes.size()>0){
                             //If food souirce is in sight
                            if(organism.eyes.get(0).getSightOrganisms().indexOf(foodSource)!= -1){
                                // but not in eating range - approach at full speed
                                if(Math.hypot(organism.getY()-foodSource.getY(),organism.getX()-foodSource.getX()) > 30){//100
                                organism.speed = organism.speedMax;
                                }else{
                                    //if in eating range, and organism at low energy - then wait and eat
                                    if(organism.getEnergy()<organism.getEnergyMax()/2 && foodSource.leafs.size()>0){ //***LEAFS - will need to be changed
                                    organism.speed = 0;
                                    }
                                }
                            }
                         }
                    }
                 //Based on waiting
                    if(waiting == true){
                        organism.speed = 0;
                    }
        }else{
            organism.speed = 0;
        }


    }

    public void nutriment(){
    
        if(organism.mouth!= null){
              //  System.out.println(organism.mouth.getEntities().size()+"");

            
            //* this may no longer be applicable as food is automatically digested by the stomach.
//            if(organism.mouth.getEntities().size()>0){
//                if(organism.getEnergy()<(organism.getEnergyMax()*.8)) {
//                    organism.consumeFood(organism.mouth.getEntities().get(0),organism.mouth);
//                }
//            }
        }
        
    }
    public void feed(Receiver receiver) {
    }
    public void basicEnergyTax(){
       // organism.setEnergy(organism.getEnergy()-energyCost);
    }
    public void setWeight() {
    }

    public double getWeight() {
        return weight;
    }

    public void setEnergyCost() {
    }

    public double getEnergyCost() {
        return energyCost;
    }

    public void setMutables() {
    }

    public Field[] getMutables() {
        return mutables;
    }

    public void wander(){
        if(organism.wander == true){
            //Check stress level
            if(organism.energy < organism.energyMax/4){ //Critically low energy
                organism.direction+=(Math.toRadians(10.0-(Math.random()*30.0)));
            }else{
                if(organism.energy < organism.energyMax/2){ //Low energy
                organism.direction+=(Math.toRadians(10.0-(Math.random()*20.0)));
            }else{ //Normal energy
                organism.direction+=(Math.toRadians(10.0-(Math.random()*20.0)));
            }
            }

        }
    }

    public boolean isContainer(){
        return false;
    }

    public ArrayList<Entity> getEntities(){
        return null;
    }

    public void decay(){

    }
  public Organism getOrganism() {
        return organism;
    }

    public void setOrganism(Organism organism) {
        this.organism = organism;
    }

    public Organism getFoodSource() {
        return foodSource;
    }

    public void setFoodSource(Organism foodSource) {
        this.foodSource = foodSource;
    }

    public ArrayList<String> getPredatorSpecies() {
        return predatorSpecies;
    }

    public void setPredatorSpecies(ArrayList<String> predatorSpecies) {
        this.predatorSpecies = predatorSpecies;
    }

    public ArrayList<String> getPreySpecies() {
        return preySpecies;
    }

    public void setPreySpecies(ArrayList<String> preySpecies) {
        this.preySpecies = preySpecies;
    }

    public boolean isWaiting() {
        return waiting;
    }

    public void setWaiting(boolean waiting) {
        this.waiting = waiting;
    }



}


