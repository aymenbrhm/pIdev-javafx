/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 *
 * @author zaghdoudi
 */
public class Constat {
        private int id;
        private String Type_Accident;
        private String Lieu_Accident;
        private String VehiculeA;
        private String VehiculeB;
        private String ImmatriculeA;
        private String ImmatriculeB;
        private Date Date_Accident;
        
        public Constat () {
        
        this.id = 0;
        this.Type_Accident = "";
        this. Lieu_Accident = "";
        this.VehiculeA = "";
        this.VehiculeB = "";
        this.ImmatriculeA = "";
        this.ImmatriculeB = "";
        
         }
        public Constat (String Type_Accident, String Lieu_Accident, String VehiculeA, String VehiculeB, String ImmatriculeA, String ImmatriculeB,Date Date_Accident) {
       
      
       this.Type_Accident = Type_Accident;
       this.Type_Accident = Type_Accident;
       this.Lieu_Accident = Lieu_Accident;
       this.VehiculeA = VehiculeA;
       this.VehiculeB = VehiculeB;
       this.ImmatriculeA = ImmatriculeA;
       this.ImmatriculeB = ImmatriculeB;
       this.Date_Accident = Date_Accident;
        
    }

    public Constat(int id,String Type_Accident, String Lieu_Accident, String VehiculeA, String VehiculeB, String ImmatriculeA, String ImmatriculeB,Date Date_Accident) {
       this.id = id;
       
        this.Type_Accident = Type_Accident;
        this.Lieu_Accident = Lieu_Accident;
        this.VehiculeA = VehiculeA;
        this.VehiculeB = VehiculeB;
        this.ImmatriculeA = ImmatriculeA;
         this.ImmatriculeB = ImmatriculeB; 
         this.Date_Accident = Date_Accident; 
    }

    

    

  

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
 
    /**
     * @return the Type_Accident
     */
    public String getType_Accident() {
        return Type_Accident;
    }

    /**
     * @param Type_Accident the Type_Accident to set
     */
    public void setType_Accident(String Type_Accident) {
        this.Type_Accident = Type_Accident;
    }

    /**
     * @return the Lieu_Accident
     */
    public String getLieu_Accident() {
        return Lieu_Accident;
    }

    /**
     * @param Lieu_Accident the Lieu_Accident to set
     */
    public void setLieu_Accident(String Lieu_Accident) {
        this.Lieu_Accident = Lieu_Accident;
    }

    /**
     * @return the VehiculeA
     */
    public String getVehiculeA() {
        return VehiculeA;
    }

    /**
     * @param VehiculeA the VehiculeA to set
     */
    public void setVehiculeA(String VehiculeA) {
        this.VehiculeA = VehiculeA;
    }

    /**
     * @return the VehiculeB
     */
    public String getVehiculeB() {
        return VehiculeB;
    }

    /**
     * @param VehiculeB the VehiculeB to set
     */
    public void setVehiculeB(String VehiculeB) {
        this.VehiculeB = VehiculeB;
    }

    /**
     * @return the ImmatriculeA
     */
    public String getImmatriculeA() {
        return ImmatriculeA;
    }

    /**
     * @param ImmatriculeA the ImmatriculeA to set
     */
    public void setImmatriculeA(String ImmatriculeA) {
        this.ImmatriculeA = ImmatriculeA;
    }

    /**
     * @return the ImmatriculeB
     */
    public String getImmatriculeB() {
        return ImmatriculeB;
    }

    /**
     * @param ImmatriculeB the ImmatriculeB to set
     */
    public void setImmatriculeB(String ImmatriculeB) {
        this.ImmatriculeB = ImmatriculeB;
    }

    /**
     * @return the Date_Accident
     */
    public Date getDate_Accident() {
        return Date_Accident;
    }

    /**
     * @param Date_Accident the Date_Accident to set
     */
    public void setDate_Accident(Date Date_Accident) {
        this.Date_Accident = Date_Accident;
    }
        
        @Override
    public String toString() {
        return "Constat{" + "id=" + id + ", Type_Accident=" + Type_Accident + ", Lieu_Accident=" + Lieu_Accident + ", VehiculeA=" + VehiculeA + ", VehiculeB=" + VehiculeB + ", ImmatriculeA=" + ImmatriculeA + ", ImmatriculeB=" + ImmatriculeB +", Date_Accident=" + Date_Accident +  '}';
    }
    
}
