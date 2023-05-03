/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Model.Constat;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import util.MyConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import service.constatInterface;





/**
 *
 * @author zaghdoudi
 */
public class Constatservice implements constatInterface<Constat> {
       Connection conn;
    String sql="";
    Statement ste;
    
    public Constatservice() {
       conn = MyConnection.getInstance().getCnx();
    }

    @Override
    public void ajouter(Constat t) {
           try {
               String sql = "insert into constat ( type_daccident, lieu_daccident,  vehicule_a,  vehicule_b,  immatricule_a, immatricule_b,date_daccident )"
                       + "values (?,?,?,?,?,?,?)";
               PreparedStatement ste = conn.prepareStatement(sql);
               ste.setString(1, t.getType_Accident());
               ste.setString(2, t.getLieu_Accident());
               ste.setString(3,t.getVehiculeA());
               ste.setString(4,t.getVehiculeB());
               ste.setString(5,t.getImmatriculeA());
               ste.setString(6,t.getImmatriculeB());
               ste.setDate(7,t.getDate_Accident());
               
               if(ste.executeUpdate()==1 ){
                   System.out.println("constat ajoutée");
               }else{
                   System.out.println("probleme d'insertion constat avec la base");
               }
           } catch (SQLException ex) {
               System.out.println(ex.getMessage());
           }
    }

    @Override
    public void supprimer(Constat t) {
           try {
            String req = "DELETE FROM `constat` WHERE `id` = " + t.getId();
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("constat supprimé");
            
          
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Constat> afficherconstat() {
        List<Constat> Constats = new ArrayList<>();
           try {
               String sql = "select * from constat";
               Statement ste = conn.createStatement();
               ResultSet r = ste.executeQuery(sql);
               while (r.next()) {
                   
                   Constat c = new Constat(r.getInt("id"),r.getString("type_daccident"),
                           r.getString("lieu_daccident"), r.getString("vehicule_a"),r.getString("vehicule_b"),r.getString("immatricule_a"),r.getString("immatricule_b"),r.getDate("date_daccident"));
                   Constats.add(c);
                   
               }
           } catch (SQLException ex) {
              System.out.println(ex.getMessage());
           }
     return Constats;
    }

   
    public void modifier(int id,String Type_Accident, String Lieu_Accident, String VehiculeA, String VehiculeB, String ImmatriculeA, String ImmatriculeB,Date Date_Accident ) {
        System.out.println(id);  
        
        String sql = "update constat set type_daccident =?, lieu_daccident=?,  vehicule_a=?,  vehicule_b=?,  immatricule_a=?, immatricule_b=?,date_daccident=? where id=?";
        
        try {
           
            PreparedStatement ste = conn.prepareStatement(sql);
            ste.setString(1, Type_Accident);
            ste.setString(2, Lieu_Accident);
            ste.setString(3, VehiculeA);
            ste.setString(4, VehiculeB);
            ste.setString(5, ImmatriculeA);
            ste.setString(6, ImmatriculeB);
            ste.setDate(7, Date_Accident);
          
           ste.executeUpdate();
            System.out.println("constat  a éte modifié avec succées !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
   
  
}
