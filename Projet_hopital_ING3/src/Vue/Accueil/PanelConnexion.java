
package Vue.Accueil;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import Vue.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Emmanuelle THIROLOIX&Léa Blanchard
 */
public class PanelConnexion extends JPanel{
    /**
     * ATTRIBUTES
     */
    private final LineBorder roundedLineBorder ;
    private boolean type; // false= ECE // true= local
    private Font f=new Font("Courier", 1, 20);
    
    private JTextField entreeUserNameECE;
    private JTextField entreeLoginDatabase;
    private JTextField entreeNameDatabase;
    private JPasswordField entreePasswordDatabase;
    private JPasswordField entreePasswordECE;
    
    private String userNameECE="";
    private String loginDatabase="";
    private String passwordDatabase="";
    private String passwordECE="";
    private String nameDatabase="";
    
    private JLabel titre;
    private JLabel UNECE;
    private JLabel LD;
    private JLabel PD;
    private JLabel PECE;
    private JLabel ND;
    private JLabel error;
    
    private Button valider;
    
    private int scaley=75;
    private int labelSize=200;
    private int taillePolice=25;
    private int xTitle=this.getX()+100;
    private int yTitle=this.getY()+50;
    private int xLigne=this.getX()+100;
    private int yLigne=this.getY()+200;
    private int xBox=this.getX()+200+labelSize;
    private int yBox=this.getY()+200;
    private int xValider=this.getX()+300;
    private int yValider=this.getY()+600;
    
    
    public PanelConnexion(GridBagConstraints gc, int x, int y,boolean b){
        super();
        type=b;
        roundedLineBorder=new LineBorder(new Color(34, 66, 124), 10, true); // On crée la bordure des panels
        this.setBackground(new Color(255,255,255,150));
        this.setBorder(roundedLineBorder);
        gc.gridx=x;
        gc.gridy=y;
        
        this.setLayout(null);
        
        /**
         * ajout du titre
         */
        titre= new JLabel();
        titre.setSize(500, 38);
        titre.setLocation(xTitle,yTitle);
        titre.setFont(new Font("Courier", 1, 38));
        titre.setForeground(new Color(34, 66, 124));
        
        error= new JLabel();
        error.setSize(200, 28);
        error.setLocation(xValider,yValider+100);
        error.setFont(new Font("Courier", 1, 38));
        error.setForeground(new Color(34, 66, 124));
        error.setText("");
        
        /**
         * Ajout de champs du formulaire de connexion
         */
        if(!type){
            this.initECE();
        }
        else{
            this.initLocal();
        }
        this.add(titre);
        this.initLoginDatabase();
        this.initPasswordDatabase();
        
        valider=new Button("Valider",xValider,yValider,200,100,Color.BLUE);
        this.add(valider);
    }
    
    /**
     * Initialisation des zones d'entrée de texte pour connexion ECE
     */
    public void initECE(){
        titre.setText("Connexion au serveur ECE");
        this.initUserNameECE();
        this.initPasswordECE();
    }
    public void initUserNameECE(){
        UNECE= new JLabel();
        UNECE.setSize(labelSize, taillePolice);
        UNECE.setLocation(xLigne,yLigne);
        yLigne=yLigne+scaley+taillePolice;
        UNECE.setFont(f);
        UNECE.setForeground(new Color(34, 66, 124));
        UNECE.setText("User Name ECE: ");
        
        this.add(UNECE);
        
        entreeUserNameECE= new JTextField(40);
        entreeUserNameECE.setLocation(xBox,yBox);
        yBox=yBox+scaley+taillePolice;
        entreeUserNameECE.setSize(labelSize,taillePolice);
                
        this.add(entreeUserNameECE);
    }
    public void initPasswordECE(){
        PECE= new JLabel();
        PECE.setSize(labelSize, taillePolice);
        PECE.setLocation(xLigne,yLigne);
        yLigne=yLigne+scaley+taillePolice;
        PECE.setFont(f);
        PECE.setForeground(new Color(34, 66, 124));
        PECE.setText("Password ECE: ");
        
        this.add(PECE);
        
        entreePasswordECE= new JPasswordField(20);
        entreePasswordECE.setLocation(xBox,yBox);
        yBox=yBox+scaley+taillePolice;
        entreePasswordECE.setSize(labelSize,taillePolice);
        
        this.add(entreePasswordECE);
    }
    
