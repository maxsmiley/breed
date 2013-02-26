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
public class Eye extends BodyPart{
    Field mutables[];
    Organism organism = null;
    double ColorPerception =0; //Between 0-100
    double SightDistance =0;
    double Acuity =0;//Dark or light (-100)-100
    double sightRadius = 0;
    double energyMax = 600;

    Field fieldList[];

    ArrayList<Organism> sightOrganisms = new ArrayList<Organism>();
     ArrayList<Entity> sightEntities = new ArrayList<Entity>();

    double weight =.02;
    double energyCost =.09; //.09  < but plus the generatevision cost (.8) = .17
    
    public Eye(){
        testSettings();
        
    }

    public Eye(Organism organism1){
        this();
        setOrganism(organism1);
//        fieldList = this.getClass().getDeclaredFields();
//        for(int i = 0; i < fieldList.length; i++){
//            System.out.println(fieldList[i].getName()+" " +fieldList[i].getType());
//        }
    }

    public void testSettings(){
        energy = 100;
        SightDistance = 300;
         sightRadius = Math.toRadians(60);

    }

    
    public void function() {
        double generateVisionCost = .08;
         if(energy - generateVisionCost > 0){
            energy-= generateVisionCost ;
        }
      
            
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

    public void generateVision(){ //Costs - .08
        double generateVisionCost = .08;
        if(energy - generateVisionCost > 0){
            //energy-= generateVisionCost ;

            
            organism.getSight().reset();
            organism.getSight().addPoint((int)organism.getX(), (int)organism.getY());
            //Left Side
            boolean leftOkay = true;
            for(int i = 0; i < organism.eyes.get(0).getSightDistance(); i += (i<20 ? 5 : (i < 50) ? 15 : 25)){

                if(organism.getEnvironment().terrain[Math.min((int)(organism.getEnvironment().getWidth()/organism.getEnvironment().getGridWidth())-1, Math.max(0,(int)((organism.getX()+Math.cos(organism.getDirection()+organism.eyes.get(0).getSightRadius()/2.0)*i)/organism.getEnvironment().getGridWidth())))] [Math.min((int)(organism.getEnvironment().getHeight()/organism.getEnvironment().getGridHeight())-1,Math.max(0,(int)((organism.getY()+Math.sin(organism.getDirection()+organism.eyes.get(0).getSightRadius()/2.0)*i)/organism.getEnvironment().gridHeight)))].isImpathable()){
                    leftOkay = false;
                    organism.getSight().addPoint((int)(organism.getX()+Math.cos(organism.getDirection()+organism.eyes.get(0).getSightRadius()/2.0)*i), (int)(organism.getY()+Math.sin(organism.getDirection()+organism.eyes.get(0).getSightRadius()/2.0)*i));
                     break;
                }else{

                }
            }
            if(leftOkay){
                 organism.getSight().addPoint((int)(organism.getX()+Math.cos(organism.getDirection()+organism.eyes.get(0).getSightRadius()/2.0)*organism.eyes.get(0).getSightDistance()), (int)(organism.getY()+Math.sin(organism.getDirection()+organism.eyes.get(0).getSightRadius()/2.0)*organism.eyes.get(0).getSightDistance()));
            }

            //Right Side
            boolean rightOkay = true;
            for(int i = 0; i < organism.eyes.get(0).getSightDistance(); i += (i<20 ? 5 : (i < 50) ? 15 : 25)){


                 if(organism.getEnvironment().terrain[Math.min((int)(organism.getEnvironment().getWidth()/organism.getEnvironment().getGridWidth())-1, Math.max(0,(int)((organism.getX()+Math.cos(organism.getDirection()-organism.eyes.get(0).getSightRadius()/2.0)*i)/organism.getEnvironment().getGridWidth())))] [Math.min((int)(organism.getEnvironment().getHeight()/organism.getEnvironment().getGridHeight())-1,Math.max(0,(int)((organism.getY()+Math.sin(organism.getDirection()-organism.eyes.get(0).getSightRadius()/2.0)*i)/organism.getEnvironment().gridHeight)))].isImpathable()){
                     rightOkay = false;
                      organism.getSight().addPoint((int)(organism.getX()+Math.cos(organism.getDirection()-organism.eyes.get(0).getSightRadius()/2.0)*i), (int)(organism.getY()+Math.sin(organism.getDirection()-organism.eyes.get(0).getSightRadius()/2.0)*i));
                       break;
                 }else{


                }
            }
            if(rightOkay){
                  organism.getSight().addPoint((int)(organism.getX()+Math.cos(organism.getDirection()-organism.eyes.get(0).getSightRadius()/2.0)*organism.eyes.get(0).getSightDistance()), (int)(organism.getY()+Math.sin(organism.getDirection()-organism.eyes.get(0).getSightRadius()/2.0)*organism.eyes.get(0).getSightDistance()));
            }



//            sight.addPoint((int)(getX()+Math.cos(direction+eyes.get(0).getSightRadius()/2.0)*eyes.get(0).getSightDistance()), (int)(getY()+Math.sin(direction+eyes.get(0).getSightRadius()/2.0)*eyes.get(0).getSightDistance()));
//             sight.addPoint((int)(getX()+Math.cos(direction-eyes.get(0).getSightRadius()/2.0)*eyes.get(0).getSightDistance()), (int)(getY()+Math.sin(direction-eyes.get(0).getSightRadius()/2.0)*eyes.get(0).getSightDistance()));

            
            }
    }

    public void feed(Receiver receive) {
    }

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

     public ArrayList<Organism> getSightOrganisms() {
        return sightOrganisms;
    }

    public void setSightOrganisms(ArrayList<Organism> sightOrganisms) {
        this.sightOrganisms = sightOrganisms;
    }

    public double getAcuity() {
        return Acuity;
    }

    public void setAcuity(double Acuity) {
        this.Acuity = Acuity;
    }

    public double getColorPerception() {
        return ColorPerception;
    }

    public void setColorPerception(double ColorPerception) {
        this.ColorPerception = ColorPerception;
    }

    public double getSightDistance() {
        return SightDistance;
    }

    public void setSightDistance(double SightDistance) {
        this.SightDistance = SightDistance;
    }

    public double getSightRadius() {
        return sightRadius;
    }

    public void setSightRadius(double sightRadius) {
        this.sightRadius = sightRadius;
    }

    public ArrayList<Entity> getSightEntities() {
        return sightEntities;
    }

    public void setSightEntities(ArrayList<Entity> sightEntities) {
        this.sightEntities = sightEntities;
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
