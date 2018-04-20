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
public class Soigne implements Table{

    private Connexion conn;
    public int no_docteur;
    public int no_malade;
    public int nb_att;
    public String[] attributs={"no_malade", "no_docteur"};;
    public JTextField[] textAjout;
    public JLabel[] labelAjout;
    
    public Soigne(int no_d, int no_m, Connexion c){
        no_docteur=no_d;
        nb_att=2;
        no_m=no_malade;
        conn=c;
        this.initLabelsAjout();
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
    public String toString(){
        return ("no_docteur: "+no_docteur+" / no_malade: "+no_malade);
    }

    @Override
    public void ajouter(String attribut) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean modifier(ArrayList<String> attribut,ArrayList<String> new_attribut) {
        Boolean ok=false;
        String requete="UPDATE `hopital`.`soigne` SET `soigne`.`";
            for(int i=0; i<attribut.size();i++){
                requete=requete+attribut.get(i)+"`='"+new_attribut.get(i)+"'";
                if(i!=attribut.size()-1){
                    requete=requete+",`soigne`.`";
                }
            }
            requete=requete+" WHERE CONCAT(`soigne`.`no_docteur`) = '"+no_docteur+"' AND CONCAT(`soigne`.`no_malade`) = '"+no_malade+"';";
            
        try {
            conn.executeUpdate(requete);
            ok=true;
        }catch (SQLException ex) {
            System.out.println("ECHEC REQUETE MODIF: "+requete);
            Logger.getLogger(Soigne.class.getName()).log(Level.SEVERE, null, ex);
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
            String requete="DELETE FROM `hopital`.`soigne` WHERE CONCAT(`soigne`.`no_docteur`) = '"+no_docteur+"' AND CONCAT(`soigne`.`no_malade`) = '"+no_malade+"';";
            conn.executeUpdate(requete);
            ok=true;
        } catch (SQLException ex) {
            Logger.getLogger(Chambre.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }
    
}
