/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Interfaces.ContratInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Model.Contrat;
import Model.Voiture;
import java.time.LocalDate;
import java.util.Calendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.MyConnection;

/**
 *
 * @author admin
 */
public class ContratService implements ContratInterface{
    //var
    Connection cnx = MyConnection.getInstance().getCnx();




   
    @Override
    public void affecterJoueur(Contrat c, Voiture v) {
        try {
            String req ="UPDATE `Contrat` SET `matricule_id` = ? WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, v.getId());
            ps.setInt(2, c.getId());
            ps.executeUpdate();
            System.out.println("Contrat updated successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }

    @Override
    public void addContrat(Contrat c) {
    //try {
          //  String req = "INSERT INTO `player`(`name`, `number`, `age`) VALUES ('"+ c.getName() +"',"+c.getNumber()+","+c.getAge()+")";
          //  Statement st = cnx.createStatement();
           // st.executeUpdate(req);
           // System.out.println("Player Added successfully!");
       // } catch (SQLException ex) {
          //  ex.printStackTrace();
      //  }    
    }

    @Override
    public void addContrat2(Contrat c) {
        Calendar cal = Calendar.getInstance();
        LocalDate currentDate = LocalDate.now();
      java.sql.Date sqlDate = java.sql.Date.valueOf(currentDate);
int p =c.getPeriode();
 LocalDate fin=currentDate.plusMonths(p);
       java.sql.Date sqlDate1 = java.sql.Date.valueOf(fin);

 try {
            
            String req = "INSERT INTO `contrat`( `date_deb`,`date_fin`,`type_de_contrat`) VALUES (?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
           // ps.setString(1, c.getName());
           ps.setDate(1,sqlDate);
           ps.setDate(2,sqlDate1);
            ps.setString(3, c.getType());
            ps.executeUpdate();
            System.out.println("contrat Added Successfully!");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }    }
    
    

    @Override
    public ObservableList fetchContrats() {
    ObservableList<Contrat> Contrats = FXCollections.observableArrayList();
        try {
            
           // String req = "SELECT * FROM Contrat";
            String req2 = "SELECT Contrat.*,Voiture.* FROM Contrat JOIN Voiture ON Contrat.matricule_id=Voiture.id";

       //     Statement st = cnx.createStatement();
            Statement st2 = cnx.createStatement();

         //   ResultSet rs = st.executeQuery(req);
            ResultSet rs2 = st2.executeQuery(req2);
            
            while (rs2.next()) {                
                Contrat c = new Contrat();
                c.setId(rs2.getInt("id"));
                //v.setMatricule(rs.getString(2));
                c.setDatedeb(rs2.getDate("date_deb"));
                c.setDatefin(rs2.getDate("date_fin"));
                c.setType(rs2.getString("type_de_contrat"));
                c.setMatricule(rs2.getString("matricule"));
                
                Contrats.add(c);
            }
           // while (rs2.next()) {
           // c.setMatricule(rs2.getString(11));
       // }
            
         
             
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return Contrats;   
    }


    @Override
    public List<Contrat> searchContrats(String type) {
        List<Contrat> Contrats = new ArrayList<>();
    try {
        String req = "SELECT * FROM Contrat WHERE type_de_contrat = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, type);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Contrat c = new Contrat();
            c.setId(rs.getInt(1));
            c.setType(rs.getString(6));
            Contrats.add(c);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return Contrats;
    }

    @Override
    public void suppContrat(Contrat c) {
        
        try {
        String req = "DELETE FROM Contrat WHERE id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        int contratId = c.getId();
        ps.setInt(1,contratId);
             int rowsAffected = ps.executeUpdate();
    if (rowsAffected > 0) {
        System.out.println("Contrat deleted successfully.");
    } else {
        System.out.println("Contrat not found.");
    }
    
        
    } catch (SQLException ex) {
        ex.printStackTrace();
        
        
        
        
        
    }
    }

    @Override
    public void updateContrat(Contrat c) {
        
            String req="UPDATE Contrat SET type_de_contrat = ? WHERE id = ?";
        try {
            PreparedStatement stmt = cnx.prepareStatement(req);
            String type=c.getType();
            int id = c.getId();
            stmt.setString(1,type); 
            stmt.setInt(2,id);
            stmt.executeUpdate();
            System.out.println("Contrat updated successfully");
            } catch (SQLException e) {
    System.out.println("Error updating contrat: " + e.getMessage());
}
    }

    @Override
    public ObservableList<Contrat> fetchContrats1() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void fetchandcountContract() {
    try {
        String sql = "SELECT type_de_contrat, COUNT(*) AS count FROM Contrat GROUP BY type_de_contrat";
        PreparedStatement statement = cnx.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        while(result.next()) {
            String type = result.getString("type_de_contrat");
            int count = result.getInt("count");
            System.out.println("Type: " + type + ", Count: " + count);
        }
    } catch(SQLException e) {
        e.printStackTrace();
    }
}
    }


    
    
