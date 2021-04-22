/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.CandidatStage;
import models.Evenement;
import utils.DataSource;

/**
 *
 * @author User
 */
public class ServiceEvenement {
      private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    private Connection conn;

    public ServiceEvenement() {
        conn = DataSource.getInstance().getCnx();
    }

    public List<Evenement> readAll() {
        String req = "select * from evenement";

        List<Evenement> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
  list.add(new Evenement(rs.getInt("id"),rs.getString("nom"),rs.getDate("date"), rs.getString("adresse"),rs.getClob("description"),rs.getString("entreprise"),rs.getDouble("prix"),rs.getInt("cap"),rs.getTime("horaire")));
                          }
            

        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
     public void addEvenement(Evenement Evenement) {
        String req = "insert into evenement (nom,date,adresse,description,entreprise,prix,cap,horaire) values (?,?,?,?,?,?,?,?)";
           System.out.println("ok1");
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, Evenement.getNom());
            pst.setDate(2, Evenement.getDate());
            pst.setString(3, Evenement.getDescription());
            pst.setString(4, Evenement.getEntreprise());
            pst.setDouble(5, Evenement.getPrix());
            pst.setInt(6, Evenement.getCap());
            pst.setTime(7, Evenement.getHoraire());
           

            pst.executeUpdate();
                       System.out.println("ok2");


        } catch (SQLException ex) {
            Logger.getLogger(serviceOffreStage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
