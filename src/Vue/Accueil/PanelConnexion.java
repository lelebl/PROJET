/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue.Accueil;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 *
 * @author Emmanuelle THIROLOIX
 */
public class PanelConnexion extends JPanel{
    /**
     * ATTRIBUTES
     */
    private final LineBorder roundedLineBorder ;
    private boolean type; // false= ECE // true= local
    
    private JTextField entreeUserNameECE;
    private JTextField entreeloginDatabase;
    private JTextField entreeNameDatabase;
    private JPasswordField entreePasswordDatabase;
    private JPasswordField entreePasswordECE;
    
    private String userNameECE="";
    private String loginDatabase="";
    private String passwordDatabase="";
    private String passwordECE="";
    private String nameDatabase="";
    
    public PanelConnexion(GridBagConstraints gc, int x, int y,boolean b){
        super();
        type=b;
        roundedLineBorder=new LineBorder(new Color(34, 66, 124), 10, true); // On crée la bordure des panels
        this.setBackground(new Color(255,255,255,150));
        this.setBorder(roundedLineBorder);
        gc.gridx=x;
        gc.gridy=y;
        
        if(!type){
            this.initUserNameECE();
        }
        
    }
    
    /**
     * Initialisation des zones d'entrée de texte pour connexion ECE
     */
    public void initUserNameECE(){
        entreeUserNameECE= new JTextField(20);
        entreeUserNameECE.setSize(500,20);
        this.add(entreeUserNameECE);
    }
    
    /**
     * GUETTERS
     */
    public String getUserNameECE(){
        return userNameECE;
    }
    public String getLoginDatabase(){
        return loginDatabase;
    }
    public String getPasswordDatabase(){
        return passwordDatabase;
    }
    public String getPasswordECE(){
        return passwordECE;
    }
    public String getNameDatabas(){
        return nameDatabase;
    }
}
