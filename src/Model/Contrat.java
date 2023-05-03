/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author USER
 */
public class Contrat {

    public static void add(Contrat p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private int id;
    private int periode;
    private String image;
    private String type;
    private Date datedeb;
    private Date datefin;
    private String matricule;
    private Voiture voiture;

    public Contrat(int periode, String type, String image,Voiture voiture,String matricule,Date datedeb,Date datefin) {
        this.periode = periode;
        this.type = type;
        this.matricule = matricule;
        this.image = image;
        this.datedeb=datedeb;
        this.datefin=datefin;
        this.voiture=voiture;
    }

    public Contrat() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public Voiture getVoiture() {
        return voiture;
    }

    public void setVoiture(Voiture voiture) {
        this.voiture = voiture;
    }

    

    public int getPeriode() {
        return periode;
    }

    public void setPeriode(int periode) {
        this.periode = periode;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getImage() {
        return image;
    }
    
    public Date getDatedeb(){
        return datedeb;
    }
    public void setDatedeb(Date datedeb){
        this.datedeb=datedeb;
    }
    public Date getDatefin(){
        return datefin;
    }
    public void setDatefin(Date datefin){
        this.datefin=datefin;
    }
    public Contrat(String type) {
        this.type = type;
    }

    public void setImage(String image) {
        this.image = image;
    }
        
    @Override
    public String toString() {
        return "Contrat{" + "periode=" + periode + ", type=" + type + ", image=" + image + '}';
    }

    public int getId() {
return id;    }
    
    
}