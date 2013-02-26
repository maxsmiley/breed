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
 * @author maxsmiley
 */
public class Womb extends BodyPart{
    Field mutables[];
    double weight;
    double energyCost = .55;
    boolean isContainer = false;
    Organism organism;
    Organism fetus;
    Organism father;
    double time = 0;
    double delivery = 510;
    boolean pregnant = false;
    String taskName = "pregnant";
    double fetalEnergy = 50;
    double energyTranfer = .5;


    public Womb(){

    }

    public Womb(Organism o ){
        setOrganism(o);

         organism.getMutables().addMutable("delivery", 50, this, Mutable.SEX_FEMALE);
//         organism.getMutables().addMutable("energyTransfer", 50, this, Mutable.SEX_FEMALE);
          organism.getMutables().registerMutables(this);

          energy = 200;
          energyMax = 900;
    }
    @Override
    public void setMutables() {
    }

    public Field[] getMutables() {
        return mutables;
    }

   

    public void function() {
        //Make a task
//            if(pregnant){
//                if(time < delivery ){
//                    time ++;
//                }else{
//                    time = 0;
//                    pregnant = false;
//                    organism.birthOffspring();
//                }
//            }
        
        //Energy drained from the body differs based on pegnancy status:
        if(pregnant){
            energyCost = .45;
        }else{
            energyCost = .05;
        }
        
       if(organism.tasks.getTask(taskName) != null){
            
        }
    }



    public void impregnate( Organism father1){
        setFather(father1);
        if(!pregnant){
        organism.tasks.addTask(new Task(taskName, delivery){
            public void completion(){
                
                    pregnant = false;
                    organism.birthOffspring();
                    organism.tasks.getTask(taskName).setStatic();
                    organism.setEnergy(fetalEnergy);
                    state = Task.STATIC;
                
            }

            public void function(){
                if( energy - energyTranfer > 0){
                     fetalEnergy+=energyTranfer*1.5;
                     //organism.setEnergy(organism.getEnergy()-energyTranfer);
                     energy-=energyTranfer;
                }
            }
        });
        pregnant = true;
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

    public boolean isContainer() {
        return isContainer;
    }

    public ArrayList<Entity> getEntities() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void decay() {
    }

    public Organism getOrganism() {
        return organism;
    }

    public void setOrganism(Organism organism) {
        this.organism = organism;
    }

    public double getDelivery() {
        return delivery;
    }

    public void setDelivery(double delivery) {
        this.delivery = delivery;
    }

    public Organism getFetus() {
        return fetus;
    }

    public void setFetus(Organism fetus) {
        this.fetus = fetus;
    }

    public boolean isPregnant() {
        return pregnant;
    }

    public void setPregnant(boolean pregnant) {
        this.pregnant = pregnant;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public Organism getFather() {
        return father;
    }

    public void setFather(Organism father) {
        this.father = father;
    }

}
