/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;

/**
 *
 * @author User
 */
public class Stagiaire {
     private Integer id;

    private String etablissemnt;
 
    
    private String type_diplome;

  
    private String filiere_diplome;

  
    private Date date_diplome;

   
    private Integer experience;

  
    private String competence;
    
    private String nom;

    public Stagiaire() {
    }

    public Stagiaire(String etablissemnt, String type_diplome, String filiere_diplome, Date date_diplome, Integer experience, String competence, String nom) {
        this.etablissemnt = etablissemnt;
        this.type_diplome = type_diplome;
        this.filiere_diplome = filiere_diplome;
        this.date_diplome = date_diplome;
        this.experience = experience;
        this.competence = competence;
        this.nom = nom;
    }

    public Stagiaire(Integer id, String etablissemnt, String type_diplome, String filiere_diplome, Date date_diplome, Integer experience, String competence, String nom) {
        this.id = id;
        this.etablissemnt = etablissemnt;
        this.type_diplome = type_diplome;
        this.filiere_diplome = filiere_diplome;
        this.date_diplome = date_diplome;
        this.experience = experience;
        this.competence = competence;
        this.nom = nom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEtablissemnt() {
        return etablissemnt;
    }

    public void setEtablissemnt(String etablissemnt) {
        this.etablissemnt = etablissemnt;
    }

    public String getType_diplome() {
        return type_diplome;
    }

    public void setType_diplome(String type_diplome) {
        this.type_diplome = type_diplome;
    }

    public String getFiliere_diplome() {
        return filiere_diplome;
    }

    public void setFiliere_diplome(String filiere_diplome) {
        this.filiere_diplome = filiere_diplome;
    }

    public Date getDate_diplome() {
        return date_diplome;
    }

    public void setDate_diplome(Date date_diplome) {
        this.date_diplome = date_diplome;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public String getCompetence() {
        return competence;
    }

    public void setCompetence(String competence) {
        this.competence = competence;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return + id + ":" + nom;
    }
    
    
}
