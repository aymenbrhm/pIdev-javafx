/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Interfaces.ContratInterface;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Period;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.ContratService;

/**
 *
 * @author oXCToo
 */
public class Bergila extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
            //mailutil.sendmail("ddjbhy@gmail.com");
            ContratInterface contrat=new ContratService();
            contrat.fetchandcountContract();
            try {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/final", "root", "");
    String query = "SELECT PERIOD_DIFF(EXTRACT(YEAR_MONTH FROM date_fin), EXTRACT(YEAR_MONTH FROM date_deb)) AS period, SUM(1) AS count FROM Contrat GROUP BY period";
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery(query);
    while (rs.next()) {
        int periodInMonths = rs.getInt("period");
        int count = rs.getInt("count");
        System.out.println("period: " + periodInMonths + " count: " + count);
    }
} catch (SQLException e) {
    e.printStackTrace();
}

      
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        launch(args);
    }
    
}
