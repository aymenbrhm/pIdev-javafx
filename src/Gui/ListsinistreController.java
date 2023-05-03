/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Model.sinistre;
import Services.sinistreservice;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author zaghdoudi
 */
public class ListsinistreController implements Initializable {
    
     private Stage stage;
     private Stage stage1;
     private Scene scene;
     private Parent root;

  
  
   @FXML
    private TableView<sinistre> table;
    private sinistreservice sinistreservice = new sinistreservice();

    @FXML
    private TableColumn<sinistre, String> tflastname;

    @FXML
    private TableColumn<sinistre, String> tffirstname;

    @FXML
    private TableColumn<sinistre, String> tfEmail;

    @FXML
    private TableColumn<sinistre, String> tfAdresse;

    @FXML
    private TableColumn<sinistre, String> tfDescription;

    @FXML
    private TableColumn<sinistre, String> tffichier_Accident;

    @FXML
    private TableColumn<sinistre, String> tfID;

    @FXML
    private Button btnAjouter;

    @FXML
    private Button btnsupprimer;

    @FXML
    private Button btnmodifier;

    @FXML
    private Button btnMap;

    @FXML
    private TextField search;

    @FXML
    private Button Exporter;
    @FXML
    private TableColumn<sinistre, String> tfTypeVehicule;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        List<sinistre> sinsitre = sinistreservice.afficher();
        System.out.println(sinsitre);

        ObservableList<sinistre> listsinistre = FXCollections.observableArrayList();
        listsinistre.setAll(sinsitre);
        
        tfID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tflastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        tffirstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        tfEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tfAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        tfDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        tfTypeVehicule.setCellValueFactory(new PropertyValueFactory<>("typeduvehicule"));
        tffichier_Accident.setCellValueFactory(new PropertyValueFactory<>("fichieraccident"));
        
        
       
         table.getItems().setAll(listsinistre);
         
          // code initialisation du tableau
    // ...
         search.textProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != null && (newValue.length() < oldValue.length())) {
                table.setItems(listsinistre);
            }
            String value = newValue.toLowerCase();
            ObservableList<sinistre> subentries = FXCollections.observableArrayList();
            for (sinistre entry : table.getItems()) {
                boolean match = true;
                String firstname = entry.getFirstname();
                String lastname = entry.getLastname();
                String addresse = entry.getAdresse();
                String email = entry.getEmail();

                if (!firstname.toLowerCase().contains(value)
                        && !lastname.toLowerCase().contains(value)
                        && !addresse.toLowerCase().contains(value)
                        && !email.toLowerCase().contains(value)) {
                    match = false;
                }
                if (match) {
                    subentries.add(entry);
                }
            }
            table.setItems(subentries);
        });
    
     
    }    

    @FXML
    private void Ajouter(ActionEvent event) {
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/gui/Ajoutersinistre.fxml"));
            
            Scene sc = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(sc);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ListsinistreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

 
    @FXML
    private void supprimer(ActionEvent event) {
            
          sinistre selectedsinistre = table.getSelectionModel().getSelectedItem();

        if ( selectedsinistre == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No user selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a user in the table.");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm deletion");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete the selected user?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.out.println(selectedsinistre);
            sinistreservice.supprimer(selectedsinistre);
            List<sinistre> vvlist = sinistreservice.afficher();

            // affiche les données dans le tableau
            table.getItems().setAll(vvlist);
        

        
    }

   
    } 

    @FXML
    private void handleMouseAction(MouseEvent event) {
        sinistre s = table.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void modifier(ActionEvent event) {
         try {
             Parent loader = FXMLLoader.load(getClass().getResource("modifiersinistre.fxml"));
             table.getScene().setRoot(loader);
         } catch (IOException ex) {
             Logger.getLogger(ListsinistreController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @FXML
    private void Map(ActionEvent event) {
        
         try {
             root = FXMLLoader.load(getClass().getResource("/gui/Mapsinistre.fxml"));
             stage = (Stage)((Node)event.getSource()).getScene().getWindow();
             scene = new Scene(root);
             stage.setScene(scene);
             stage.show();
         } catch (IOException ex) {
             Logger.getLogger(ListsinistreController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    
    
    private String getPath() {
        String userHome = System.getProperty("user.home");
        String fileSeparator = System.getProperty("file.separator");
        String documentsPath = userHome + fileSeparator + "Documents";
        System.out.println("User's documents path: " + documentsPath);
        return documentsPath;
    }

    @FXML
    private void Exporter(ActionEvent event) {
        try {
            //the file path
//            File file = new File("C:\\Users\\user\\Desktop\\image\\file.csv");
                        File file = new File(getPath() + "\\filesinistre.csv");

            //if the file not exist create one
            if (!file.exists()) {
                file.createNewFile();
            } else {
                file.delete();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("Lastname;Firstname;Email;Adresse;Description;fichier_Accident;id;");
            bw.newLine();
            //loop for jtable rows
            for (int i = 0; i < table.getItems().size(); i++) {
                //loop for jtable column
                for (int j = 0; j < table.getColumns().size(); j++) {
                    bw.write(table.getColumns().get(j).getCellData(i) + ";");
                }
                bw.newLine();
            }
            //close BufferedWriter
            bw.close();
            //close FileWriter 
            fw.close();
            JOptionPane.showMessageDialog(null, "Data Exported");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
   

  

  
        
    
}
