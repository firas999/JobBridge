/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import models.Formation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class TestFormationController implements Initializable {

    @FXML
    private ImageView imagelab;
    @FXML
    private Label DescriptionFormation;
    @FXML
    private Label VHformation;
    @FXML
    private Label DateFormation;
    @FXML
    private Label AdresseFormation;
    @FXML
    private Label PrixFormation;
    @FXML
    private Label FormationEntreprise;
    @FXML
    private Label PromoFormation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     public void setData(Formation F) {
     //   Prod = P;
        DescriptionFormation.setText(F.getDescription());
        VHformation.setText(String.valueOf(F.getVolumeHoraire()));
        DateFormation.setText(String.valueOf(F.getDate_formation()));
        AdresseFormation.setText(F.getAdresse());
        PrixFormation.setText(String.valueOf(F.getPrix()));
        FormationEntreprise.setText(F.getNomEntreprise());
        if (F.getPromo()==1)
            PromoFormation.setText("En promotion");
        else 
            PromoFormation.setVisible(false);
            
        
         System.out.println(F.getImage());
         String path = "/"+F.getImage();
         path = path.replace("\\","/"); 
        System.out.println("****************");
         System.out.println("correct path : "+path);
         
       Image image = new Image(getClass().getResourceAsStream(path));
       imagelab.setImage(image);
       
}
    
}
