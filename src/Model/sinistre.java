/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author zaghdoudi
 */
public class sinistre {
     private int id;
        private String lastname;
        private String Firstname;
        private String Email;
        private String Adresse;
        private String Description;
        private String TypeVehicule;
        private String fichier_Accident;
        
        
        public sinistre() {
        
        this.id = 0;
        this.lastname = "";
        this.Firstname = "";
        this.Email = "";
        this.Adresse = "";
        this.Description = "";
        this.TypeVehicule = "";
        this.fichier_Accident = "";
    }
        
    /**
     *
     * @param lastname
     * @param Firstname
     * @param Email
    
     * @param Adresse
     * @param Description
     * @param fichier_Accident
     */
    public sinistre ( String lastname, String Firstname,String Email,String Adresse,String Description,String TypeVehicule ,String fichier_Accident ) {
         
        this.lastname = lastname;
        this.Firstname = Firstname;
        this. Email = Email;
        this.Adresse =Adresse;
        this.Description =Description;
         this.TypeVehicule =TypeVehicule;
        this.fichier_Accident = fichier_Accident;
        
        
        
    }

    public sinistre(int id , String lastname, String Firstname, String Email,  String Adresse,String Description ,String TypeVehicule,String fichier_Accident) {
      this.id = id;
      this.lastname = lastname;
      this.Firstname = Firstname;
      this.Email = Email;
      this.Adresse = Adresse;
      this.Description =Description;
      this.TypeVehicule =TypeVehicule;
      this.fichier_Accident = fichier_Accident;        
                 
        
    }

    public sinistre(int id) {
        this.id = id;
        
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
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the Firstname
     */
    public String getFirstname() {
        return Firstname;
    }

    /**
     * @param Firstname the Firstname to set
     */
    public void setFirstname(String Firstname) {
        this.Firstname = Firstname;
    }

    /**
     * @return the Email
     */
    public String getEmail() {
        return Email;
    }

    /**
     * @param Email the Email to set
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }


    /**
     * @return the Adresse
     */
    public String getAdresse() {
        return Adresse;
    }

    /**
     * @param Adresse the Adresse to set
     */
    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }
     /**
     * @param Description  the Adresse to set
     */
    
    
      public String getDescription() {
        return Description;
    }
/**
     * @param Description the Adresse to set
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }
   public String getTypeVehicule() {
        return TypeVehicule;
    }
/**
     * @param Description the Adresse to set
     */
    public void setTypeVehicule(String TypeVehicule) {
        this.TypeVehicule = TypeVehicule;
    }
    /**
     * @return the fichieraccident
     */
    public String getFichier_Accident() {
        return fichier_Accident;
    }

    /**
     * @param Fichier_Accident the fichieraccident to set
     */
    public void setFichier_Accident(String fichier_Accident) {
        this.fichier_Accident = fichier_Accident;
        
        
    }
     @Override
    public String toString() {
        return "sinistre{" + "id=" + id + ", lastname=" + lastname + ", Firstname=" + Firstname + ", Email=" + Email + ", Adresse=" + Adresse + ",Description =" + Description + ",typevehicule =" + TypeVehicule + ", fichier_Accident=" + fichier_Accident + '}';
    }

    
}
