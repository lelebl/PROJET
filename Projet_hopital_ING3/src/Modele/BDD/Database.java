/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele.BDD;

import Modele.Connexion;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author Emmanuelle THIROLOIX&Léa Blanchard
 */
public class Database {
    /**
     * Attributs
    */
    private int nbr_tables;
    private String login;
    private String mdp;
    private String tables[];
    public Connexion conn;
    
    /**
     * Les Tables:
     */
    public ArrayList<Chambre> chambres;
    public ArrayList<Docteur> docteurs;
    public ArrayList<Employe> employes;
    public ArrayList<Hospitalisation> hospitalisations;
    public ArrayList<Infirmier> infirmiers;
    public ArrayList<Malade> malades;
    public ArrayList<Maladie> maladies;
    public ArrayList<Service> services;
    public ArrayList<Soigne> soignes;
    
    
    /**
     * Constructeur
     */
    public Database(String l, String m, Connexion c) throws SQLException{
        login=l;
        mdp=m;
        conn=c;
        nbr_tables=c.tables.size();
        tables=new String[nbr_tables];
        for (int i=0;i<c.tables.size();i++){ //on parcourt les différentes tables de la BDD
            tables[i]= c.tables.get(i);
        }
        //Ajout des instances:
        chambres=c.getChambre(conn);
        docteurs=c.getDocteur(conn);
        employes=c.getEmploye(conn);
        hospitalisations=c.getHospitalisation(conn);
        infirmiers=c.getInfirmier(conn);
        malades=c.getMalade(conn);
        services=c.getService(conn);
        soignes=c.getSoigne(conn);
        maladies=c.getMaladie(conn);
    }
    
    /**
     * GUETTERS
     */
    public Connexion getC(){
        return conn;
    }
    public int getNbrTables(){
        return nbr_tables;
    }
    public String[] getNomTable(){
        return tables;
    }
    
}
