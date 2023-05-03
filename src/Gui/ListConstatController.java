/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Model.Constat;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import Services.Constatservice;

/**
 * FXML Controller class
 *
 * @author zaghdoudi
 */
public class ListConstatController implements Initializable {
      private Stage stage;
     private Stage stage1;
     private Scene scene;
     private Parent root;
     
     

    @FXML
    private TableColumn<Constat, String> tftype_accident;
    @FXML
    private TableColumn<Constat, String> tflieu_accident;
    @FXML
    private TableColumn<Constat, String> tfVehiculeA;
    @FXML
    private TableColumn<Constat, String> tfVehiculeB;
    @FXML
    private TableColumn<Constat, String> tfimmatriculeA;
    @FXML
    private TableColumn<Constat, String> tfImmatriculeB;
    @FXML
    private TableColumn<Constat, Date> dpDate_Accident;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnmodifier;
    @FXML
    private TableView<Constat> tableConstat;
    private Constatservice cs = new Constatservice();
    @FXML
    private Button btnexport;
    @FXML
    private TextField recherche;
 
    
    

    /**
     * Initializes the controller class.7
     * tftype_accident.setCellValueFactory(new PropertyValueFactory<>("type_daccident"));
        tflieu_accident.setCellValueFactory(new PropertyValueFactory<>("lieu_daccident"));
        tfVehiculeA.setCellValueFactory(new PropertyValueFactory<>("vehicule_a"));
        tfVehiculeB.setCellValueFactory(new PropertyValueFactory<>("vehicule_b"));
        tfimmatriculeA.setCellValueFactory(new PropertyValueFactory<>("immatricule_a"));
        tfImmatriculeB.setCellValueFactory(new PropertyValueFactory<>("immatricule_b"));
        dpDate_Accident.setCellValueFactory(new PropertyValueFactory<>("date_daccident"));
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
             List<Constat> constat = cs.afficherconstat();
             System.out.println(constat);
             ObservableList<Constat> listConstat = FXCollections.observableArrayList(); 
             listConstat.setAll(constat);
             tftype_accident.setCellValueFactory(new PropertyValueFactory<>("type_daccident"));
             tflieu_accident.setCellValueFactory(new PropertyValueFactory<>("lieu_daccident"));
             tfVehiculeA.setCellValueFactory(new PropertyValueFactory<>("vehicule_a"));
             tfVehiculeB.setCellValueFactory(new PropertyValueFactory<>("vehicule_b"));
             tfimmatriculeA.setCellValueFactory(new PropertyValueFactory<>("immatricule_a"));
             tfImmatriculeB.setCellValueFactory(new PropertyValueFactory<>("immatricule_b"));
             dpDate_Accident.setCellValueFactory(new PropertyValueFactory<>("date_daccident"));
             tableConstat.setItems(listConstat); 
       
     // ...
         recherche.textProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != null && (newValue.length() < oldValue.length())) {
                tableConstat.setItems(listConstat);
            }
            String value = newValue.toLowerCase();
            ObservableList<Constat> subentries = FXCollections.observableArrayList();
            for (Constat entry : tableConstat.getItems()) {
                boolean match = true;
               String Type_Accident = entry.getType_Accident();
                 String Lieu_Accident = entry.getLieu_Accident();
                 String VehiculeA = entry.getVehiculeA();
                 String VehiculeB = entry.getVehiculeB();

                if (!Type_Accident.toLowerCase().contains(value)
                        && !Lieu_Accident.toLowerCase().contains(value)
                        && !VehiculeA.toLowerCase().contains(value)
                        && !VehiculeB.toLowerCase().contains(value)) {
                match = false;
                }
                if (match) {
                    subentries.add(entry);
                }
            }
            tableConstat.setItems(subentries);
        });
    
    
      
    }    

    @FXML
    private void Ajouter(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/gui/AjouterConstat.fxml"));
            
            Scene sc = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(sc);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ListConstatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void supprimer(ActionEvent event) {
            Constat selectedconstat = tableConstat.getSelectionModel().getSelectedItem();

        if ( selectedconstat == null) {
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
            System.out.println(selectedconstat);
             cs.supprimer(selectedconstat);
            List<Constat> cclist = cs.afficherconstat();

            // affiche les données dans le tableau
            tableConstat.getItems().setAll(cclist);
    }
    }

    @FXML
    private void modifier(ActionEvent event) {
          try {
              Parent loader = FXMLLoader.load(getClass().getResource("modifierConstat.fxml"));
              tableConstat.getScene().setRoot(loader);
          } catch (IOException ex) {
              Logger.getLogger(ListConstatController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void export(ActionEvent event) {
         try {
            //the file path
//            File file = new File("C:\\Users\\user\\Desktop\\image\\file.csv");
            File file = new File(getPath() + "\\fileConstat.csv");

            //if the file not exist create one
            if (!file.exists()) {
                file.createNewFile();
            } else {
                file.delete();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("type_daccident;lieu_daccident;vehicule_a;vehicule_b;immatricule_a;date_daccident;");
            bw.newLine();
            //loop for jtable rows
            for (int i = 0; i < tableConstat.getItems().size(); i++) {
                //loop for jtable column
                for (int j = 0; j < tableConstat.getColumns().size(); j++) {
                    bw.write(tableConstat.getColumns().get(j).getCellData(i) + ";");
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
    

