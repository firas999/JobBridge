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
import models.Participant;
import utils.DataSource;

/**
 *
 * @author User
 */
public class ServiceParticipant {
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    private Connection conn;

    public ServiceParticipant() {
        conn = DataSource.getInstance().getCnx();
    }

    public List<Participant> readAll() {
        String req = "select * from participant";

        List<Participant> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
  list.add(new Participant(rs.getInt("id"),rs.getString("type_participant"), rs.getInt("id_evenement_id"),rs.getString("nom"), rs.getString("prenom"),rs.getString("mail")));
                          }
            

        } catch (SQLException ex) {
            Logger.getLogger(ServiceParticipant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public void addParticipant(Participant Participant) {
        String req = "insert into participant (type_participant,id_evenement_id,nom,prenom,mail) values (?,?,?,?,?)";
           System.out.println("ok1");
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, Participant.getType_participant());
            pst.setInt(2, Participant.getId_evenement_id());
            pst.setString(3, Participant.getNom());
            pst.setString(4, Participant.getPrenom());
           pst.setString(5, Participant.getMail());

            pst.executeUpdate();
                       System.out.println("ok2");


        } catch (SQLException ex) {
            Logger.getLogger(ServiceParticipant.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     public void supprimer(int id) {
      try {
            String requete = "DELETE FROM participant WHERE id=?";
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }  
    }
      public void modifier(Participant Participant) {
     try{
        String requete = "UPDATE participant  SET type_participant=?, nom=? , prenom=? , mail=?" 
                    + " WHERE id=?";
             
            PreparedStatement pst = conn.prepareStatement(requete);
          pst = conn.prepareStatement(requete);
          pst.setString(1, Participant.getType_participant());
            pst.setString(2, Participant.getNom());
            pst.setString(3, Participant.getPrenom());
           pst.setString(4, Participant.getMail());
           pst.setInt(5, Participant.getId());

    pst.executeUpdate();
       } catch (SQLException ex) {
            Logger.getLogger(ServiceParticipant.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}

