package Vue;


import Vue.Accueil.AccueilPan;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Fenetre extends JFrame{
    /**
     * ATTRIBUTES
     */
    private final int taillex; 
    private final int tailley;
    private int nbrPan;
    
    // MENU
    private JMenuBar menuBar;
    private JMenuItem maj;
    private JMenuItem rech_info;
    private JMenuItem reporting;
    
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
    * Initialisation menu
    */
    public void initMenu(){
        menuBar = new JMenuBar();
	maj = new JMenuItem("Mise à jour");
        rech_info = new JMenuItem("Recherche d'information");
        reporting = new JMenuItem("Reporting");
            //defined the action
            /**
        maj.addActionListener(action);
        rech_info.addActionListener(action);
        reporting.addActionListener(action);
        **/
            //add it to my menubar
        menuBar.add(maj);
        menuBar.add(rech_info);
        menuBar.add(reporting);
            //set font
        Font f = new Font("Courier", Font.BOLD,20);
        maj.setFont(f);
        rech_info.setFont(f);
        reporting.setFont(f);
            //Initialization color
        maj.setBackground(Color.white);
        rech_info.setBackground(Color.white);
        reporting.setBackground(Color.white);
            //add to the frame
        this.setJMenuBar(menuBar);
    }
}
