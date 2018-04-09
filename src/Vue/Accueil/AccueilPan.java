/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue.Accueil;

import Vue.MyPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 *
 * @author Emmanuelle THIROLOIX
 */
public class AccueilPan extends MyPanel{
    /**
     * ATTRIBUTES
     */
    private JPanel coECE;
    private JPanel coLocal;
    private JTextField enterUser;
    private JPasswordField enterPassword;
    private JButton valider;
    
    /**
     * CONSTRUCTORS
     */
    public AccueilPan(){
        super(true,"Accueil");
        
        // Initialisation panel général
        this.setLayout(new GridBagLayout()); 
        GridBagConstraints gc = new GridBagConstraints();
        
        gc.fill = GridBagConstraints.BOTH; //on occupe tout l'espace disponible
	gc.ipady = gc.anchor = GridBagConstraints.CENTER; // on centre les composants
        gc.weightx = 2; // 2 case en abscisse 
	gc.weighty = 1; // 1 case en ordonnée
        LineBorder roundedLineBorder = new LineBorder(new Color(34, 66, 124), 10, true); // On crée la bordure des panels
        
        // Panel de connexion Local
        gc.insets = new Insets(50, 100, 50, 50); //on applique des marges
        coLocal=new PanelConnexion(gc,0,0,true);
        this.add(coLocal, gc);
        
        // Panel de connexion ECE
        gc.insets = new Insets(50, 50, 50, 100); //on applique des marges
        coECE= new PanelConnexion(gc,1,0,false);
        this.add(coECE, gc);
    }
    
}
