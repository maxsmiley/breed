/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package breed;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

/**
 *
 * @author xx
 */
public class Call {
   Polygon area  = new Polygon();
   String content = "";
   double x, y, radius=0;
   double radiusMax;
   Organism organism;
   public Call(double x1, double y1, double r1){
      x=x1; y = y1; radiusMax = r1;
   }
   public Call(double x1, double y1, double r1, Organism organism1){
      x=x1; y = y1; radiusMax = r1;
      organism = organism1;
   }
    public Call(double x1, double y1, double r1, Organism organism1, String cont){
      x=x1; y = y1; radiusMax = r1;
      organism = organism1;
      content = cont;
   }

   public void draw(Graphics g){
//       g.setColor(new Color(0,0,0,150));
       g.setColor(new Color(0,0,0,255-Math.min(255,(int)(255*(radius/radiusMax)))));

       if(area.npoints >0)
       g.drawOval((int)(x-radius/2.),(int)(y-radius/2.),(int)(radius),(int)(radius));
//       g.drawPolygon(area);
       area.reset();
       
   }

   public void run(){
       if(radius < radiusMax){
           radius +=9.5;
            area.reset();

           for(int i = 0; i <= 366; i+=30){
                area.addPoint((int)(x+radius*Math.cos(Math.toRadians(i))),(int)(y+radius*Math.sin(Math.toRadians(i))));
           }


           if(organism != null){
               for(Organism o : organism.getEnvironment().getLivingOrganisms()){
                    if(o != organism && area.contains(o.getX(), o.getY())){
                        o.hear(this, x, y);
                       //o.setDirection(Math.atan2(y-o.getY(),x-o.getX()));
    //                    g.setColor(new Color(0,0,0,100));
    //                    g.drawLine((int)x, (int)y, (int)o.getX(), (int)o.getY());
                    }
               }
           }
       }else{
           area.reset();
       }
      

//       if(organism != null){
//           for(Organism o : organism.getEnvironment().getLivingOrganisms()){
//                if(o != organism && area.contains(o.getX(), o.getY())){
//                    o.setDirection(Math.atan2(y-o.getY(),x-o.getX()));
//
//                }
//           }
//       }
     
   }

    public Polygon getArea() {
        return area;
    }

    public void setArea(Polygon area) {
        this.area = area;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Organism getOrganism() {
        return organism;
    }

    public void setOrganism(Organism organism) {
        this.organism = organism;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getRadiusMax() {
        return radiusMax;
    }

    public void setRadiusMax(double radiusMax) {
        this.radiusMax = radiusMax;
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
   

}
