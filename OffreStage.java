/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author User
 */
public class OffreStage {
    int id;
    int id_entreprise_id;
    String type_stage;
    String duree;
    String exigence;
    String nomentreprise;
    public OffreStage(){
        
    }

    public OffreStage(int id) {
        this.id = id;
    }
   
    public OffreStage(int id_entreprise_id, String type_stage, String duree, String exigence) {
        this.id_entreprise_id = id_entreprise_id;
        this.type_stage = type_stage;
        this.duree = duree;
        this.exigence = exigence;
    }

    public OffreStage(String nomentreprise,String type_stage, String duree, String exigence) {
        this.type_stage = type_stage;
        this.duree = duree;
        this.exigence = exigence;
        this.nomentreprise = nomentreprise;
    }

    public OffreStage(int id, String nomentreprise, String type_stage, String duree, String exigence) {
        this.id = id;
        this.type_stage = type_stage;
        this.duree = duree;
        this.exigence = exigence;
        this.nomentreprise = nomentreprise;
    }

    public OffreStage(int id, int id_entreprise_id, String type_stage,String duree,String exigence) {
        this.id = id;
        this.id_entreprise_id = id_entreprise_id;
        this.type_stage = type_stage;
        this.duree = duree;
        this.exigence = exigence;
    }
     public OffreStage(String type_stage,String duree,String exigence,int id) {
        this.type_stage = type_stage;
        this.duree = duree;
        this.exigence = exigence;
        this.id = id;
    }

    public OffreStage(int i, int j, LocalDate value, String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_entreprise_id() {
        return id_entreprise_id;
    }

    public void setId_entreprise_id(int id_entreprise_id) {
        this.id_entreprise_id = id_entreprise_id;
    }

    public String getType_stage() {
        return type_stage;
    }

    public void setType_stage(String type_stage) {
        this.type_stage = type_stage;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getExigence() {
        return exigence;
    }

    public void setExigence(String exigence) {
        this.exigence = exigence;
    }

    public String getNomentreprise() {
        return nomentreprise;
    }

    public void setNomentreprise(String nomentreprise) {
        this.nomentreprise = nomentreprise;
    }

    @Override
    public String toString() {
        return "OffreStage{" + "id=" + id + ", id_entreprise_id=" + id_entreprise_id + ", type_stage=" + type_stage + ", duree=" + duree + ", exigence=" + exigence + '}';
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
        final OffreStage other = (OffreStage) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.id_entreprise_id != other.id_entreprise_id) {
            return false;
        }
        if (!Objects.equals(this.type_stage, other.type_stage)) {
            return false;
        }
        if (!Objects.equals(this.duree, other.duree)) {
            return false;
        }
        if (!Objects.equals(this.exigence, other.exigence)) {
            return false;
        }
        return true;
    }
    
    
}
