/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.EntrepriseController;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Entreprise;
import utils.DataSource;

/**
 *
 * @author User
 */
public class ServiceStagiaire implements IService.IServiceStagiaire{
        public ObservableList<Entreprise> data=FXCollections.observableArrayList();

    private  Connection conn;
    public ServiceStagiaire(){
        conn= DataSource.getInstance().getCnx();
    }
     public List afficherIdNom() {
        try {
    
           ResultSet rs = conn.createStatement().executeQuery("select id,nom from stagiaire");
            while (rs.next()){
                data.add(new Entreprise(rs.getInt("id"),rs.getString("nom")));
                System.out.println(rs.getString("nom"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EntrepriseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
        
    }
}
