
package Vue;

import Modele.BDD.Database;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;


public class MAJPan extends MyPanel{
    /**
     * ATTRIBUTES
     */
    public String currentType=""; // supprimer, ajouter, modifier
    public String currentTable=""; // table qu'on met à jour
    private Database myData; //notre connexion a la BDD
    private JButton boutons[]; // liste des tables de la BDD
    private JPanel menuTable;
    private JPanel pan;
    private JLabel titre;
    
    private JComboBox listSupp;
    
    private JLabel[] labelAjout;
    private JTextField[] textAjout;
    private String[] saisie;
    private JOptionPane errorInfo = new JOptionPane();
    
    private JButton valider;
    
    
    /**
     * CONSTRUCTORS
     */
    public MAJPan(){
        super(false,"MAJ");
        errorInfo.setSize(300,100);
        errorInfo.setFont(new Font("Times New Roman", Font.BOLD,20));
        // Déclaration des panels
        menuTable=new JPanel();
        pan= new JPanel();
        LineBorder roundedLineBorder = new LineBorder(new Color(34, 66, 124), 10, true); // On crée la bordure des panels
        menuTable.setBackground(new Color(255,255,255,150));
        menuTable.setBorder(roundedLineBorder);
        menuTable.setLayout(null);
        pan.setBackground(new Color(255,255,255,150));
        pan.setPreferredSize(new Dimension(this.getHeight()-100,this.getWidth()/5));
        pan.setBorder(roundedLineBorder);
        pan.setLayout(null);
        
        // Positionnement des labels:
        this.setLayout(new GridBagLayout()); 
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH; //on occupe tout l'espace disponible
	gc.ipady = gc.anchor = GridBagConstraints.CENTER; // on centre les composants
        gc.weightx = 2; // 2 case en abscisse 
	gc.weighty = 2; // 2 case en ordonnée
        gc.insets = new Insets(50, 50, 50, 50); //on applique des marges
        gc.gridx = 0;
        gc.gridy = 1;
        this.add(menuTable, gc);
        gc.gridx=1;
        gc.gridy=1;
        this.add(pan, gc);
        
        boutons=null; //tant que l'on a pas de connexion on ne peut pas faire les boutons
        myData=null; //car pas encore de connexion
        this.setVisible(true);
    }
    /**
     * SETTERS
     */
    public void setBoutons(){
        
        titre=new JLabel();
        titre.setSize(500, 38);
        titre.setLocation(menuTable.getX()+75,menuTable.getY()+30);
        titre.setFont(new Font("Times New Roman", Font.BOLD,38));
        titre.setForeground(new Color(3,34,76));
        titre.setText("");
        menuTable.add(titre);
        
        boutons= new JButton[myData.getNbrTables()];
        int posx=menuTable.getX()+ 70;
        int posy=menuTable.getY()+100;
        
        TableListener mylistener=new TableListener();
        for (int i=0;i<myData.getNbrTables();i++){ //on parcourt les différentes tables de la BDD
            boutons[i]= new JButton(myData.getNomTable()[i]);
            boutons[i].setBackground(new Color(255,255,255));
            boutons[i].setForeground(new Color(3,34,76));
            boutons[i].setFont(new Font("Times New Roman", Font.BOLD,20));
            boutons[i].setSize(200,50);
            boutons[i].setLocation(posx,posy);
            posy=posy+70;
            boutons[i].addActionListener(mylistener);
            menuTable.add(boutons[i]);
        }
        
    }
    public void setData(Database a){
        myData=a;
        this.setBoutons();
    }
    public void setTitle(String t){
        currentType=t;
        titre.setText(currentType);
    }
    
    
    
}
