/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Model.sinistre;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import Services.sinistreservice;

/**
 * FXML Controller class
 *
 * @author zaghdoudi
 */
public class ModifiersinistreController implements Initializable {

    @FXML
    private TextField tfLastname;
    @FXML
    private TextField tfFirstname;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfAdresse;
    @FXML
    private TextField TfDescription;
    @FXML
    private TextField tfFichier_accident;
    @FXML
    private Button btnModifier;
    @FXML
    private TableView<sinistre> table;
        private sinistreservice sinistreservice = new sinistreservice();
         int index = -1;

    @FXML
    private TableColumn<sinistre, String> tflastname;
    @FXML
    private TableColumn<sinistre, String> tffirstname;
    @FXML
    private TableColumn<sinistre, String> tfemail;
    @FXML
    private TableColumn<sinistre, String> tfadress;
    @FXML
    private TableColumn<sinistre, String> tfFichier_Accident;
    @FXML
    private TableColumn<sinistre, String> tfDescription;
    private TableColumn<sinistre, String> tfvehicul;
    @FXML
    private TableColumn<sinistre, Integer> tfID;
    @FXML
    private TextField tfiD;
    @FXML
    private TableColumn<sinistre, String> tfvehicul12;
    @FXML
    private TextField tfvehicule;

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
        tfemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tfadress.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        tfDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        tfvehicul.setCellValueFactory(new PropertyValueFactory<>("typeduvehicule"));
        tfFichier_Accident.setCellValueFactory(new PropertyValueFactory<>("fichieraccident"));
        
        
       
         table.getItems().setAll(listsinistre);
    }    

    @FXML
    private void Modifier(ActionEvent event) {
        
        int id = table.getSelectionModel().getSelectedItem().getId();
        


//        os.modifier(Integer.parseInt(proprietaire.getText()), nom.getText(), secteur.getText(), description.getText(), competence.getText(), startDate, endDate, typeOffre.getSelectionModel().getSelectedItem(), id);
//        this.onStart();
        int response = JOptionPane.showConfirmDialog(null, "voulez vous confirmer les modifications de " + id + "?", "Confirmer", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            System.out.println("User clicked Yes");
            sinistreservice.modifier(id,tffirstname.getText(),tflastname.getText(),tfemail.getText(),tfadress.getText(),tfDescription.getText(),tfvehicul.getText(),tfFichier_Accident.getText());
        } else {
            System.out.println("User clicked No");
        }
    }

    @FXML
    private void getSelected(MouseEvent event) {
         index= table.getSelectionModel().getSelectedIndex();
             if (index<=-1) {
                 return;
             }
             tfiD.setText(tfID.getCellData(index).toString());
             tfFirstname.setText(tffirstname.getCellData(index).toString());
             
               tfLastname.setText(tflastname.getCellData(index).toString());
               tfEmail.setText(tfemail.getCellData(index).toString());
               tfAdresse.setText(tfadress.getCellData(index).toString());
               TfDescription.setText(tfDescription.getCellData(index).toString());
               tfvehicule.setText(tfvehicul.getCellData(index).toString());
               tfFichier_accident.setText(tfFichier_Accident.getCellData(index).toString());
    
    }
    
}
