/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import entity.Blog;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import Services.BlogService;
import service.EvenementService;


/**
 * FXML Controller class
 *
 * @author hp
 */
public class ShowBlogController implements Initializable {

    @FXML
    private ListView<Blog> listView;
   
    ObservableList<Blog> data;
    
    public static int idE ;
    
    EvenementService ds = new EvenementService();

   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            BlogService cs = new BlogService();
            data = (ObservableList<Blog>) cs.getAllBlog();   
           
            listView.setItems(data);
            listView.setCellFactory((ListView<Blog> param) -> new ListViewBlog());
            
            
            // TODO
        } catch (SQLDataException ex) {
            Logger.getLogger(ShowBlogController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    




    @FXML
    private void handleClose(ActionEvent event) {
    }

    @FXML
    private void GoEvenement(ActionEvent event) {
        
               Parent root;
        try {
          root = FXMLLoader.load(getClass().getResource("/gui/ShowEvenement.fxml"));
          Stage myWindow = (Stage) listView.getScene().getWindow();
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

    

