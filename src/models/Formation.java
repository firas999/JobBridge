/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;
import javafx.scene.image.ImageView;

/**
 *
 * @author dell
 */
public class Formation {
    
    private Integer id;
    private Integer idEntreprise;
    private String Description;
    private Integer VolumeHoraire;
    private Date date_formation;
    private String adresse;
    private Integer prix;
    private Integer Promo;
    private String NomEntreprise;
    private String image;
    private ImageView img;

    public Formation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Formation(Integer id, Integer idEntreprise, String Description, Integer VolumeHoraire, Date date_formation, String adresse, Integer prix, Integer Promo) {
        this.id = id;
        this.idEntreprise = idEntreprise;
        this.Description = Description;
        this.VolumeHoraire = VolumeHoraire;
        this.date_formation = date_formation;
        this.adresse = adresse;
        this.prix = prix;   
        this.Promo = Promo;
       
    }
    public Formation(Integer id, String NomEntreprise, String Description, Integer VolumeHoraire, Date date_formation, String adresse,String image, Integer prix, Integer Promo) {
        this.id = id;
        this.NomEntreprise = NomEntreprise;
        this.Description = Description;
        this.VolumeHoraire = VolumeHoraire;
        this.date_formation = date_formation;
        this.adresse = adresse;
        this.prix = prix;   
        this.Promo = Promo;
         this.image=image;
    }
    public Formation(Integer id, String NomEntreprise, String Description, Integer VolumeHoraire, Date date_formation, String adresse, Integer prix, Integer Promo,ImageView image) {
        this.id = id;
        this.NomEntreprise = NomEntreprise;
        this.Description = Description;
        this.VolumeHoraire = VolumeHoraire;
        this.date_formation = date_formation;
        this.adresse = adresse;
        this.prix = prix;   
        this.Promo = Promo;
         this.img=image;
    }

    public Formation(Integer id, String Description, Integer VolumeHoraire, Date date_formation, String adresse, Integer prix, Integer Promo) {
        this.id = id;
        this.Description = Description;
        this.VolumeHoraire = VolumeHoraire;
        this.date_formation = date_formation;
        this.adresse = adresse;
        this.prix = prix;
        this.Promo = Promo;
    }

    public Formation(String Description, Integer VolumeHoraire, Date date_formation, String adresse, Integer prix, Integer Promo,String image) {
        this.Description = Description;
        this.VolumeHoraire = VolumeHoraire;
        this.date_formation = date_formation;
        this.adresse = adresse;
        this.prix = prix;
        this.Promo = Promo;
        this.image=image;
    }
    
    public Formation(String Description, Integer VolumeHoraire, Date date_formation, String adresse, Integer prix, Integer Promo,ImageView image) {
        this.Description = Description;
        this.VolumeHoraire = VolumeHoraire;
        this.date_formation = date_formation;
        this.adresse = adresse;
        this.prix = prix;
        this.Promo = Promo;
        this.img=image;
    }
    
    /**
     *
     * @param Description
     * @param VolumeHoraire
     * @param date_formation
     * @param adresse
     * @param prix
     * @param id
     */
    public Formation(String Description, Integer VolumeHoraire, Date date_formation, String adresse, Integer prix,Integer Promo, Integer id) {
        this.Description = Description;
        this.VolumeHoraire = VolumeHoraire;
        this.date_formation = date_formation;
        this.adresse = adresse;
        this.prix = prix;
        this.Promo = Promo;
        this.id = id;
    }
    

    public Formation(int id) {
        this.id = id;
    }

    public String getNomEntreprise() {
        return NomEntreprise;
    }

    public void setNomEntreprise(String NomEntreprise) {
        this.NomEntreprise = NomEntreprise;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdEntreprise() {
        return idEntreprise;
    }

    public void setIdEntreprise(Integer idEntreprise) {
        this.idEntreprise = idEntreprise;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Integer getVolumeHoraire() {
        return VolumeHoraire;
    }

    public void setVolumeHoraire(Integer VolumeHoraire) {
        this.VolumeHoraire = VolumeHoraire;
    }

    public Date getDate_formation() {
        return date_formation;
    }

    public void setDate_formation(Date date_formation) {
        this.date_formation = date_formation;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    public Integer getPromo() {
        return Promo;
    }

    public void setPromo(Integer Promo) {
        this.Promo = Promo;
    }
    
     public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }
    
    
}
