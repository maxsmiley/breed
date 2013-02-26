
package breed;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;




public class MainFrame extends JFrame implements KeyListener{

    ArrayList<Boolean> keys = new ArrayList<Boolean>();
    JPanel mainPane;
    EnvironmentScreen environmentScreen;
//    GameScreen gameScreen;
//    OptionsScreen optionsScreen;
////    JTextField text;
//    Sidebar sidebar;
//    MenuScreen menuScreen;
//    EditorScreen editorScreen;
    Timer timer = new Timer();
    int screenWidth=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    int screenHeight =(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    InfoScreen infoScreen;
//    Image cursorImage = toolkit.getImage("Cursor.png");
//    Point hotSpot =new Point(0,0);
//    Cursor cursor = toolkit.createCustomCursor(cursorImage, hotSpot, "Tron");
    
    public MainFrame(){
//        setCursor(cursor);
        //Keys
        for(int i = 0; i<= 129; i++){
            keys.add(new Boolean(false));
        }
       addKeyListener(this);
        

//        sidebar = new Sidebar(this);
//        sidebar.setPreferredSize(new Dimension(150,screenHeight));
//        sidebar.setMinimumSize(new Dimension(150,screenHeight));
//        sidebar.setMaximumSize(new Dimension(150,screenHeight));
//        sidebar.setBackground(Color.WHITE);
//        sidebar.setBounds(0,0,150,screenHeight);



                  environmentScreen = new EnvironmentScreen(this);
                 environmentScreen.setBackground(Color.blue);
                 environmentScreen.setOpaque(true);
                 environmentScreen.setPreferredSize(new Dimension(screenWidth-150-300,screenHeight));
                 environmentScreen.setBounds(100,0,screenWidth-150-400,screenHeight);
                 environmentScreen.createEnvironment();

                 infoScreen = new InfoScreen(this);
                 infoScreen.setBackground(Color.blue);
                 infoScreen.setOpaque(true);
                 infoScreen.setPreferredSize(new Dimension(200,screenHeight));
                 infoScreen.setBounds(screenWidth-400+5,0,400-5,screenHeight);
//                 environmentScreen.settHeight(screenHeight);
//                 environmentScreen.settWidth(screenWidth-150);
//
//
//                 editorScreen = new EditorScreen(this);
//                 editorScreen.setBackground(Color.blue);
//                 editorScreen.setOpaque(true);
//                 editorScreen.setPreferredSize(new Dimension(screenWidth-150,screenHeight));
//                 editorScreen.setBounds(150,0,screenWidth-150,screenHeight);
//
//                 optionsScreen = new OptionsScreen(this);
//                 optionsScreen.setBackground(Color.blue);
//                 optionsScreen.setOpaque(true);
//                 optionsScreen.setPreferredSize(new Dimension(screenWidth-150,screenHeight));
//                 optionsScreen.setBounds(150,0,screenWidth-150,screenHeight);


//                 menuScreen = new MenuScreen(this){
//                     public void keyEvent(KeyEvent e){
//                         for(Button b: buttons){
//                             b.keyEvent(e);
//                         }
//                     }
//
//
//                 };
//                 menuScreen.setBackground(Color.blue);
//                 menuScreen.setOpaque(true);
//                 menuScreen.setPreferredSize(new Dimension(screenWidth-150,screenHeight));
//                 menuScreen.setBounds(150,0,screenWidth-150,screenHeight);
//
                 mainPane = new Panel();

        mainPane.setLayout(null);
        mainPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        mainPane.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPane.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPane.add(Box.createGlue());
        mainPane.add(environmentScreen);
        mainPane.add(infoScreen);
        environmentScreen.setVisible(true);
////        mainPane.add(p1);
//        mainPane.add(sidebar);
//        mainPane.add(gameScreen);
//        mainPane.add(optionsScreen);
//        mainPane.add(menuScreen);
//        mainPane.add(editorScreen);
//        optionsScreen.setVisible(false);
//        menuScreen.setVisible(false);
//        editorScreen.setVisible(false);
        
         this.setPreferredSize(new Dimension(screenWidth,screenHeight));
         this.setUndecorated(true);
//         this.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
         
        this.getContentPane().add(mainPane);
                this.pack();
                this.setVisible(true);
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//                timer.getPanels().add(sidebar);
//                timer.getPanels().add(gameScreen);
//                timer.getPanels().add(optionsScreen);
//                timer.getPanels().add(menuScreen);
//                timer.getPanels().add(editorScreen);

                timer.getPanels().add(infoScreen);
                timer.getPanels().add(environmentScreen);
                timer.start();
    }

    public void keyTyped(KeyEvent e) {
        for(int p = 0; p < timer.getPanels().size(); p ++){
//        for(Panel p : timer.getPanels()){
             timer.getPanels().get(p).keyTyped(e);
         }
    }

    public void keyPressed(KeyEvent e) {
//        keys.set(e.getKeyCode(),true);
//        if(e.getKeyCode() == KeyEvent.VK_P){
//            gameScreen.getGame().setPaused(!gameScreen.getGame().isPaused());
//        }
//        for(Player p : gameScreen.getGame().getPlayers()){
//              p.setPressingKey(false);
//
//             p.pressEvent(keys);
//
//             if(p.isComputer()){
//                 p.setPressingKey(true);
//             }
//
//        }

        for(int p = 0; p < timer.getPanels().size(); p ++){
//         for(Panel p : timer.getPanels()){
             timer.getPanels().get(p).keyPressed(e);
         }
    }

    public void keyReleased(KeyEvent e) {
//        keys.set(e.getKeyCode(),false);
//         for(Player p : gameScreen.getGame().getPlayers()){
//                 p.releaseEvent(keys);
//         }

        for(int p = 0; p < timer.getPanels().size(); p ++){
//         for(Panel p : timer.getPanels()){
             timer.getPanels().get(p).keyReleased(e);
         }


    }

    public EnvironmentScreen getEnvironmentScreen() {
        return environmentScreen;
    }

    public void setEnvironmentScreen(EnvironmentScreen environmentScreen) {
        this.environmentScreen = environmentScreen;
    }

    public InfoScreen getInfoScreen() {
        return infoScreen;
    }

    public void setInfoScreen(InfoScreen infoScreen) {
        this.infoScreen = infoScreen;
    }

    public JPanel getMainPane() {
        return mainPane;
    }

    public void setMainPane(JPanel mainPane) {
        this.mainPane = mainPane;
    }

    

    



}
