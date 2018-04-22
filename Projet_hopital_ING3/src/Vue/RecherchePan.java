/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Modele.BDD.Database;
import Vue.Reporting.ReportingPan;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JComboBox;
import javax.swing.JLabel;

/**
 *
 *  @author Emmanuelle THIROLOIX & Léa BLANCHARD
 */
public class RecherchePan extends MyPanel{
    /**
     * ATTRIBUTES
     */
    private Database myData; //notre connexion a la BDD
    private JComboBox what;
    private Object[] liste= new Object[10];
    
    /**
     * CONSTRUCTORS
     */
    public RecherchePan(){
        super(false,"Recherche");
        
        // Ajout de la combobox
        liste[0]="Avez-vous une question?";
        liste[1]="Médecins n'ayant aucun malade à soigner";
        liste[2]="Prénom et nom des malades affiliés a la mutuelle MAAF";
        liste[3]="Prénom et nom des infirmières de nuit";
        liste[4]="Info directeur de chaque service";
        liste[5]="Moyenne des salaires de infirmiers par service";
        liste[6]="Maladie en fonction du stade";
        liste[7]="Rapport entre le nombre d'infirmiers et de malades dans un service";
        liste[8]="Moyenne des salaires en fonction de la rotation des infirmiers";
        liste[9]="Nombre d'hospitalisation par batiment";
        what= new JComboBox(liste);
        what.setLocation(400,30);
        what.setFont(new Font("Times New Roman", Font.BOLD,26));
        what.setSize(new Dimension(1000, 50));
        what.setBackground(new Color(255,255,255));
        what.addItemListener(new ItemQuestionListener());
        this.add(what);
        
        
    }
    
