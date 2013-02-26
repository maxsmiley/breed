/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package breed;

import BodyParts.BodyPart;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author xx
 */
public class Entity implements Movable {
    double x = 100, y=200;
    Organism organism;
    BodyPart bodyPart;
    double weight;
    boolean outside = false;
    Image image = null;
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Environment environment;

    public Entity(){
        
    }

    public Entity(BodyPart bodyPart1){
        setBodyPart(bodyPart1);
        setWeight(getBodyPart().getWeight());
    }

    public Entity(Organism organism1){
        setOrganism(organism1);
    }

    public Entity(Environment e, double x1, double y1, BodyPart bp){
        setEnvironment(e);
        setX(x1);
        setY(y1);
        setBodyPart(bp);
        outside = true;

         
    }

    public void run(){
        if(outside){

        }
    }

    public void draw(Graphics g2){
         Graphics2D g = (Graphics2D)g2;
        g.setColor(Color.BLUE);
        g.fillOval((int)(x), (int)y, 10, 10);
//       try {
//                            //                         image = toolkit.getImage("Leaf1.png");
//                            image = ImageIO.read(new File("src/Images/Leaf1.png"));
//
//                        } catch (IOException ex) {
//                        }
//         g.drawImage(image, (int)x, (int)y, null);

//        if(outside){
//            if(image!= null){
//                g.drawImage(image, (int)x, (int)y, null);
//            }
//            if(image == null){
//                if(bodyPart != null){
////                    if(bodyPart.getClass().getSimpleName() == "Leaf"){
//                        try {
//                            //                         image = toolkit.getImage("Leaf1.png");
//                            ImageIO.read(new File("src/Leaf1.png"));
//                            g.drawImage(image, (int)x, (int)y, null);
//                        } catch (IOException ex) {
//                            Logger.getLogger(Entity.class.getName()).log(Level.SEVERE, null, ex);
//                        }
////                    }
//
//                    }
//            }
//        }
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

    public BodyPart getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(BodyPart bodyPart) {
        this.bodyPart = bodyPart;
    }

    public Organism getOrganism() {
        return organism;
    }

    public void setOrganism(Organism organism) {
        this.organism = organism;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public boolean isOutside() {
        return outside;
    }

    public void setOutside(boolean outside) {
        this.outside = outside;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }



   

}
