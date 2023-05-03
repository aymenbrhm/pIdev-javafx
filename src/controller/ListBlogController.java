/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Blog;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import Services.BlogService;

/**
 * FXML Controller class
 *
 * @author Ala Jaidi
 */
public class ListBlogController implements Initializable {

    @FXML
    private TableView<Blog> table;
    @FXML
    private TableColumn<Blog, String> nom;
    @FXML
    private TableColumn<Blog, Date> date;
    @FXML
    private TableColumn<Blog, String> description;

        
    private ObservableList<Blog> UserData = FXCollections.observableArrayList();
    
    BlogService cs = new BlogService();
    
    static int idBlog ;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
              try {
            List<Blog> listb= new ArrayList<Blog>();
            
            listb = cs.getAllBlog();
     
            UserData.clear();
            UserData.addAll(listb);
            table.setItems(UserData);
            
            description.setCellValueFactory
                      (
                              new PropertyValueFactory<>("description")
                      );
                        nom.setCellValueFactory
                      (
                              new PropertyValueFactory<>("nomblog")
                      );
                                    date.setCellValueFactory
                      (
                              new PropertyValueFactory<>("date")
                      );
        } catch (SQLDataException ex) {
            Logger.getLogger(ListBlogController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void Ajouter(ActionEvent event) {
        
       Parent root;
        try {
          root = FXMLLoader.load(getClass().getResource("/gui/AjouterBlog.fxml"));
          Stage myWindow = (Stage) table.getScene().getWindow();
          Scene sc = new Scene(root);
          myWindow.setScene(sc);
          myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
          myWindow.show();
          } catch (IOException ex) {
          Logger.getLogger(AjouterBlogController.class.getName()).log(Level.SEVERE, null, ex);
           }
        
    }

    @FXML
    private void Supprimer(ActionEvent event) throws SQLDataException {
        
                
        cs.deleteBlog(table.getSelectionModel().getSelectedItem().getId());
        resetTableData();
        
        
    }

    @FXML
    private void Modifier(ActionEvent event) {
        idBlog  =  table.getSelectionModel().getSelectedItem().getId();     

               Parent root;
        try {
          root = FXMLLoader.load(getClass().getResource("/gui/ModifierBlog.fxml"));
          Stage myWindow = (Stage) table.getScene().getWindow();
          Scene sc = new Scene(root);
          myWindow.setScene(sc);
          myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
          myWindow.show();
          } catch (IOException ex) {
          Logger.getLogger(AjouterBlogController.class.getName()).log(Level.SEVERE, null, ex);
           }
    }
    
    
    
        public void resetTableData() throws SQLDataException
    {
        List<Blog> lisre = new ArrayList<>();
        lisre = cs.getAllBlog();
        ObservableList<Blog> data = FXCollections.observableArrayList(lisre);
        table.setItems(data);
    }

    @FXML
    private void GoEvenemet(ActionEvent event) {
        
               Parent root;
        try {
          root = FXMLLoader.load(getClass().getResource("/gui/ListEvenement.fxml"));
          Stage myWindow = (Stage) table.getScene().getWindow();
          Scene sc = new Scene(root);
          myWindow.setScene(sc);
          myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
          myWindow.show();
          } catch (IOException ex) {
          Logger.getLogger(AjouterBlogController.class.getName()).log(Level.SEVERE, null, ex);
           }
        
    }
    
    
    
}
