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
 * @author Emmanuelle THIROLOIX
 */
public class Docteur implements Table {

    private Connexion conn;
    public int numero;
    public String specialite;
    public int nb_att;
    public String[] attributs={"numero", "specialite"};;
    public JTextField[] textAjout;
    public JLabel[] labelAjout;
    
    public Docteur(int n,String s,Connexion c){
        numero=n;
        nb_att=2;
        specialite=s;
        conn=c;
        this.initLabelsAjout();
    }
    
        @Override
    public String toString(){
        return ("numero: "+numero+" / specialite: "+specialite);
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
        try {
            String requete="UPDATE `hopital`.`docteur` SET `docteur`.`";
            for(int i=0; i<attribut.size();i++){
                requete=requete+attribut.get(i)+"`='"+new_attribut.get(i)+"'";
                if(i!=attribut.size()-1){
                    requete=requete+",`docteur`.`";
                }
            }
            requete=requete+" WHERE CONCAT(`docteur`.`numero`) ='"+numero+"';";
            conn.executeUpdate(requete);
            ok=true;
        }catch (SQLException ex) {
            Logger.getLogger(Docteur.class.getName()).log(Level.SEVERE, null, ex);
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
            String requete="DELETE FROM `hopital`.`docteur` WHERE CONCAT(`docteur`.`numero`) ='"+numero+"';";
            conn.executeUpdate(requete);
            ok=true;
        } catch (SQLException ex) {
            Logger.getLogger(Docteur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }
}
