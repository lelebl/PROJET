/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Modele.Connexion;
import java.sql.SQLException;

/**
 *
 * @author lele
 */
public class PROJET_HOPITAL {

   /**
     * ATTRIBUTES
     */
    private Fenetre fen; //fenetre graphique
    private Connexion c; 
    private String passwordDatabase;
    private String loginDatabase;
    private String nameDatabase;
    private String userNameECE;
    private String passwordECE;
    
    /**
     * CONSTRUCTORS
     */
    PROJET_HOPITAL(){
        fen= new Fenetre();
        try{
            c = new Connexion(userNameECE, passwordECE, loginDatabase, passwordDatabase);
        }catch(SQLException e){
            System.out.println("SQLException: "+e);
        }catch(ClassNotFoundException e2){
            System.out.println("ClassNotFoundException: "+e2);
        }
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PROJET_HOPITAL run= new PROJET_HOPITAL();
    }
    
}
