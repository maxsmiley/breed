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
public class Stem extends BodyPart{
    Field mutables[];
    double energyCost = .55;
    double weight = .1;
    Organism organism;
    double LeafReproductionTime=0;
    double LeafReproductionRate=900;
    String leafTaskName = "growleaf";

    public Stem(){

    }
    public Stem(Organism o ){
        setOrganism(o);

        organism.tasks.addTask(new Task(leafTaskName, LeafReproductionRate, 15, Color.GREEN){
                            public void completion(){
                                 organism.leafs.add(new Leaf(organism));
                                 organism.tasks.getTask(leafTaskName).setStatic();

                            }
                     });

    }
    public void basicEnergyTax() {
          organism.setEnergy(organism.getEnergy()-energyCost);
    }

    public void function() {
        //For now, the basic function of the stem to produce more Leafs on the organism
//        if(LeafReproductionTime < LeafReproductionRate){
//        LeafReproductionTime+=(Math.random()*15);
//        }else{
//            LeafReproductionTime=0;
//            organism.leafs.add(new Leaf(organism));
//        }

         organism.tasks.addTask(new Task(leafTaskName, LeafReproductionRate, 15, Color.GREEN){
                            public void completion(){
                                 organism.leafs.add(new Leaf(organism));
                                 organism.tasks.getTask(leafTaskName).setStatic();

                            }
                     });
        
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
public boolean isContainer(){
        return false;
    }
public ArrayList<Entity> getEntities(){
        return null;
    }
 public void decay(){

    }

}
