/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Model.Contrat;
import Model.Voiture;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface VoitureInterface {
     public void addVoiture(Voiture v);
     
     public List<Voiture> fetchVoitures();

     
}
