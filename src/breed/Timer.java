/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package breed;

import java.util.ArrayList;

/**
 *
 * @author SmileyM
 */
public class Timer extends Thread {
 boolean graphicsRunning = true;
 ArrayList<Panel> panels = new ArrayList<Panel>();
 long time = 1;
 Environment environment;
 public Timer(){

 }
 public Timer(Environment environ){
     environment = environ;
 }
    public void run(){
      
                    while(graphicsRunning){
                        for(int p = 0; p < panels.size(); p ++){
//                            for(Panel p : panels){
                                panels.get(p).repaint();
                            }
                        try{
                            sleep(30);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }

         }

    public boolean isGraphicsRunning() {
        return graphicsRunning;
    }

    public void setGraphicsRunning(boolean graphicsRunning) {
        this.graphicsRunning = graphicsRunning;
    }

    public ArrayList<Panel> getPanels() {
        return panels;
    }

    public void setPanels(ArrayList<Panel> panels) {
        this.panels = panels;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
    


}
