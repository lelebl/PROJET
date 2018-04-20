/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/*
 * 
 * Librairies importées
 */
import Modele.BDD.Chambre;
import Modele.BDD.Docteur;
import Modele.BDD.Hospitalisation;
import Modele.BDD.Infirmier;
import Modele.BDD.Maladie;
import Modele.BDD.Service;
import Modele.BDD.Soigne;
import java.sql.*;
import java.util.ArrayList;

/**
 * 
 * Connexion a votre BDD locale ou à distance sur le serveur de l'ECE via le tunnel SSH
 * 
 * @author segado
 */
public class Connexion {

    /**
     * Attributs prives : connexion JDBC, statement, ordre requete et resultat
     * requete
     */
    private Connection conn;
    private Statement stmt;
    private ResultSet rset;
    private ResultSetMetaData rsetMeta;
    /**
     * ArrayList public pour les tables
     */
    public ArrayList<String> tables = new ArrayList<>();
    /**
     * ArrayList public pour les requêtes de sélection
     */
    public ArrayList<String> requetes = new ArrayList<>();
    /**
     * ArrayList public pour les requêtes de MAJ
     */
    public ArrayList<String> requetesMaj = new ArrayList<>();

    /**
     * Constructeur avec 3 paramètres : nom, login et password de la BDD locale
     *
     * @param nameDatabase
     * @param loginDatabase
     * @param passwordDatabase
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public Connexion(String nameDatabase, String loginDatabase, String passwordDatabase) throws SQLException, ClassNotFoundException {
        // chargement driver "com.mysql.jdbc.Driver"
        Class.forName("com.mysql.jdbc.Driver");

        // url de connexion "jdbc:mysql://localhost:3305/usernameECE"
        String urlDatabase = "jdbc:mysql://localhost/" + nameDatabase;

        //création d'une connexion JDBC à la base 
        conn = DriverManager.getConnection(urlDatabase, loginDatabase, passwordDatabase);

        // création d'un ordre SQL (statement)
        stmt = conn.createStatement();
    }

    /**
     * Constructeur avec 4 paramètres : username et password ECE, login et
     * password de la BDD à distance sur le serveur de l'ECE
     * @param usernameECE
     * @param passwordECE
     * @param loginDatabase
     * @param passwordDatabase
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public Connexion(String usernameECE, String passwordECE, String loginDatabase, String passwordDatabase) throws SQLException, ClassNotFoundException {
        // chargement driver "com.mysql.jdbc.Driver"
        Class.forName("com.mysql.jdbc.Driver");

        // Connexion via le tunnel SSH avec le username et le password ECE
        SSHTunnel ssh = new SSHTunnel(usernameECE, passwordECE);

        if (ssh.connect()) {
            System.out.println("Connexion reussie");

            // url de connexion "jdbc:mysql://localhost:3305/usernameECE"
            String urlDatabase = "jdbc:mysql://localhost:3305/" + usernameECE;

            //création d'une connexion JDBC à la base
            conn = DriverManager.getConnection(urlDatabase, loginDatabase, passwordDatabase);

            // création d'un ordre SQL (statement)
            stmt = conn.createStatement();

        }
    }

    /**
     * Méthode qui ajoute la table en parametre dans son ArrayList
     *
     * @param table
     */
    public void ajouterTable(String table) {
        tables.add(table);
    }

    /**
     * Méthode qui ajoute la requete de selection en parametre dans son
     * ArrayList
     *
     * @param requete
     */
    public void ajouterRequete(String requete) {
        requetes.add(requete);
    }

    /**
     * Méthode qui ajoute la requete de MAJ en parametre dans son
     * ArrayList
     *
     * @param requete
     */
    public void ajouterRequeteMaj(String requete) {
        requetesMaj.add(requete);
    }

    /**
     * Méthode qui retourne l'ArrayList des champs de la table en parametre
     *
     * @param table
     * @return
     * @throws java.sql.SQLException
     */
    public ArrayList remplirChampsTable(String table) throws SQLException {
        // récupération de l'ordre de la requete
        rset = stmt.executeQuery("select * from " + table);

        // récupération du résultat de l'ordre
        rsetMeta = rset.getMetaData();

        // calcul du nombre de colonnes du resultat
        int nbColonne = rsetMeta.getColumnCount();

        // creation d'une ArrayList de String
        ArrayList<String> liste;
        liste = new ArrayList<>();
        String champs = "";
        // Ajouter tous les champs du resultat dans l'ArrayList
        for (int i = 0; i < nbColonne; i++) {
            champs = champs + " " + rsetMeta.getColumnLabel(i + 1);
        }

        // ajouter un "\n" à la ligne des champs
        champs = champs + "\n";

        // ajouter les champs de la ligne dans l'ArrayList
        liste.add(champs);

        // Retourner l'ArrayList
        return liste;
    }

