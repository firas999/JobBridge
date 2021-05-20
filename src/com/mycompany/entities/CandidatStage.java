/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.util.Date;

/**
 *
 * @author User
 */
public class CandidatStage {
    int id;
    int identreprise_id;
    int idstagiaire_id;
    Date date_candidature;
    String email;

    public CandidatStage(int id, Date date_candidature, String email) {
        this.id = id;
        this.date_candidature = date_candidature;
        this.email = email;
    }

    public CandidatStage() {
    }

    public CandidatStage(int identreprise_id, int idstagiaire_id, Date date_candidature, String email) {
        this.identreprise_id = identreprise_id;
        this.idstagiaire_id = idstagiaire_id;
        this.date_candidature = date_candidature;
        this.email = email;
    }

    public CandidatStage(int id, int identreprise_id, int idstagiaire_id, Date date_candidature, String email) {
        this.id = id;
        this.identreprise_id = identreprise_id;
        this.idstagiaire_id = idstagiaire_id;
        this.date_candidature = date_candidature;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdentreprise_id() {
        return identreprise_id;
    }

    public void setIdentreprise_id(int identreprise_id) {
        this.identreprise_id = identreprise_id;
    }

    public int getIdstagiaire_id() {
        return idstagiaire_id;
    }

    public void setIdstagiaire_id(int idstagiaire_id) {
        this.idstagiaire_id = idstagiaire_id;
    }

    public Date getDate_candidature() {
        return date_candidature;
    }

    public void setDate_candidature(Date date_candidature) {
        this.date_candidature = date_candidature;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "CandidatStage{" + "id=" + id + ", identreprise_id=" + identreprise_id + ", idstagiaire_id=" + idstagiaire_id + ", date_candidature=" + date_candidature + ", email=" + email + '}';
    }
    
}
