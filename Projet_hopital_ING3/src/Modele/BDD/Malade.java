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
public class Malade implements Table {

    private Connexion conn;
    public int numero;
    public String nom;
    public String prenom;
    public String adresse;
    public String tel;
    public String mutuelle;
    public String maladie;
    public int nb_att;
    public String[] attributs={"numero", "nom", "prenom", "adresse","telephone","mutuelle","maladie"};
    public JLabel[] labelAjout;
    public JTextField[] textAjout;
    
    public Malade(int no, String n, String pre, String ad, String te, String mut, String mala, Connexion c){
        no=numero;
        nb_att=7;
        nom=n;
        prenom=pre;
        adresse=ad;
        tel=te;
        mutuelle=mut;
        maladie=mala;
        conn=c;
        this.initLabelsAjout();
    }
    
        @Override
    public String toString(){
        return ("nom: "+nom+" / prenom: "+prenom+" / adresse: "+adresse+" / tel: "+tel+" / mutuelle: "+mutuelle+" / maladie: "+maladie);
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
        String requete="UPDATE `hopital`.`malade` SET `malade`.`";
            for(int i=0; i<attribut.size();i++){
                requete=requete+attribut.get(i)+"`='"+new_attribut.get(i)+"'";
                if(i!=attribut.size()-1){
                    requete=requete+",`malade`.`";
                }
            }
            requete=requete+" WHERE CONCAT(`malade`.`numero`) = '"+numero+"';";
            
        try {
            conn.executeUpdate(requete);
            ok=true;
        }catch (SQLException ex) {
            System.out.println("ECHEC REQUETE MODIF: "+requete);
            Logger.getLogger(Malade.class.getName()).log(Level.SEVERE, null, ex);
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
            String requete="DELETE FROM `hopital`.`malade` WHERE CONCAT(`malade`.`numero`) = '"+numero+"';";
            conn.executeUpdate(requete);
            ok=true;
        } catch (SQLException ex) {
            Logger.getLogger(Chambre.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }
    
}
