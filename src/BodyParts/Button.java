/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BodyParts;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;


public class Button {
    boolean highlighted= false, selected= false;
    double x, y;
    String name;
    Polygon area = new Polygon();
    Color borderColor =  new Color(0, 0, 0, 0);
    Color pressedBorderColor = new Color(0, 0, 0, 0);
    Color highlightBorderColor = new Color(0, 0, 0, 0);
    Color selectedBorderColor = new Color(0, 0, 0, 0);
    Color selectedFillColor= new Color(0, 0, 0, 0);
    Color fillColor = new Color(0, 0, 0, 0);
    Color highlightFillColor = new Color(100, 100, 100, 50);
    Color sliderColor = Color.GRAY;
    Color textColor = Color.BLACK;
    Color selectedTextColor = Color.WHITE;
    double textScale = 1;
    private boolean pressed = false;
    private double width = 0, height = 0;
    String type = "";
    boolean toggle = false;
    boolean clickOnce = false;
    boolean showTopText = false;
    boolean showCenterText = true;
    int borderThickness = 1; //must be even number
    Font font = new Font("HELVETICA", Font.PLAIN, 15);
    Font centerFont = new Font("FUTURA", Font.PLAIN, 20);
    Graphics g;
    boolean slider = false;
    Rectangle sliderRect;
    boolean visible = true;
    int toggles = 0;
    boolean locked = false;
    public int key;

    public Button() {
        setX(0);
        setY(0);
        setHeight(100);
        setWidth(100);
        setName("");
    }

    public Button(String name1, int x1, int y1, int width1, int height1) {
        setName(name1);
        setX(x1);
        setY(y1);
        setHeight(height1);
        setWidth(width1);
        setType(name1);
    }
    public Button(String name1, int x1, int y1, int width1, int height1, boolean slider1) {
        setName(name1);
        setX(x1);
        setY(y1);
        setHeight(height1);
        setWidth(width1);
        setType(name1);
        if(slider1){
            slider = true;
            sliderRect = new Rectangle((int)(x+width/2),(int)(y+height/2-5),5,10);
        }
    }

    public void draw(Graphics g) {
        this.g = g;
        if(visible){
//             g.setFont(new Font("FUTURA", Font.PLAIN, 10));



            area.reset();
            area.addPoint((int) (x), (int) (y));
            area.addPoint((int) (x + width), (int) (y));
            area.addPoint((int) (x + width), (int) (y + height));
            area.addPoint((int) (x), (int) (y + height));

            //Fill
            g.setColor(fillColor);
            if (selected) {
                g.setColor(selectedFillColor);
            }
            if (highlighted) {
                g.setColor(highlightFillColor);
            }
            
            g.fillRect((int) (x), (int) (y), (int) width, (int) height);


            //Border
            g.setColor(borderColor);
            if (highlighted) {
                g.setColor(highlightBorderColor);
            }

            if (selected) {

                if(!pressed){
                    g.setColor(Color.BLACK);
                    g.drawRect((int) (x-1-borderThickness/2), (int) (y-1-borderThickness/2), (int) width+borderThickness+1, (int) height+borderThickness+1);
                }
                g.setColor(selectedBorderColor);

            }
            if (pressed) {
                g.setColor(pressedBorderColor);
            }

              //Thickness
            if(borderThickness >1){
              for(int i = -borderThickness/2; i < borderThickness/2; i++){
              g.drawRect((int) (x+i), (int) (y+i), (int) width, (int) height);
              }
            }
            else{
                g.drawRect((int) (x), (int) (y), (int) width, (int) height);
            }




            if(showTopText){
                g.setFont(new Font("FUTURA", Font.PLAIN, 10));
                g.setColor(textColor);
                if (!name.equals("")) {
                    g.drawString(name.substring(0,Math.min(name.length(),6)), (int) (x - width / 2), (int) (y - height / 2));
                }
            }

            
            if(showCenterText){
                g.setColor(textColor);
                if(selected){
                      g.setColor(selectedTextColor);
                }
                g.setFont(centerFont);
                if (!name.equals("")) {
                    //g.drawString(name.substring(0,Math.min(name.length(),(int)(width/5))), (int) (x+width/2 - name.length()*8), (int) (y + height/2+5));
                } g.drawString(name.substring(0,Math.min(name.length(),(int)(width/5))), (int) x+5,(int)( y + height/2 +5));
            }

            //Slider
            if(slider){
                g.setColor(sliderColor);
                g.drawLine((int)(x+10),(int)(y+height/2),(int)(x+width-10),(int)(y+height/2));
                g.setColor(Color.WHITE);
                g.fillRect((int)sliderRect.getX(), (int)sliderRect.getY(), (int)sliderRect.getWidth(), (int)sliderRect.getHeight());
            }


        }
    }