    /**
     * Reponses
     */
    public String[][] getReponse(int indice){
        String[][] reponse;
        switch (indice){
            case 1:
                reponse=this.rep0();
                break;
            case 2:
                reponse=this.rep1();
                break;
            case 3:
                reponse=this.rep2();
                break;
            case 4:
                reponse=this.rep3();
                break;
            case 5:
                reponse=this.rep4();
                break;
            case 6:
                reponse=this.rep5();
                break;
            case 7:
                reponse=this.rep6();
                break;
            case 8:
                reponse=this.rep7();
                break;
            default:
                reponse=this.rep8();
                break;
                
        }
       return reponse;       
    }
    public String[][] rep1(){
     String[][] rep1;
        
        ArrayList<String> nom= new ArrayList<>();
        ArrayList<String> prenom= new ArrayList<>();
        int nbr=0;
        for(int i=0; i<myData.malades.size(); i++){
            if(myData.malades.get(i).mutuelle.equals("MAAF")){
                nom.add(myData.malades.get(i).nom);
                prenom.add(myData.malades.get(i).prenom);
                nbr++;
            }
        }
        
        rep1= new String[nbr+1][2];
        rep1[0][0]="Nom";
        rep1[0][1]="Prénom";
        
        for(int i=0; i<nbr;i++){
            rep1[i+1][0]=nom.get(i);
            rep1[i+1][1]=prenom.get(i);
        }
        
        return rep1;
    }
    public String[][]  rep2(){
        String[][] rep0;
        ArrayList<Integer> no_inf= new ArrayList<>();
        for(int i=0; i<myData.infirmiers.size(); i++){
            if(myData.infirmiers.get(i).rotation.equals("NUIT"))
            {
                no_inf.add(myData.infirmiers.get(i).numero);
            }
        }
       
        
        String[] nom= new String[no_inf.size()];
        String[] prenom= new String[no_inf.size()];
        for(int i=0; i<myData.employes.size(); i++){
            for(int j=0;  j<no_inf.size(); j++){
                if(myData.employes.get(i).numero==no_inf.get(j)){
                    nom[j]=myData.employes.get(i).nom;
                    prenom[j]=myData.employes.get(i).prenom;
                }
            }
        }
        
        rep0= new String[no_inf.size()+1][3];
        rep0[0][0]="Numéro";
        rep0[0][1]="Nom";
        rep0[0][2]="Prénom";
        
        for(int i=0; i<no_inf.size();i++){
            rep0[i+1][0]=String.valueOf(no_inf.get(i));
            rep0[i+1][1]=nom[i];
            rep0[i+1][2]=prenom[i];
        }
        
        return rep0;
    }
    public String[][]  rep3(){
        String[][] rep0;
        ArrayList<Integer> no_dirr= new ArrayList<>();
        String[] service= new String[myData.services.size()];
        for(int i=0; i<myData.services.size(); i++){
                no_dirr.add(myData.services.get(i).directeur);
                service[i]=myData.services.get(i).code;
        }
        String[] nom= new String[no_dirr.size()];
        String[] prenom= new String[no_dirr.size()];
        String[] numero= new String[no_dirr.size()];
        String[] adresse= new String[no_dirr.size()];
        String[] tel= new String[no_dirr.size()];
        for(int i=0; i<myData.employes.size(); i++){
            for(int j=0;  j<no_dirr.size(); j++){
                if(myData.employes.get(i).numero==no_dirr.get(j)){
                    nom[j]=myData.employes.get(i).nom;
                    prenom[j]=myData.employes.get(i).prenom;
                    numero[j]=String.valueOf(myData.employes.get(i).numero);
                    adresse[j]=myData.employes.get(i).adresse;
                    tel[j]=myData.employes.get(i).tel;
                }
            }
        }
        
        rep0= new String[no_dirr.size()+1][6];
        rep0[0][0]="Service";
        rep0[0][1]="Numéro";
        rep0[0][2]="Nom";
        rep0[0][3]="Prénom";
        rep0[0][5]="Adresse";
        rep0[0][4]="Tel";
        
        for(int i=0; i<no_dirr.size();i++){
            rep0[i+1][0]=service[i];
            rep0[i+1][1]=numero[i];
            rep0[i+1][2]=nom[i];
            rep0[i+1][3]=prenom[i];
            rep0[i+1][5]=adresse[i];
            rep0[i+1][4]=tel[i];
        }
        
        return rep0;
    }
    public String[][]  rep4(){
        String[][] rep0;
        float[] tot= new float[3];
        float[] deno= new float[3];
        for(int i=0; i<3; i++){
            deno[i]=0;
        }
        for(int i=0; i<myData.infirmiers.size(); i++){
            if(myData.infirmiers.get(i).code_service.equals("REA"))
            {
                tot[0]=tot[0]+myData.infirmiers.get(i).salaire;
                deno[0]=deno[0]+1;
            }
            else if(myData.infirmiers.get(i).code_service.equals("CHG"))
            {
                tot[1]=tot[1]+myData.infirmiers.get(i).salaire;
                deno[1]=deno[1]+1;
            }
            else if(myData.infirmiers.get(i).code_service.equals("CAR"))
            {
                tot[2]=tot[2]+myData.infirmiers.get(i).salaire;
                deno[2]=deno[2]+1;
            }
        }
       float[] moyenne= new float[3];
       for(int i=0; i<3; i++){
           moyenne[i]=tot[i]/deno[i];
       }
        
        rep0= new String[4][2];
        rep0[0][0]="Service";
        rep0[0][1]="Salaire moyen";
        rep0[1][0]="REA";
        rep0[2][0]="CHG";
        rep0[3][0]="CAR";
        for(int i=0; i<3;i++){
            rep0[i+1][1]=String.valueOf(moyenne[i]);
        }
        
        return rep0;
    }
    public String[][]  rep5(){
        String[][] rep0;
        ArrayList<String> nom1= new ArrayList<>();
        ArrayList<String> nom2= new ArrayList<>();
        ArrayList<String> nom3= new ArrayList<>();
        ArrayList<String> nom4= new ArrayList<>();
        ArrayList<String> nom5= new ArrayList<>();
        for(int i=0; i<myData.maladies.size(); i++){
            if(myData.maladies.get(i).stade==1)
            {
                nom1.add(myData.maladies.get(i).nom);
            }
            else if(myData.maladies.get(i).stade==2)
            {
               nom2.add(myData.maladies.get(i).nom);;
            }
            else if(myData.maladies.get(i).stade==3)
            {
                nom3.add(myData.maladies.get(i).nom);
            }
            else if(myData.maladies.get(i).stade==4)
            {
                nom4.add(myData.maladies.get(i).nom);
            }
            else if(myData.maladies.get(i).stade==5)
            {
                nom5.add(myData.maladies.get(i).nom);
            }
        }
        int max=0;
        if(max<nom1.size()){
            max=nom1.size();
        }
        if(max<nom2.size()){
            max=nom2.size();
        }
        if(max<nom3.size()){
            max=nom3.size();
        }
        if(max<nom4.size()){
            max=nom4.size();
        }
        if(max<nom5.size()){
            max=nom5.size();
        }
        
        rep0= new String[max+1][5];
        rep0[0][0]="STADE 1";
        rep0[0][1]="STADE 2";
        rep0[0][2]="STADE 3";
        rep0[0][3]="STADE 4";
        rep0[0][4]="STADE 5";
        for(int i=0; i<nom1.size(); i++){
            rep0[i+1][0]=nom1.get(i);
        }
        for(int i=0; i<nom2.size(); i++){
            rep0[i+1][1]=nom2.get(i);
        }
        for(int i=0; i<nom3.size(); i++){
            rep0[i+1][2]=nom3.get(i);
        }
        for(int i=0; i<nom4.size(); i++){
            rep0[i+1][3]=nom4.get(i);
        }
        for(int i=0; i<nom5.size(); i++){
            rep0[i+1][4]=nom5.get(i);
        }
        
        return rep0;
    }
    public String[][]  rep6(){
        // Rap entre le nbr d'infirmière et de malade par service:
        // 1: récupérer tous les services
        // 2: pour chaque service récupérer le nbr d'infirmiers
        // 3: pour chaque service récupérer le nbr de malade
        
        String[][] rep;
        String[] services= new String[myData.services.size()];
        String[] codes= new String[myData.services.size()];
        for(int i=0; i<myData.services.size(); i++){
            services[i]=myData.services.get(i).nom;
            codes[i]=myData.services.get(i).code;
        }
        
        float[] nbr_infirmiers= new float[services.length];
        for(int i=0; i<codes.length; i++){
            for(int j=0; j<myData.infirmiers.size(); j++){
                if(myData.infirmiers.get(j).code_service.equals(codes[i])){
                    nbr_infirmiers[i]=nbr_infirmiers[i]+1;
                }
            }
        }
        
        float[] nbr_malades= new float[services.length];
        for(int i=0; i<codes.length; i++){
            for(int j=0; j<myData.hospitalisations.size(); j++){
                if(myData.hospitalisations.get(j).code_service.equals(codes[i])){
                    nbr_malades[i]=nbr_malades[i]+1;
                }
            }
        }
        
        //On fait le rapport:
        float[] rapport= new float[services.length];
        for(int i=0; i<services.length; i++){
            rapport[i]=nbr_malades[i]/nbr_infirmiers[i];
        }
        
        rep= new String[services.length+1][2];
        rep[0][0]="Service";
        rep[0][1]="Rapport";
        
        for(int i=0; i<services.length; i++){
            rep[i+1][0]=services[i];
            rep[i+1][1]=String.valueOf(rapport[i]);
        }
        
        return rep;
    }
    public String[][]  rep8(){
        // Nombre d'hospitalisation par batiment:
        // 1: on récupère les (service)
        // 2: on compte le nbr d'hospitalisation par service
        // 3: on récupère les batiments
        // 4: on vérifie quel service est dans quel batiment
        
        String[][] rep;
        String[] codes= new String[myData.services.size()];
        for(int i=0; i<myData.services.size(); i++){
            codes[i]=myData.services.get(i).code;
        }
        
        int[] nbr_hospi_serv= new int[codes.length];
        for(int i=0; i<codes.length; i++){
            nbr_hospi_serv[i]=0;
            for(int j=0; j<myData.hospitalisations.size();j++){
                if(myData.hospitalisations.get(j).code_service.equals(codes[i])){
                    nbr_hospi_serv[i]=nbr_hospi_serv[i]+1;
                }
            }
        }
        
        ArrayList<String> baptiments= new ArrayList<>();
        for(int i=0; i<codes.length; i++){
            baptiments.add(String.valueOf(myData.services.get(i).batiment));
        }
        Set<String> bapt= new HashSet();
        bapt.addAll(baptiments);
        Iterator<String> it= bapt.iterator();
        ArrayList<String> b= new ArrayList<>();
        while(it.hasNext()){
            b.add(it.next());
        }
        int[] nbr_hospi_bap= new int [b.size()];
        
        for(int i=0; i<b.size();i++){
            for(int j=0; j<codes.length; j++){
                if(baptiments.get(j).equals(b.get(i))){
                    nbr_hospi_bap[i]=nbr_hospi_bap[i]+nbr_hospi_serv[j];
                }
            }
        }
        
        rep= new String[b.size()+1][2];
        rep[0][0]="Batiments";
        rep[0][1]="Nbr Hospi";
        for(int i=0; i<b.size(); i++){
            rep[i+1][0]=b.get(i);
            rep[i+1][1]=String.valueOf(nbr_hospi_bap[i]);
        }
        
        return rep;
    }
    public String[][]  rep7(){
        String[][] rep;
        //Moyenne des salaires en fonction de la rotation des infirmiers
        // 1: on réupère dans un tableau la somme de tout les salaires du jour puis de la nuit
        // 2: on vérifie cbm d'infirmier il y a dejour et cbm de nuit
        // 3: on divise
        float[] total= new float[2];
        float[] deno= new float[2];
        for(int i=0; i<myData.infirmiers.size(); i++){
            if(myData.infirmiers.get(i).rotation.equals("JOUR")){
                total[0]=total[0]+myData.infirmiers.get(i).salaire;
                deno[0]=deno[0]+1;
            }
            else if(myData.infirmiers.get(i).rotation.equals("NUIT")){
                total[1]=total[1]+myData.infirmiers.get(i).salaire;
                deno[1]=deno[1]+1;
            }
        }
        
        float[] moyenne= new float[2];
        moyenne[0]=total[0]/deno[0];
        moyenne[1]=total[1]/deno[1];
        
        rep= new String[2+1][2];
        rep[0][0]="Rotation";
        rep[0][1]="Moyenne Sal";
        rep[1][0]="JOUR";
        rep[2][0]="NUIT";
        rep[1][1]=String.valueOf(moyenne[0]);
        rep[2][1]=String.valueOf(moyenne[1]);
        
        return rep;
    }
    public String[][] rep0(){
        String[][] rep0;
        ArrayList<Integer> no_docteur= new ArrayList<>();
        for(int i=0; i<myData.docteurs.size(); i++){
            no_docteur.add(myData.docteurs.get(i).numero);
        }
        
        
            for(int j=0; j<myData.soignes.size();  j++){
                for(int i=0; i<no_docteur.size(); i++){
                if(myData.soignes.get(j).no_docteur==no_docteur.get(i)){
                    no_docteur.remove(i);
                }
                }
            }
        
        
        ArrayList<String> specialite=new ArrayList<>();
        for(int i=0; i<no_docteur.size(); i++){
            specialite.add(myData.docteurs.get(i).specialite);
        }
        
        String[] nom= new String[no_docteur.size()];
        String[] prenom= new String[no_docteur.size()];
        for(int i=0; i<myData.employes.size(); i++){
            for(int j=0;  j<no_docteur.size(); j++){
                if(myData.employes.get(i).numero==no_docteur.get(j)){
                    nom[j]=myData.employes.get(i).nom;
                    prenom[j]=myData.employes.get(i).prenom;
                }
            }
        }
        
        rep0= new String[no_docteur.size()+1][4];
        rep0[0][0]="Numéro";
        rep0[0][1]="Nom";
        rep0[0][2]="Prénom";
        rep0[0][3]="Spécialité";
        
        for(int i=0; i<no_docteur.size();i++){
            rep0[i+1][0]=String.valueOf(no_docteur.get(i));
            rep0[i+1][1]=nom[i];
            rep0[i+1][2]=prenom[i];
            rep0[i+1][3]=specialite.get(i);
        }
        
        return rep0;
    }
    
