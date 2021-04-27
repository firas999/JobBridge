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
import models.OffreStage;
import utils.DataSource;

/**
 *
 * @author User
 */
public class serviceOffreStage {

    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    private Connection conn;

    public serviceOffreStage() {
        conn = DataSource.getInstance().getCnx();
    }

    public String convertDate(String date){
        String convertedDate="";
            if (date.indexOf("P")+2==date.indexOf("Y")){
                convertedDate+=""+date.charAt(date.indexOf("P")+1)+" year(s), ";
            }else{
                convertedDate+=""+date.charAt(date.indexOf("P")+1)+date.charAt(date.indexOf("P")+2)+" year(s), "; 
            }
            if (date.indexOf("Y")+2==date.indexOf("M")){
                convertedDate+=""+date.charAt(date.indexOf("Y")+1)+" month(s), ";
            }else{
                convertedDate+=""+date.charAt(date.indexOf("Y")+1)+date.charAt(date.indexOf("Y")+2)+" month(s), "; 
            }
            if (date.indexOf("M")+2==date.indexOf("D")){
                convertedDate+=""+date.charAt(date.indexOf("M")+1)+" day(s)";
            }else{
                convertedDate+=""+date.charAt(date.indexOf("M")+1)+date.charAt(date.indexOf("M")+2)+" day(s)"; 
            }
            
        
        return convertedDate;
    }
    public String getNomEntrepriseFromID(int idE) throws SQLException{
       String nom="";   
       String qry="select nom from entreprise where id="+idE+"";
       PreparedStatement pst = conn.prepareStatement(qry);
       ResultSet rs= pst.executeQuery();
while (rs.next()){
    nom=rs.getString("nom");
    System.out.println(rs.getString("nom"));
    
}
return nom;
}
    public List<OffreStage> readAll() {
        String req = "select * from offre_stage";
        List<OffreStage> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new OffreStage(rs.getInt("id"), getNomEntrepriseFromID(rs.getInt("id_entreprise_id")), rs.getString("type_stage"), convertDate(rs.getString("duree")), rs.getString("exigence")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(serviceOffreStage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void addOfferStage(OffreStage OffreStage) {
        String req = "insert into offre_stage (id_entreprise_id,type_stage,duree,exigence) values (?,?,?,?)";
        System.out.println("ok1");
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, OffreStage.getId_entreprise_id());
            pst.setString(2, OffreStage.getType_stage());
            pst.setString(3, OffreStage.getDuree());
            pst.setString(4, OffreStage.getExigence());

            pst.executeUpdate();
            System.out.println("ok2");

        } catch (SQLException ex) {
            Logger.getLogger(serviceOffreStage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void supprimer(int id) {
        try {
            String requete = "DELETE FROM offre_stage WHERE id=?";
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void modifier(OffreStage OffreStage) {
        try {
            String requete = "UPDATE offre_stage  SET type_stage=?, duree=?, exigence=?"
                    + " WHERE id=?";
            PreparedStatement pst = conn.prepareStatement(requete);
            pst = conn.prepareStatement(requete);
            pst.setString(1, OffreStage.getType_stage());
            pst.setString(2, OffreStage.getDuree());
            pst.setString(3, OffreStage.getExigence());
            pst.setInt(4, OffreStage.getId());

            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(serviceOffreStage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<OffreStage> trieE() {
        String req = "select * from offre_stage ORDER BY exigence ";

        List<OffreStage> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new OffreStage(rs.getInt("id"), rs.getInt("id_entreprise_id"), rs.getString("type_stage"), rs.getString("duree"), rs.getString("exigence")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(serviceOffreStage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<OffreStage> trieT() {
        String req = "select * from offre_stage ORDER BY type_stage ";

        List<OffreStage> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new OffreStage(rs.getInt("id"), rs.getInt("id_entreprise_id"), rs.getString("type_stage"), rs.getString("duree"), rs.getString("exigence")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(serviceOffreStage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<OffreStage> trieS() {
        String req = "select * from offre_stage ORDER BY id_entreprise_id ";

        List<OffreStage> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new OffreStage(rs.getInt("id"), rs.getInt("id_entreprise_id"), rs.getString("type_stage"), rs.getString("duree"), rs.getString("exigence")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(serviceOffreStage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public int stat(String type) {
        int i = 0;
        try {

            String qry = "SELECT COUNT(*) FROM offre_stage WHERE type_stage=?";
            PreparedStatement pst = conn.prepareStatement(qry);
            pst.setString(1, type);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                i = rs.getInt(1);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return i;
    }
}
