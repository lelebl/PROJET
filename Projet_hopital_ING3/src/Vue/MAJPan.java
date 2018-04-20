
package Vue;

import Modele.BDD.Database;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;


public class MAJPan extends MyPanel{
    /**
     * ATTRIBUTES
     */
    public String currentType=""; // supprimer, ajouter, modifier
    public String currentTable=""; // table qu'on met à jour
    private Database myData; //notre connexion a la BDD
    private JButton boutons[]; // liste des tables de la BDD
    private JPanel menuTable;
    private JPanel pan;
    private JLabel titre;
    
    private JComboBox listSupp;
    
    private JLabel[] labelAjout;
    private JTextField[] textAjout;
    private String[] saisie;
    private JOptionPane errorInfo = new JOptionPane();
    
    private JButton valider;
    
    
    /**
     * CONSTRUCTORS
     */
    public MAJPan(){
        super(false,"MAJ");
        errorInfo.setSize(300,100);
        errorInfo.setFont(new Font("Times New Roman", Font.BOLD,20));
        // Déclaration des panels
        menuTable=new JPanel();
        pan= new JPanel();
        LineBorder roundedLineBorder = new LineBorder(new Color(34, 66, 124), 10, true); // On crée la bordure des panels
        menuTable.setBackground(new Color(255,255,255,150));
        menuTable.setBorder(roundedLineBorder);
        menuTable.setLayout(null);
        pan.setBackground(new Color(255,255,255,150));
        pan.setPreferredSize(new Dimension(this.getHeight()-100,this.getWidth()/5));
        pan.setBorder(roundedLineBorder);
        pan.setLayout(null);
        
        // Positionnement des labels:
        this.setLayout(new GridBagLayout()); 
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH; //on occupe tout l'espace disponible
	gc.ipady = gc.anchor = GridBagConstraints.CENTER; // on centre les composants
        gc.weightx = 2; // 2 case en abscisse 
	gc.weighty = 2; // 2 case en ordonnée
        gc.insets = new Insets(50, 50, 50, 50); //on applique des marges
        gc.gridx = 0;
        gc.gridy = 1;
        this.add(menuTable, gc);
        gc.gridx=1;
        gc.gridy=1;
        this.add(pan, gc);
        
        boutons=null; //tant que l'on a pas de connexion on ne peut pas faire les boutons
        myData=null; //car pas encore de connexion
        this.setVisible(true);
    }
    /**
     * SETTERS
     */
    public void setBoutons(){
        
        titre=new JLabel();
        titre.setSize(500, 38);
        titre.setLocation(menuTable.getX()+75,menuTable.getY()+30);
        titre.setFont(new Font("Times New Roman", Font.BOLD,38));
        titre.setForeground(new Color(3,34,76));
        titre.setText("");
        menuTable.add(titre);
        
        boutons= new JButton[myData.getNbrTables()];
        int posx=menuTable.getX()+ 70;
        int posy=menuTable.getY()+100;
        
        TableListener mylistener=new TableListener();
        for (int i=0;i<myData.getNbrTables();i++){ //on parcourt les différentes tables de la BDD
            boutons[i]= new JButton(myData.getNomTable()[i]);
            boutons[i].setBackground(new Color(255,255,255));
            boutons[i].setForeground(new Color(3,34,76));
            boutons[i].setFont(new Font("Times New Roman", Font.BOLD,20));
            boutons[i].setSize(200,50);
            boutons[i].setLocation(posx,posy);
            posy=posy+70;
            boutons[i].addActionListener(mylistener);
            menuTable.add(boutons[i]);
        }
        
    }
    public void setData(Database a){
        myData=a;
        this.setBoutons();
    }
    public void setTitle(String t){
        currentType=t;
        titre.setText(currentType);
    }
    /**
     * SUPPRIMER
     */
    public void setPaneSupprimer(){
        pan.removeAll();
        Object[] liste= new Object[0];
        
        if(currentTable.equals("chambre")){
            liste= new Object[myData.chambres.size()];
            for(int i=0;i<myData.chambres.size();i++){
                liste[i]=myData.chambres.get(i).toString();
            }
        }
        else if(currentTable.equals("docteur")){
            liste= new Object[myData.docteurs.size()];
            for(int i=0;i<myData.docteurs.size();i++){
                liste[i]=myData.docteurs.get(i).toString();
            }
        }
        else if(currentTable.equals("employe")){
            liste= new Object[myData.employes.size()];
            for(int i=0;i<myData.employes.size();i++){
                liste[i]=myData.employes.get(i).toString();
            }
        }
        else if(currentTable.equals("hospitalisation")){
            liste= new Object[myData.hospitalisations.size()];
            for(int i=0;i<myData.hospitalisations.size();i++){
                liste[i]=myData.hospitalisations.get(i).toString();
            }
        }
        else if(currentTable.equals("infirmier")){
            liste= new Object[myData.infirmiers.size()];
            for(int i=0;i<myData.infirmiers.size();i++){
                liste[i]=myData.infirmiers.get(i).toString();
            }
        }
        else if(currentTable.equals("malade")){
            liste= new Object[myData.malades.size()];
           for(int i=0;i<myData.malades.size();i++){
                liste[i]=myData.malades.get(i).toString();
            }
        }
        else if(currentTable.equals("maladie")){
            liste= new Object[myData.maladies.size()];
            for(int i=0;i<myData.maladies.size();i++){
                liste[i]=myData.maladies.get(i).toString();
            }
        }
        else if(currentTable.equals("service")){
            liste= new Object[myData.services.size()];
            for(int i=0;i<myData.services.size();i++){
                liste[i]=myData.services.get(i).toString();
            }
        }
        else if(currentTable.equals("soigne")){
            liste= new Object[myData.soignes.size()];
            for(int i=0;i<myData.soignes.size();i++){
                liste[i]=myData.soignes.get(i).toString();
            }
        }
        listSupp= new JComboBox(liste);
        listSupp.setLocation(pan.getX(), pan.getY()+100);
        listSupp.setFont(new Font("Times New Roman", Font.BOLD,20));
        listSupp.setSize(new Dimension(800, 40));
        listSupp.setBackground(new Color(255,255,255));
        pan.add(listSupp);
        
        valider= new JButton();
        valider.setBackground(Color.BLUE);
        valider.setFont(new Font("Times New Roman", Font.BOLD,30));
        valider.setLocation(pan.getX(),pan.getY()+500);
        valider.setSize(200,50);
        valider.addActionListener(new ButtonListener());
        valider.setText(currentType);
        pan.add(valider);
        
        this.repaint();
    }
    public void supprimer(int indice) throws SQLException{
        /**
         * Suppression dans la BDD
         */
        if(currentTable.equals("chambre")){
            boolean ok=myData.chambres.get(indice).supprimer();
            if(ok){
                System.out.println("Suppression réussie");
                errorInfo.showMessageDialog(null,"Suppression de la chambre effectuée", "Suppression", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                System.out.println("Supression échouée");
                errorInfo.showMessageDialog(null,"Suppression de la chambre échouée", "Suppression", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else if(currentTable.equals("docteur")){
            boolean ok=myData.docteurs.get(indice).supprimer();
            if(ok){
                System.out.println("Suppression réussie");
                errorInfo.showMessageDialog(null,"Suppression du docteur effectuée", "Suppression", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                System.out.println("Supression échouée");
                errorInfo.showMessageDialog(null,"Suppression du docteur échouée", "Suppression", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else if(currentTable.equals("employe")){
            boolean ok=myData.employes.get(indice).supprimer();
            if(ok){
                System.out.println("Suppression réussie");
                errorInfo.showMessageDialog(null,"Suppression de l'employé effectuée", "Suppression", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                System.out.println("Supression échouée");
                errorInfo.showMessageDialog(null,"Suppression de l'employé échouée", "Suppression", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else if(currentTable.equals("hospitalisation")){
            boolean ok=myData.hospitalisations.get(indice).supprimer();
            if(ok){
                System.out.println("Suppression réussie");
                errorInfo.showMessageDialog(null,"Suppression de l'hospitalisation effectuée", "Suppression", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                System.out.println("Supression échouée");
                errorInfo.showMessageDialog(null,"Suppression de l'hospitalisation échouée", "Suppression", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else if(currentTable.equals("infirmier")){
            boolean ok=myData.infirmiers.get(indice).supprimer();
            if(ok){
                System.out.println("Suppression réussie");
                errorInfo.showMessageDialog(null,"Suppression de l'infirmier effectuée", "Suppression", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                System.out.println("Supression échouée");
                errorInfo.showMessageDialog(null,"Suppression de l'infirmier échouée", "Suppression", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else if(currentTable.equals("malade")){
            boolean ok=myData.malades.get(indice).supprimer();
            if(ok){
                System.out.println("Suppression réussie");
                errorInfo.showMessageDialog(null,"Suppression du malade effectuée", "Suppression", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                System.out.println("Supression échouée");
                errorInfo.showMessageDialog(null,"Suppression du malade échouée", "Suppression", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else if(currentTable.equals("maladie")){
            boolean ok=myData.maladies.get(indice).supprimer();
            if(ok){
                System.out.println("Suppression réussie");
                errorInfo.showMessageDialog(null,"Suppression de la maladie effectuée", "Suppression", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                System.out.println("Supression échouée");
                errorInfo.showMessageDialog(null,"Suppression de la maladie échouée", "Suppression", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else if(currentTable.equals("service")){
            boolean ok=myData.services.get(indice).supprimer();
            if(ok){
                System.out.println("Suppression réussie");
                errorInfo.showMessageDialog(null,"Suppression du service effectuée", "Suppression", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                System.out.println("Supression échouée");
                errorInfo.showMessageDialog(null,"Suppression du service échouée", "Suppression", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else if(currentTable.equals("soigne")){
            boolean ok=myData.soignes.get(indice).supprimer();
            if(ok){
                System.out.println("Suppression réussie");
                errorInfo.showMessageDialog(null,"Suppression du soin effectuée", "Suppression", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                System.out.println("Supression échouée");
                errorInfo.showMessageDialog(null,"Suppression du soin échouée", "Suppression", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        
        /**
         * Suppression dans les table
         */
        if( currentTable!=null) { 
            System.out.println(currentTable);
            switch (currentTable) {
                case "chambre":
                    myData.chambres.remove(indice);
                    break;
                case "docteur":
                    myData.docteurs.remove(indice);
                    break;
                case "employe":
                    myData.employes.remove(indice);
                    break;
                case "hospitalisation":
                    myData.hospitalisations.remove(indice);
                    break;
                case "infirmier":
                    myData.infirmiers.remove(indice);
                    break;
                case "malade":
                    myData.malades.remove(indice);
                    break;
                case "maladie":
                    myData.maladies.remove(indice);
                    break;
                case "service":
                    myData.services.remove(indice);
                    break;
                case "soigne":
                    myData.soignes.remove(indice);
                    break;
                default:
                    break;
            }
        }
    }
    /**
     * AJOUT 
     */
    public void setPaneAjout(){
        pan.removeAll();
        
        valider= new JButton();
        valider.setBackground(Color.BLUE);
        valider.setFont(new Font("Times New Roman", Font.BOLD,30));
        valider.setLocation(pan.getX()+200,pan.getY()+500);
        valider.setSize(200,50);
        valider.addActionListener(new ButtonListener());
        valider.setText(currentType);
        pan.add(valider);
        
        int x1,x2,y1,y2;
        x1=pan.getX()+50;
        x2=pan.getX()+350;
        y1=pan.getY()+200;
        y2=y1;
                
        if(currentTable.equals("chambre")){
            
            textAjout=myData.chambres.get(0).textAjout;
            labelAjout=myData.chambres.get(0).labelAjout;
            for(int i=0; i<labelAjout.length;i++){
                labelAjout[i].setLocation(x1,y1);
                textAjout[i].setLocation(x2,y2);
                labelAjout[i].setSize(300,25);
                textAjout[i].setSize(300,25);
                pan.add(labelAjout[i]);
                pan.add(textAjout[i]);
                y1=y1+50;
                y2=y1;
            }
        }
        else if(currentTable.equals("docteur")){
            textAjout=myData.docteurs.get(0).textAjout;
            labelAjout=myData.docteurs.get(0).labelAjout;
            for(int i=0; i<labelAjout.length;i++){
                labelAjout[i].setLocation(x1,y1);
                textAjout[i].setLocation(x2,y2);
                labelAjout[i].setSize(300,25);
                textAjout[i].setSize(300,25);
                pan.add(labelAjout[i]);
                pan.add(textAjout[i]);
                y1=y1+50;
                y2=y1;
            }
        }
        else if(currentTable.equals("employe")){
            textAjout=myData.employes.get(0).textAjout;
            labelAjout=myData.employes.get(0).labelAjout;
            for(int i=0; i<labelAjout.length;i++){
                labelAjout[i].setLocation(x1,y1);
                textAjout[i].setLocation(x2,y2);
                labelAjout[i].setSize(300,25);
                textAjout[i].setSize(300,25);
                pan.add(labelAjout[i]);
                pan.add(textAjout[i]);
                y1=y1+50;
                y2=y1;
            }
        }
        else if(currentTable.equals("hospitalisation")){
            textAjout=myData.hospitalisations.get(0).textAjout;
            labelAjout=myData.hospitalisations.get(0).labelAjout;
            for(int i=0; i<labelAjout.length;i++){
                labelAjout[i].setLocation(x1,y1);
                textAjout[i].setLocation(x2,y2);
                labelAjout[i].setSize(300,25);
                textAjout[i].setSize(300,25);
                pan.add(labelAjout[i]);
                pan.add(textAjout[i]);
                y1=y1+50;
                y2=y1;
            }
        }
        else if(currentTable.equals("infirmier")){
            textAjout=myData.infirmiers.get(0).textAjout;
            labelAjout=myData.infirmiers.get(0).labelAjout;
            for(int i=0; i<labelAjout.length;i++){
                labelAjout[i].setLocation(x1,y1);
                textAjout[i].setLocation(x2,y2);
                labelAjout[i].setSize(300,25);
                textAjout[i].setSize(300,25);
                pan.add(labelAjout[i]);
                pan.add(textAjout[i]);
                y1=y1+50;
                y2=y1;
            }
        }
        else if(currentTable.equals("malade")){
            textAjout=myData.malades.get(0).textAjout;
            labelAjout=myData.malades.get(0).labelAjout;
            for(int i=0; i<labelAjout.length;i++){
                labelAjout[i].setLocation(x1,y1);
                textAjout[i].setLocation(x2,y2);
                labelAjout[i].setSize(300,25);
                textAjout[i].setSize(300,25);
                pan.add(labelAjout[i]);
                pan.add(textAjout[i]);
                y1=y1+50;
                y2=y1;
            }
        }
        else if(currentTable.equals("maladie")){
            textAjout=myData.maladies.get(0).textAjout;
            labelAjout=myData.maladies.get(0).labelAjout;
            for(int i=0; i<labelAjout.length;i++){
                labelAjout[i].setLocation(x1,y1);
                textAjout[i].setLocation(x2,y2);
                labelAjout[i].setSize(300,25);
                textAjout[i].setSize(300,25);
                pan.add(labelAjout[i]);
                pan.add(textAjout[i]);
                y1=y1+50;
                y2=y1;
            }
        }
        else if(currentTable.equals("service")){
            textAjout=myData.services.get(0).textAjout;
            labelAjout=myData.services.get(0).labelAjout;
            for(int i=0; i<labelAjout.length;i++){
                labelAjout[i].setLocation(x1,y1);
                textAjout[i].setLocation(x2,y2);
                labelAjout[i].setSize(300,25);
                textAjout[i].setSize(300,25);
                pan.add(labelAjout[i]);
                pan.add(textAjout[i]);
                y1=y1+50;
                y2=y1;
            }
        }
        else if(currentTable.equals("soigne")){
            textAjout=myData.soignes.get(0).textAjout;
            labelAjout=myData.soignes.get(0).labelAjout;
            for(int i=0; i<labelAjout.length;i++){
                labelAjout[i].setLocation(x1,y1);
                textAjout[i].setLocation(x2,y2);
                labelAjout[i].setSize(300,25);
                textAjout[i].setSize(300,25);
                pan.add(labelAjout[i]);
                pan.add(textAjout[i]);
                y1=y1+50;
                y2=y1;
            }
        }
        this.repaint();
    }
    public void ajout() throws SQLException {
        boolean champs_complets=true;
        // on vérifie que tous les champs sont completés;
        for(int i=0; i<textAjout.length;i++){
            if(textAjout[i].getText().equals("")){
                champs_complets=false;
            }
        }
        //Si tous les champs sont complet:
        if(champs_complets){
            /**
             * AJOUT DANS LA BDD
             */
            String requete="INSERT INTO `hopital`.`"+currentTable+"` (`";
            for(int i=0; i<labelAjout.length;i++){//on parcourt les labels
                requete=requete+labelAjout[i].getText()+"`";
                if(i!=textAjout.length-1){
                    requete=requete+", `";
                }
            }
            requete=requete+") VALUES ('";
            for(int i=0; i<textAjout.length;i++){//on parcourt les entrées de texte
                requete=requete+textAjout[i].getText()+"'";
                if(i!=textAjout.length-1){
                    requete=requete+", '";
                }
            }
            requete=requete+");";
            System.out.println("Vous demandez: "+requete);
            try{
                myData.conn.executeUpdate(requete);
                errorInfo.showMessageDialog(null,"Requete: "+requete, "Ajout effectué", JOptionPane.INFORMATION_MESSAGE);
            }catch(SQLException e){
                errorInfo.showMessageDialog(null,"Erreur: "+e, "Erreur d'ajout", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Impossible de faire l'ajout: "+ e);
            }


            if( currentTable!=null) { 

                switch (currentTable) {
                    case "chambre":
                        myData.chambres=myData.conn.getChambre(myData.conn);
                        break;
                    case "docteur":
                        myData.docteurs=myData.conn.getDocteur(myData.conn);
                        break;
                    case "employe":
                        myData.employes=myData.conn.getEmploye(myData.conn);
                        break;
                    case "hospitalisation":
                        myData.hospitalisations=myData.conn.getHospitalisation(myData.conn);
                        break;
                    case "infirmier":
                        myData.infirmiers=myData.conn.getInfirmier(myData.conn);
                        break;
                    case "malade":
                        myData.malades=myData.conn.getMalade(myData.conn);
                        break;
                    case "maladie":
                        myData.maladies=myData.conn.getMaladie(myData.conn);
                        break;
                    case "service":
                        myData.services=myData.conn.getService(myData.conn);
                        break;
                    case "soigne":
                        myData.soignes=myData.conn.getSoigne(myData.conn);
                        break;
                    default:
                        break;
                }
            }
        }
        else{
            errorInfo.showMessageDialog(null,"Veuillez compléter TOUS les champs", "Erreur d'ajout", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    
}
