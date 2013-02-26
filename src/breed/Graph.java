/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package breed;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author xx
 */
public class Graph {
    //*********** MAKE GRAPHS ABLE TO STORE MULTIPLE LINES OF VALUES **************
//    ArrayList<Double> values = new ArrayList<Double>();
    GraphValue graphValues[];
    Organism organism;
    double values[];
    double trackValue;
    int scalesY;
    double x, y, height=50, width=100;
    int delay=0, delayCount=0;
    int valueCount=0;
     int style =2;
     int type =0;
     int valueSize=10;
     Color lineColor = new Color(255,255,255,150);
    final int STYLE_POINTS=0, STYLE_VERTICALSHIFT=1, STYLE_LINE=2;
    final Color lineColors[] = {Color.WHITE, Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW,Color.MAGENTA,Color.orange};
    boolean obeyTime = false;
    Environment environment;
    boolean seperateExtremes=false;
    
    double max = Double.MIN_VALUE, min = Double.MAX_VALUE;
    String valueName="";
    Lable lable;
    public Graph( double trackValue1, double x1, double y1, int valueSize1 ){
        trackValue = trackValue1;
        valueSize = valueSize1;
        values = new double[valueSize];
        setX(x1);
         setY(y1);

    }
    public Graph( double trackValue1, double x1, double y1, int valueSize1, int valueSize2 ){
        trackValue = trackValue1;
        valueSize = valueSize1;
        graphValues = new GraphValue[valueSize2];
        for(int i = 0; i < graphValues.length; i ++){
            graphValues[i] = new GraphValue(this);
            graphValues[i].setColor(lineColors[i]);
        }
        setX(x1);
         setY(y1);
         setType(1);

    }
    public Graph( Organism o, double trackValue1, double x1, double y1, int valueSize1 ){
        setOrganism(o);
        trackValue = trackValue1;
        valueSize = valueSize1;
         values = new double[valueSize];
        setX(x1);
         setY(y1);
         //testSettings();

    }
    public Graph( Organism o, double trackValue1, double x1, double y1, int valueSize1, int valueSize2 ){
        setOrganism(o);
        trackValue = trackValue1;
        valueSize = valueSize1;
        graphValues = new GraphValue[valueSize2];
        for(int i = 0; i < graphValues.length; i ++){
            graphValues[i] = new GraphValue(this);
            graphValues[i].setColor(lineColors[i]);
        }
        setX(x1);
         setY(y1);
          setType(1);
         //testSettings();

    }
    public void testSettings(){
        values = new double[20];

    }

