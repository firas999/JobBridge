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
//import com.mycompany.pi.entities.Certification;
import com.mycompany.pi.entities.DemandeCertif;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ouaji
 */
public class ServiceDemandeCertif {
    
    
     public ArrayList<DemandeCertif> DemandesCertifs;
    
    public static ServiceDemandeCertif instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceDemandeCertif() {
         req = new ConnectionRequest();
    }

    public static ServiceDemandeCertif getInstance() {
        if (instance == null) {
            instance = new ServiceDemandeCertif();
        }
        return instance;
    }
    
    
    

    public ArrayList<DemandeCertif> parseTasks(String jsonText){
        try {
            DemandesCertifs=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
            */
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.               
            
            Le format Json impose que l'objet soit définit sous forme
            de clé valeur avec la valeur elle même peut être un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adéquate en Java pour stocker des couples Key/Value.
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
            */
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                DemandeCertif t = new DemandeCertif();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
               
            //    float Prix = Float.parseFloat(obj.get("Prix").toString());
             //   t.setPrix((int) Prix);
               
                 // t.setType(obj.get("Type").toString());
              t.setPrenom_participant(obj.get("PrenomParticipant").toString());
             //   t.setDate_passage((Date) obj.getPrenom_participant("prenom_participant"));
           t.setEmail(obj.get("Email").toString());
              t.setNom_participant(obj.get("NomParticipant").toString());
//                 t.setIdentreprise_id(((int)Float.parseFloat(obj.get("identreprise_id").toString())));

                
                        
                //Ajouter la tâche extraite de la réponse Json à la liste
                DemandesCertifs.add(t);
            }
            
            
           
            
            
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return DemandesCertifs;
    }
    
    public ArrayList<DemandeCertif> getAllTasks0(){
        String url = Statics.BASE_URL+"/AfficherDemandeCertifJS/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                DemandesCertifs = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return DemandesCertifs;
    }
    
}