    public void afficheRep(int indice){
        this.removeAll();
        this.add(what);
        if(indice!=0){
        
        String[][] rep=this.getReponse(indice);
        int i=rep.length;
        int j=rep[0].length;
        
        int x=100;
        int y=100;
        
        JLabel[][] labs;
        labs=new JLabel[i][j];
        for(int a=0; a<i; a++){
            for(int b=0; b<j; b++){
                 labs[a][b]=new JLabel();
                if(indice==4 && b==5){
                    labs[a][b].setSize(400,40);
                }
                else if(indice==6){
                    labs[a][b].setSize(400,40);
                }
                else if(indice==7){
                    labs[a][b].setSize(400,40);
                }
                else{
                    labs[a][b].setSize(200,40);
                }
                labs[a][b].setText(rep[a][b]);
                labs[a][b].setFont(new Font("Times New Roman", Font.BOLD,20));
                labs[a][b].setLocation(x,y);
                this.add(labs[a][b]);
                if(indice==4){
                    x=x+250;
                }
                else if(indice==7){
                    x=x+400;
                }
                else{
                    x=x+300;
                }
            }
            y=y+50;
            x=100;
        }
        }
        this.repaint();
    }
    
    /**
     * SETTERS
     */
    public void setData(Database l){
        myData=l;
    }
    
    /**
     * LISTENER
     */
    class ItemQuestionListener implements ItemListener{
        @Override
        public void itemStateChanged(ItemEvent event) {
            
           if (event.getStateChange() == ItemEvent.SELECTED) {
              Object item = event.getItem();
              for(int i=0; i<10; i++){
                  if(item==liste[i]){
                      afficheRep(i);
                  }
              }
           }
        }   
    }
    
}
