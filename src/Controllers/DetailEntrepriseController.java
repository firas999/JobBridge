/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class DetailEntrepriseController implements Initializable {

    private TextField tfNomEntreprise;
    private TextField tfSecteurEntreprise;
    private TextField tfMatriculeFiscal;
    private Label tfSiteEntreprise;
    private TextField tfEmailEntreprise;
    private Label tfTelephoneEntreprise;
    private TextField tftailleEntreprise;
    @FXML
    private ImageView imagelab;
    @FXML
    private Label testNom;
    @FXML
    private Label Site;
    @FXML
    private Label tele;
    @FXML
    private Label secteur;
    @FXML
    private Label mf;
    @FXML
    private Label email;
    @FXML
    private Label taille;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setTEXTFIELDS(String nom,String secteur,String mf,String site,String email , String tel, String taille,String path){
       this.secteur.setText(secteur);
        this.mf.setText(mf);
       Site.setText(site);
        this.email.setText(email);
        tele.setText(tel);
        this.taille.setText(taille);
               Image image = new Image(getClass().getResourceAsStream(path));
    imagelab.setImage(image);
    testNom.setText(nom);
    } 
    
}
