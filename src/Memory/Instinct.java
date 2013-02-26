/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Memory;

import breed.Organism;

/**
 *
 * @author maxsmiley
 */
public class Instinct {
    String name = "";
    Organism organism;

    public Instinct(){

    }

    public Instinct(String Name, Organism org){
        setName(Name);
        setOrganism(org);
    }
    
    public void run(){
        if(condition()){
            action();
        }
    }

    public boolean condition(){
        return false;
    }

    public void action(){
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Organism getOrganism() {
        return organism;
    }

    public void setOrganism(Organism organism) {
        this.organism = organism;
    }




}
