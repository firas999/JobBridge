/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import models.Entreprise;
import models.Formation;
import services.ServiceEntreprise;
import services.ServiceFormation;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class AfficherFormatinFrontController implements Initializable {

   private final List<Formation> FormationList = new ArrayList<>();
      services.ServiceFormation serviceFormation = new ServiceFormation();
    @FXML
    private AnchorPane produits;
    @FXML
    private GridPane grid;

    /**
     * Initializes the controller class.
     */
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        FormationList.addAll(serviceFormation.afficher());
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < FormationList.size(); i++) {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("/Interface/testFormation.fxml"));
                AnchorPane anchorPane = fxmlloader.load();

                TestFormationController AA = fxmlloader.getController();
              
                AA.setData(FormationList.get(i));
                if (column == 2) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row);
              //  GridPane.setMargin(anchorPane,Insets(35));
           
               // GridPane.setMargin(anchorPane, new javafx.geometry.Insets(35));
               // GridPane.setPadding(new Insets(133, 10, 111, 10))
                }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    
    
}
