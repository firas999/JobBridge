/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

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

    public OffreStage(int id_entreprise_id, String type_stage, String duree, String exigence) {
        this.id_entreprise_id = id_entreprise_id;
        this.type_stage = type_stage;
        this.duree = duree;
        this.exigence = exigence;
    }

    public OffreStage(int id, int id_entreprise_id, String type_stage, String duree, String exigence) {
        this.id = id;
        this.id_entreprise_id = id_entreprise_id;
        this.type_stage = type_stage;
        this.duree = duree;
        this.exigence = exigence;
    }

    public OffreStage() {
        
    }

    public int getId() {
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

    @Override
    public String toString() {
        return "OffreStage{" + "id=" + id + ", id_entreprise_id=" + id_entreprise_id + ", type_stage=" + type_stage + ", duree=" + duree + ", exigence=" + exigence + '}';
    }
    
}
