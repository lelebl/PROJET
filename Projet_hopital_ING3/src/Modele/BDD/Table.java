/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele.BDD;

import Modele.Connexion;
import java.util.ArrayList;

/**
 *
 * @author Emmanuelle THIROLOIX&Léa Blanchard
 */
public interface Table {
    
    public Boolean supprimer();
    public void ajouter(String attribut);
    public boolean modifier(ArrayList<String> attribut,ArrayList<String> new_attribut);
    public void setC(Connexion c);
}
