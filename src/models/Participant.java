/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Objects;

/**
 *
 * @author User
 */
public class Participant {
    private Integer id;
    private String type_participant;
    private Integer id_evenement_id;
    private String nom;
    private String prenom;
    private String mail;

    public Participant() {
    }

    public Participant(Integer id) {
        this.id = id;
    }

    public Participant( String type_participant, String nom, String prenom, String mail,Integer id) {
        this.id = id;
        this.type_participant = type_participant;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
    }

    public Participant(String type_participant, Integer id_evenement_id, String nom, String prenom, String mail) {
        this.type_participant = type_participant;
        this.id_evenement_id = id_evenement_id;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
    }

    public Participant(Integer id, String type_participant, Integer id_evenement_id, String nom, String prenom, String mail) {
        this.id = id;
        this.type_participant = type_participant;
        this.id_evenement_id = id_evenement_id;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
    }
     public Participant(String type_participant, Integer id_evenement_id, String nom, String prenom, String mail,Integer id) {
        this.id = id;
        this.type_participant = type_participant;
        this.id_evenement_id = id_evenement_id;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType_participant() {
        return type_participant;
    }

    public void setType_participant(String type_participant) {
        this.type_participant = type_participant;
    }

    public Integer getId_evenement_id() {
        return id_evenement_id;
    }

    public void setId_evenement_id(Integer id_evenement_id) {
        this.id_evenement_id = id_evenement_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "Participant{" + "id=" + id + ", type_participant=" + type_participant + ", id_evenement_id=" + id_evenement_id + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Participant other = (Participant) obj;
        if (!Objects.equals(this.type_participant, other.type_participant)) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.mail, other.mail)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.id_evenement_id, other.id_evenement_id)) {
            return false;
        }
        return true;
    }
    
}
