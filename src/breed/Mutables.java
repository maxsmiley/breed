/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package breed;

import BodyParts.BodyPart;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maxsmiley
 */
public class Mutables {
    int mutableCount = 0;
    int mutableSize = 50;
    int namesCount = 0;
    double variations[] = new double[mutableSize];
    Mutable mutables[] = new Mutable[mutableSize];
    String names[] = new String[mutableSize];
    int sex[] = new int[mutableSize];
//    BodyPart parts[] = new BodyPart[mutableSize];
    boolean set[] = new boolean[mutableSize];
    Organism organism;

    public Mutables(){
        for(int i = 0; i < set.length; i ++){
            set[i] = false;
        }

    }

    public Mutables(Organism o ){
        setOrganism(o);

    }

    public void addMutable(String name, double variation, BodyPart b, int sex1){
        if(!containsMutable(name)){
            names[namesCount] = name;
            variations[namesCount] = variation;
            sex[namesCount] = sex1;
            namesCount++;
        }
        
    }

    public void addMutable(Mutable m){
         for(int i = 0 ; i < namesCount; i++){
            if(names[i].equals(m.getName())){
               mutables[i] = m;
               mutables[i].setVariation(variations[i]);
               mutableCount++;
//                System.out.println("Step 2 - " +mutables[i].getName());
            }
        }

    }

    public void registerMutables(BodyPart b){
        for(int f = 0; f < b.getClass().getDeclaredFields().length; f ++){
//            for(Field f : b.getClass().getDeclaredFields()){

                if(b.getOrganism().getMutables().containsName(b.getClass().getDeclaredFields()[f].getName())){
//                    System.out.println("Step 1 - " +f.getName());
                    //b.getOrganism().getMutables().
                    addMutable(new Mutable(b.getClass().getDeclaredFields()[f], b.getClass().getDeclaredFields()[f].getName(), b, sex[getNameID(b.getClass().getDeclaredFields()[f].getName())]));
                }
            }
    }

    public void applyMutations(){
        for(int b = 0; b < getOrganism().getParts().size(); b ++){
//        for( BodyPart b : getOrganism().getParts()){
                 for(Field f : getOrganism().getParts().get(b).getClass().getDeclaredFields()){
              if(getOrganism().getMutables().getNameID(f.getName()) != -1){
//                  System.out.println("SUCCESSFUL MUTATION");
            try {

                     if(f.toString().contains("double")){
//                                double value = 0;  f.setDouble((Object)b, (f.getDouble((Object)b)+Math.random()*1-.5));
 double value = 0;
 if(sex[getNameID(f.getName())]==Mutable.SEX_UNISEX){
     value += getOrganism().getMother().getMutables().getMutable(f.getName()).getValue();
     value += getOrganism().getFather().getMutables().getMutable(f.getName()).getValue();
     value /= 2;
     value += (Math.random()*getMutable(f.getName()).getVariation())-(getMutable(f.getName()).getVariation()/2.);
     value = Math.max(0, value);
     getMutable(f.getName()).setValue(value);
 }else
 if(sex[getNameID(f.getName())]==Mutable.SEX_MALE){
     value += getOrganism().getFather().getMutables().getMutable(f.getName()).getValue();
     value += (Math.random()*getMutable(f.getName()).getVariation())-(getMutable(f.getName()).getVariation()/2.);
     value = Math.max(0, value);
     getMutable(f.getName()).setValue(value);
 }else
 if(sex[getNameID(f.getName())]==Mutable.SEX_FEMALE){
     value += getOrganism().getMother().getMutables().getMutable(f.getName()).getValue();
     value += (Math.random()*getMutable(f.getName()).getVariation())-(getMutable(f.getName()).getVariation()/2.);
     value = Math.max(0, value);
     getMutable(f.getName()).setValue(value);
 }
// System.out.println(f.getName()+ " " + value + " " + sex[getNameID(f.getName())]);
                            f.setDouble((Object)(getOrganism().getParts().get(b)), (value));

                            }
                        } catch (IllegalArgumentException ex) {
                            Logger.getLogger(Organism.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IllegalAccessException ex) {
                            Logger.getLogger(Organism.class.getName()).log(Level.SEVERE, null, ex);
                        }






             }
        }
        }
   
    }


//    public boolean containsMutable(String s){
//        for(int i = 0 ; i < mutableCount; i++){
////               System.out.println("Name found" + mutables[i].getName() + " for " + s);
//            if(mutables[i].getName().equals(s)){
//
//                return true;
//            }
//        }
//        return false;
//    }
      public boolean containsMutable(String s){
          if( getNameID(s) != -1){
                if( mutables[getNameID(s)] != null){
                    return true;
                }
          }
        return false;
    }

    public boolean containsName(String s){
        for(int i = 0 ; i < namesCount; i++){
            if(names[i].equals(s)){
                return true;
            }
        }
        return false;
    }

    public int getNameID(String s){
        for(int i = 0 ; i < namesCount; i++){
            if(names[i].equals(s)){
                return i;
            }
        }

        return -1;
    }

    public Organism getOrganism() {
        return organism;
    }

    public void setOrganism(Organism organism) {
        this.organism = organism;
    }

    public Mutable getMutable(String s){
        if(getNameID(s) != -1)
         return mutables[getNameID(s)];
        

        return null;

    }

}
