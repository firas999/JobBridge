/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.sun.istack.internal.logging.Logger;
import entity.Certification;
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
public class CertificationService implements IService<Certification> {

      private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    private Connection conn;

    public CertificationService() {
        conn = DataSource.getInstance().getCnx();
    }


    
    @Override
    public void insert(Certification c) {
       String req = "insert into certification (type,date_passage,prix,description,nom,entreprise_id) values (?,?,?,?,?,?)";

        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, c.getType());
            pst.setDate(2, c.getDate_passage());
            pst.setInt(3, c.getPrix());
            pst.setString(4, c.getDescription());
            pst.setString(5, c.getNom());
            pst.setInt(6, c.getEntreprise_id());

            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("er");
        }

    }

    @Override
    public List<Certification> readAll() {
  String req = "select * from certification";

        List<Certification> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new Certification(rs.getInt("id"), rs.getString("type"),rs.getDate(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getInt(7)));
            }

        } catch (SQLException ex) {
            System.out.println("er");
        }
        return list;    }

    @Override
    public List Trie_Desc() {
    List<Certification> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM certification ";
            ste = conn.createStatement();
            rs = ste.executeQuery(requete);
            while (rs.next()) {
         list.add(new Certification(rs.getInt("id"), rs.getString("type"),rs.getDate(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getInt(7)));            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
Collections.sort(list,new comparing_desc());
        return list;   
    }

    @Override
    public void supprimer(int t) {

        try {
            String requete = "DELETE FROM certification WHERE id=?";
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1, t);
             int row= pst.executeUpdate();

if(row>0)
            System.out.println("Certification supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }  



    @Override
    public void modifier_2(Certification c, int id) {
        try {
            String requete = "UPDATE  certification SET type=?,date_passage=?,prix=?,description=?,nom=?,entreprise_id=?"
                    + " WHERE id=?";
            Statement st = conn.createStatement();

            PreparedStatement pst = conn.prepareStatement(requete);

          pst.setString(1, c.getType());
            pst.setDate(2, c.getDate_passage());
            pst.setInt(3, c.getPrix());
            pst.setString(4, c.getDescription());
            pst.setString(5, c.getNom());
            pst.setInt(6, c.getEntreprise_id());
            pst.setInt(7, c.getId());
            pst.executeUpdate();

            System.out.println(c.getId());

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Certification> getCertification() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Entreprise> getEntreprise() {
  List<Entreprise> list = new ArrayList<>();

        try {
            String requete = "SELECT id,nom FROM entreprise";
            PreparedStatement pst = conn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Entreprise(rs.getInt(1),rs.getString(2)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;  
    }



   

  


    
}

  
 

    



    class comparing_desc implements Comparator<Certification>{

    @Override
    public int compare(Certification o1, Certification o2) {
         return o2.getDate_passage().compareTo(o1.getDate_passage());
   
    }}

