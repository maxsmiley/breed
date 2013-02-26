/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package breed;

import java.awt.Color;

/**
 *
 * @author maxsmiley
 */
public class GraphValue {
    String valueName = "";
    Color color = Color.WHITE;
    Lable lable;
    Graph graph;
    double values[];
    double trackValue;
    int delay=0, delayCount=0;
    int valueCount=0;
    int style =2;
    double max, min;
    boolean obeyTime = false;

    public GraphValue(){

    }

    public GraphValue(Graph g){
        setGraph(g);
        values = new double[graph.getValueSize()];
        if(graph.getLable()!= null){
            setLable(graph.getLable());
        }
        setDelay(graph.getDelay());
    }

    public void addValue(double val){ //With delay
        if(delayCount>=delay){ //Consider Delay
            delayCount=0;
                if(valueCount<values.length){ //First go thru of value - pushing out the graph
                    values[valueCount]=val;
                    valueCount++;
                }
                else{//Shift values down
                    for(int i = 0; i < values.length-1; i++){
                        values[i]=values[i+1];
                    }
                    values[values.length-1]=val;
                }
        }else{
            delayCount++;
        }

    }

    public void addValue(){
        if(valueCount<values.length){ //First go thru of value - pushing out the graph
            values[valueCount]=lable.getValue();
            valueCount++;
        }
        else{//Shift values down
            for(int i = 0; i < values.length-1; i++){
                values[i]=values[i+1];
            }
            values[values.length-1]=lable.getValue();
        }

    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public double getValue(int i){
        if(values.length >=i && valueCount >= i){
        return values[i];
        }
        return -1;
    }

    public Lable getLable() {
        return lable;
    }

    public void setLable(Lable lable) {
        this.lable = lable;
    }

    public int getValueCount() {
        return valueCount;
    }

    public void setValueCount(int valueCount) {
        this.valueCount = valueCount;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    public void setMax(double max) {
        this.max = max;
    }



    public void setMin(double min) {
        this.min = min;
    }

    public void resetExtremes(){
        max = Double.MIN_VALUE;
        min = Double.MAX_VALUE;
    }

    public void reset(){
        resetExtremes();
        valueCount=0;
    }
    public double getMax(){

        for(int i = 0; i<valueCount && i < values.length; i ++){
            if(values[i]>max){
                max = values[i];
            }
        }
        if(lable != null){
            lable.setMax(max);
        }
        return max;
    }
     public double getMin(){

        for(int i = 0; i<valueCount && i < values.length; i ++){
            if(values[i]<min){
                min = values[i];
            }
        }
        lable.setMin(min);
        return min;
    }

    public double[] getValues() {
        return values;
    }

    public void setValues(double[] values) {
        this.values = values;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getDelayCount() {
        return delayCount;
    }

    public void setDelayCount(int delayCount) {
        this.delayCount = delayCount;
    }

     
}
