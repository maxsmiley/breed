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
public class Hand  extends BodyPart{
    Field mutables[];
    ArrayList<Entity>  entities = new  ArrayList<Entity>();
    double energyCost=0;
    double weight;
    Organism organism;
    double carryingCapacity=5;//(lb)

    public Hand(){

    }
 public void basicEnergyTax() {
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

    public void grab(Entity object){
        
    }
public boolean isContainer(){
        return true;
    }
public ArrayList<Entity> getEntities(){
        return entities;
    }
 public void decay(){

    }
}
