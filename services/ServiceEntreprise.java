/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Entreprise;
import com.mycompany.entities.OffreStage;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author User
 */
public class ServiceEntreprise {
      public ArrayList<Entreprise> Entreprises;
    
    public static ServiceEntreprise instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceEntreprise() {
         req = new ConnectionRequest();
    }

    public static ServiceEntreprise getInstance() {
        if (instance == null) {
            instance = new ServiceEntreprise();
        }
        return instance;
    }
    
      public ArrayList<Entreprise> getAllTasks(){
         String url = Statics.BASE_URL+"/entreprise/AfficherEntrepriseJS";
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
    @Override
    public void actionPerformed(NetworkEvent evt) {
    Entreprises = parseabsence(new String(req.getResponseData()));
    req.removeResponseListener(this);
    }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
       //System.out.println(sprints);
    return Entreprises;
      /*  String url = Statics.BASE_URL+"AfficherCandidatStageJS";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                CandidatStages = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return CandidatStages;*/
    }
    
    

    /*public boolean addTask(Entreprise Entreprise) {
        String url = Statics.BASE_URL + "/Entreprise/" + Entreprise.getId_entreprise_id() + "/" + Entreprise.getType_stage() + "/" + Entreprise.getDuree() + "/" + Entreprise.getExigence(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
         /*   }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }*/
public ArrayList<Entreprise> parseabsence(String jsonText){
        try {
            Entreprises=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println("map");
            System.out.println(tasksListJson);
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            System.out.println("list");
            System.out.println(list);
            for(Map<String,Object> obj : list){
             
             Entreprise u = new Entreprise();
               float id = Float.parseFloat(obj.get("id").toString());
               float taille = Float.parseFloat(obj.get("Taille").toString());
               //float id_user = Float.parseFloat(obj.get("idstagiaire_id").toString());
       
                u.setId((int)id);
                u.setTaille((int)taille);
                u.setNom(obj.get("Nom").toString());
                u.setEmail(obj.get("email").toString());
                u.setSiteWeb(obj.get("SiteWeb").toString());
                u.setImage(obj.get("image").toString());
                
                
              
                
                Entreprises.add(u);
            }
        } catch (IOException ex) {
            
        }
        return Entreprises;
    }
}
