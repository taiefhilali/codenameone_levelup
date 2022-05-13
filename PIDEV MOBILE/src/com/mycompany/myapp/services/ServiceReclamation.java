/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

/**
 *
 * @author User
 */
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Livraison;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author User
 */
public class ServiceReclamation {
    


/**
 *
 * @author bhk
 */


    public ArrayList<Reclamation> reclamations;
    
    public static ServiceReclamation instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceReclamation() {
         req = new ConnectionRequest();
    }

    public static ServiceReclamation getInstance() {
        if (instance == null) {
            instance = new ServiceReclamation();
        }
        return instance;
    }

    public boolean addReclamation(Reclamation r) {
        System.out.println(r);
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
     //  String url = Statics.BASE_URL + "/livraison/addLivraisontJson/add/"+l.getDate()+"/"+l.getEtat_livraison()+"/2/15";
     //String url = Statics.BASE_URL + "/livraison/addLivraisontJson/add/"+l.getId_user()+"/"+l.getId_commande()+"?date="+l.getDate()+"&etat_livraison="+l.getEtat_livraison();
    String url = Statics.BASE_URL + "reclamation/addReclamationtJson/add/1/33?description="+r.getDescription()+"&warn=1";
       req.setUrl(url);
       req.setPost(false);
        System.out.println(url);
       
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Reclamation> parseReclamations(String jsonText){
        try {
            reclamations=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> reclamationsListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)reclamationsListJson.get("root");
            for(Map<String,Object> obj : list){
                Reclamation r = new Reclamation();
                float id = Float.parseFloat(obj.get("idReclamation").toString());
                r.setId_reclamation((int)id);
              
                r.setDescription(obj.get("description").toString());
                reclamations.add(r);
            }
            
            
        } catch (IOException ex) {
            
        }
        return reclamations;
    }
    
    
 public boolean supprimerReclamation(Reclamation r){
     String url = Statics.BASE_URL+"reclamation/deleteReclamationJson/46?description="+r.getDescription()+"&warn=1";;
     req.setUrl(url);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
    
    }
    
    public boolean modifierReclamation(Reclamation r) {
        System.out.println(r);
        System.out.println("********");
       //String url = Statics.BASE_URL + "editReclamationtJson/edit?Id_reclamation="  + r.getDescription()+ "" + r.getWarn() ;
       String url = Statics.BASE_URL + "reclamation/editReclamationtJson/edit/46/1/33?description="+r.getDescription()+"&warn=1";
    
       req.setUrl(url);
       
       req.addArgument("Description", r.getDescription());
       req.addArgument("Warn", r.getWarn()+"");
      
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    public ArrayList<Reclamation> getAllReclamations(){
        //String url = Statics.BASE_URL+"/tasks/";
        req=new ConnectionRequest();
        String url = Statics.BASE_URL+"reclamation/AllReclamations";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               reclamations = parseReclamations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reclamations;
    }
}
