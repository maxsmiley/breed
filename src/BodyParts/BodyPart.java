/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BodyParts;

import breed.Entity;
import breed.Mutable;
import breed.Organism;
import breed.Receiver;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 *
 * @author xx
 */
public class BodyPart {
    Field mutables[];
    public ArrayList<Entity> entities = new ArrayList<Entity>();
    public double weight;
    public double energyCost; //The energy required of the body part to function
    public boolean container = false;
    public Organism organism = null;
    public double energy = 100; // current amount of energy in body part
    public double energyMax = 100;
    public boolean functioning = true;
    public double functionCost; //The energy taxed from the organism and added to the body part's energy

    public void basicEnergyTax(){
        if(getEnergy() < getEnergyMax()){
            if(getOrganism()!= null){
                getOrganism().setEnergy(getOrganism().getEnergy() - getEnergyCost());
                setEnergy(getEnergy()+getEnergyCost());
            }
        }
        if(energy > 0 ){
            functioning = true;
        }else{
            functioning = false;
        }
    }
    public void function(){}
    public void feed( Receiver receiver){}

    public boolean isContainer() {
        return container;
    }

    public void setContainer(boolean container) {
        this.container = container;
    }

    public double getEnergyCost() {
        return energyCost;
    }

    public void setEnergyCost(double energyCost) {
        this.energyCost = energyCost;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
  
    public void decay(){}

    public Organism getOrganism() {
        return organism;
    }

    public void setOrganism(Organism organism) {
        this.organism = organism;
    }

    public void setMutables() {
    }

    public Field[] getMutables() {
        return mutables;
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public double getEnergyMax() {
        return energyMax;
    }

    public void setEnergyMax(double energyMax) {
        this.energyMax = energyMax;
    }

    public boolean isFunctioning() {
        return functioning;
    }

    public void setFunctioning(boolean functioning) {
        this.functioning = functioning;
    }

    public double getFunctionCost() {
        return functionCost;
    }

    public void setFunctionCost(double functionCost) {
        this.functionCost = functionCost;
    }

 
    
}
