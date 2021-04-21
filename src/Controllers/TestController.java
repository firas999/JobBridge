/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Entreprise;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class TestController implements Initializable {

    @FXML
    private ImageView imagelab;
    @FXML
    private Label Nomlab;
    @FXML
    private Label Prixlab;
    @FXML
    private Label Quantitelab;
    @FXML
    private Button Commander;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setData(Entreprise E) {
     //   Prod = P;
        Nomlab.setText(E.getNom());
        Prixlab.setText(String.valueOf(E.getNom()));
        Quantitelab.setText(String.valueOf(E.getSecteur()));
       /*Image image = new Image(getClass().getResourceAsStream(E.getImage()));
       imagelab.setImage(image);
       */
}

    @FXML
    private void Passer_Commande(ActionEvent event) {
    }
    
}
