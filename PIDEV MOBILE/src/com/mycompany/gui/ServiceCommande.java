/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import Models.Commande;
import Models.Commande_elem;
import Models.Produit;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class ServiceCommande {
    
     ArrayList<Commande> result = new ArrayList<>();
     ArrayList<Commande_elem> result1 = new ArrayList<>();
     ArrayList<Produit> result2 = new ArrayList<>();
     public boolean resultOK;
    public static ServiceCommande instance = null;
    
    private ConnectionRequest req ;
    
     public static ServiceCommande getInstance(){
         if(instance == null)
             instance = new ServiceCommande();
             return instance;
     }
    
    public ServiceCommande(){
        req = new ConnectionRequest();
    }
    
     public ArrayList<Commande> parseCommandes(String jsonText){
        try {
            result =new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Commande t = new Commande();
                float id = Float.parseFloat(obj.get("idCommande").toString());
                t.setId_commande((int)id);
                t.setPrix_livraison((Float.parseFloat(obj.get("prixLivraison").toString())));
                t.setPrix_produits((Float.parseFloat(obj.get("prixProduits").toString())));
                t.setPrix_total((Float.parseFloat(obj.get("prixTotal").toString())));
                t.setMode(obj.get("mode").toString());
                result.add(t);
            }
        } catch (IOException ex) {
            
        }
        return result;
    }
    
      public ArrayList<Produit> parseCommandeDetailsProducts(String jsonText){
        try {
        
            result2 =new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
      
           
            for(Map<String,Object> obj : list){
                Produit t = new Produit();
                float id = Float.parseFloat(obj.get("idProduit").toString());
                float prix = Float.parseFloat(obj.get("prix").toString());
                String nom = obj.get("nom").toString();
                String Desc = obj.get("description").toString();
                   float prixFinal = Float.parseFloat(obj.get("prixFinal").toString());
            
                t.setId_produit(id);
                t.setPrix(prix);
                t.setNom(nom);
                t.setDescription(Desc);
                t.setPrixFinal(prixFinal);
                result2.add(t);
            }
            
        } catch (IOException ex) {
            
        }
        return result2;
    }
     
     
     public ArrayList<Commande_elem> parseCommandeDetails(String jsonText){
        try {
          
            result1 =new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
          
            for(Map<String,Object> obj : list){
                Commande_elem t = new Commande_elem();
                float id = Float.parseFloat(obj.get("idElemc").toString());
                float quant = Float.parseFloat(obj.get("quantite").toString());
                
                t.setId_elemC((int)id);
                t.setQuantite((int)quant);
   
                result1.add(t);
            }
            
        } catch (IOException ex) {
            
        }
        return result1;
    }
     
     
    public ArrayList<Commande> getAllCommandes(int id){
        //String url = Statics.BASE_URL+"/tasks/";
       
          req=new ConnectionRequest();
        String url = Statics.BASE_URL+"commande/ClientCommandesJSON/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                result = parseCommandes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
    
    public ArrayList<Commande_elem> getCommandeDetails(int id){
        //String url = Statics.BASE_URL+"/tasks/";
       
        req=new ConnectionRequest();
        String url = Statics.BASE_URL+"detail/commande/CommandeDetails/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                result1 = parseCommandeDetails(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result1;
    }
    
    public ArrayList<Produit> getCommandeDetailsOff(int id){
        //String url = Statics.BASE_URL+"/tasks/";
       
        req=new ConnectionRequest();
        String url = Statics.BASE_URL+"detail/commande/CommandeDetailsJoint/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                result2 = parseCommandeDetailsProducts(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result2;
    }
    
    
    
    public boolean deleteCommande(int id ) {
        String url = Statics.BASE_URL +"commande/deleteCommande/"+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    resultOK = req.getResponseCode() == 200;
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOK;
    }
    
    
    public boolean AjouterCommande(int idU,float produits , float Livraison , float Total) {
        String url = Statics.BASE_URL +"commande/AddCmdJSON/"+idU+"/"+produits+"/"+Livraison+"/"+Total+"/Livraison?latt=33.22121211312&lonn=23.85442213112";
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    resultOK = req.getResponseCode() == 200;
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOK;
    }
     
}
