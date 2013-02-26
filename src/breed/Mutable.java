/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package breed;

import BodyParts.BodyPart;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xx
 */
public class Mutable {
    String name="";
    double value;
    int type = 0;
    final static int TYPE_INT = 0, TYPE_DOUBLE = 1, TYPE_STRING = 2;
    double variation = 1;
    Field field;
    BodyPart bodyPart;
    int sex = 0;
    final static int SEX_UNISEX = 0, SEX_MALE = 1, SEX_FEMALE = 2;

        public Mutable(){

        }

        public Mutable(Field f){
            setField(f);
        }

         public Mutable(Field f, String name1, BodyPart b, int sex){
            setField(f);
            setName(name1);
            setBodyPart(b);
            setSex(sex);
        try {
            setValue(getField().getDouble(getBodyPart()));
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Mutable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Mutable.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getVariation() {
        return variation;
    }

    public void setVariation(double variation) {
        this.variation = variation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public BodyPart getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(BodyPart bodypart) {
        this.bodyPart = bodypart;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

        

}
