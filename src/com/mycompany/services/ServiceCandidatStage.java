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
import com.mycompany.entities.CandidatStage;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author User
 */
public class ServiceCandidatStage {
     public ArrayList<CandidatStage> CandidatStages;
    
    public static ServiceCandidatStage instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceCandidatStage() {
         req = new ConnectionRequest();
    }

    public static ServiceCandidatStage getInstance() {
        if (instance == null) {
            instance = new ServiceCandidatStage();
        }
        return instance;
    }
    
      public ArrayList<CandidatStage> getAllTasks(){
         String url = Statics.BASE_URL+"AfficherCandidatStageJS";
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
    @Override
    public void actionPerformed(NetworkEvent evt) {
    CandidatStages = parseabsence(new String(req.getResponseData()));
    req.removeResponseListener(this);
    }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
       //System.out.println(sprints);
    return CandidatStages;
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
    
    

    public boolean addTask(CandidatStage CandidatStage) {
        String url = Statics.BASE_URL + "/CandidatStage/" + CandidatStage.getIdentreprise_id() + "/" + CandidatStage.getIdstagiaire_id() + "/" + CandidatStage.getDate_candidature() + "/" + CandidatStage.getEmail(); //création de l'URL
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
public ArrayList<CandidatStage> parseabsence(String jsonText){
        try {
            CandidatStages=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println("map");
            System.out.println(tasksListJson);
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            System.out.println("list");
            System.out.println(list);
            for(Map<String,Object> obj : list){
             
             CandidatStage u = new CandidatStage();
               float id = Float.parseFloat(obj.get("id").toString());
               float nbr = Float.parseFloat(obj.get("identreprise_id").toString());
               //float id_user = Float.parseFloat(obj.get("idstagiaire_id").toString());
       
                u.setId((int)id);
               // u.setIdentreprise_id((int)nbr);
               // u.setIdstagiaire_id((int)id_user);date_candidature
                u.setDate_candidature((java.util.Date) (Date) obj.get("date_candidature"));
                //u.setEmail(obj.get("email").toString());
                
                
                
              
                
                CandidatStages.add(u);
            }
        } catch (IOException ex) {
            
        }
        return CandidatStages;
    }
  
   
  
    
}
