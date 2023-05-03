/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Gui;

import Model.Constat;
import java.net.URL;
import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import Services.Constatservice;

/**
 * FXML Controller class
 *
 * @author zaghdoudi
 */
public class ModifierConstatController implements Initializable {

    @FXML
    private TableColumn<Constat, Integer> tfID;
    @FXML
    private TableColumn<Constat, String> tftype_accident;
    @FXML
    private TableColumn<Constat, String> tflieu_accident;
    @FXML
    private TableColumn<Constat, String> tfvehiculeA;
    @FXML
    private TableColumn<Constat, String> tfvehiculeB;
    @FXML
    private TableColumn<Constat, String> tfimmatriculeA;
    @FXML
    private TableColumn<Constat, String> tfimmatriculeB;
    @FXML
    private TableColumn<Constat, Date> dpdate_accident;
    @FXML
    private TextField tfId;
    
     @FXML
    private ComboBox<String> tftype_Accident;

    @FXML
    private TextField tflieu_Accident;

    @FXML
    private TextField tfVehiculeA;
    @FXML
    private TextField tfVehiculeB;
    @FXML
    private TextField tfImmatriculeA;
    @FXML
    private TextField tfImmatriculeB;
    @FXML
    private DatePicker tfdate_accident;
    @FXML
    private Button btnmodifier;
    @FXML
    private TableView<Constat> tableConstat;
    private Constatservice cs = new Constatservice();
     int index = -1;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  List<Constat> constat = cs.afficherconstat();
             System.out.println(constat);
             ObservableList<Constat> listConstat = FXCollections.observableArrayList(); 
             listConstat.setAll(constat);
             tftype_accident.setCellValueFactory(new PropertyValueFactory<>("type_daccident"));
             tflieu_accident.setCellValueFactory(new PropertyValueFactory<>("lieu_daccident"));
             tfvehiculeA.setCellValueFactory(new PropertyValueFactory<>("vehicule_a"));
             tfvehiculeB.setCellValueFactory(new PropertyValueFactory<>("vehicule_b"));
             tfimmatriculeA.setCellValueFactory(new PropertyValueFactory<>("immatricule_a"));
             tfimmatriculeB.setCellValueFactory(new PropertyValueFactory<>("immatricule_b"));
             dpdate_accident.setCellValueFactory(new PropertyValueFactory<>("date_daccident"));
             tableConstat.setItems(listConstat); 
           }    

    @FXML
    private void modifier(ActionEvent event) {
            Date startDate = Date.valueOf(tfdate_accident.getValue().toString());

        int id = tableConstat.getSelectionModel().getSelectedItem().getId();
       


//        os.modifier(Integer.parseInt(proprietaire.getText()), nom.getText(), secteur.getText(), description.getText(), competence.getText(), startDate, endDate, typeOffre.getSelectionModel().getSelectedItem(), id);
//        this.onStart();
        int response = JOptionPane.showConfirmDialog(null, "voulez vous confirmer les modifications de " + id + "?", "Confirmer", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            System.out.println("User clicked Yes");
            cs.modifier(id,tftype_Accident.getSelectionModel().getSelectedItem(),tflieu_Accident.getText(),tfVehiculeA.getText(),tfVehiculeB.getText(),tfImmatriculeA.getText(),tfImmatriculeB.getText(),startDate);
        } else {
            System.out.println("User clicked No");
        }
        
        
    }

    @FXML
    private void getSelected(MouseEvent event) {
        
          index= tableConstat.getSelectionModel().getSelectedIndex();
             if (index<=-1) {
                 return;
             }
               
               tftype_accident.setText(tftype_accident.getCellData(index).toString());
               tflieu_Accident.setText(tflieu_accident.getCellData(index).toString());
               tfVehiculeA.setText(tfvehiculeA.getCellData(index).toString());
               tfVehiculeB.setText(tfvehiculeB.getCellData(index).toString());
               tfImmatriculeA.setText(tfimmatriculeA.getCellData(index).toString());
               tfImmatriculeB.setText(tfimmatriculeB.getCellData(index).toString());
               Format formatter = new SimpleDateFormat("yyyy-MM-dd");
               String date_accident = formatter.format(dpdate_accident.getCellData(index));
                tfdate_accident.setValue(LocalDate.parse(date_accident));
               tftype_Accident.setValue(tftype_accident.getCellData(index).toString());
 
    }

}
