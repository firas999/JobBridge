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
public class OffreEmploi {

    private int id, entreprise_id;
    private String type, experience, exigence, poste;
    private int salaire;

    public OffreEmploi() {
    }

    public OffreEmploi(int id, int entreprise_id, String type, String experience, String exigence, String poste, int salaire) {
        this.id = id;
        this.entreprise_id = entreprise_id;
        this.type = type;
        this.experience = experience;
        this.exigence = exigence;
        this.poste = poste;
        this.salaire = salaire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEntreprise_id() {
        return entreprise_id;
    }

    public void setEntreprise_id(int entreprise_id) {
        this.entreprise_id = entreprise_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getExigence() {
        return exigence;
    }

    public void setExigence(String exigence) {
        this.exigence = exigence;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public int getSalaire() {
        return salaire;
    }

    public void setSalaire(int salaire) {
        this.salaire = salaire;
    }

    @Override
    public String toString() {
        return "OffreEmploi{" + "id=" + id + ", entreprise_id=" + entreprise_id + ", type=" + type + ", experience=" + experience + ", exigence=" + exigence + ", poste=" + poste + ", salaire=" + salaire + '}';
    }
    
}
