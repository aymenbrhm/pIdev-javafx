/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Admin
 */
    

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author USER
 */
public class Voiture {
    private int id;
    private String matricule;
    private String couleur;
    private int Nombre_de_places;
    private int        Puissance;
    private String   Energie;

    public Voiture(String matricule, String couleur, int Nombre_de_places, int Puissance, String Energie) {
        this.matricule = matricule;
        this.couleur = couleur;
        this.Nombre_de_places = Nombre_de_places;
        this.Puissance = Puissance;
        this.Energie = Energie;
    }

    public Voiture() {
    }


    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public int getNombre_de_places() {
        return Nombre_de_places;
    }

    public void setNombre_de_places(int Nombre_de_places) {
        this.Nombre_de_places = Nombre_de_places;
    }

    public int getPuissance() {
        return Puissance;
    }

    public void setPuissance(int Puissance) {
        this.Puissance = Puissance;
    }

    public String getEnergie() {
        return Energie;
    }

    public void setEnergie(String Energie) {
        this.Energie = Energie;
    }
   
   // @Override
   // public String toString() {
   //     return "Poste{" + "id=" + id + ", titre=" + datedeb + ", decription=" + datefin + '}';
   // }

    public int getId() {
        return id;
        
    }

    public void setId(int i) {
        this.id=id;
        
    }
    
    
}
