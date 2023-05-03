/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Interfaces.VoitureInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Model.Voiture;
import util.MyConnection;

/**
 *
 * @author admin
 */
public class VoitureService implements VoitureInterface{
    //var
    Connection cnx = MyConnection.getInstance().getCnx();




   
   

  

  

    @Override
    public List<Voiture> fetchVoitures() {
    List<Voiture> Voitures = new ArrayList<>();
        try {
            
            String req = "SELECT * FROM Voiture";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {                
                Voiture v = new Voiture();
                v.setId(rs.getInt(1));
                v.setMatricule(rs.getString(3));
                v.setCouleur(rs.getString(4));
                v.setNombre_de_places(rs.getInt(5));
                v.setPuissance(rs.getInt(6));
                v.setEnergie(rs.getString(7));
       
        

                //c.setAge(rs.getInt("age"));
                
                Voitures.add(v);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return Voitures;    }

    @Override
    public void addVoiture(Voiture v) {
     

 try {
            
            String req = "INSERT INTO `Voiture`( `matricule`,`couleur`,`nombre_de_places`,`puissance`,`energie`) VALUES (?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
           // ps.setString(1, c.getName());
           ps.setString(1,v.getMatricule());
           ps.setString(2,v.getCouleur());
           ps.setInt(3,v.getNombre_de_places());
           
           ps.setInt(4,v.getPuissance());
           ps.setString(5,v.getEnergie());
            ps.executeUpdate();
            System.out.println("contrat Added Successfully!");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }        }

   
    
}