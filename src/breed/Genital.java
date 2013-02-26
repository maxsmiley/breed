/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package breed;

import BodyParts.BodyPart;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Motile
 * @author xx
 */
public class Genital  extends BodyPart{
    Field mutables[];
    double weight=.35;
    double semenCount;
    double semenGenerationRate;
    Organism organism;

    double energyCost =.01;

    public Genital(){
           testSettings();
    }
    public Genital(Organism organism1){
           this();
           setOrganism(organism1);
    }

    public void testSettings(){
        semenCount=1;
        semenGenerationRate = .002;
    }
    public void basicEnergyTax(){
        organism.setEnergy(organism.getEnergy()-energyCost);
    }
    public void function() {
         
        if(organism != null){
            if(organism.getAge()>=organism.getPuberty()){
            semenCount+=semenGenerationRate;
            }
        }else{
//            semenCount+=semenGenerationRate;
        }

        
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

    public int getSemenCount() {
        return (int)semenCount;
    }

    public void setSemenCount(int semenCount) {
        this.semenCount = semenCount;
    }

    public double getSemenGenerationRate() {
        return semenGenerationRate;
    }

    public void setSemenGenerationRate(double semenGenerationRate) {
        this.semenGenerationRate = semenGenerationRate;
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
