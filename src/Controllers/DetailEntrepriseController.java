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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class DetailEntrepriseController implements Initializable {

    @FXML
    private TextField tfNomEntreprise;
    @FXML
    private TextField tfSecteurEntreprise;
    @FXML
    private TextField tfMatriculeFiscal;
    @FXML
    private TextField tfSiteEntreprise;
    @FXML
    private TextField tfEmailEntreprise;
    @FXML
    private TextField tfTelephoneEntreprise;
    @FXML
    private TextField tftailleEntreprise;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setTEXTFIELDS(String nom,String secteur,String mf,String site,String email , String tel, String taille){
        tfNomEntreprise.setText(nom);
        tfSecteurEntreprise.setText(secteur);
        tfMatriculeFiscal.setText(mf);
        tfSiteEntreprise.setText(site);
        tfEmailEntreprise.setText(email);
        tfTelephoneEntreprise.setText(tel);
        tftailleEntreprise.setText(taille);
    }
    
}
