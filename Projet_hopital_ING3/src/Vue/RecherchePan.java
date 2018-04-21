package Vue;

import Modele.BDD.Database;
import Vue.Reporting.ReportingPan;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;


public class RecherchePan extends MyPanel{
    /**
     * ATTRIBUTES
     */
    private Database myData; //notre connexion a la BDD
    private JComboBox what;
    private Object[] liste= new Object[10];
    private String[] requete= new String[10];
    
    /**
     * CONSTRUCTORS
     */
    public RecherchePan(){
        super(false,"Recherche");
        
        // Ajout de la combobox
        liste[0]="Médecins n'ayant aucun malade à soiger";
        liste[1]="";
        liste[2]="";
        liste[3]="";
        liste[4]="";
        liste[5]="";
        liste[6]="";
        liste[7]="";
        liste[8]="";
        liste[9]="";
        what= new JComboBox(liste);
        what.setLocation(400,30);
        what.setFont(new Font("Times New Roman", Font.BOLD,26));
        what.setSize(new Dimension(1000, 50));
        what.setBackground(new Color(255,255,255));
        what.addItemListener(new ItemQuestionListener());
        this.add(what);
        
        // Initialisation des requetes
        
    }
    
    /**
     * SETTERS
     */
    public void setData(Database a){
        myData=a;
    }
    
    /**
     * LISTENER
     */
    class ItemQuestionListener implements ItemListener{
        @Override
        public void itemStateChanged(ItemEvent event) {
           if (event.getStateChange() == ItemEvent.SELECTED) {
              Object item = event.getItem();
              for(int i=0; i<10; i++){
                  if(item==liste[i]){
                      
                  }
              }
           }
        }   
    }
    
}
