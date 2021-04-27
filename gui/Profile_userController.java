/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Models.user;
import Utils.UserSession;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class Profile_userController implements Initializable {

    @FXML
    private Text labelll;
    @FXML
    private Circle circle;
    @FXML
    private Circle circle1;
    @FXML
    private Text nom;
    @FXML
    private Text prenom;
    @FXML
    private Text type;
    @FXML
    private Text id;
    @FXML
    private Text nbrfollow;
     user u = UserSession.getInstance().getLoggedUser();
    @FXML
    private Button btn_back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       labelll.setText(u.getNom()+" "+u.getPrenom());
                 Image image ;
                image = new Image(u.getImg());
               
                circle.setFill(new ImagePattern(image));
                nom.setText(u.getNom());
                prenom.setText(u.getPrenom());
                type.setText(u.getType());
                String nb= String.valueOf(u.getNbr_follow());
                nbrfollow.setText(nb);
                
                   Image image1 ;
                image1 = new Image(u.getImg());
               
                circle1.setFill(new ImagePattern(image1));
                       
    }    

    @FXML
    private void backtoAffichage(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AfficherCandidat.fxml"));
        try {
            Parent root = loader.load();
            btn_back.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    }
    
}
