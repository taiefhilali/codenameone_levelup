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
import com.mycompany.myapp.entities.Livraison;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author User
 */
public class ServiceLivraison {
    


/**
 *
 * @author bhk
 */


    public ArrayList<Livraison> livraisons;
    
    public static ServiceLivraison instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceLivraison() {
         req = new ConnectionRequest();
    }

    public static ServiceLivraison getInstance() {
        if (instance == null) {
            instance = new ServiceLivraison();
        }
        return instance;
    }

    public boolean addLivraison(Livraison l) {
        System.out.println(l);
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
     //  String url = Statics.BASE_URL + "/livraison/addLivraisontJson/add/"+l.getDate()+"/"+l.getEtat_livraison()+"/2/15";
     //String url = Statics.BASE_URL + "/livraison/addLivraisontJson/add/"+l.getId_user()+"/"+l.getId_commande()+"?date="+l.getDate()+"&etat_livraison="+l.getEtat_livraison();
    String url = Statics.BASE_URL + "livraison/addjson/1/11?date=2022-02-27&etatLivraison="+l.getEtat_livraison();
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

    public ArrayList<Livraison> parseLivraisons(String jsonText){
        try {
            livraisons=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> livraisonsListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)livraisonsListJson.get("root");
            for(Map<String,Object> obj : list){
                Livraison l = new Livraison();
                float id = Float.parseFloat(obj.get("idLivraison").toString());
                l.setId_livraison((int)id);
              
                    l.setDate(obj.get("date").toString());
                    l.setEtat_livraison(obj.get("etatLivraison").toString());
                livraisons.add(l);
            }
            
            
        } catch (IOException ex) {
            
        }
        return livraisons;
    }
      public boolean supprimerLivraison(Livraison l){
     String url = Statics.BASE_URL+"livraison/deleteLivraisonJson/41?date=2022-02-27&etatLivraison="+l.getEtat_livraison();
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
    
    public boolean modifierLivraison(Livraison l) {
        System.out.println(l);
        System.out.println("********");
       //String url = Statics.BASE_URL + "editjson?Id_Livraison="  + l.getDate()+ "&date=" + l.getEtat_livraison() ;
         String url = Statics.BASE_URL + "livraison/editjson/41/1/11?date=2022-02-27&etatLivraison="+l.getEtat_livraison();       
    
       req.setUrl(url);
       
     
      
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
    public ArrayList<Livraison> getAllLivraisons(){
        //String url = Statics.BASE_URL+"/tasks/";
        req=new ConnectionRequest();
        String url = Statics.BASE_URL+"livraison/AllLivraisons";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                livraisons = parseLivraisons(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return livraisons;
    }
}

    