    public void clickedEvent(Graphics g){
        draw(g);
        
    }
    public void selectedEvent(){
        
    }
    public void registerKey(int key1){
        if(key1==key){
            keyEvent();
        }
    }
    public void keyEvent(){
        
    }


    public void registerHighlight(int x, int y){
         if(!locked){
                if(area.contains(x,y)){
                    highlighted = true;
                }
                else{
                    highlighted = false;
                }
                draw(g);
         }
    }
    public void registerSelect(int x, int y){
         if(!locked){
                if(clickOnce){
                    clickedEvent(g);
                }
                else{
                    if(toggle){
                        if(highlighted == true){
                            selected = !selected;
                        }
                    }
                    else{
                        if(highlighted == true){
                            selected = true;
                        }
                        else{
                            selected = false;
                        }
                    }
                }if(selected){selectEvent();}
                    draw(g);
                    
         }
    }
    public void selectEvent(){

    }
    public void registerPress(int x1, int y1){
        if(!locked){
                if(area.contains(x1,y1) && highlighted){
                     pressed = true;
                     if(slider){
                    sliderRect.setBounds(Math.max(Math.min(x1-2,(int)(x+width-15)),(int)(x+10)),(int)(y+height/2-5),5,10);
                }

                      pressEvent();
                }
                else{
                     pressed = false;
                }

                draw(g);
        }
           
    }
    public void pressEvent(){
        
    }
    public void keyEvent(KeyEvent e){
        
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public double getHeight() {
        return height;
    }

    public void setHeight(double height1) {
        height = height1;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width1) {
        width = width1;
    }

  

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public Color getHighlightBorderColor() {
        return highlightBorderColor;
    }

    public void setHighlightBorderColor(Color highlightBorderColor) {
        this.highlightBorderColor = highlightBorderColor;
    }

    public Color getHighlightFillColor() {
        return highlightFillColor;
    }

    public void setHighlightFillColor(Color highlightFillColor) {
        this.highlightFillColor = highlightFillColor;
    }

    public Color getSelectedBorderColor() {
        return selectedBorderColor;
    }

    public void setSelectedBorderColor(Color selectedBorderColor) {
        this.selectedBorderColor = selectedBorderColor;
    }

    public Color getSelectedFillColor() {
        return selectedFillColor;
    }

    public void setSelectedFillColor(Color selectedFillColor) {
        this.selectedFillColor = selectedFillColor;
    }

    public boolean isShowTopText() {
        return showTopText;
    }

    public void setShowTopText(boolean showTopText) {
        this.showTopText = showTopText;
    }

    public int getBorderThickness() {
        return borderThickness;
    }

    public void setBorderThickness(int borderThickness) {
        this.borderThickness = borderThickness;
    }

    public boolean isClickOnce() {
        return clickOnce;
    }

    public void setClickOnce(boolean clickOnce) {
        this.clickOnce = clickOnce;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Color getPressedBorderColor() {
        return pressedBorderColor;
    }

    public void setPressedBorderColor(Color pressedBorderColor) {
        this.pressedBorderColor = pressedBorderColor;
    }

    public boolean isToggle() {
        return toggle;
    }

    public void setToggle(boolean toggle) {
        this.toggle = toggle;
    }

    public boolean isShowCenterText() {
        return showCenterText;
    }

    public void setShowCenterText(boolean showCenterText) {
        this.showCenterText = showCenterText;
    }

    public boolean isSlider() {
        return slider;
    }

    public void setSlider(boolean slider) {
        this.slider = slider;
    }

    public Rectangle getSliderRect() {
        return sliderRect;
    }

    public void setSliderRect(Rectangle sliderRect) {
        this.sliderRect = sliderRect;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getToggles() {
        return toggles;
    }

    public void setToggles(int toggles) {
        this.toggles = toggles;
    }

    public Font getCenterFont() {
        return centerFont;
    }

    public void setCenterFont(Font centerFont) {
        this.centerFont = centerFont;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Polygon getArea() {
        return area;
    }

    public void setArea(Polygon area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public Color getSelectedTextColor() {
        return selectedTextColor;
    }

    public void setSelectedTextColor(Color selectedTextColor) {
        this.selectedTextColor = selectedTextColor;
    }

    

}

