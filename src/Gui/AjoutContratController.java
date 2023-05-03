package Gui;

import Interfaces.ContratInterface;
import Model.Contrat;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import com.gluonhq.charm.glisten.control.DropdownButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.MenuItem;
import Services.ContratService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;


public class AjoutContratController implements Initializable {

    @FXML
    private Label titre;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Button button;
    @FXML // fx:id="fruitCombo"
    private ComboBox<String> comboBox1;
    @FXML
    private ComboBox<Integer> comboBox2;
    @FXML
    private ComboBox<String> comboBox3;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // Add event handlers for each DropdownButton

    ajout();
     // populate the fruit combo box with item choices.
    comboBox1.getItems().setAll("L'assurance au tiers", "L'assurance au tiers plus", "L'assurance tous risques","L'assurance auto au kilomètre");
    comboBox2.getItems().setAll(3, 6, 12);
    comboBox3.getItems().setAll("22Tu2222", "33tu3333", "44tu4444");
}

  @FXML
private void ajout() {
    String selectedValue1 = comboBox1.getSelectionModel().getSelectedItem();;
    Integer selectedValue2 = comboBox2.getSelectionModel().getSelectedItem();;
    String selectedValue3 = comboBox3.getSelectionModel().getSelectedItem();;

    if (selectedValue1 != null && selectedValue2 != null) {
        Contrat c = new Contrat();
        c.setType(selectedValue1);
        c.setPeriode(selectedValue2);
        c.setMatricule(selectedValue3);
        
        ContratInterface contratService = new ContratService();
        contratService.addContrat2(c);
        
    }

}}

    
    

