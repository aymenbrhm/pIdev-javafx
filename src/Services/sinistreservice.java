/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Model.sinistre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import service.NewInterface;
import util.MyConnection;

/**
 *
 * @author zaghdoudi
 */
public class sinistreservice implements NewInterface<sinistre> {
    
    Connection conn;
    String sql="";
    Statement ste;
    public sinistreservice() {
       conn = MyConnection.getInstance().getCnx();
    }

    @Override
    public void ajouter(sinistre t) {
        try {
            String sql = "insert into sinstre (lastname,firstname ,email ,adresse,Description,typeduvehicule,fichieraccident)"
                    + "values (?,?,?,?,?,?,?)";
            PreparedStatement ste = conn.prepareStatement(sql);
            
            ste.setString(1, t.getLastname());
            ste.setString(2, t.getFirstname());
            ste.setString(3,t.getEmail());
            ste.setString(4,t.getAdresse());
            ste.setString(5,t.getDescription());
            ste.setString(6,t.getTypeVehicule());
            ste.setString(7,t.getFichier_Accident());
            
            if(ste.executeUpdate()==1 ){
                System.out.println("sinistre ajoutée");
            }else{
                System.out.println("probleme d'insertion sinistre avec la base");
            }
        } catch (SQLException ex) {
            
        }
    }

    @Override
    public void supprimer(sinistre t) {
        
        
        try {
            String req = "DELETE FROM `sinstre` WHERE `id` = " + t.getId();
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("sinstre supprimé");
            
          
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
          
            
    }

    @Override
    public List<sinistre> afficher() {
        List<sinistre> sinistres = new ArrayList<>();
        try {
            String sql = "select * from sinstre";
            Statement ste = conn.createStatement();
            ResultSet r = ste.executeQuery(sql);
            while (r.next()) {

                sinistre s = new sinistre(r.getInt("id"),r.getString("lastname"),
                r.getString("firstname"),r.getString("email"), r.getString("adresse"),r.getString("Description"),r.getString("typeduvehicule"), r.getString("fichieraccident"));
                sinistres.add(s);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return sinistres;
    
    }

    public void modifier(int id , String lastname, String Firstname, String Email,String Adresse,String Description ,String TypeVehicule ,String fichier_Accident) {
          System.out.println(id);
          String sql = " UPDATE sinstre SET lastname=?,firstname=?,email=?,adresse=?,Description=?,typeduvehicule=? fichieraccident=? where id=?";
        try {
           
            PreparedStatement ste = conn.prepareStatement(sql);
            ste.setInt(1,id);
            ste.setString(2,lastname);
            ste.setString(3,Firstname);
            ste.setString(4,Email);
            ste.setString(5,Adresse);
            ste.setString(6,Description);
            ste.setString(7,TypeVehicule);
            ste.setString(8,fichier_Accident);
          
            ste.executeUpdate();
            System.out.println("sinistre  a éte modifié avec succées !");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    
    
    
}
