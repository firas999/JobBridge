/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author User
 */
public class Entreprise {
     private Integer id;

    private String Secteur;
 
    
    private String SiteWeb;

  
    private Integer Taille;

  
    private Integer Telephone;

   
    private String email;

  
    private String Nom;

    public Entreprise(String text, String text0, String text1, String text2, String text3, String text4) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public Entreprise(Integer id, String Secteur, String SiteWeb, Integer Taille, Integer Telephone, String email, String Nom, String MatriculeFiscal) {
        this.id = id;
        this.Secteur = Secteur;
        this.SiteWeb = SiteWeb;
        this.Taille = Taille;
        this.Telephone = Telephone;
        this.email = email;
        this.Nom = Nom;
        this.MatriculeFiscal = MatriculeFiscal;
    }

    public Entreprise(String Secteur, String SiteWeb, Integer Taille, Integer Telephone, String email, String Nom, String MatriculeFiscal) {
        this.Secteur = Secteur;
        this.SiteWeb = SiteWeb;
        this.Taille = Taille;
        this.Telephone = Telephone;
        this.email = email;
        this.Nom = Nom;
        this.MatriculeFiscal = MatriculeFiscal;
    }

    public Entreprise(Integer id, String Nom) {
        this.id = id;
        this.Nom = Nom;
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

    public Entreprise(String Nom) {
        this.Nom = Nom;
    }

    @Override
    public String toString() {
        return  + id + ":" + Nom ;
    }

  
    
      

   

}