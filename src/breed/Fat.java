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
public class Fat  extends BodyPart{
    Field mutables[];
    double energyCost=.1; //This value is also the energy stored - assuming a perfect ratio of energy tranfer into fat for now
    double weight; //This value changes based on the amount of energy stored
    double storedEnergy =0; //Specific to fats function
    double storedEnergyMax = 1200; //Maximum amount of energy that can be stored as fat
    double energyWeightRatio = .02; //How much weight is added for energy stored
    Organism organism;
   
    public Fat(){

    }

    public Fat(Organism o){
        organism = o;
    }
    public void basicEnergyTax() {
         //Slightly different, the energy taxed gets added to the fat storage.
        if(organism != null){
             //Also, when organism is low on energy, fat begins to be tranfered back into usable energy
        //*** change this to only happen when stomach is empty and energy is low ****
            if(organism.getEnergy() < organism.getEnergyMax()/3 ){ //Make the ratio a variable
                 //If low on energy, return energy from fat
                 if(storedEnergy - energyCost >= 0){
                     organism.setEnergy(organism.getEnergy()+energyCost);
                     storedEnergy-=energyCost;
                 }
            }else{
                //Else, take energy and turn into fat
                 if(storedEnergy + energyCost <= storedEnergyMax){
                     organism.setEnergy(organism.getEnergy()-energyCost);
                     storedEnergy+=energyCost;
                 }
            }
        }
         
       
       

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

    public double getWeight() { //Special adjustment of weight done based on amount of energy stored
        weight=storedEnergy*energyWeightRatio;
        return weight;
    }

    public void setEnergyCost() {
    }

    public double getEnergyCost() {
        return energyCost;
    }

    public double getEnergyWeightRatio() {
        return energyWeightRatio;
    }

    public void setEnergyWeightRatio(double energyWeightRatio) {
        this.energyWeightRatio = energyWeightRatio;
    }

    public Organism getOrganism() {
        return organism;
    }

    public void setOrganism(Organism organism) {
        this.organism = organism;
    }

    public double getStoredEnergy() {
        return storedEnergy;
    }

    public void setStoredEnergy(double storedEnergy) {
        this.storedEnergy = storedEnergy;
    }

    public double getStoredEnergyMax() {
        return storedEnergyMax;
    }

    public void setStoredEnergyMax(double storedEnergyMax) {
        this.storedEnergyMax = storedEnergyMax;
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
