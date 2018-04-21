/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Vue.*;
import Controleur.*;
import Modele.*;
import Modele.BDD.Database;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class Projet_hopital_ING3 {

    /**
     * ATTRIBUTES
     */
    private Fenetre fen; //fenetre graphique
    private String passwordDatabase;
    private String loginDatabase;
    private String nameDatabase;
    private String userNameECE;
    private String passwordECE;
    private Database myData;
    
    private Button valider[];
    
    /**
     * CONSTRUCTORS
     */
    Projet_hopital_ING3(){
        fen= new Fenetre();
        
        valider= new Button[2];
        valider[0]=fen.getAccueil().getPanCoECE().getValider();
        valider[1]=fen.getAccueil().getPanCoLocal().getValider();
        valider[0].addActionListener(new ValiderListener());
        valider[1].addActionListener(new ValiderListener());
    }
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Projet_hopital_ING3 run= new Projet_hopital_ING3();
        
    }

    class ValiderListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
           if(e.getSource()==valider[0]){// connextion ECE
                userNameECE=fen.getAccueil().getPanCoECE().getUserNameECE();
                passwordECE=fen.getAccueil().getPanCoECE().getPasswordECE();
                loginDatabase=fen.getAccueil().getPanCoECE().getLoginDatabase();
                passwordDatabase=fen.getAccueil().getPanCoECE().getPasswordDatabase();
                try{
                       //fen.setC(new Connexion("et152116","emmaTH-96", "et152116-rw","LXUQjQxm"));
                       myData= new Database(loginDatabase,passwordDatabase,new Connexion(userNameECE, passwordECE, loginDatabase, passwordDatabase));
                       if(myData.getC()!=null){ //si la connexion a réussi
                           System.out.println("Connexion ECE réussie");
                            setFenetres();
                        }
                        else{// echec connection$
                           myData=null;
                            System.out.println("ECHEC CONNEXION");
                            fen.getAccueil().InitPanCo();
                        }
                   }catch(SQLException s){
                       System.out.println("SQLException s1: "+s);
                   }catch(ClassNotFoundException s2){
                       System.out.println("ClassNotFoundException s2: "+s2);
                   }
            
            }
           
            else if(e.getSource()==valider[1]){// connexion local
                // on récupère les données
                loginDatabase=fen.getAccueil().getPanCoLocal().getLoginDatabase();
                passwordDatabase=fen.getAccueil().getPanCoLocal().getPasswordDatabase();
                nameDatabase=fen.getAccueil().getPanCoLocal().getNameDatabase();
                try{
                    //myData= new Database(loginDatabase,passwordDatabase,new Connexion(nameDatabase,loginDatabase, passwordDatabase))
                    myData= new Database(loginDatabase,passwordDatabase,new Connexion("hopital","root",""));
                    if(myData.getC()!=null){//si la connexion a réussi
                            System.out.println("Connexion local réussie");
                            setFenetres();
                    }
                    else{// echec connection
                        myData=null;
                        System.out.println("ECHEC CONNEXION");
                        fen.getAccueil().InitPanCo();
                    }
                }catch(SQLException a){
                    System.out.println("SQLException: "+a);
                }catch(ClassNotFoundException a2){
                    System.out.println("ClassNotFoundException: "+a2);
                }
            }
        }
        
    }
    
    public void setFenetres(){
        fen.setData(myData);
        fen.setPan(fen.getReport(),"");
    }
    
}
