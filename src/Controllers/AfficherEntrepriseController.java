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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import models.Entreprise;
import services.ServiceEntreprise;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class AfficherEntrepriseController implements Initializable {
private final List<Entreprise> entrepriseList = new ArrayList<>();
    ServiceEntreprise ES = new ServiceEntreprise();
    @FXML
    private AnchorPane produits;
    @FXML
    private GridPane grid;

    /**
     * Initializes the controller class.
     */
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        entrepriseList.addAll(ES.afficher());
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < entrepriseList.size(); i++) {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("/Interface/test.fxml"));
                AnchorPane anchorPane = fxmlloader.load();

                TestController AA = fxmlloader.getController();
               //  AA.setData(produit.get(0));
                AA.setData(entrepriseList.get(i));
                if (column == 4) {
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
