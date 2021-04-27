/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class StatController implements Initializable {

    @FXML
    private PieChart statMembre;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            // TODO
            loadDataPie();
        } catch (SQLException ex) {
            Logger.getLogger(StatController.class.getName()).log(Level.SEVERE, null, ex);
        }
        statMembre.setData(piechartdata);
    }    
    ObservableList<PieChart.Data> piechartdata;
     Connection cnx;
    ResultSet rs;
     public void loadDataPie() throws SQLException{
        piechartdata = FXCollections.observableArrayList();
        String dbUsername = "root";
        String dbPassword = "";
        String dbURL = "jdbc:mysql://127.0.0.1:3307/projet_recrutement1";
        
  
        
        cnx = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        PreparedStatement pst = cnx.prepareStatement("SELECT discr as 'candidats', COUNT(discr) as 'total' FROM user GROUP BY discr  ");
        rs=pst.executeQuery();
        
        while(rs.next()){
            piechartdata.add(new PieChart.Data(rs.getString("candidats"),rs.getInt("total")));

        }  
    }

    @FXML
    private void backtoacceuil(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Acceuil.fxml"));
        try {
            Parent root = loader.load();
            back.getScene().setRoot(root);
        } catch (IOException ex) {
           
    }
    }
}
