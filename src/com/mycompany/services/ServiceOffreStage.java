/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.OffreStage;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author User
 */
public class ServiceOffreStage {
         public ArrayList<OffreStage> OffreStages;
    
    public static ServiceOffreStage instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceOffreStage() {
         req = new ConnectionRequest();
    }

    public static ServiceOffreStage getInstance() {
        if (instance == null) {
            instance = new ServiceOffreStage();
        }
        return instance;
    }
    
      public ArrayList<OffreStage> getAllTasks(){
         String url = Statics.BASE_URL+"/offre/stage/AfficherCandidatStageJS";
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
    @Override
    public void actionPerformed(NetworkEvent evt) {
    OffreStages = parseabsence(new String(req.getResponseData()));
    req.removeResponseListener(this);
    }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
       //System.out.println(sprints);
    return OffreStages;
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
    
    

    public boolean addTask(OffreStage OffreStage) {
        String url = Statics.BASE_URL + "/OffreStage/" + OffreStage.getId_entreprise_id() + "/" + OffreStage.getType_stage() + "/" + OffreStage.getDuree() + "/" + OffreStage.getExigence(); //création de l'URL
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
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
public ArrayList<OffreStage> parseabsence(String jsonText){
        try {
            OffreStages=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println("map");
            System.out.println(tasksListJson);
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            System.out.println("list");
            System.out.println(list);
            for(Map<String,Object> obj : list){
             
             OffreStage u = new OffreStage();
               float id = Float.parseFloat(obj.get("id").toString());
               //float nbr = Float.parseFloat(obj.get("IdEntreprise").toString());
               //float id_user = Float.parseFloat(obj.get("idstagiaire_id").toString());
       
                u.setId((int)id);
                //u.setId_entreprise_id((int)nbr);
                u.setType_stage(obj.get("TypeStage").toString());
                u.setDuree(obj.get("Duree").toString());
                u.setExigence(obj.get("Exigence").toString());
                
                
                
              
                
                OffreStages.add(u);
            }
        } catch (IOException ex) {
            
        }
        return OffreStages;
    }
public ArrayList<OffreStage> getSug(String s){
        String url = Statics.BASE_URL+"/act/Recherche/"+s;
        req.setUrl(url);
        req.setPost(false);
       
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String reponsejson=new String(req.getResponseData());
               
               OffreStages = parseabsence(reponsejson);
               
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return OffreStages;
    }
}
