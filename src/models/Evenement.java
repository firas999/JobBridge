/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Clob;
import java.sql.Date;
import static java.sql.Date.valueOf;
import java.sql.Time;
import java.util.Objects;
import javafx.scene.text.Text;


/**
 *
 * @author User
 */
public class Evenement {
    private Integer id;

    private String nom;
 
    
    private Date date;

  
    private String Adresse;

  
    private String description;

   
    private String entreprise;

  
    private Double prix;
    
    private Integer cap;
    
    private Time horaire;

    public Evenement() {
    }

    public Evenement(Integer id) {
        this.id = id;
    }

    public Evenement(String nom, Date date, String Adresse, String description, String entreprise, Double prix, Integer cap, Time horaire) {
        this.nom = nom;
        this.date = date;
        this.Adresse = Adresse;
        this.description = description;
        this.entreprise = entreprise;
        this.prix = prix;
        this.cap = cap;
        this.horaire = horaire;
    }

    public Evenement(Integer id, String nom, Date date, String Adresse, String entreprise, Double prix, Integer cap, Time horaire) {
        this.id = id;
        this.nom = nom;
        this.date = date;
        this.Adresse = Adresse;
        this.entreprise = entreprise;
        this.prix = prix;
        this.cap = cap;
        this.horaire = horaire;
    }
    

    public Evenement(Integer id, String nom, Date date, String Adresse, String description, String entreprise, Double prix, Integer cap, Time horaire) {
        this.id = id;
        this.nom = nom;
        this.date = date;
        this.Adresse = Adresse;
        this.description = description;
        this.entreprise = entreprise;
        this.prix = prix;
        this.cap = cap;
        this.horaire = horaire;
    }
     public Evenement( String nom, Date date, String Adresse, String description, String entreprise, Double prix, Integer cap, Time horaire,Integer id) {
        this.id = id;
        this.nom = nom;
        this.date = date;
        this.Adresse = Adresse;
        this.description = description;
        this.entreprise = entreprise;
        this.prix = prix;
        this.cap = cap;
        this.horaire = horaire;
    }

    public Evenement(int aInt, String string, Date date,String string0, Clob clob, String string1, double aDouble, int aInt0, Time time) {
        this.id = aInt;
        this.nom = string;
        this.date = date;
        this.Adresse = string0;
        this.description = clob.toString();
        this.entreprise = string1;
        this.prix = aDouble;
        this.cap = aInt0;
        this.horaire = time;
    }

    public Evenement(String text, String text0, String text1, String text2, int i, double j, Date valueOf) {
       this.nom = text;
               this.Adresse = text0;
        this.description = text1;
                this.entreprise = text2;
                        this.cap = i;
        this.prix = j;
        this.date = valueOf;



    }

    public Evenement(String text, String text0, String text1, String text2, int i, int j, Date valueOf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Integer getCap() {
        return cap;
    }

    public void setCap(Integer cap) {
        this.cap = cap;
    }

    public Time getHoraire() {
        return horaire;
    }

    public void setHoraire(Time horaire) {
        this.horaire = horaire;
    }

    @Override
    public String toString() {
        return  + id + ":" + nom;
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
        final Evenement other = (Evenement) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.Adresse, other.Adresse)) {
            return false;
        }
        if (!Objects.equals(this.entreprise, other.entreprise)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.prix, other.prix)) {
            return false;
        }
        if (!Objects.equals(this.cap, other.cap)) {
            return false;
        }
        if (!Objects.equals(this.horaire, other.horaire)) {
            return false;
        }
        return true;
    }
    
    
}
