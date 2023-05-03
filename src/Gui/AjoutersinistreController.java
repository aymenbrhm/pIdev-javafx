/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import API.EnvoyerEmail;
import Model.sinistre;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import Services.sinistreservice;
import util.MyConnection;

/**
 * FXML Controller class
 *
 * @author zaghdoudi
 */
public class AjoutersinistreController implements Initializable {
    
    private Stage stage;
      private Stage stage1;
     private Scene scene;
     private Parent root;


    @FXML
    private TextField tfFirstname;
    @FXML
    private TextField tfLastname;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfAdresse;
    @FXML
    private TextField tfFichier_Accident;
    @FXML
    private Button btnAjouter;
    @FXML
    private TextField tfDescription;
    @FXML
    private Button btnretour;
    // initialiser le compteur de sinistres
    @FXML
    private TextField tfType_vehicule;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    

 

@FXML
    private void Ajoutersinistre(ActionEvent event) {
    String Firstname = tfFirstname.getText();
    String lastname = tfLastname.getText();
    String Email = tfEmail.getText();
    String Adresse = tfAdresse.getText();
    String Description = tfDescription.getText();
     String TypeVehicule = tfType_vehicule.getText();
    String fichier_Accident = tfFichier_Accident.getText();
    
    // Vérifier que tous les champs sont remplis
    if (Firstname.isEmpty() || lastname.isEmpty() || Email.isEmpty() || Adresse.isEmpty() || Description.isEmpty() ||TypeVehicule
            .isEmpty() || fichier_Accident.isEmpty()) {
        // Afficher une boîte de dialogue d'erreur
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("Tous les champs sont obligatoires !");
        alert.showAndWait();
    } else if (!Email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
        // Afficher une boîte de dialogue d'erreur
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("L'adresse e-mail n'est pas valide !");
        alert.showAndWait();
    } else if(nombreSinistres(lastname,Firstname)>=2){
    
        try {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("nom et prenom ont déja utilisé un remorquage");
            alert.setHeaderText("Tu as le droit ajouter deux sinistre.Tu reçois un email pour plus de détails");
            alert.showAndWait();
            
            
            
            String destinataire = "jesserzaghdoudi@gmail.com";
            int code = new Random().nextInt(99999);
            EnvoyerEmail.envoyer(destinataire, code);
        } catch (MessagingException ex) {
            Logger.getLogger(AjoutersinistreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        else{
                 // Traiter les données
        sinistre s = new sinistre(Firstname,lastname,Email,Adresse,Description,TypeVehicule,fichier_Accident);
        sinistreservice ss = new sinistreservice();
        ss.ajouter(s);
                }
              
      }
    

    public int nombreSinistres(String lastname, String Firstname ) {
    int nombreSinistres = 0;
    try {
        String requete = "SELECT COUNT(*) as nbsinistre FROM sinstre WHERE lastname=? AND firstname=?";
        PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(requete);
        ps.setString(1, lastname);
        ps.setString(2, lastname);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            nombreSinistres = rs.getInt("nbsinistre");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return nombreSinistres;
    }

@FXML
private void retour(ActionEvent event) {
    try {
        root = FXMLLoader.load(getClass().getResource("/gui/listsinistre.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(AjoutersinistreController.class.getName()).log(Level.SEVERE, null, ex);
    }
}


}
