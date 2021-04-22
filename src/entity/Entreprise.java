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
public class Entreprise {
    
    
     private int id;
     private String secteur;
     private String site_web;
    private int  taille;
    private int telephone;
    private String email;
    private String nom;
    private String matricule_fiscal;

    public Entreprise(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }
    
    
     public Entreprise(int id, String secteur, String site_web, int taille, int telephone, String email, String nom, String matricule_fiscal) {
        this.id = id;
        this.secteur = secteur;
        this.site_web = site_web;
        this.taille = taille;
        this.telephone = telephone;
        this.email = email;
        this.nom = nom;
        this.matricule_fiscal = matricule_fiscal;
    }

    public Entreprise(String secteur, String site_web, int taille, int telephone, String email, String nom, String matricule_fiscal) {
        this.secteur = secteur;
        this.site_web = site_web;
        this.taille = taille;
        this.telephone = telephone;
        this.email = email;
        this.nom = nom;
        this.matricule_fiscal = matricule_fiscal;
    }

    public int getId() {
        return id;
    }

    public String getSecteur() {
        return secteur;
    }

    public String getSite_web() {
        return site_web;
    }

    public int getTaille() {
        return taille;
    }

    public int getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public String getNom() {
        return nom;
    }

    public String getMatricule_fiscal() {
        return matricule_fiscal;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    public void setSite_web(String site_web) {
        this.site_web = site_web;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setMatricule_fiscal(String matricule_fiscal) {
        this.matricule_fiscal = matricule_fiscal;
    }
     
     
      @Override
    public String toString() {
        return + id + ":" + nom;
    }
            
            
            
            
            
            
            
    
    
}
