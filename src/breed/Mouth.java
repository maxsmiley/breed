/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package breed;

import BodyParts.BodyPart;
import java.awt.Color;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 *
 * @author xx
 */
public class Mouth  extends BodyPart implements Container{
    Field mutables[];
    Organism organism;
    double weight =.1;
    double energyCost =.09; //Basic rate of maintanence
    ArrayList<Entity> entities = new ArrayList<Entity>();
    double carryingCapacity=.4;//2
    double carriedWeight = 0;
    String biteTaskName = "bite";
    double biteTaskTime = 32;
    double biteEnergyCost = .01;
    Organism target;
    double reach = 30;

    public Mouth(){
        testSettings();
    }
    public Mouth(Organism organis){
        this();
            organism = organis;
                    container = true;
    }

    public void testSettings(){
    }

    public void basicEnergyTax(){
        organism.setEnergy(organism.getEnergy()-energyCost);
    }

    public void function() {

    }

    public void feed(Receiver receiver) {
    }

    public void setMutables() {
    }

    public Field[] getMutables() {
        return mutables;
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


    public Organism getOrganism() {
        return organism;
    }

    public void setOrganism(Organism organism) {
        this.organism = organism;
    }

    public void bite(Organism target1){
        setTarget(target1);
            //Needs to be changed - alot
            if(target.leafs.size()>0){
                if(target.leafs.get(0).getWeight() + getCarriedWeight() <= carryingCapacity ){

                    organism.tasks.addTask(new Task(biteTaskName, biteTaskTime, Color.GREEN){
                            public void completion(){
                                if(target.leafs.size()>0){
                                    if(target.leafs.get(0).getWeight() + getCarriedWeight() <= carryingCapacity ){
                                                     entities.add(new Entity(target.leafs.get(0)));

                                                     target.parts.remove(target.leafs.get(0));
                                                     target.leafs.remove(0);
                                                        organism.tasks.getTask(biteTaskName).setStatic();
                                    }
                                }

                            }

                            public void function(){
                                //***** Make a waiting system in brain *********
                                organism.brain.setWaiting(true);
                                //organism.setEnergy(organism.getEnergy()-biteEnergyCost);
                            }
                     });
                   

                    
                }
            }
            if(target.seeds.size()>0){
                for(int s = 0; s < target.seeds.size(); s ++){
//                for(Seed s: target.seeds){
                    if(target.seeds.get(s).seedPassed()){
                        entities.add(new Entity(target.seeds.get(s).cloneTo(organism)));
                    }
                }
            }

            if(organism.species == "Test3" && target.species=="Test1" ){

                if(target.parts.size()>0){
                int id = (int)(Math.random()*target.parts.size());
                 if(getCarriedWeight() + target.parts.get(id).getWeight() < carryingCapacity){
                       entities.add(new Entity(target.parts.get(id)));
                       target.removePart(id);
                 }
                }else{
                    target.setEnergy(target.getEnergy()-100);
                    organism.setEnergy(organism.getEnergy()+100);
                }
            }
    }

    public void swallow(){ //For now swallow is rather dum - and will tranfer all content in the mouth into the organisms stomach.
                           // In the future the organism should have some concious control of what it would like to swallow/spit out/ or keep in its mouth
        if(organism.stomachs.size() > 0){
            for(int i = 0; i < entities.size(); i ++){
                organism.stomachs.get(0).tranferEntity(entities.get(i), this);
            }
        }

    }

    public boolean addEntity(Entity ent){
        if(ent.getWeight() + getCarriedWeight() <= carryingCapacity ){
                                                     entities.add(ent);
                                                     return true;
                                    }
        return false;
    }

    public double getCarriedWeight(){
        carriedWeight =0;
        for(int e = 0; e < entities.size(); e ++){
//        for(Entity e : entities){
            carriedWeight+=entities.get(e).getWeight();
        }
        
        return carriedWeight;
    }

    public double getCarryingCapacity() {
        return carryingCapacity;
    }

    public void setCarryingCapacity(double carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

 public void decay(){

    }

    public Organism getTarget() {
        return target;
    }

    public void setTarget(Organism target) {
        this.target = target;
    }

    public double getReach() {
        return reach;
    }

    public void setReach(double reach) {
        this.reach = reach;
    }
 
    
}