    /**
     * Methode qui retourne l'ArrayList des champs de la requete en parametre
     * @param requete
     * @return 
     * @throws java.sql.SQLException
     */
    public ArrayList remplirChampsRequete(String requete) throws SQLException {
        // récupération de l'ordre de la requete
        rset = stmt.executeQuery(requete);

        // récupération du résultat de l'ordre
        rsetMeta = rset.getMetaData();

        // calcul du nombre de colonnes du resultat
        int nbColonne = rsetMeta.getColumnCount();

        // creation d'une ArrayList de String
        ArrayList<String> liste;
        liste = new ArrayList<String>();

        // tant qu'il reste une ligne 
        while (rset.next()) {
            String champs;
            champs = rset.getString(1); // ajouter premier champ

            // Concatener les champs de la ligne separes par ,
            for (int i = 1; i < nbColonne; i++) {
                champs = champs + "," + rset.getString(i + 1);
            }

            // ajouter un "\n" à la ligne des champs
            champs = champs + "\n";

            // ajouter les champs de la ligne dans l'ArrayList
            liste.add(champs);
        }

        // Retourner l'ArrayList
        return liste;
    }

    /**
     * Méthode qui execute une requete de MAJ en parametre
     * @param requeteMaj
     * @throws java.sql.SQLException
     */
    public void executeUpdate(String requeteMaj) throws SQLException {
        stmt.executeUpdate(requeteMaj);
    }
    
