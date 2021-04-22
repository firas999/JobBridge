/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.sun.istack.internal.logging.Logger;
import entity.Certification;
import entity.DemandeCertification;
import entity.Entreprise;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import utils.DataSource;


/**
 *
 * @author ouaji
 */
public class DemandeCertificationService implements IService<DemandeCertification> {

       private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    private Connection conn1;

    public DemandeCertificationService() {
        conn1 = DataSource.getInstance().getCnx();
    }

    @Override
    public void insert(DemandeCertification t) {
String req = "insert into demande_certification (certification_id,nom_participant,prenom_participant,date_demande,experience_participant,email) values (?,?,?,?,?,?)";

        try {
            pst = conn1.prepareStatement(req);
            pst.setInt(1, t.getCertification_id());
            pst.setString(2, t.getNom_participant());
            pst.setString(3, t.getPrenom_participant());
            pst.setDate(4, t.getDate_demande());
            pst.setString(5, t.getExperience_participant());
            pst.setString(6, t.getEmail());

            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("erreurAjout");
        }   
    }    

    @Override
    public List<DemandeCertification> readAll() {
  String req = "select * from demande_certification";
        
        List<DemandeCertification> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM demande_certification";
            PreparedStatement pst = conn1.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new DemandeCertification(rs.getInt("id"),rs.getInt("certification_id"),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getString(6),rs.getString(7)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
        
        
        
    }

    @Override
    public List Trie_Desc() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(int t) {
 try {
            String requete = "DELETE FROM demande_certification WHERE id=?";
            PreparedStatement pst = conn1.prepareStatement(requete);
            pst.setInt(1, t);
             int row= pst.executeUpdate();

if(row>0)
            System.out.println("DemandeCertification supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    }

   

    @Override
    public void modifier_2(DemandeCertification t, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Certification> getCertification() {
                 List<Certification> list = new ArrayList<>();

        try {
            String requete = "SELECT id,nom FROM certification";
            PreparedStatement pst = conn1.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Certification(rs.getInt(1),rs.getString(2)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }  

    @Override
    public List<Entreprise> getEntreprise() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}









    
  

    
 