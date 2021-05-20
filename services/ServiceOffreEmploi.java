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
import com.mycompany.entities.OffreEmploi;
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
public class ServiceOffreEmploi {
     public ArrayList<OffreEmploi> OffreEmplois;
    
    public static ServiceOffreEmploi instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceOffreEmploi() {
         req = new ConnectionRequest();
    }

    public static ServiceOffreEmploi getInstance() {
        if (instance == null) {
            instance = new ServiceOffreEmploi();
        }
        return instance;
    }
    
      public ArrayList<OffreEmploi> getAllTasks(){
         String url = Statics.BASE_URL+"/offre/emploi/AfficherOffreEmploiJS";
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
    @Override
    public void actionPerformed(NetworkEvent evt) {
    OffreEmplois = parseabsence(new String(req.getResponseData()));
    req.removeResponseListener(this);
    }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
       //System.out.println(sprints);
    return OffreEmplois;
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
    
    

   public boolean addTask(OffreEmploi OffreEmploi) {
     //   String url = Statics.BASE_URL + "/OffreStage/" + OffreStage.getId_entreprise_id() + "/" + OffreStage.getType_stage() + "/" + OffreStage.getDuree() + "/" + OffreStage.getExigence(); //création de l'URL
      //  req.setUrl(url);// Insertion de l'URL de notre demande de connexion
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
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
public ArrayList<OffreEmploi> parseabsence(String jsonText){
        try {
            OffreEmplois=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println("map");
            System.out.println(tasksListJson);
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            System.out.println("list");
            System.out.println(list);
            for(Map<String,Object> obj : list){
             
             OffreEmploi u = new OffreEmploi();
               float id = Float.parseFloat(obj.get("id").toString());
               float nbr = Float.parseFloat(obj.get("Salaire").toString());
               //float id_user = Float.parseFloat(obj.get("idstagiaire_id").toString());
       
                u.setId((int)id);
                u.setSalaire((int)nbr);
                u.setPoste(obj.get("Poste").toString());
                u.setType(obj.get("Type").toString());
                u.setExigence(obj.get("Exigence").toString());
                
                
                
              
                
               OffreEmplois.add(u);
            }
        } catch (IOException ex) {
            
        }
        return OffreEmplois;
    }
public ArrayList<OffreEmploi> getSug(String s){
        String url = Statics.BASE_URL+"/act/Recherche/"+s;
        req.setUrl(url);
        req.setPost(false);
       
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String reponsejson=new String(req.getResponseData());
               
               OffreEmplois = parseabsence(reponsejson);
               
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return OffreEmplois;
    }
}