    /**
     * METHODES QUI RETOURNE DES TABLEAUX D'OBJET DES TABLES
     * @param c connexion
     * @return objects
     * @throws java.sql.SQLException
     */
    
    
    public ArrayList<Hospitalisation> getHospitalisation(Connexion c)throws SQLException{
        // rÃ©cupÃ©ration de l'ordre de la requete
        rset = stmt.executeQuery("select * from hospitalisation");

        // rÃ©cupÃ©ration du rÃ©sultat de l'ordre
        rsetMeta = rset.getMetaData();
        
        // calcul du nombre de colonnes du resultat
        int nbColonne = rsetMeta.getColumnCount();
        
        // création du tableau
        ArrayList<Hospitalisation> hosp=new ArrayList<>();
        
        rset.last();
        int nbr_instance=rset.getRow();
        rset.first();
        String code="";
        int no=0;
        int no_chambre=0;
        int lit=0;
        int n=0;
        while(rset.next()){
            if(n==0){
                rset.first();
            }
            for(int i=nbColonne;i>0;i--){//on parcour les colonne
                if(rsetMeta.getColumnLabel(i).contains("code_service")){
                    if(rset.getObject(i).toString()!=null){
                        code=(rset.getObject(i).toString());
                    }
                    else code="";
                }
                else if(rsetMeta.getColumnLabel(i).contains("numero_malade")){
                    if(rset.getObject(i).toString()!=null){
                        no=Integer.parseInt(rset.getObject(i).toString());
                    }
                    else no=0;
                    
                }
                else if(rsetMeta.getColumnLabel(i).contains("lit")){
                    lit=Integer.parseInt(rset.getObject(i).toString());
                }
                else if(rsetMeta.getColumnLabel(i).contains("no_chambre")){
                    if(rset.getObject(i).toString()!=null){
                        no_chambre=Integer.parseInt(rset.getObject(i).toString());
                    }
                    else no_chambre=0;
                }
            }
            hosp.add(new Hospitalisation(no,code,no_chambre,lit,c));
            n++;
        }
        
        return hosp;
    }
    public ArrayList<Infirmier> getInfirmier(Connexion c)throws SQLException{
        // rÃ©cupÃ©ration de l'ordre de la requete
        rset = stmt.executeQuery("select * from infirmier");

        // rÃ©cupÃ©ration du rÃ©sultat de l'ordre
        rsetMeta = rset.getMetaData();
        
        // calcul du nombre de colonnes du resultat
        int nbColonne = rsetMeta.getColumnCount();
        
        // création du tableau
        ArrayList<Infirmier> inf= new ArrayList<>();
        
        rset.last();
        int nbr_instance=rset.getRow();
        rset.first();
        Float salaire=0f;
        String rotation="";
        String code_service="";
        int numero=0;
        int n=0;
        while(rset.next()){
            if(n==0){
                rset.first();
            }
            for(int i=nbColonne;i>0;i--){//on parcour les colonne
                if(rsetMeta.getColumnLabel(i).contains("numero")){
                    numero=Integer.parseInt(rset.getObject(i).toString());
                }
                else if(rsetMeta.getColumnLabel(i).contains("code_service")){
                    if(rset.getObject(i).toString()!=null){
                        code_service=(rset.getObject(i).toString());
                    }
                    else code_service="";
                }
                else if(rsetMeta.getColumnLabel(i).contains("rotation")){
                    rotation=(rset.getObject(i).toString());
                }
                else if(rsetMeta.getColumnLabel(i).contains("salaire")){
                    salaire=Float.parseFloat(rset.getObject(i).toString());
                }
            }
            inf.add(new Infirmier(numero,code_service,rotation,salaire,c));
            n++;
        }
        
        return inf;
    }
    
    
    public ArrayList<Service> getService(Connexion c)throws SQLException{
        // rÃ©cupÃ©ration de l'ordre de la requete
        rset = stmt.executeQuery("select * from service");

        // rÃ©cupÃ©ration du rÃ©sultat de l'ordre
        rsetMeta = rset.getMetaData();
        
        // calcul du nombre de colonnes du resultat
        int nbColonne = rsetMeta.getColumnCount();
        
        // création du tableau
        ArrayList<Service> ser=new ArrayList<>();
        
        rset.last();
        int nbr_instance=rset.getRow();
        rset.first();
        char batiment=0;
        int directeur=0;
        String nom="";
        String code="";
        int n=0;
        System.out.println("SERVICE:");
        while(rset.next()){
            if(n==0){
                rset.first();
            }
            for(int i=nbColonne;i>0;i--){//on parcour les colonne
                
                if(rsetMeta.getColumnLabel(i).contains("code")){
                    code=(rset.getObject(i).toString());
                }
                else if(rsetMeta.getColumnLabel(i).contains("nom")){
                    nom=(rset.getObject(i).toString());
                }
                else if(rsetMeta.getColumnLabel(i).contains("batiment")){
                    String s=(rset.getObject(i).toString());
                    batiment=s.charAt(0);
                }
                else if(rsetMeta.getColumnLabel(i).contains("directeur")){
                    directeur=Integer.parseInt(rset.getObject(i).toString());
                }
            }
            ser.add(new Service(code,nom,batiment,directeur,c));
            n++;
        }
        
        return ser;
    }
    
