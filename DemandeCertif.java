/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pi.entities;

import java.util.Date;

/**
 *
 * @author ouaji
 */
public class DemandeCertif {
    
     private int id;
   private int   certification_id;
   private String    nom_participant;
   private String    prenom_participant;
   private Date    date_demande;
   private String    experience_participant;
   private String    email;

    public DemandeCertif(int certification_id, String nom_participant, String prenom_participant, Date date_demande, String experience_participant, String email) {
        this.certification_id = certification_id;
        this.nom_participant = nom_participant;
        this.prenom_participant = prenom_participant;
        this.date_demande = date_demande;
        this.experience_participant = experience_participant;
        this.email = email;
    }

    public DemandeCertif() {
    }
    

    public DemandeCertif(int id, int certification_id, String nom_participant, String prenom_participant, Date date_demande, String experience_participant, String email) {
        this.id = id;
        this.certification_id = certification_id;
        this.nom_participant = nom_participant;
        this.prenom_participant = prenom_participant;
        this.date_demande = date_demande;
        this.experience_participant = experience_participant;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public int getCertification_id() {
        return certification_id;
    }

    public String getNom_participant() {
        return nom_participant;
    }

    public String getPrenom_participant() {
        return prenom_participant;
    }

    public Date getDate_demande() {
        return date_demande;
    }

    public String getExperience_participant() {
        return experience_participant;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCertification_id(int certification_id) {
        this.certification_id = certification_id;
    }

    public void setNom_participant(String nom_participant) {
        this.nom_participant = nom_participant;
    }

    public void setPrenom_participant(String prenom_participant) {
        this.prenom_participant = prenom_participant;
    }

    public void setDate_demande(Date date_demande) {
        this.date_demande = date_demande;
    }

    public void setExperience_participant(String experience_participant) {
        this.experience_participant = experience_participant;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "DemandeCertif{" + "id=" + id + ", certification_id=" + certification_id + ", nom_participant=" + nom_participant + ", prenom_participant=" + prenom_participant + ", date_demande=" + date_demande + ", experience_participant=" + experience_participant + ", email=" + email + '}';
    }
   
   
   
   
   
    
    
}
