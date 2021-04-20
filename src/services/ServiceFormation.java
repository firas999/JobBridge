/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Controllers.EntrepriseController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Entreprise;
import models.Formation;
import utils.DataSource;

/**
 *
 * @author dell
 */
public class ServiceFormation implements Iservices.IserviceFormation {
public ObservableList<Formation> data=FXCollections.observableArrayList();
public ObservableList<Entreprise> options=FXCollections.observableArrayList();

    private  Connection conn;
    public ServiceFormation(){
        conn= DataSource.getInstance().getCnx();
    }
    
    
    
    public void ajouter(Formation F,String NE) {
        int idE=0;
     try {
         String qry="select id from entreprise where nom='"+NE+"'";
       PreparedStatement pst1 = conn.prepareStatement(qry);
       ResultSet rs= pst1.executeQuery();
while (rs.next()){
    idE=rs.getInt("id");
    
    
}
         
            String requete = "INSERT INTO formation(description,entreprise_id,volume_horaire, date_formation,adresse, prix, promo)"
                    + " VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1,F.getDescription());
            pst.setInt(2,idE);
            pst.setInt(3,F.getVolumeHoraire());
            pst.setDate(4,F.getDate_formation());
            pst.setString(5,F.getAdresse());
            pst.setInt(6,F.getPrix());
            pst.setInt(7,F.getPromo());
            pst.executeUpdate();
             
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(EntrepriseController.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }

    @Override
    public void supprimer(int id) {
         try {
            String requete = "DELETE FROM formation WHERE id=?";
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }  
    }

   public void modifier(Formation F) {
    try{
        String requete = "UPDATE formation  SET  description=?,volume_horaire=?, date_formation=?,adresse=?, prix=?, promo=?"
                    + " WHERE id=?";
            PreparedStatement pst = conn.prepareStatement(requete);
               pst.setString(1,F.getDescription());
            pst.setInt(2,F.getVolumeHoraire());
            pst.setDate(3, new java.sql.Date(F.getDate_formation().getTime()));
            pst.setString(4,F.getAdresse());
            pst.setInt(5,F.getPrix());
            pst.setInt(6,F.getPromo());
            pst.setInt(7,F.getId());
            pst.executeUpdate();
       } catch (SQLException ex) {
            Logger.getLogger(EntrepriseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
   public List selectEntreprises() throws SQLException{
       String qry="select * from entreprise";
       PreparedStatement pst = conn.prepareStatement(qry);
       ResultSet rs= pst.executeQuery();
while (rs.next()){
    options.add(new Entreprise(rs.getInt("id"),rs.getString("nom")));
    
}
       return options;
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
   
    @Override
    public List afficher() {
      try {
    
           ResultSet rs = conn.createStatement().executeQuery("select * from formation");
            while (rs.next()){
                
                    
                data.add(new Formation(rs.getInt("id"),getNomEntrepriseFromID(rs.getInt("entreprise_id")),rs.getString("description"),rs.getInt("volume_horaire"),
                        rs.getDate("date_formation"),rs.getString("adresse"),rs.getInt("prix"),rs.getInt("promo")));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(EntrepriseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    @Override
    public void ajouter(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void getIDEntreprise(String nomEntreprise) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
