/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class AcceuilController implements Initializable {

    @FXML
    private Button afficheremployer;
    @FXML
    private Button affichercandidat;
    @FXML
    private Button afficherstat;
    @FXML
    private Button logout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void affichageemployer(ActionEvent event) {
   FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/afficherEmployer.fxml"));

        try {
            Parent root = loader.load();
            afficheremployer.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    }
      @FXML
    private void affichagecandidat(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AfficherCandidat.fxml"));
          try {
            Parent root = loader.load();
            affichercandidat.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    }
    

    @FXML
    private void affichageStat(ActionEvent event) {
       
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/stat.fxml"));
        try {
            Parent root = loader.load();
            afficherstat.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
        
    }

    @FXML
    private void Logout(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Login.fxml"));
        try {
            Parent root = loader.load();
            logout.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    }
    
    
}
    
