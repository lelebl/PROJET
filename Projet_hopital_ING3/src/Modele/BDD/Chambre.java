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
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Emmanuelle THIROLOIX
 */
public class Chambre implements Table{

    private Connexion conn;
    public String code_service;
    public int no_chambre;
    public int surveillant;
    public int nb_lits;
    public int nb_att;
    public String[] attributs={"code_service", "no_chambre", "surveillant", "nb_lits"};;
    public JTextField[] textAjout;
    public JLabel[] labelAjout;
    

    /**
     * Constructeur
     */
    public Chambre(String c, int n, int s, int nb, Connexion co){
        nb_att=4;
        code_service=c;
        no_chambre=n;
        surveillant=s;
        nb_lits=nb;
        conn=co;
        this.initLabelsAjout();
    }
    
        @Override
    public String toString(){
        return ("code_service: "+code_service+" / no_chambre: "+no_chambre+" / sureveillant: "+surveillant+" / nb_lits: "+nb_lits);
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
    public Boolean supprimer() {
        Boolean ok=false;
        try {
            String requete="DELETE FROM `hopital`.`chambre` WHERE `chambre`.`code_service` = '"+code_service+"' AND CONCAT(`chambre`.`no_chambre`) = '"+no_chambre+"';";
            conn.executeUpdate(requete);
            ok=true;
        } catch (SQLException ex) {
            Logger.getLogger(Chambre.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }

    @Override
    public void ajouter(String attribut) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean modifier(ArrayList<String> attribut,ArrayList<String> new_attribut) {
        Boolean ok=false;
        try {
            String requete="UPDATE `hopital`.`chambre` SET `chambre`.`";
            for(int i=0; i<attribut.size();i++){
                requete=requete+attribut.get(i)+"`='"+new_attribut.get(i)+"'";
                if(i!=attribut.size()-1){
                    requete=requete+",`chambre`.`";
                }
            }
            requete=requete+" WHERE `chambre`.`code_service` = '"+code_service+"' AND CONCAT(`chambre`.`no_chambre`) = '"+no_chambre+"';";
            
            conn.executeUpdate(requete);
            ok=true;
        }catch (SQLException ex) {
            Logger.getLogger(Chambre.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }

    @Override
    public void setC(Connexion c) {
        conn=c;
    }
    
}
