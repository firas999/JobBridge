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
import utils.DataSource;

/**
 *
 * @author dell
 */
public class ServiceEntreprise implements Iservices.IserviceEntreprise{
    public ObservableList<Entreprise> data=FXCollections.observableArrayList();

    private  Connection conn;
    public ServiceEntreprise(){
        conn= DataSource.getInstance().getCnx();
    }
    public void ajouter(Entreprise E) {
         try {
            String requete = "INSERT INTO entreprise(secteur, site_web, taille, telephone, email, nom, matricule_fiscal)"
                    + " VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1,E.getSecteur());
            pst.setString(2,E.getSiteWeb());
            pst.setInt(3,E.getTaille());
            pst.setInt(4,E.getTelephone());
            pst.setString(5,E.getEmail());
            pst.setString(6,E.getNom());
            pst.setString(7,E.getMatriculeFiscal());
            pst.executeUpdate();
             
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(EntrepriseController.class.getName()).log(Level.SEVERE, null, ex);
        }
          
        
    }

    
    public void supprimer(int id) {
      try {
            String requete = "DELETE FROM Entreprise WHERE id=?";
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }  
    }

    public void modifierChamp(String champ,String Valeur,int id){
     try{
        
        String requete = "UPDATE entreprise  SET "+champ+"=?"
                    + " WHERE id=?";
            PreparedStatement pst = conn.prepareStatement(requete);
            
            pst.setString(1,Valeur);
            pst.setInt(2,id);
            pst.executeUpdate();
       } catch (SQLException ex) {
            Logger.getLogger(EntrepriseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void modifier(Entreprise E) {
     try{
        String requete = "UPDATE entreprise  SET  secteur=? , site_web=?, taille=?, telephone=?, email=?, nom=?, matricule_fiscal=?"
                    + " WHERE id=?";
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1,E.getSecteur());
            pst.setString(2,E.getSiteWeb());
            pst.setInt(3,E.getTaille());
            pst.setInt(4,E.getTelephone());
            pst.setString(5,E.getEmail());
            pst.setString(6,E.getNom());
            pst.setString(7,E.getMatriculeFiscal());
            pst.setInt(8,E.getId());
            pst.executeUpdate();
       } catch (SQLException ex) {
            Logger.getLogger(EntrepriseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public List afficher() {
        try {
            
    
           ResultSet rs = conn.createStatement().executeQuery("select * from entreprise");
            while (rs.next()){
                data.add(new Entreprise(rs.getInt("id"),rs.getString("secteur"),rs.getString("site_Web"),
                        rs.getInt("taille"),rs.getInt("telephone"),rs.getString("email"),rs.getString("nom"),
                        rs.getString("matricule_fiscal")));
                System.out.println("************"+rs.getString("secteur") + rs.getString("site_Web")+
                        rs.getInt("taille")+rs.getInt("telephone")+rs.getString("email")+rs.getString("nom")+
                        rs.getString("matricule_fiscal"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EntrepriseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
        
    }

    
    public void supprimerFormationCreerParEntreprise(int id) {
        try {
            String requete = "DELETE  FROM formation WHERE entreprise_id=?";
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }  
    }
    
    public void ajouter(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void modifier(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    

   
    
}
