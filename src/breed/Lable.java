/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package breed;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xx
 */
public class Lable {
    String anotation = "";
    double dValue;
    String name;
    int iValue;
    double x, y;
    double max, min; boolean hasExtremes = false;
    boolean isDouble=false,isInt=false;
    Polygon area = new Polygon();
    boolean highlighted = false, selected = false;
    double width = 150;
    boolean displayValue = true;
    
    public Lable(){
        
    }

    public Lable(String name1, double value, double x1, double y1){
        name = name1;
        dValue = value;
        isDouble=true;
        x=x1;
        y=y1;
    }

    public String getName(){
        return name;
    }

    public void draw(Graphics g){
        area.reset();
        area.addPoint((int)x, (int)y-5);
        area.addPoint((int)(x+width), (int)y-5);
        area.addPoint((int)(x+width), (int)y+5);
        area.addPoint((int)x, (int)y+5);
        g.setColor(Color.WHITE);
        
        if(highlighted){
            //g.setColor(new Color(255,255,150,255));
            g.setColor(new Color(150,200,255,255));
        }
        if(selected){
           // g.setColor(Color.yellow);
             g.setColor(new Color(90,160,255,255));
        }
        if(name == "Sex"){ //Unique settings:
            if(getValue() == 0){
                g.drawString(name+": Male", (int)x, (int)y);
            }
            if(getValue() == 1){
                g.drawString(name+": Female", (int)x, (int)y);
            }
            
        }else{
            if(displayValue){
                if(isDouble){
                g.drawString(name+": " +(float)dValue + anotation, (int)x, (int)y);
                }
                if(isInt){
                g.drawString(name+": " +iValue + anotation, (int)x, (int)y);
                }
            }else{
                g.drawString(name + anotation, (int)x, (int)y);

            }
        }
    }

    

    public void update(double update){
        if(isDouble){
        dValue = update;
        }
        if(isInt){
        iValue = (int)update;
        }

    }

    public Polygon getArea() {
        return area;
    }

    public void setArea(Polygon area) {
        this.area = area;
    }

    public boolean isHighlighted() {
        return highlighted;
    }

    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    



//    public String getValue(){
//        if(value.getType()==Integer.class){
//            try {
//                return "" + value.getInt(value);
//            } catch (IllegalArgumentException ex) {
//                Logger.getLogger(Lable.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (IllegalAccessException ex) {
//                Logger.getLogger(Lable.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        if(value.getType()==Double.class){
//            try {
//                return "" + value.getDouble(value);
//            } catch (IllegalArgumentException ex) {
//                Logger.getLogger(Lable.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (IllegalAccessException ex) {
//                Logger.getLogger(Lable.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        if(value.getType()==String.class){
//
//                return "" + value.toString();
//        }
//
//
//        return "";
//    }
 



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

    public double getValue(){
        if(isDouble)
        return dValue;

        return iValue;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        hasExtremes = true;
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        hasExtremes = true;
        this.min = min;
    }

    public String getAnotation() {
        return anotation;
    }

    public void setAnotation(String anotation) {
        this.anotation = anotation;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public boolean isDisplayValue() {
        return displayValue;
    }

    public void setDisplayValue(boolean displayValue) {
        this.displayValue = displayValue;
    }

    public boolean isIsDouble() {
        return isDouble;
    }

    public void setIsDouble(boolean isDouble) {
        this.isDouble = isDouble;
    }

    public boolean isIsInt() {
        return isInt;
    }

    public void setIsInt(boolean isInt) {
        this.isInt = isInt;
    }

    
}
