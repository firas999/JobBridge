/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netdev.mindspace.entites;

import java.util.Date;

/**
 *
 * @author hp
 */
public class User {
   
    private int id;
    private String nom;
    private String prenom;
    private String motdepasse;  
    private String type;
    private String discr;
    private String diplome;
    private String entreprise;
    private String typedecontrat;
    private String posteoccupe;
    private Date date;
    private String competence;
    private String secteur;
    private String siteweb;
    private int tailleentreprise;
    private int numerotelephone;
    private String img;
   
    private String block;

    public User(int id, String nom, String prenom, String motdepasse, String type, String discr, String diplome, String entreprise, String typedecontrat, String posteoccupe, Date date, String competence, String secteur, String siteweb, int tailleentreprise, int numerotelephone, String img, String roles,  int nbr_follow ,int etat,String block) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.motdepasse = motdepasse;
        this.type = type;
        this.discr = discr;
        this.diplome = diplome;
        this.entreprise = entreprise;
        this.typedecontrat = typedecontrat;
        this.posteoccupe = posteoccupe;
        this.date = date;
        this.competence = competence;
        this.secteur = secteur;
        this.siteweb = siteweb;
        this.tailleentreprise = tailleentreprise;
        this.numerotelephone = numerotelephone;
        this.img = img;
        this.block = block;
        this.roles = roles;
        this.nbr_follow = nbr_follow;
        this.etat = etat;
    }

    public User(int id) {
        this.id = id;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
    private String roles ;
    private int nbr_follow ;
    private int etat;

    public int getEtat() {
        return etat;
    }

    
    
    public User() {
    }

    public User(String nom, String prenom, String motdepasse, String type, String discr, String secteur, String siteweb, int tailleentreprise, int numerotelephone, String roles) {
        this.nom = nom;
        this.prenom = prenom;
        this.motdepasse = motdepasse;
        this.type = type;
        this.discr = discr;
        this.secteur = secteur;
        this.siteweb = siteweb;
        this.tailleentreprise = tailleentreprise;
        this.numerotelephone = numerotelephone;
      
        this.roles = roles;
    }
     public User(int id,String nom, String prenom, String motdepasse, String type, String discr, String secteur, String siteweb, int tailleentreprise, int numerotelephone,int nbr_follow,String block) {
        this.nom = nom;
        this.prenom = prenom;
        this.motdepasse = motdepasse;
        this.type = type;
        this.discr = discr;
        this.secteur = secteur;
        this.siteweb = siteweb;
        this.tailleentreprise = tailleentreprise;
        this.numerotelephone = numerotelephone;
         this.nbr_follow=nbr_follow;
        this.block=block;
          this.id=id;
      }

    public User(String nom, String prenom, String motdepasse, String type, String discr, String diplome, String entreprise, String typedecontrat, String posteoccupe, Date date, String competence, String secteur, String siteweb, int tailleentreprise, int numerotelephone, String img, String roles, int nbr_follow) {
        this.nom = nom;
        this.prenom = prenom;
        this.motdepasse = motdepasse;
        this.type = type;
        this.discr = discr;
        this.diplome = diplome;
        this.entreprise = entreprise;
        this.typedecontrat = typedecontrat;
        this.posteoccupe = posteoccupe;
        this.date = date;
        this.competence = competence;
        this.secteur = secteur;
        this.siteweb = siteweb;
        this.tailleentreprise = tailleentreprise;
        this.numerotelephone = numerotelephone;
        this.img = img;
        this.roles = roles;
        this.nbr_follow = nbr_follow;
    }

    public User(String nom, String prenom, String motdepasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.motdepasse = motdepasse;
    }

    public User(int id, String nom, String prenom, String motdepasse, String type, String discr, String diplome, String entreprise, String typedecontrat, String posteoccupe, Date date, String competence,int nbr_follow) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.motdepasse = motdepasse;
        this.type = type;
        this.discr = discr;
        this.diplome = diplome;
        this.entreprise = entreprise;
        this.typedecontrat = typedecontrat;
        this.posteoccupe = posteoccupe;
        this.date = date;
        this.competence = competence;
        this.nbr_follow=nbr_follow;
     
        
    }
     public User(int id, String nom, String prenom, String motdepasse, String type, String discr, String diplome, String entreprise, String typedecontrat, String posteoccupe, Date date, String competence, String img) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.motdepasse = motdepasse;
        this.type = type;
        this.discr = discr;
        this.diplome = diplome;
        this.entreprise = entreprise;
        this.typedecontrat = typedecontrat;
        this.posteoccupe = posteoccupe;
        this.date = date;
        this.competence = competence;
        
        this.img = img;
    }

    public User(String nom, String prenom, String motdepasse, String discr) {
        this.nom = nom;
        this.prenom = prenom;
        this.motdepasse = motdepasse;
        this.discr = discr;
    }
    

    public User(int id, String nom, String prenom, String motdepasse, String type, String discr, String diplome, String entreprise, String typedecontrat, String posteoccupe, Date date, String competence, String secteur, String siteweb, int tailleentreprise, int numerotelephone, String img, String roles, int nbr_follow) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.motdepasse = motdepasse;
        this.type = type;
        this.discr = discr;
        this.diplome = diplome;
        this.entreprise = entreprise;
        this.typedecontrat = typedecontrat;
        this.posteoccupe = posteoccupe;
        this.date = date;
        this.competence = competence;
        this.secteur = secteur;
        this.siteweb = siteweb;
        this.tailleentreprise = tailleentreprise;
        this.numerotelephone = numerotelephone;
        this.img = img;
        this.roles = roles;
        this.nbr_follow = nbr_follow;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDiscr() {
        return discr;
    }

    public void setDiscr(String discr) {
        this.discr = discr;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public String getTypedecontrat() {
        return typedecontrat;
    }

    public void setTypedecontrat(String typedecontrat) {
        this.typedecontrat = typedecontrat;
    }

    public String getPosteoccupe() {
        return posteoccupe;
    }

    public void setPosteoccupe(String posteoccupe) {
        this.posteoccupe = posteoccupe;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCompetence() {
        return competence;
    }

    public void setCompetence(String competence) {
        this.competence = competence;
    }

    public String getSecteur() {
        return secteur;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    public String getSiteweb() {
        return siteweb;
    }

    public void setSiteweb(String siteweb) {
        this.siteweb = siteweb;
    }

    public int getTailleentreprise() {
        return tailleentreprise;
    }

    public void setTailleentreprise(int tailleentreprise) {
        this.tailleentreprise = tailleentreprise;
    }

    public int getNumerotelephone() {
        return numerotelephone;
    }

    public void setNumerotelephone(int numerotelephone) {
        this.numerotelephone = numerotelephone;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String role) {
        this.roles = role;
    }

    public int getNbr_follow() {
        return nbr_follow;
    }

    public void setNbr_follow(int nbr_follow) {
        this.nbr_follow = nbr_follow;
    }

    @Override
    public String toString() {
        return "user{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", motdepasse=" + motdepasse + ", type=" + type + ", discr=" + discr + ", diplome=" + diplome + ", entreprise=" + entreprise + ", typedecontrat=" + typedecontrat + ", posteoccupe=" + posteoccupe + ", date=" + date + ", competence=" + competence + ", secteur=" + secteur + ", siteweb=" + siteweb + ", tailleentreprise=" + tailleentreprise + ", numerotelephone=" + numerotelephone + ", img=" + img + ", roles=" + roles + ", nbr_follow=" + nbr_follow + '}';
    }
    
    
    
    
    
}
