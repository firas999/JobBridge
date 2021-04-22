/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;

/**
 *
 * @author ouaji
 */
public class Certification {
    
    private int id;
     private String type;
     private Date date_passage;
    private int  prix;
    private String description;
    private String nom;
    private int entreprise_id;

    public Certification() {
    }

    public Certification(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }
    
    
    
    
    

    public Certification(String type, Date date_passage, int prix, String description, String nom, int entreprise_id) {
        this.type = type;
        this.date_passage = date_passage;
        this.prix = prix;
        this.description = description;
        this.nom = nom;
        this.entreprise_id = entreprise_id;
    }

    public Certification(int id, String type, Date date_passage, int prix, String description, String nom, int entreprise_id) {
        this.id = id;
        this.type = type;
        this.date_passage = date_passage;
        this.prix = prix;
        this.description = description;
        this.nom = nom;
        this.entreprise_id = entreprise_id;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Date getDate_passage() {
        return date_passage;
    }

    public int getPrix() {
        return prix;
    }

    public String getDescription() {
        return description;
    }

    public String getNom() {
        return nom;
    }

    public int getEntreprise_id() {
        return entreprise_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDate_passage(Date date_passage) {
        this.date_passage = date_passage;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEntreprise_id(int entreprise_id) {
        this.entreprise_id = entreprise_id;
    }

  @Override
    public String toString() {
        return + id + ":" + nom;
    }
    
    
    
    
    
    
    
    
    
    
    
}
