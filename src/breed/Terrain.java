/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package breed;

import java.awt.Color;

/**
 *
 * @author xx
 */ //dark dirt = (80,40,10,255)
public class Terrain {
    int type;
    Color color;
    public static int TYPE_RANDOM=-1;
    public static int TYPE_DIRT=0, TYPE_GRASS=1, TYPE_WATER=2, TYPE_ROCK=3, TYPE_GRASSYDIRT = 4;
    public static final int TYPES[]= {TYPE_DIRT,TYPE_GRASS, TYPE_WATER, TYPE_ROCK, TYPE_GRASSYDIRT};
    public static final Color COLORS[]= {new Color(100,60,25,255),new Color(0,160,20,255),new Color(100,100,255,255),new Color(100,100,100,255),new Color(50,110,22,255)};

    public Terrain(){
        setType(TYPES[1]);
        setColor(COLORS[type]);
    }

    public Terrain(int type1){
        if(type1==TYPE_RANDOM){
                setType(getRandomType());
        }else{
        setType(type1);
        }
        if(type >-1)
         setColor(COLORS[type]);
    }

    public int getRandomType(){
        return TYPES[(int)(Math.random()*TYPES.length)];
    }
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isImpathable(int type1){
        if(type1 == TYPE_ROCK){
            return true;
        }
        return false;

    }

     public boolean isImpathable(){
        if(type == TYPE_ROCK){
            return true;
        }
        return false;

    }



}
