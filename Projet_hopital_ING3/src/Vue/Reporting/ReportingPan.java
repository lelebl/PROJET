/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue.Reporting;

import Modele.BDD.Database;
import Vue.MyPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;



public class ReportingPan extends MyPanel {
    /**
     * ATTRIBUTES
     */
    private Database myData;
    private JComboBox what;
    private Object[] liste= new Object[4];
    
    private String nC0;
    private String nC1;
    private String nC2;
    private String nC3;
    
    private PieChart c0;
    private BarChart c1;
    private MultiplePieChart c2;
    private BarChart c3;
    
    private ChartPanel[] charts;
    private ChartPanel currentChart;
    
    /**
     * CONSTRUCTORS
     */
    public ReportingPan(){
        super(false,"Reporting");
        myData=null;
        this.setLayout(null);
        
        // Ajout de la combobox
        nC0="Pourcentage d'hospitalisation par service";
        nC1="Nombre de Malade et Docteur par spécialité";
        nC2="Lits disponibles par chambre";
        nC3="Rotation par service";
        
        liste[0]=nC0;
        liste[1]=nC1;
        liste[2]=nC2;
        liste[3]=nC3;
        what= new JComboBox(liste);
        what.setLocation(400,20);
        what.setFont(new Font("Times New Roman", Font.BOLD,30));
        what.setSize(new Dimension(1000, 50));
        what.setBackground(new Color(255,255,255));
        what.addItemListener(new ItemChangeListener());
        this.add(what);
        
        this.setVisible(true);
    }
    
