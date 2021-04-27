/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import services.serviceOffreStage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class StatistiqueOffreStageController implements Initializable {

    @FXML
    private Button offrestage;
    @FXML
    private Button candidatstage;
    @FXML
    private PieChart statoffrestage;
    @FXML
    private Button offreemploi;
    @FXML
    private Button candidatemploi;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        serviceOffreStage ps = new serviceOffreStage();
        int i=ps.stat("PFE");
        int j=ps.stat("Technicien");
        int k=ps.stat("Ouvrier");
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                new PieChart.Data("Ouvrier", k),
                new PieChart.Data("Technicien", j),
                new PieChart.Data("PFE", i));
   statoffrestage.setData(pieChartData);
    }    

    @FXML
    private void offrestage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("OffreStage.fxml"));
        offrestage.getScene().setRoot(root);
    }

    @FXML
    private void candidatstage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CandidatureStage.fxml"));
        offrestage.getScene().setRoot(root);
    }

     @FXML
    private void offreemploi(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AfficherOffreEmploi.fxml"));
        offreemploi.getScene().setRoot(root);
    }

    @FXML
    private void candidatemploi(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("candidatureEmploi.fxml"));
        candidatemploi.getScene().setRoot(root);
    }

    
}