    public ArrayList<Soigne> getSoigne(Connexion c)throws SQLException{
        // rÃ©cupÃ©ration de l'ordre de la requete
        rset = stmt.executeQuery("select * from soigne");

        // rÃ©cupÃ©ration du rÃ©sultat de l'ordre
        rsetMeta = rset.getMetaData();
        
        // calcul du nombre de colonnes du resultat
        int nbColonne = rsetMeta.getColumnCount();
        
        // création du tableau
        ArrayList<Soigne> soi=new ArrayList<>();
        
        rset.last();
        int nbr_instance=rset.getRow();
        rset.first();
        int nod=0;
        int nom=0;
        int n=0;
        System.out.println("SERVICE:");
        while(rset.next()){
            if(n==0){
                rset.first();
            }
            for(int i=nbColonne;i>0;i--){//on parcour les colonne
                
                if(rsetMeta.getColumnLabel(i).contains("no_docteur")){
                    if(rset.getObject(i).toString()!=null){
                        nod=Integer.parseInt(rset.getObject(i).toString());
                    }
                    else nod=0;
                }
                else if(rsetMeta.getColumnLabel(i).contains("no_malade")){
                    if(rset.getObject(i).toString()!=null){
                        nom=Integer.parseInt(rset.getObject(i).toString());
                    }
                    else nom=0;
                }
            }
            soi.add(new Soigne(nod, nom,c));
            n++;
        }
        
        return soi;
    }
    public ArrayList<Chambre> getChambre(Connexion c)throws SQLException{
        // rÃ©cupÃ©ration de l'ordre de la requete
        rset = stmt.executeQuery("select * from chambre");

        // rÃ©cupÃ©ration du rÃ©sultat de l'ordre
        rsetMeta = rset.getMetaData();
        
        // calcul du nombre de colonnes du resultat
        int nbColonne = rsetMeta.getColumnCount();
        
        // création du tableau
        ArrayList<Chambre> chambres = new ArrayList<>();
        
        rset.last();
        int nbr_instance=rset.getRow();
        String code="";
        rset.first();
        int no=0;
        int surv=0;
        int lits=0;
        int n=0;
        while(rset.next()){
            if(n==0){
                rset.first();
            }
            for(int i=nbColonne;i>0;i--){//on parcour les colonne
                if(rsetMeta.getColumnLabel(i).contains("code_service")){
                    if(rset.getObject(i).toString()!=null){
                        code=(rset.getObject(i).toString());
                    }
                    else code="";
                }
                else if(rsetMeta.getColumnLabel(i).contains("no_chambre")){
                    if(rset.getObject(i).toString()!=null){
                        no=Integer.parseInt(rset.getObject(i).toString());
                    }
                    else no=0;
                    
                }
                else if(rsetMeta.getColumnLabel(i).contains("surveillant")){
                    if(rset.getObject(i).toString()!=null){
                        surv=Integer.parseInt(rset.getObject(i).toString());
                    }
                    else surv=0;
                    
                }
                else if(rsetMeta.getColumnLabel(i).contains("nb_lits")){
                    lits=Integer.parseInt(rset.getObject(i).toString());
                }
            }
            chambres.add(new Chambre(code,no,surv,lits,c));
            n++;
        }
        
        return chambres;
    }
    public ArrayList<Maladie> getMaladie(Connexion c)throws SQLException{
        // rÃ©cupÃ©ration de l'ordre de la requete
        rset = stmt.executeQuery("select * from chambre");

        // rÃ©cupÃ©ration du rÃ©sultat de l'ordre
        rsetMeta = rset.getMetaData();
        
        // calcul du nombre de colonnes du resultat
        int nbColonne = rsetMeta.getColumnCount();
        
        // création du tableau
        ArrayList<Maladie> mal= new ArrayList<>();
        
        rset.last();
        int nbr_instance=rset.getRow();
        String nom="";
        String localisation="";
        String specialite="";
        rset.first();
        int stade=0;
        int n=0;
        while(rset.next()){
            if(n==0){
                rset.first();
            }
            for(int i=nbColonne;i>0;i--){//on parcour les colonne
                if(rsetMeta.getColumnLabel(i).contains("localisation")){
                    localisation=(rset.getObject(i).toString());
                }
                else if(rsetMeta.getColumnLabel(i).contains("specialite")){
                    specialite=(rset.getObject(i).toString());
                }
                else if(rsetMeta.getColumnLabel(i).contains("nom")){
                    nom=(rset.getObject(i).toString());
                }
                else if(rsetMeta.getColumnLabel(i).contains("stade")){
                    stade=Integer.parseInt(rset.getObject(i).toString());
                }
            }
            mal.add(new Maladie(nom,localisation,stade,specialite,c));
            n++;
        }
        
        return mal;
    }
    public ArrayList<Docteur> getDocteur(Connexion c)throws SQLException{
        // rÃ©cupÃ©ration de l'ordre de la requete
        rset = stmt.executeQuery("select * from docteur");

        // rÃ©cupÃ©ration du rÃ©sultat de l'ordre
        rsetMeta = rset.getMetaData();
        
        // calcul du nombre de colonnes du resultat
        int nbColonne = rsetMeta.getColumnCount();
        
        // création du tableau
        ArrayList<Docteur> docteurs=new ArrayList<>();
        
        rset.last();
        int nbr_instance=rset.getRow();
        String spe="";
        rset.first();
        int no=0;
        int n=0;
        while(rset.next()){
            if(n==0){
                rset.first();
            }
            for(int i=nbColonne;i>0;i--){//on parcour les colonne
                if(rsetMeta.getColumnLabel(i).contains("specialite")){
                    spe=(rset.getObject(i).toString());
                }
                else if(rsetMeta.getColumnLabel(i).contains("numero")){
                    no=Integer.parseInt(rset.getObject(i).toString());
                }
            }
            docteurs.add(new Docteur(no,spe,c));
            n++;
        }
        
        return docteurs;
    }
    
}
