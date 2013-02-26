/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package breed;

import java.awt.Color;

/**
 *
 * @author xx
 */
public class Task {
    Color color = Color.CYAN;
    String name;
    double duration;
    double progress=0;
    boolean complete;
    int state = 0;
    public final static int INPROGRESS = 1, COMPLETED = 2, STATIC = 0 , FAILED = -1;
    double progressVariance = 0;
    double progressBuffer = 0;
    
    
    public Task(){

    }
    public Task(String task){
        setName(task);
    }

    public Task(String task, double dur){
        setDuration(dur);
        setName(task);
    }

    public Task(String task, double dur, Color color1){
        setDuration(dur);
        setName(task);
        setColor(color1);
    }

    public Task(String task, double dur, double variance, Color color1){
        setDuration(dur);
        setName(task);
        setColor(color1);
        setProgressVariance(variance);
    }
    
    public void run(){
        if(state == 1){ //If running
            if(progressVariance > 0){
                progress+= Math.random()*progressVariance + progressBuffer;
            }else{
            progress+= 1 + progressBuffer;
            }
            if(progress >= duration){
                complete = true;
                state = 2;
                completion();
            }

            if(progress < 0 ){
                state = -1;
                failure();
            }
             function();
            
        }
//         progress++;
//        if(progress >= duration){
//                complete = true;
//            }
    }

    public void function(){

    }

    public void start(){
        complete = false;
        progress = 0;
        state = 1;

    }

    public void completion(){
        state = 0;
    }

    public void failure(){
        state = 0;
    }

    public void setStatic(){
        state = 0;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getProgressVariance() {
        return progressVariance;
    }

    public void setProgressVariance(double progressVariance) {
        this.progressVariance = progressVariance;
    }








}
