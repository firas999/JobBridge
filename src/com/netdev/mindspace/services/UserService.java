/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netdev.mindspace.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.List;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.netdev.mindspace.entites.User;
import com.netdev.mindspace.utils.Statics;
import com.netdev.mindspace.utils.Session;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author hp
 */
public class UserService {
     public ArrayList<User> membre;
    
    public static UserService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private UserService() {
         req = new ConnectionRequest();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }
    
    public void signup(TextField nom, TextField prenom, TextField motdepasse, TextField type,TextField secteur, TextField siteweb, TextField tailleentreprise, TextField numerotelephone, TextField img, Resources res)
    {
      String url = Statics.BASE_URL + "/candidat/candidat/membre/signup?" + "nom=" + nom.getText().toString() + "&prenom=" + prenom.getText().toString()
                 + "&motdepasse=" + motdepasse.getText().toString() +  "&type=" + type.getText().toString()
                + "&secteur=" + secteur.getText().toString() + "&siteweb=" + siteweb.getText().toString() + "&tailleentreprise=" + tailleentreprise.getText().toString() 
                + "&numerotelephone=" + numerotelephone.getText().toString()+ "&img=" +  "&img=" + img.getText().toString()+ "&level=";
        
        req.setUrl(url);
        if(nom.getText().equals("") && prenom.getText().equals("") && motdepasse.getText().equals("") && type.getText().equals("")&& secteur.getText().equals("")
                && siteweb.getText().equals("") && tailleentreprise.getText().equals("") && numerotelephone.getText().equals("") )
        {
            Dialog.show("Erreur", "Veillez remplire tous les champs", "ok",null);
        }
        
        req.addResponseListener((e)-> {
            byte[]data = (byte[])e.getMetaData();
            String responceData =new String(data);
            System.out.println("data =====>"+responceData);
            System.out.println("data =====>"+data);
            
                      
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        
    }
    
    public ArrayList<User> users;
    
      public ArrayList<User> parseTasks(String jsonText){
        try {
            users=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

           
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
            java.util.List<Map<String,Object>> list = (java.util.List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                User t = new User();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                
                t.setNom(obj.get("nom").toString());
                t.setPrenom(obj.get("prenom").toString());
                 t.setType(obj.get("type").toString());
                  t.setSecteur(obj.get("secteur").toString());
                   t.setSiteweb(obj.get("siteweb").toString());
                    t.setTailleentreprise(((int)Float.parseFloat(obj.get("tailleentreprise").toString())));
                    t.setNumerotelephone(((int)Float.parseFloat(obj.get("numerotelephone").toString()))); 
                    t.setImg(obj.get("img").toString());
                     
                      
               //Ajouter la tâche extraite de la réponse Json à la liste
                users.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return users;
    }
    
    
  public ArrayList<User> getAllUsers(){
        String url = Statics.BASE_URL+"/candidat/candidat/getUsers";
        
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseTasks(new String(req.getResponseData())); 
                req.removeResponseListener(this);
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
    } 
  public User getUserr(int id){
         User u = new User();
        String url = Statics.BASE_URL+"/candidat/candidat/getUser/"+"{"+id+"}";
        
        req.setUrl(url);
        String str= new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
            
                JSONParser jsonp = new JSONParser();
                
                try{
                    
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                        
                u.setNom(obj.get("nom").toString());
                System.out.println(u.getNom());
                            
                }
                catch(IOException ex){
                    System.out.println("error related to sql : " + ex.getMessage());
                
                }
                
                System.out.println("data === " + str);
                
        }));
                NetworkManager.getInstance().addToQueueAndWait(req);
        return u;
    }
  
   
 
    
  
       public void signin(TextField nom ,TextField motdepasse ,Resources res)
    {
        String url = Statics.BASE_URL + "/candidat/candidat/signin?" + "nom=" + nom.getText().toString() + "&motdepasse=" + nom.getText().toString(); //création de l'URL
        req.setUrl(url);
        
        req.addResponseListener((e)-> {
            
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) +"";
            
            try{
            if(json.equals("failed"))
            {
                Dialog.show("Echec d'authontification", "Email ou password incorrect", "OK",null);
            }
            else {
                System.out.println("data ====>"+json);
//                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
//                if(user.size() >0)
                    Dialog.show("succes", "login cb", "ok",null);
            }
            }catch(Exception ex) {
                System.out.println(ex.getMessage());
            }
            
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    public Map<String, Object> parseConnexion(String jsonText) {
        User u = new User();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            return tasksListJson;

        } catch (IOException ex) {

        }
        return null;
    }
    public Map<String, Object> resultatCnx;
    
      public String connect(TextField nom ,TextField motdepasse) {
        String url = Statics.BASE_URL + "/candidat/candidat/signin?" + "nom=" + nom.getText().toString() + "&motdepasse=" + motdepasse.getText().toString(); //création de l'URL
       
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

               
                resultatCnx = parseConnexion(new String(req.getResponseData()));
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultatCnx.get("resultat").toString();
    }
     public User getUser(String text) {
         ArrayList<User>  l = getAllUsers();
        boolean test = false;
        int i;
        for (i = 0; i < l.size(); i++) {
            System.err.println(l.get(i).getId());
            if (l.get(i).getNom().compareTo(text) == 0) {
                return l.get(i);
            }
        }
        return null;
    }
   
     public User getUser1(int id) {
      ArrayList<User>  l = getAllUsers();
        int i;
        for (i = 0; i < l.size(); i++) {
            if (l.get(i).getId()== id) {
                return l.get(i);
            }
        }
        return null;
    }
      public void update(int id, String nom, String prenom, String type) {
        String url = Statics.BASE_URL + "/candidat/mobile/UpdateUserMobile/" + id + "/" + nom + "/" + prenom + "/" + type ;
        System.out.print(url);
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl(url);

        NetworkManager.getInstance().addToQueueAndWait(req);

    }
       String json;
    public String getPasswordByEmail(String email,Resources res){
        
         String url = Statics.BASE_URL+"/candidat/membre/getPasswordByEmail?address="+email;
        req = new ConnectionRequest(url , false);
        req.setUrl(url);
         req.addResponseListener((e)->{
             
             JSONParser j = new JSONParser();
         json = new String(req.getResponseData())+"";
        
         try {
             System.out.println("data =="+json);
            Map<String,Object> password =j.parseJSON(new CharArrayReader(json.toCharArray()));
            
            
         }catch(Exception ex){
            ex.printStackTrace();
        }
             
         });
         
          NetworkManager.getInstance().addToQueueAndWait(req);
          return json;
         }
       public void updatePassword(int id, String password) {
        String url = Statics.BASE_URL + "/candidat/mobile/UpdatePasswordMobile/" + id + "/" + password ;
        System.out.print(url);
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl(url);

        NetworkManager.getInstance().addToQueueAndWait(req);

    }
}
