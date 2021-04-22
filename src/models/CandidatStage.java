/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;
import java.util.Objects;

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

    public CandidatStage() {
    }

    public CandidatStage(int id) {
        this.id = id;
    }

    public CandidatStage( Date date_candidature, String email,int id) {
        this.id = id;
        this.date_candidature = date_candidature;
        this.email = email;
    }

    public CandidatStage(int identreprise_id, int idstagaire_id, Date date_candidature, String email) {
        this.identreprise_id = identreprise_id;
        this.idstagiaire_id = idstagaire_id;
        this.date_candidature = date_candidature;
        this.email = email;
    }

    public CandidatStage(int id, int identreprise_id, int idstagaire_id, Date date_candidature, String email) {
        this.id = id;
        this.identreprise_id = identreprise_id;
        this.idstagiaire_id = idstagaire_id;
        this.date_candidature = date_candidature;
        this.email = email;
    }
       public CandidatStage(int identreprise_id, int idstagaire_id, Date date_candidature, String email,int id) {
        this.identreprise_id = identreprise_id;
        this.idstagiaire_id = idstagaire_id;
        this.date_candidature = date_candidature;
        this.email = email;
        this.id = id;
    }

    public Integer getId() {
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

    public void setIdstagiaire_id(int idstagaire_id) {
        this.idstagiaire_id = idstagaire_id;
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
        return "CandidatStage{" + "id=" + id + ", identreprise_id=" + identreprise_id + ", idstagaire_id=" + idstagiaire_id + ", date_candidature=" + date_candidature + ", email=" + email + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final CandidatStage other = (CandidatStage) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.identreprise_id != other.identreprise_id) {
            return false;
        }
        if (this.idstagiaire_id != other.idstagiaire_id) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.date_candidature, other.date_candidature)) {
            return false;
        }
        return true;
    }
    
    
    
}
