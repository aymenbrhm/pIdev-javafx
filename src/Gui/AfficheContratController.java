/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Model.Contrat;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ContratService;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AfficheContratController implements Initializable {

    @FXML
    private TableColumn<Contrat, Date> dated;
    @FXML
    private TableColumn<Contrat, Date> datef;
    @FXML
    private TableColumn<Contrat, String> type;
    @FXML
    private TableColumn<Contrat, String> mat;
    @FXML
    private TableView<Contrat> tab;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       

        table();
        //  tfLastname.setCellValueFactory(new PropertyValueFactory<>("Lastname"));
        dated.setCellValueFactory(new PropertyValueFactory<>("datedeb"));
        datef.setCellValueFactory(new PropertyValueFactory<>("datefin"));
        type.setCellValueFactory(new PropertyValueFactory<>("type de contrat"));
        mat.setCellValueFactory(new PropertyValueFactory<>("Matricule"));
        

    
    }    
    public void table()  {
          ObservableList<Contrat> offers = new ContratService().fetchContrats();
        int myIndex = tab.getSelectionModel().getSelectedIndex();
          
      tab.setItems(offers);    
      dated.setCellValueFactory(new PropertyValueFactory<>("datedeb"));
      datef.setCellValueFactory(new PropertyValueFactory<>("datefin"));  
      type.setCellValueFactory(new PropertyValueFactory<>("type de contrat"));  
      mat.setCellValueFactory(new PropertyValueFactory<>("Matricule"));  
      System.out.println(offers);

        
 


}
}