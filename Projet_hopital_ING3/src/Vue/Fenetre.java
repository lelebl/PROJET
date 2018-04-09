
package Vue;

import Modele.Connexion;
import Vue.Accueil.AccueilPan;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Emmanuelle THIROLOIX
 */
public class Fenetre extends JFrame {
    /**
     * ATTRIBUTES
     */
    private final int taillex; 
    private final int tailley;
    private int nbrPan;
    private Connexion c; 
    
    // MENU
    private JMenuBar menuBar;
    private JMenuItem maj;
    private JMenuItem rech_info;
    private JMenuItem reporting;
    private JMenuItem accueil;
    
    // Panels:
    private MyPanel[] panels;
    private AccueilPan accueilPan;
    private MAJPan majPan;
    private ReportingPan reportPan;
    private RecherchePan recherchePan;
    
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
        this.c=null;
        
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
    public Connexion getC(){
        return c;
    }
    
    /**
     * SETTERS
     */
    public void setC(Connexion a){
        c=a;
        reportPan.setC(a);
    }
    public void setPan(MyPanel p){
        if(p==panels[0]){ //retour accueil
            c=null;
            maj.setBackground(Color.white);
            reporting.setBackground(Color.white);
            rech_info.setBackground(Color.white);
            accueil.setBackground(Color.DARK_GRAY);
            accueilPan.InitPanCo();
            reportPan.setVisible(false);
            majPan.setVisible(false);
            recherchePan.setVisible(false);
            accueilPan.setVisible(true);
            this.setContentPane(accueilPan);
        }
        else if(p==panels[1])// Mise a jour
        {
            reporting.setBackground(Color.white);
            rech_info.setBackground(Color.white);
            accueil.setBackground(Color.white);
            maj.setBackground(Color.DARK_GRAY);
            reportPan.setVisible(false);
            majPan.setVisible(true);
            recherchePan.setVisible(false);
            accueilPan.setVisible(false);
            this.setContentPane(majPan);
        }
        else if(p==panels[2])//Reporting
        {
            maj.setBackground(Color.white);
            rech_info.setBackground(Color.white);
            accueil.setBackground(Color.white);
            reporting.setBackground(Color.DARK_GRAY);
            reportPan.setVisible(true);
            majPan.setVisible(false);
            recherchePan.setVisible(false);
            accueilPan.setVisible(false);
            this.setContentPane(reportPan);
        }
        else //Recherche
        {
            maj.setBackground(Color.white);
            reporting.setBackground(Color.white);
            accueil.setBackground(Color.white);
            rech_info.setBackground(Color.DARK_GRAY);
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
	maj = new JMenuItem("Mise à jour");
        rech_info = new JMenuItem("Recherche d'information");
        reporting = new JMenuItem("Reporting");
        accueil = new JMenuItem("Accueil");
            //defined the action
        accueil.addMouseListener(new MenuListener());
        maj.addMouseListener(new MenuListener());
        rech_info.addMouseListener(new MenuListener());
        reporting.addMouseListener(new MenuListener());
        
            //add it to my menubar
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

    class MenuListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getSource()==maj && c!=null){
                setPan(majPan);
            }
            else if(e.getSource()==reporting && c!=null){
                setPan(reportPan);
            }
            else if(e.getSource()==rech_info && c!=null){
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
            if(e.getSource()==maj && c!=null){
                if(getContentPane()!=majPan){
                maj.setBackground(Color.GRAY);
                }
            }
            else if(e.getSource()==reporting && c!=null){
                if(getContentPane()!=reportPan){
                reporting.setBackground(Color.GRAY);
                }
            }
            else if(e.getSource()==rech_info && c!=null){
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
            if(e.getSource()==maj && c!=null){
                if(getContentPane()!=majPan){
                    maj.setBackground(Color.white);
                }
            }
            else if(e.getSource()==reporting && c!=null){
                if(getContentPane()!=reportPan){
                    reporting.setBackground(Color.white);
                }
            }
            else if(e.getSource()==rech_info && c!=null){
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
        
    }
}
