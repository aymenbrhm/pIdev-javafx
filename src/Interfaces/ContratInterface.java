/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Model.Contrat;
import Model.Voiture;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Admin
 */
public interface ContratInterface {
     public void addContrat(Contrat c);
     public void addContrat2(Contrat c);
     public void suppContrat(Contrat c);
     public void updateContrat(Contrat c);
     public ObservableList fetchContrats();
     public ObservableList<Contrat> fetchContrats1();
     public  List<Contrat> searchContrats(String type);
     public void affecterJoueur(Contrat c, Voiture v);
     public void fetchandcountContract();

     
}
