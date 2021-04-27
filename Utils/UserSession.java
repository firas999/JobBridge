/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Models.user;
import Services.serviceuser;
import java.sql.Date;
import java.sql.SQLException;
import javafx.scene.image.ImageView;

/**
 *
 * @author Dell
 */
public class UserSession {
     private static UserSession instance;
    private user loggedUser;
    
    
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
    private ImageView image1;
    private String block;
// login session
    public UserSession(String nom) {
        serviceuser cn = new serviceuser();
        user u = cn.getUserById(nom); 
        this.id = u.getId();
        this.nom = u.getNom();
        this.prenom = u.getPrenom();
        this.motdepasse = u.getPrenom();
        this.type = u.getType();
        this.discr = u.getDiscr();
        this.diplome = u.getDiplome();
        this.entreprise = u.getEntreprise();
        this.typedecontrat = u.getTypedecontrat();
        this.posteoccupe = u.getPosteoccupe();
        this.date = u.getDate();
        this.competence = u.getCompetence();
        this.secteur = u.getSecteur();
        this.siteweb = u.getSiteweb();
        this.tailleentreprise = u.getTailleentreprise();
        this.numerotelephone = u.getNumerotelephone();
        this.img = u.getImg();
        this.block = u.getBlock();
    }
    
     public static UserSession setInstance(String nom) throws SQLException {
        if (instance == null) {
            instance = new UserSession(nom);
        }
        return instance;
    }
    public static UserSession setInstance(int id) throws SQLException {
        if (instance == null) {
            instance = new UserSession(id); 
        }
        return instance;
    }
     public UserSession(int id) {
        this.id = id;
    }
public void cleanUserSession() {
        nom = "";// or null
    }

    public static UserSession getInstance() {
        return instance;
    }

    public static void setInstance(UserSession instance) {
        UserSession.instance = instance;
    }

    public user getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(user loggedUser) {
        this.loggedUser = loggedUser;
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

    public ImageView getImage1() {
        return image1;
    }

    public void setImage1(ImageView image1) {
        this.image1 = image1;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }
    
    
}
