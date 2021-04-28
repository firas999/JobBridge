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
    private Label Nom;
    private Label secteur;
    @FXML
    private Button Commander;
    @FXML
    private Label NomEntreprise;
    @FXML
    private Label secteurEntreprise;
    @FXML
    private Label MFEntreprise;
    @FXML
    private Label SiteEntreprise;
    @FXML
    private Label EmailEntreprise;
    @FXML
    private Label TeleEntreprise1;
    @FXML
    private Label TailleEntreprise;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setData(Entreprise E) {
     //   Prod = P;
        NomEntreprise.setText(E.getNom());
        secteurEntreprise.setText(E.getSecteur());
        MFEntreprise.setText(E.getMatriculeFiscal());
        SiteEntreprise.setText(E.getSiteWeb());
        EmailEntreprise.setText(E.getEmail());
        TeleEntreprise1.setText(String.valueOf(E.getTelephone()));
         TailleEntreprise.setText(String.valueOf(E.getTaille()));
        
       System.out.println(E.getImage());
         String path = "/"+E.getImage();
         path = path.replace("\\","/");
         
         System.out.println("correct path : "+path);
         
       Image image = new Image(getClass().getResourceAsStream(path));
       imagelab.setImage(image);
}

    @FXML
    private void Passer_Commande(ActionEvent event) {
    }
    
}
