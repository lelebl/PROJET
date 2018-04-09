
package Vue;

import Modele.Connexion;
import java.awt.Color;


public class ReportingPan extends MyPanel{
    /**
     * ATTRIBUTES
     */
    private Connexion c;
    
    /**
     * CONSTRUCTORS
     */
    public ReportingPan(){
        super(false,"Reporting");
        c=null;
    }
    
    /**
     * GUETTERS
     */
    
    /**
     * SETTERS
     */
    public void setC(Connexion b){
        c=b;
    }
    
}
