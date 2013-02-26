/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package breed;

import BodyParts.BodyPart;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 *
 * @author xx
 */
public class Stomach  extends BodyPart{
    Field mutables[];
    double energyCost = .09;
    double weight = 0;
    Organism organism;
    double carryingCapacity=.65;//2
    double carriedWeight = 0;
    double digestionRate = 1;
    double maxDigestionRate = 9;//10

    public Stomach(){

    }

    public Stomach(Organism org){
        organism = org;
        container = true;

    }
    public void basicEnergyTax(){
        organism.setEnergy(organism.getEnergy()-energyCost);
    }
    public void function() {
        digest();
    }

    public void feed(Receiver receiver) {
    }

    public void digest(){
        double digestion = 0;
        for(int i = 0 ; i < entities.size() && digestion < maxDigestionRate; i ++){
            if(entities.get(i).getBodyPart().getEnergy() > 0){ //Check whether food in stomach has any energy left
                if(entities.get(i).getBodyPart().getEnergy() - digestionRate > 0){
                    entities.get(i).getBodyPart().setEnergy(entities.get(i).getBodyPart().getEnergy() - digestionRate);
                    organism.setEnergy(organism.getEnergy()+ digestionRate);
                    digestion+=digestionRate;
                }else{
                    organism.setEnergy(organism.getEnergy()+ entities.get(i).getBodyPart().getEnergy());
                    digestion+=entities.get(i).getBodyPart().getEnergy();
                    entities.remove(i);
                }
            }else{// otherwise remove piece from stomach - ****ADD A WASTE SYSTEM LATER****
                entities.remove(i);
            }
                
        }
        
    }

    public void tranferEntity(Entity e, BodyPart source){
        if(e.getWeight() + getCarriedWeight() <= carryingCapacity ){
            entities.add(e);
            source.getEntities().remove(e);
        }
    }

    public double getCarriedWeight(){
        carriedWeight =0;
        for(Entity e : entities){
            carriedWeight+=e.getWeight();
        }

        return carriedWeight;
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


 public void decay(){

    }
   public Organism getOrganism() {
        return organism;
    }

    public void setOrganism(Organism organism) {
        this.organism = organism;
    }

   

    public void setCarriedWeight(double carriedWeight) {
        this.carriedWeight = carriedWeight;
    }

    public double getCarryingCapacity() {
        return carryingCapacity;
    }

    public void setCarryingCapacity(double carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }


}
