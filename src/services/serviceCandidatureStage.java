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
import models.OffreStage;
import utils.DataSource;

/**
 *
 * @author User
 */
public class serviceCandidatureStage {
     private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    private Connection conn;

    public serviceCandidatureStage() {
        conn = DataSource.getInstance().getCnx();
    }

    public List<CandidatStage> readAll() {
        String req = "select * from candidat_stage";

        List<CandidatStage> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
  list.add(new CandidatStage(rs.getInt("id"),rs.getInt("identreprise_id"), rs.getInt("idstagiaire_id"),rs.getDate("date_candidature"), rs.getString("email")));
                          }
            

        } catch (SQLException ex) {
            Logger.getLogger(serviceCandidatureStage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public void addCandidatStage(CandidatStage CandidatStage) {
        String req = "insert into candidat_stage (identreprise_id,idstagiaire_id,date_candidature,email) values (?,?,?,?)";
           System.out.println("ok1");
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, CandidatStage.getIdentreprise_id());
            pst.setInt(2, CandidatStage.getIdstagiaire_id());
            pst.setDate(3, CandidatStage.getDate_candidature());
            pst.setString(4, CandidatStage.getEmail());
           

            pst.executeUpdate();
                       System.out.println("ok2");


        } catch (SQLException ex) {
            Logger.getLogger(serviceOffreStage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     public void supprimer(int id) {
      try {
            String requete = "DELETE FROM candidat_stage WHERE id=?";
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }  
    }
      public void modifier(CandidatStage CandidatStagee) {
     try{
        String requete = "UPDATE candidat_stage  SET date_candidature=?, email=?"
                    + " WHERE id=?";
            PreparedStatement pst = conn.prepareStatement(requete);
          pst = conn.prepareStatement(requete);
            pst.setDate(1, CandidatStagee.getDate_candidature());
            pst.setString(2, CandidatStagee.getEmail());
            pst.setInt(3, CandidatStagee.getId());

    pst.executeUpdate();
       } catch (SQLException ex) {
            Logger.getLogger(serviceOffreStage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
