/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author oXCToo
 */
public class HomeController implements Initializable {
    
    
      @FXML
    private VBox pnl_scroll;
    @FXML
    private Button ListeContrat;
@FXML
    private ImageView exit;
    @FXML
    private VBox vbox;
    @FXML
    private Button addcontrat;
    @FXML
    private Button stat;
    @FXML
    private Button pay;
   
    
    
    private void handleButtonAction(MouseEvent event) {        
       refreshNodes();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        exit.setOnMouseClicked(event -> {
            System.exit(0);
        });

         
    }    
    
    private void refreshNodes()
    {
        //pnl_scroll.getChildren().clear();
        
        //Node [] nodes = new  Node[15];
        
        /*for(int i = 0; i<10; i++)
        {
            try {
                nodes[i] = (Node)FXMLLoader.load(getClass().getResource("Item.fxml"));
               pnl_scroll.getChildren().add(nodes[i]);
                
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }  */
    }

    @FXML
    private void ListContrat(ActionEvent event) throws IOException {
        //Stage stage = new Stage();
                pnl_scroll.getChildren().clear();
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/AfficheContrat.fxml"));
                pnl_scroll.getChildren().add(root);
        //Scene scene = new Scene(root);
        //stage.setScene(scene);
        //stage.show();
    }

    /*@FXML
    private void addContrat(ActionEvent event) throws IOException {
        pnl_scroll.getChildren().clear();
        Parent root = FXMLLoader.load(getClass().getResource("/bergila/AjoutContrat.fxml"));
                pnl_scroll.getChildren().add(root);
    }*/

    @FXML
    private void Ajouter(ActionEvent event) throws IOException {
        pnl_scroll.getChildren().clear();
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/AjoutContrat.fxml"));
                pnl_scroll.getChildren().add(root);
    }

    @FXML
    private void stat(ActionEvent event) throws IOException {
        pnl_scroll.getChildren().clear();
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/Stat.fxml"));
                pnl_scroll.getChildren().add(root);
    }

    @FXML
    private void payement(ActionEvent event) throws IOException {
         pnl_scroll.getChildren().clear();
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/Stripe.fxml"));
                pnl_scroll.getChildren().add(root);
    }
    
}
