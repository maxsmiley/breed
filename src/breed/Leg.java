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
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 *
 * @author xx
 */
public class Leg  extends BodyPart{
    double jointLengths[] = {10.0,11.0} ;
    double strength;
    Organism organism;
    double weight = .5; //This value is dependent on the legs strenght - representing muscle mass
    double speedEnergyRatio = 0; //Squared value - how much energy is required based on speed
    double strengthWeightRatio = .95; //how heavy legs are based on muscle mass
    double weightSpeedEnergyRatio = 1.06; //Squared value - how much energy is required based on thr weight and speed of the leg //over weight
    double energyCost =.3;
    double energyMax = 900;
    boolean functioning = true;
    
    public Leg(){
        testSettings();
    }
    public Leg(Organism organis){
        this();
          setOrganism(organis);

          organism.getMutables().addMutable("strength", 1, this, Mutable.SEX_UNISEX);
          organism.getMutables().registerMutables(this);
             
    }

    public void testSettings(){
        strength = .7;
        speedEnergyRatio = .4;
        energy = 10;
    }
    
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

    public void function() {

    }

    public void feed(Receiver receiver) {
    }

    public void setMutables() {
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

    public double[] getJointLengths() {
        return jointLengths;
    }

    public void setJointLengths(double[] jointLengths) {
        this.jointLengths = jointLengths;
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public Organism getOrganism() {
        return organism;
    }

    public void setOrganism(Organism organism) {
        this.organism = organism;
    }

    public double getSpeedEnergyRatio() {
        return speedEnergyRatio;
    }

    public void setSpeedEnergyRatio(double speedEnergyRatio) {
        this.speedEnergyRatio = speedEnergyRatio;
    }
    public boolean isContainer(){
        return false;
    }
    public ArrayList<Entity> getEntities(){
        return null;
    }
     public void decay(){

    }

    public double getStrengthWeightRatio() {
        return strengthWeightRatio;
    }

    public void setStrengthWeightRatio(double strengthWeightRatio) {
        this.strengthWeightRatio = strengthWeightRatio;
    }

    public double getWeightSpeedEnergyRatio() {
        return weightSpeedEnergyRatio;
    }

    public void setWeightSpeedEnergyRatio(double weightSpeedEnergyRatio) {
        this.weightSpeedEnergyRatio = weightSpeedEnergyRatio;
    }


}
