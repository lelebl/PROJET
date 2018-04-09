/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Emmanuelle THIROLOIX
 */
public class MyPanel extends JPanel{
    
    /**
     * ATTRIBUTES:
     */
    protected int taillex;
    protected int tailley;
    protected boolean visible; //pour savoir si c'est lui qu'on affiche
    protected String nom;
    private Image fond;
    
    /**
     * DEFAULT CONSTRUCTOR
     */
    public MyPanel(){
        
    }
    /**
     * CONSTRUCTOR
     * @param v visible ou non
     * @param n nom du panel
     */
    public MyPanel(boolean v, String n){
        super();
        nom=n;
        
        // Dimensionner le panel
        // On récupère la dimension de l'écran pour l'appliquer à la fnetre JFrame
        Dimension tailleMoniteur= Toolkit.getDefaultToolkit().getScreenSize();
        taillex=tailleMoniteur.width;
        tailley=tailleMoniteur.height;
        this.setSize(taillex,tailley);
        
        // Fond:
        /**
         * Chargement de l'image de fond 
         */
        try{
            fond= ImageIO.read(new File("Image/fond.jpg"));
        }catch (IOException e) {
            System.out.println("Probleme chargement fond");
            e.printStackTrace();
        }
        
        // Afficher ou non le panel
        visible = v;
        this.setVisible(visible);
    }
    
     @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.drawImage(fond,0,0,getWidth(), getHeight(),null);
    }
    
    /**
     * SETTERS
     */
    public void setV(boolean b){
        visible=b;
        this.setVisible(b);
    }
    
    /**
     * GUETTERS
     */
    public boolean getV(){
        return visible;
    }
}
