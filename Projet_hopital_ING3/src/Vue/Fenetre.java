
package Vue;

import Modele.Connexion;
import Vue.Accueil.AccueilPan;
import Vue.Reporting.ReportingPan; 
import Modele.BDD.Database;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Fenetre extends JFrame {
    /**
     * ATTRIBUTES
     */
    private final int taillex; 
    private final int tailley;
    private final int nbrPan;
    private Database myData; 
    
    // MENU
    private JMenuBar menuBar;
    private JMenu maj;
    private JMenuItem maj_supprimer;
    private JMenuItem maj_ajouter;
    private JMenuItem maj_modifier;
    private JMenu rech_info;
    private JMenu reporting;
    private JMenu accueil;
    
    // Panels:
    private MyPanel[] panels;
    private AccueilPan accueilPan;
    private MAJPan majPan;
    private ReportingPan reportPan;
    private RecherchePan recherchePan;
    
    /**
    /**
     * CONSTRUCTORS
     */
    public Fenetre(){
        super("System Informatique Hospitalier");
        
        // On récupère la dimension de l'écran pour l'appliquer à la fnetre JFrame
        Dimension tailleMoniteur= Toolkit.getDefaultToolkit().getScreenSize();
        taillex=(tailleMoniteur.width)-50;
        tailley=(tailleMoniteur.height)-70;
        this.setSize(taillex,tailley);
        
        // On initialise les options
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //fermeture de la fenêtre en appuyant sur la croix
        this.setResizable(true); //pour permettre a l'utilisateur de redimensionner
        this.setLocationRelativeTo(null);
        this.myData=null;
        
        // On initialise le menu
        this.initMenu();
        
        // On initialise les panels:
        nbrPan=4;
        panels= new MyPanel[nbrPan];
        accueilPan= new AccueilPan();
        panels[0]=accueilPan;
        majPan= new MAJPan();
        panels[1]=majPan;
        reportPan= new ReportingPan();
        panels[2]=reportPan;
        recherchePan= new RecherchePan();
        panels[3]=recherchePan;
        
        // On met le panel d'accueil sur la fenetre graphique
        this.setContentPane(accueilPan);
        
        // On rend la fenêtre visible
	this.setVisible(true);
    }
    
    /**
     * GUETTERS
     */
    public AccueilPan getAccueil(){
        return accueilPan;
    }
    public ReportingPan getReport(){
        return reportPan;
    }
    public Database getMyData(){
        return myData;
    }
    
    /**
     * SETTERS
     */
   public void setData(Database a){
        myData=a;
        if(myData!=null){
            //On initialise les connexions des différents panels
            majPan.setData(a);
            recherchePan.setData(a);
            reportPan.setData(a);
        }
    }
   
    public void setPan(MyPanel p, String type){
        if(p==panels[0]){ //retour accueil
            majPan.pan.removeAll();
            myData=null;
            accueilPan.InitPanCo();
            reportPan.setVisible(false);
            majPan.setVisible(false);
            recherchePan.setVisible(false);
            accueilPan.setVisible(true);
            this.setContentPane(accueilPan);
        }
        else if(p==panels[1])// Mise a jour
        {
            majPan.pan.removeAll();
            System.out.println("MAJ PAN");
            reportPan.setVisible(false);
            majPan.setVisible(true);
            recherchePan.setVisible(false);
            accueilPan.setVisible(false);
            majPan.setTitle(type);
            this.setContentPane(majPan);
        }
        else if(p==panels[2])//Reporting
        {
            majPan.pan.removeAll();
            System.out.println("REPORTING PAN");
            reportPan.setVisible(true);
            majPan.setVisible(false);
            recherchePan.setVisible(false);
            accueilPan.setVisible(false);
            reportPan.initCharts();
            this.setContentPane(reportPan);
        }
        else //Recherche
        {
            majPan.pan.removeAll();
            System.out.println("SEARCH PAN");
            reportPan.setVisible(false);
            majPan.setVisible(false);
            recherchePan.setVisible(true);
            accueilPan.setVisible(false);
            this.setContentPane(recherchePan);
        }
    }
    
    /**
    * Initialisation menu
    */
    public void initMenu(){
        menuBar = new JMenuBar();
	maj = new JMenu("Mise à jour");
        maj_ajouter = new JMenuItem("Ajouter");
        maj_modifier = new JMenuItem("Modifier");
        maj_supprimer = new JMenuItem("Supprimer");
        rech_info = new JMenu("Recherche d'information");
        reporting = new JMenu("Reporting");
        accueil = new JMenu("Accueil");
        
        
            //defined the action
       accueil.addMouseListener(new BarMenuListener());
        maj.addMouseListener(new BarMenuListener());
        maj_ajouter.addActionListener(new SousMenuListener());
        maj_supprimer.addActionListener(new SousMenuListener());
        maj_modifier.addActionListener(new SousMenuListener());
        rech_info.addMouseListener(new BarMenuListener());
        reporting.addMouseListener(new BarMenuListener());
        
        
            //add it to my menubar
        maj.add(maj_ajouter);
        maj.add(maj_supprimer);
        maj.add(maj_modifier);
        menuBar.add(accueil);
        menuBar.add(maj);
        menuBar.add(rech_info);
        menuBar.add(reporting);
            //set font
        Font f = new Font("Courier", Font.BOLD,20);
        maj.setFont(f);
        rech_info.setFont(f);
        reporting.setFont(f);
        accueil.setFont(f);
            //Initialization color
        maj.setBackground(Color.white);
        rech_info.setBackground(Color.white);
        reporting.setBackground(Color.white);
        accueil.setBackground(Color.DARK_GRAY);
            //add to the frame
        this.setJMenuBar(menuBar);
    }

   class SousMenuListener implements ActionListener{


        @Override
        public void actionPerformed(ActionEvent e) {
           if(e.getSource()==maj_supprimer && myData!=null){
               System.out.println("supp");
                setPan(majPan,"Supprimer");
            }
            else if(e.getSource()==maj_ajouter && myData!=null){
                setPan(majPan,"Ajouter");
            }
            else if(e.getSource()==maj_modifier && myData!=null){
                setPan(majPan,"Modifier");
            }
        }
        
    }
    class BarMenuListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getSource()==reporting && myData!=null){
                setPan(reportPan,"");
            }
            else if(e.getSource()==rech_info && myData!=null){
                setPan(recherchePan,"");
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    class MenuListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getSource()==maj && myData!=null){
                setPan(majPan);
            }
            else if(e.getSource()==reporting && myData!=null){
                setPan(reportPan);
            }
            else if(e.getSource()==rech_info && myData!=null){
                setPan(recherchePan);
            }
            else if(e.getSource()==accueil){
                setPan(accueilPan);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if(e.getSource()==maj && myData!=null){
                if(getContentPane()!=majPan){
                maj.setBackground(Color.GRAY);
                }
            }
            else if(e.getSource()==reporting && myData!=null){
                if(getContentPane()!=reportPan){
                reporting.setBackground(Color.GRAY);
                }
            }
            else if(e.getSource()==rech_info && myData!=null){
                if(getContentPane()!=recherchePan){
                rech_info.setBackground(Color.GRAY);
                }
            }
            else if(e.getSource()==accueil){
                if(getContentPane()!=accueilPan){
                accueil.setBackground(Color.GRAY);
                }
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(e.getSource()==maj && myData!=null){
                if(getContentPane()!=majPan){
                    maj.setBackground(Color.white);
                }
            }
            else if(e.getSource()==reporting && myData!=null){
                if(getContentPane()!=reportPan){
                    reporting.setBackground(Color.white);
                }
            }
            else if(e.getSource()==rech_info && myData!=null){
                if(getContentPane()!=recherchePan){
                    rech_info.setBackground(Color.white);
                }
            }
            else if(e.getSource()==accueil){
                if(getContentPane()!=accueilPan){
                    accueil.setBackground(Color.white);
                }
            }
        }

        private void setPan(RecherchePan recherchePan) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private void setPan(ReportingPan reportPan) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private void setPan(MAJPan majPan) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private void setPan(AccueilPan accueilPan) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }}}}
        
   
    
