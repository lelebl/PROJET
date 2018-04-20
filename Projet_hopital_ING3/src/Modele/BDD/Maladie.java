/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele.BDD;

import Modele.Connexion;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Emmanuelle THIROLOIX&Léa Blanchard
 */
public class Maladie implements Table {

    private Connexion conn;
    public String nom;
    public String localisation;
    public int stade;
    public String specialisation;
    public int nb_att;
    public String[] attributs={"nom", "localisation", "stade", "specialisation"};;
    public JTextField[] textAjout;
    public JLabel[] labelAjout;
    
    public Maladie(String n, String l, int s, String spe, Connexion c){
        nom=n;
        nb_att=4;
        localisation=l;
        stade=s;
        specialisation=spe;
        conn=c;
        this.initLabelsAjout();
    }
    
    @Override
    public String toString(){
        return ("nom: "+nom+" / localisation: "+localisation+" / stade: "+stade+" / specilisation: "+specialisation);
    }

    public void initLabelsAjout(){
        textAjout= new JTextField[nb_att];
        labelAjout= new JLabel[nb_att];
        
        for(int i=0; i<nb_att;i++){
            labelAjout[i]=new JLabel();
            labelAjout[i].setSize(300, 25);
            labelAjout[i].setFont(new Font("Times New Roman", Font.BOLD,20));
            labelAjout[i].setForeground(new Color(3,34,76));
            labelAjout[i].setText(attributs[i]);
            textAjout[i]=new JTextField();
        }
    }
        
   

    @Override
    public void ajouter(String attribut) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean modifier(ArrayList<String> attribut,ArrayList<String> new_attribut) {
        Boolean ok=false;
        String requete="UPDATE `hopital`.`maladie` SET `maladie`.`";
            for(int i=0; i<attribut.size();i++){
                requete=requete+attribut.get(i)+"`='"+new_attribut.get(i)+"'";
                if(i!=attribut.size()-1){
                    requete=requete+",`maladie`.`";
                }
            }
            requete=requete+" WHERE `maladie`.`nom` = '"+nom+"';";
            
        try {
            conn.executeUpdate(requete);
            ok=true;
        }catch (SQLException ex) {
            System.out.println("ECHEC REQUETE MODIF: "+requete);
            Logger.getLogger(Maladie.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }

    @Override
    public void setC(Connexion c) {
        conn=c;
    }

    @Override
    public Boolean supprimer() {
        Boolean ok=false;
        try {
            String requete="DELETE FROM `hopital`.`maladie` WHERE `maladie`.`nom` = '"+nom+"';";
            conn.executeUpdate(requete);
            ok=true;
        } catch (SQLException ex) {
            Logger.getLogger(Chambre.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }
}