    /**
     * INITIALISATION 
     */
    public void initCharts(){
        this.removeAll();
        what.setSelectedItem(liste[0]);
        this.add(what);
        charts= new ChartPanel[4];
        
        this.initC0();
        this.initC1();
        this.initC2();
        this.initC3();
        
        currentChart=charts[0];
        currentChart.setBackground(new Color (0,0,0,0));
        currentChart.setSize(this.getWidth()-600, this.getHeight()- 200);
        currentChart.setLocation(300,150);
        
        this.add(currentChart);
        repaint();
    }
    public void initC0(){
        // On récupère les noms des services
        int nbr_service=myData.services.size();
        String[] names= new String[nbr_service];
        String[] codes= new String[nbr_service];
        for(int i=0; i<nbr_service;i++){
            names[i]=myData.services.get(i).nom;
            codes[i]=myData.services.get(i).code;
                    
        }
        // On récupère le nombre de val de chaque service
        double[] values= new double[nbr_service];
        int nbr_values=0;
        for(int i=0; i<nbr_service;i++){
            values[i]=0;
            for(int j=0; j<myData.hospitalisations.size();j++){
                if(myData.hospitalisations.get(j).code_service.equals(codes[i])){
                    values[i]=values[i]+1;
                    nbr_values++;
                }
            }
        }
        // On transforme les values en pourcentage
        for(int i=0; i<values.length;i++){
            values[i]=(values[i]/nbr_values)*100;
        }
        
        // On crée le pie chart
        c0= new PieChart(nC0,names,values);
        charts[0]=c0.piePanel;
    }
    public void initC1(){
        // On récupère les spécialités:
        int nbr_docteur=myData.docteurs.size();
        ArrayList<String> spe=new ArrayList<>();
        for(int i=0; i<nbr_docteur; i++){
            spe.add(myData.docteurs.get(i).specialite);
        }
        // On enlève les doublons
        Set<String> s= new HashSet();
        s.addAll(spe);
        spe= new ArrayList(s);
        
        // On compte le nombre de docteur par spécialité:
        double[] values_d= new double[spe.size()];
        for(int i=0; i<spe.size();i++){
            
            values_d[i]=0;
            for(int j=0; j<myData.docteurs.size();j++){
                if(myData.docteurs.get(j).specialite.equals(spe.get(i))){
                    values_d[i]=values_d[i]+1;
                }
            }
        }
        
         // On compte le nombre de malade par spécialité
         double[] values_m= new double[spe.size()];
         for(int i=0; i<spe.size();i++){
             values_m[i]=0;
             for(int j=0; j<myData.docteurs.size();  j++){
                 if(myData.docteurs.get(j).specialite.equals(spe.get(i))){
                     int numero= myData.docteurs.get(j).numero;
                     for(int k=0; k<myData.soignes.size(); k++){ //on parcourt qui soigne qui
                         if(numero==myData.soignes.get(k).no_docteur){ // on ajoute tous ses malades
                             values_m[i]=values_m[i]+1;
                         }
                     }
                 }
             }
         }
         
         // On crée le barchart
         c1= new BarChart(nC1, spe, values_m, values_d, "Spécialités", "Nombre","Malades","Docteurs");
         charts[1]=c1.barPanel;
    }
    public void initC2(){
        // On récupère les numéros des chambres;
        int[] no_chambre= new int[myData.chambres.size()];
        String[] name= new String[myData.chambres.size()];
        int[] nb_lits= new int[myData.chambres.size()];
        for(int i=0; i<myData.chambres.size(); i++){
            no_chambre[i]=myData.chambres.get(i).no_chambre;
            nb_lits[i]=myData.chambres.get(i).nb_lits;
            name[i]=String.valueOf(no_chambre[i]);
            name[i]=name[i]+" "+myData.chambres.get(i).code_service;
        }
        
        // On vérifie combien de lits sont occupé par chambre
        int[] nb_occupe= new int[myData.chambres.size()];
        double[] lit_occupe= new double[myData.chambres.size()];
        double[] lit_libre= new double[myData.chambres.size()];
        for(int i=0; i<no_chambre.length; i++){
            nb_occupe[i]=0;
            for(int j=0; j<myData.hospitalisations.size(); j++){
                if(myData.hospitalisations.get(j).no_chambre==no_chambre[i]){
                    nb_occupe[i]=nb_occupe[i]+1;
                }
            }
            lit_occupe[i]=nb_occupe[i];
            lit_libre[i]=nb_lits[i]-lit_occupe[i];
        }
        
        c2= new MultiplePieChart(name, lit_libre, lit_occupe);
        charts[2]=c2.piePanel;
        
    }
    public void initC3(){
        // On récupère les noms des services
        int nbr_service=myData.services.size();
        ArrayList <String> names= new ArrayList<>();
        ArrayList <String> code= new ArrayList<>();
        for(int i=0; i<nbr_service;i++){
            names.add(myData.services.get(i).nom);
            code.add(myData.services.get(i).code);
        }
        // On recupère cbm de rotation jour et cbm de nuit
        double[] jour= new double[nbr_service];
        double[] nuit= new double[nbr_service];
        for(int i=0; i<code.size(); i++){
            for(int j=0; j<myData.infirmiers.size(); j++){
                if(myData.infirmiers.get(j).code_service.equals(code.get(i))){
                    if(myData.infirmiers.get(j).rotation.equals("JOUR")){
                        jour[i]=jour[i]+1;
                    }
                    else{
                        nuit[i]=nuit[i]+1;
                    }
                }
            }
        }
        
         
         // On crée le barchart
         c3= new BarChart(nC3, names, jour, nuit, "Services", "Nombre","Jour","Nuit");
         charts[3]=c3.barPanel;
    }
    
    /**
     * GUETTERS
     */
    
    /**
     * SETTERS
     */
    public void setData(Database b){
        myData=b;
        // Initialisation des charts
        this.initCharts();
    }
    
    public void redessinner(){
        this.removeAll();
        what.setLocation(400, 20);
        what.setSize(new Dimension(1000, 50));
        this.add(what);
        
        currentChart.setSize(this.getWidth()-600, this.getHeight()- 200);
        currentChart.setLocation(300,150);
        this.add(currentChart);
        
        this.repaint();
    }
    
    /**
     * LISTENER
     */
    class ItemChangeListener implements ItemListener{
        @Override
        public void itemStateChanged(ItemEvent event) {
           if (event.getStateChange() == ItemEvent.SELECTED) {
              Object item = event.getItem();
              if(item==liste[0]){//Pourcentage d'hospitalisation par secteur
                  currentChart=charts[0];
                  redessinner();
              }
              else if (item==liste[1]){
                  currentChart=charts[1];
                  redessinner();
              }
              else if (item==liste[2]){
                  currentChart=charts[2];
                  redessinner();
              }
              else if (item==liste[3]){
                  currentChart=charts[3];
                  redessinner();
              }
           }
        }   
    }
    
}
