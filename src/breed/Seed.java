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
public class Seed  extends BodyPart{
    Field mutables[];
    double energyCost;
    double weight=.00;
    Organism organism;
    double growthSuccessChance=12;//out of 1000 chance to grow if organism that ate seeds dies
    double chanceToPass = 5;//out of 100 chance to pass onto someone eating/interacting with organism
    
    public Seed(){

    }
    public Seed(Organism o){
        setOrganism(o);
    }

    public void basicEnergyTax() {

    }

    public void function() {
        //Summarize for now - the seeds function is to grow other organisms over time
    }
    public void decay(){
        Organism newO;
                if((Math.random()*1000)<=growthSuccessChance){
//                    Organism newO = new Organism(new Organism( organism.environment,"Test2"));
                     organism.environment.organisms.add( newO = new Organism(organism.environment,"Test2"));
//                    organism.environment.organisms.add(newO);
                    double newX = Math.min((organism.environment.getX()+organism.environment.getWidth()),organism.getX()+Math.random()*10-20);
                      double newY = Math.min((organism.environment.getY()+organism.environment.getHeight()),organism.getY()+Math.random()*10-20);
                    newO.setX(newX);
                    newO.setY(newY);
                }
                if((Math.random()*1000)<=growthSuccessChance/3){
                }
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
public boolean seedPassed(){
    if(Math.random()*100 <= chanceToPass){
        return true;
    }
    return false;
}

public Seed clone(){
    Seed clone = new Seed(organism);
    return clone;
}

public Seed cloneTo(Organism o){
    Seed clone = new Seed(o);
    return clone;
}
 
}
