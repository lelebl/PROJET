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
public class Infirmier implements Table {

    private Connexion conn;
    public int numero;
    public String code_service;
    public String rotation;
    public float salaire;
    public int nb_att;
    public String[] attributs={"numero", "code_service", "rotation", "salaire"};;
    public JTextField[] textAjout;
    public JLabel[] labelAjout;
    
    public Infirmier(int no, String code, String rota, float sal,Connexion c){
        numero=no;
        nb_att=4;
        code_service=code;
        rotation=rota;
        salaire=sal;
        conn=c;
        this.initLabelsAjout();
    }
    
        @Override
    public String toString(){
        return ("numero: "+numero+" / code_service: "+code_service+" / rotation: "+rotation+" / salaire: "+salaire);
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
        String requete="UPDATE `hopital`.`infirmier` SET `infirmier`.`";
            for(int i=0; i<attribut.size();i++){
                requete=requete+attribut.get(i)+"`='"+new_attribut.get(i)+"'";
                if(i!=attribut.size()-1){
                    requete=requete+",`infimier`.`";
                }
            }
            requete=requete+" WHERE CONCAT(`infirmier`.`numero`) = '"+numero+"';";
            
        try {
            conn.executeUpdate(requete);
            ok=true;
        }catch (SQLException ex) {
            System.out.println("ECHEC REQUETE MODIF: "+requete);
            Logger.getLogger(Infirmier.class.getName()).log(Level.SEVERE, null, ex);
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
            String requete="DELETE FROM `hopital`.`infirmier` WHERE CONCAT(`infirmier`.`numero`) = '"+numero+"';";
            conn.executeUpdate(requete);
            ok=true;
        } catch (SQLException ex) {
            Logger.getLogger(Chambre.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }
    
}
