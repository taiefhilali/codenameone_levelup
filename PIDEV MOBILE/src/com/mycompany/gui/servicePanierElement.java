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
import Models.Panier_elem;
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
public class servicePanierElement {
    
    ArrayList<Panier_elem> result = new ArrayList<>();
    ArrayList<Produit> result2 = new ArrayList<>();
    public boolean resultOK;
    public static servicePanierElement instance = null;
    
    private ConnectionRequest req ;
    
     public static servicePanierElement getInstance(){
         if(instance == null)
             instance = new servicePanierElement();
             return instance;
     }
    
    public servicePanierElement(){
        req = new ConnectionRequest();
    }
    
    
    public ArrayList<Produit> parsePanierElementsProducts(String jsonText){
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
            
                t.setId_produit(id);
                t.setPrix(prix);
                t.setNom(nom);
                t.setDescription(Desc);
                result2.add(t);
            }
            
        } catch (IOException ex) {
            
        }
        return result2;
    }
    
     public ArrayList<Produit> getPanierElementsProducts(int id){
        //String url = Statics.BASE_URL+"/tasks/";
       
        req=new ConnectionRequest();
        String url = Statics.BASE_URL+"panier/elem/PanierElementsJSONProducts/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                result2 = parsePanierElementsProducts(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result2;
    }
    
     
     
     
    
    public ArrayList<Panier_elem> parsePanierElements(String jsonText){
        try {
            result =new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Panier_elem t = new Panier_elem();
                float id = Float.parseFloat(obj.get("idElem").toString());
                float quant = Float.parseFloat(obj.get("quantite").toString());
                
                t.setId_elem(id);
                t.setQuantite((int)quant);
                result.add(t);
            }
        } catch (IOException ex) {
            
        }
        return result;
    }
    
    
     public ArrayList<Panier_elem> getPanierElements(int id){
        //String url = Statics.BASE_URL+"/tasks/";
       
          req=new ConnectionRequest();
        String url = Statics.BASE_URL+"panier/elem/PanierElementsJSON/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                result = parsePanierElements(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
    
    
     
     public boolean deleteElementPanier(int id ) {
        String url = Statics.BASE_URL +"panier/elem/deleteElementPanier/"+id;
        
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
    
     public boolean modifierElement(int quantite, int id) {
        String url = Statics.BASE_URL +"panier/elem/editJSON/"+id+"?quantite="+quantite;
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;    
}
     
     public boolean AjouterElement(int quantite, float idP, int idU) {
        String url = Statics.BASE_URL +"panier/elem/AddJSON/"+idP+"/"+idU+"/"+quantite;
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;    
}
     
     
     
}