    /**
     * Initialisation des zones d'entrée de texte pour connexion Local
     */
    public void initLocal(){
        titre.setText("Connexion au serveur local");
        this.initNameDatabase();
    }
    public void initNameDatabase(){
        ND= new JLabel();
        ND.setSize(labelSize, taillePolice);
        ND.setLocation(xLigne,yLigne);
        yLigne=yLigne+scaley+taillePolice;
        ND.setFont(f);
        ND.setForeground(new Color(34, 66, 124));
        ND.setText("Name Database: ");
        
        this.add(ND);
        
        entreeNameDatabase= new JTextField(20);
        entreeNameDatabase.setLocation(xBox,yBox);
        yBox=yBox+scaley+taillePolice;
        entreeNameDatabase.setSize(labelSize,taillePolice);
        this.add(entreeNameDatabase);
    }
    
    /**
     * Initialisation des zones d'entrée de texte dans les deux cas
     */
    public void initLoginDatabase(){
        LD= new JLabel();
        LD.setSize(labelSize, taillePolice);
        LD.setLocation(xLigne,yLigne);
        yLigne=yLigne+scaley+taillePolice;
        LD.setFont(f);
        LD.setForeground(new Color(34, 66, 124));
        LD.setText("Login Database: ");
        
        this.add(LD);
        
        entreeLoginDatabase= new JTextField(20);
        entreeLoginDatabase.setLocation(xBox,yBox);
        yBox=yBox+scaley+taillePolice;
        entreeLoginDatabase.setSize(labelSize,taillePolice);
        
        this.add(entreeLoginDatabase);
    }
    public void initPasswordDatabase(){
        PD= new JLabel();
        PD.setSize(labelSize, taillePolice);
        PD.setLocation(xLigne,yLigne);
        yLigne=yLigne+scaley+taillePolice;
        PD.setFont(f);
        PD.setForeground(new Color(34, 66, 124));
        PD.setText("Password Database: ");
        
        this.add(PD);
        
        entreePasswordDatabase= new JPasswordField(20);
        entreePasswordDatabase.setLocation(xBox,yBox);
        yBox=yBox+scaley+taillePolice;
        entreePasswordDatabase.setSize(labelSize,taillePolice);
        
        this.add(entreePasswordDatabase);
    }
    
    
    /**
     * GUETTERS
     */
    public String getUserNameECE(){
        userNameECE=entreeUserNameECE.getText();
        return userNameECE;
    }
    public String getLoginDatabase(){
        loginDatabase=entreeLoginDatabase.getText();
        return loginDatabase;
    }
    public String getPasswordDatabase(){
        char[] a=entreePasswordDatabase.getPassword();
        passwordDatabase= new String(a);
        return passwordDatabase;
    }
    public String getPasswordECE(){
        char[] c=entreePasswordECE.getPassword();
        passwordECE= new String(c);
        return passwordECE;
    }
    public String getNameDatabase(){
        nameDatabase=entreeNameDatabase.getText();
        return nameDatabase;
    }
    public Button getValider(){
        return valider;
    }
    
    /**
     * Réinitialiser les champs d'entrée de connexion
     */
    public void init(){
        error.setText("Erreur de connexion");
        userNameECE="";
        loginDatabase="";
        passwordDatabase="";
        passwordECE="";
        nameDatabase="";
        if(this.type){ //local
            entreeNameDatabase.setText("");
        }
        else{
            entreeUserNameECE.setText("");
            entreePasswordECE.setText("");
        }
        
        entreeLoginDatabase.setText("");
        entreePasswordDatabase.setText("");
        
        
    }
}