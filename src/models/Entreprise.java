/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author dell
 */
public class Entreprise {
     private Integer id;

    private String Secteur;
 
    
    private String SiteWeb;

  
    private Integer Taille;

  
    private Integer Telephone;

   
    private String email;

  
    private String Nom;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    
    

    public Entreprise(int id, String nom) {
        this.id = id;
        this.Nom = nom;
    }

    public Integer getId() {
        return id;
    }

    public String getSecteur() {
        return Secteur;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSecteur(String Secteur) {
        this.Secteur = Secteur;
    }

    public void setSiteWeb(String SiteWeb) {
        this.SiteWeb = SiteWeb;
    }

    public void setTaille(Integer Taille) {
        this.Taille = Taille;
    }

    public void setTelephone(Integer Telephone) {
        this.Telephone = Telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public void setMatriculeFiscal(String MatriculeFiscal) {
        this.MatriculeFiscal = MatriculeFiscal;
    }

    public String getSiteWeb() {
        return SiteWeb;
    }

    public Integer getTaille() {
        return Taille;
    }

    public Integer getTelephone() {
        return Telephone;
    }

    public String getEmail() {
        return email;
    }

    public String getNom() {
        return Nom;
    }

    public String getMatriculeFiscal() {
        return MatriculeFiscal;
    }

    
    private String MatriculeFiscal;

    public Entreprise(Integer id, String Secteur, String SiteWeb, Integer Taille, Integer Telephone, String email, String Nom, String MatriculeFiscal,String image) {
        this.id = id;
        this.Secteur = Secteur;
        this.SiteWeb = SiteWeb;
        this.Taille = Taille;
        this.Telephone = Telephone;
        this.email = email;
        this.Nom = Nom;
        this.MatriculeFiscal = MatriculeFiscal;
        this.image=image;
    }

    public Entreprise(String Secteur, String SiteWeb, Integer Taille, Integer Telephone, String email, String Nom, String MatriculeFiscal,String image) {
        this.Secteur = Secteur;
        this.SiteWeb = SiteWeb;
        this.Taille = Taille;
        this.Telephone = Telephone;
        this.email = email;
        this.Nom = Nom;
        this.MatriculeFiscal = MatriculeFiscal;
        this.image=image;
    }
      public Entreprise(String Secteur, String SiteWeb, Integer Taille, Integer Telephone, String email, String Nom, String MatriculeFiscal,Integer id) {
        this.Secteur = Secteur;
        this.SiteWeb = SiteWeb;
        this.Taille = Taille;
        this.Telephone = Telephone;
        this.email = email;
        this.Nom = Nom;
        this.MatriculeFiscal = MatriculeFiscal;
        this.id=id;
      }

    public Entreprise(Integer id) {
        this.id = id;
    }
      

   

}