    public void addValue(double val){
        if(delayCount>=delay){
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

    public void draw(Graphics g ){
        double currentMax = getMax();
   if(type == 0){
                g.setColor(Color.WHITE);
                g.drawLine((int)(x), (int)(y+height), (int)(x+width), (int)(y+height));
                g.drawLine((int)(x), (int)(y), (int)(x), (int)(y+height));
                 //Title
                g.setFont(new Font("HELVETICA", Font.PLAIN, 9));
                g.drawString(valueName, (int)x, (int)(y));




        //        for(int i = 0; i < width; i+=(width/values.length)){
        //
        //
        //        }
                g.setColor(lineColor);
            //STYLE 1 - POINTS - OPEN CIRCLES
                if(style ==0){
                    for(int i = 0; i < values.length && i < valueCount; i ++){
                        g.drawOval((int)(x+i*(width/values.length)), (int)(y+height-height*(values[i]/currentMax)), 3, 3);
                    }
                }
            //STYLE 2 - LINES
                if(style ==1){
                    for(int i = 0; i < values.length-1 && i < valueCount-1; i ++){
                        g.drawLine((int)(x+i*(width/values.length)), (int)(y+height-height*(values[i]/currentMax)), (int)(x+i*(width/values.length)), (int)(y+height-height*(values[i+1]/getMax())));
                    }
                }
            //STYLE 2 - LINES
                if(style ==2){
                    for(int i = 0; i < values.length-1 && i < valueCount-1; i ++){
                        g.drawLine((int)(x+i*(width/values.length)), (int)(y+height-height*(values[i]/currentMax)), (int)(x+(i+1)*(width/values.length)), (int)(y+height-height*(values[i+1]/getMax())));
                    }
                }

                 g.setColor(Color.WHITE);
                //Max
                g.setFont(new Font("HELVETICA", Font.PLAIN, 8));
                g.drawString((float)max+" ", (int)(x+width), (int)(y));

                //Max
        //        g.setColor(Color.GRAY);
        //        g.setFont(new Font("HELVETICA", Font.PLAIN, 7));
        //         g.drawString((float)max+"", (int)(x-(String.valueOf(max).length()*1.5)), (int)(y+5));
        //         g.setColor(Color.WHITE);
    }
    if(type == 1){
        g.setFont(new Font("HELVETICA", Font.PLAIN, 9));
        int nameSpace = 0;
        for(int i = 0 ; i < graphValues.length; i ++){
                g.setColor(graphValues[i].getColor());
                g.drawString(graphValues[i].getValueName(), (int)(x+nameSpace), (int)(y));
                nameSpace += (int)(graphValues[i].getValueName().length()*5.5);
        }
                g.setColor(Color.WHITE);
                g.drawLine((int)(x), (int)(y+height), (int)(x+width), (int)(y+height));
                g.drawLine((int)(x), (int)(y), (int)(x), (int)(y+height));
                 //Title
                g.setFont(new Font("HELVETICA", Font.PLAIN, 9));
                g.drawString(valueName, (int)x, (int)(y));


                g.setColor(lineColor);
            //STYLE 1 - POINTS - OPEN CIRCLES
                if(style ==0){
                    for(int i = 0; i < graphValues.length; i ++){
                        g.setColor(graphValues[i].getColor());
                        for(int i2 = 0 ; i2 < graphValues[i].getValueCount(); i2 ++ )
                        g.drawOval((int)(x+i2*(width/graphValues[i].getValues().length)),
                                (int)(y+height-height*(graphValues[i].getValue(i2)/currentMax)), 3, 3);
                    }
                }
            //STYLE 2 - LINES
                if(style ==1){
                    for(int i = 0; i < graphValues.length; i ++){
                        g.setColor(graphValues[i].getColor());
                        for(int i2 = 0 ; i2 < graphValues[i].getValueCount()-1; i2 ++ ){
                         g.drawLine((int)(x+i2*(width/graphValues[i].getValues().length)), (int)(y+height-height*(graphValues[i].getValue(i2)/currentMax)), (int)(x+i2*(width/graphValues[i].getValues().length)), (int)(y+height-height*(graphValues[i].getValue(i2+1)/currentMax)));
                         }
                    }
                }
            //STYLE 2 - LINES
                if(style ==2){
                     for(int i = 0; i < graphValues.length; i ++){
                        g.setColor(graphValues[i].getColor());
                        for(int i2 = 0 ; i2 < graphValues[i].getValueCount()-1; i2 ++ ){
                            if(seperateExtremes){
                                g.drawLine((int)(x+i2*(width/graphValues[i].getValues().length)), (int)(y+height-height*(graphValues[i].getValue(i2)/graphValues[i].getMax())), (int)(x+(i2+1)*(width/graphValues[i].getValues().length)), (int)(y+height-height*(graphValues[i].getValue(i2+1)/graphValues[i].getMax())));
                            }else
                         g.drawLine((int)(x+i2*(width/graphValues[i].getValues().length)), (int)(y+height-height*(graphValues[i].getValue(i2)/currentMax)), (int)(x+(i2+1)*(width/graphValues[i].getValues().length)), (int)(y+height-height*(graphValues[i].getValue(i2+1)/currentMax)));
                         }
                    }
                }

            //Max
                g.setFont(new Font("HELVETICA", Font.PLAIN, 8));
                if(seperateExtremes ){
                    double shiftDown = 0;
                    for(int i = 0; i < graphValues.length; i ++){
                         g.setColor(graphValues[i].getColor());
                          g.drawString((float)(graphValues[i].getMax())+" ", (int)(x+width), (int)(y+shiftDown));
                          shiftDown+=7;
                    }
                }else{
                     g.setColor(Color.WHITE);

                    g.setFont(new Font("HELVETICA", Font.PLAIN, 8));
                    g.drawString((float)max+" ", (int)(x+width), (int)(y));
                }


                //Max
        //        g.setColor(Color.GRAY);
        //        g.setFont(new Font("HELVETICA", Font.PLAIN, 7));
        //         g.drawString((float)max+"", (int)(x-(String.valueOf(max).length()*1.5)), (int)(y+5));
        //         g.setColor(Color.WHITE);
      }
    }

    public double getMax(){
        if(type == 0){

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
        if(type == 1){
            for(int i = 0; i<graphValues.length; i ++){
                if(graphValues[i].getMax()>max){
                    max = graphValues[i].getMax();
                }
            }
           
            return max;
        }
        return max;
    }
     public double getMin(){
        if(type == 0){
            for(int i = 0; i<valueCount && i < values.length; i ++){
                if(values[i]<min){
                    min = values[i];
                }
            }

            if(lable != null){
            lable.setMin(min);
            }
        }
            
        if(type == 1){
            for(int i = 0; i<graphValues.length; i ++){
                if(graphValues[i].getMin()<min){
                    max = graphValues[i].getMin();
                }
            }

            return min;
        }
      
        return min;
    }


    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Organism getOrganism() {
        return organism;
    }

    public void setOrganism(Organism organism) {
        this.organism = organism;
    }

    public double[] getValues() {
        return values;
    }

    public void setValues(double[] values) {
        this.values = values;
    }

    public int getValueCount() {
        return valueCount;
    }

    public void setValueCount(int valueCount) {
        this.valueCount = valueCount;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    public void resetExtremes(){
        max = Double.MIN_VALUE;
        min = Double.MAX_VALUE;
    }

    public void reset(){
        resetExtremes();
        valueCount=0;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public Lable getLable() {
        return lable;
    }

    public void setLable(Lable lable) {
        this.lable = lable;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public int getValueSize() {
        return valueSize;
    }

    public void setValueSize(int valueSize) {
        this.valueSize = valueSize;
    }

    public GraphValue getGraphValue(int i ){
        return graphValues[i];
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public boolean isSeperateExtremes() {
        return seperateExtremes;
    }

    public void setSeperateExtremes(boolean seperateExtremes) {
        this.seperateExtremes = seperateExtremes;
    }
    
}
