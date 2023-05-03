/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Model.Constat;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene; 
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage; 
import Services.Constatservice;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;





/**
 * FXML Controller class
 *
 * @author zaghdoudi
 */
public class AjouterConstatController implements Initializable {
    
    
     private Stage stage;
      private Stage stage1;
     private Scene scene;
     private Parent root;

 
    @FXML
    private TextField tflieu_Accident;
    @FXML
    private TextField tfVehiculeA;
    @FXML
    private TextField tfvehiculeB;
    @FXML
    private TextField tfImmatriculeA;
    @FXML
    private TextField tfImmatriculeB;
    @FXML
    private DatePicker dpDate_Accident;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnretour;
    @FXML
    private ComboBox<String> tftype_accident;
  
  
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
         tftype_accident.getItems().removeAll(tftype_accident.getItems());
        tftype_accident.getItems().addAll("dangerous" , "moyenne", "normale", "pas grave ");
        tftype_accident.getSelectionModel().select(null);
       
    }    

    @FXML
    private void Ajouter(ActionEvent event) {
        
       if (tflieu_Accident.getText().isEmpty()
                || tftype_accident.getSelectionModel().isEmpty()
                || dpDate_Accident.getValue().format(DateTimeFormatter.BASIC_ISO_DATE.ISO_DATE).isEmpty() ||tfVehiculeA.getText().isEmpty()|| tfvehiculeB.getText().isEmpty()|| tfImmatriculeA.getText().isEmpty()|| tfImmatriculeB.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("S'il vous plaît , veuillez remplir le formulaire");
            alert.showAndWait();
        } else {
              Constat c = new Constat();
          Constatservice cs = new Constatservice();
         
//LocalDate localDate = DatePicker.getValue();
//java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
//         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date dateDebut = dateFormat.parse(dateDebut.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//        Date dateFin = dateFormat.parse(dateFin.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//    Offre o = new Offre(user, nom.getText(), secteur.getText(), description.getText(),dateDebut , dateFin, typeOffer.getSelectionModel(), competence.getText());
            c.setType_Accident(tftype_accident.getSelectionModel().getSelectedItem());
            c. setLieu_Accident(tflieu_Accident.getText());
            Date startDate = Date.valueOf(dpDate_Accident.getValue().toString());
            c. setDate_Accident(startDate);
            c.setVehiculeA(tfVehiculeA.getText());
            c.setVehiculeB(tfvehiculeB.getText());
            c.setImmatriculeA(tfImmatriculeA.getText());
            c.setImmatriculeB(tfImmatriculeB.getText());

            cs.ajouter(c);
            JOptionPane.showMessageDialog(null, "constat Ajoutée avec succés ");

//        Alert alert = new Alert (Alert.AlertType.INFORMATION);
//            alert.setHeaderText (null);
//            alert.setContentText ( "ajoutée");
//            alert.showAndWait () ;
        }
    }  

    @FXML
    private void retour(ActionEvent event) {
         try {
             root = FXMLLoader.load(getClass().getResource("/gui/ListConstat.fxml"));
             stage = (Stage)((Node)event.getSource()).getScene().getWindow();
             scene = new Scene(root);
             stage.setScene(scene);
             stage.show();
         } catch (IOException ex) {
             Logger.getLogger(AjouterConstatController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        
    }

   
  


    
        
    
   
}

 