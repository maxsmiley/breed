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
public class Leaf  extends BodyPart{
    Field mutables[];
    double energyCost = -.17;
    double weight = .1;
    Organism organism;
    //For now - plants' leafs will also house their seeds, creating a chance on an organisms death who ate leafs to produce a new plant
    public Leaf(){

    }

    public Leaf(Organism org){
        organism = org;
        energy = 30;

    }
     public void basicEnergyTax(){

      organism.setEnergy(organism.getEnergy()-energyCost);
      energy -= energyCost*10;
    }
    public void function() {

    }

    public void feed(Receiver receiver) {
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
}
