/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package breed;

import java.awt.Color;
import java.awt.Polygon;


public  class Thing {
    Thing target = null;
    double angle=0;
    double x;
    double y;
    int size;
    double speed;
    int health;
    String name = "";
    Polygon area = new Polygon();
    Color color;
    boolean alive = true;
    boolean removable = false;
    boolean highlighted = false;
    boolean selected = false;
    int healthMax;
    int fireCounter = 0;
    int direction;


    
      public boolean isHighlighted() {
        return highlighted;
    }

    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }


    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    public void setRemovable(boolean b){ removable = b; }
    public boolean isRemovable(){ return removable; }
    public void setHealth(int h){health = h; }
    public int getHealth(){ return health; }
    public double getSpeed(){ return speed; }
    public void setSpeed(double s){ speed = s; }
    public void setX(double x1){ x = x1; }
    public void setY(double y1){ y = y1; }
    public double getX(){ return x; }
    public double getY(){ return y; }
    public void setSize(int s){ size = s; }
    public int getSize(){ return size; }
    public void setArea(Polygon p){ area = p; }
    public Polygon getArea(){ return area; }
    public void resetArea(){ area.reset(); }
    public void addAreaPoint(int x1, int y1){ area.addPoint(x1, y1); }
    public boolean isAlive(){ return alive; }
    public void setName(String n){ name = n; }
    public void setAlive(boolean b){alive = b;}

    public int getHealthMax() {
        return healthMax;
    }

    public void setHealthMax(int healthMax) {
        this.healthMax = healthMax;
    }

    public String getName() {
        return name;
    }

    public int getFireCounter() {
        return fireCounter;
    }

    public void setFireCounter(int fireCounter) {
        this.fireCounter = fireCounter;
    }

    public Thing getTarget() {
        return target;
    }

    public void setTarget(Thing target) {
        this.target = target;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }



    
}
