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
import Models.Categorie;
import Models.Produit;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HP
 */
public class ServiceCategorie {
    
    public boolean resultOK;

    ArrayList<Categorie> result = new ArrayList<>();
    public static ServiceCategorie instance = null;

    private ConnectionRequest req;

    public static ServiceCategorie getInstance() {
        if (instance == null) {
            instance = new ServiceCategorie();
        }
        return instance;
    }

    public ServiceCategorie() {
        req = new ConnectionRequest();
    }

    public ArrayList<Categorie> parseCategories(String jsonText) {
        try {
            result = new ArrayList<Categorie>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {

                // Déclaration des attributs affichés
                Categorie t = new Categorie();
                float id = Float.parseFloat(obj.get("idCategorie").toString());
                String nom = obj.get("nomCategorie").toString();

                t.setId_categorie(id);
                t.setNom_categorie(nom);
                result.add(t);
            }

        } catch (IOException ex) {

        }
        return result;
    }

    public ArrayList<Categorie> getCategories() {
        //String url = Statics.BASE_URL+"/tasks/";

        req = new ConnectionRequest();
        String url = Statics.BASE_URL + "categorie/CategoriesList";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                result = parseCategories(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
    //Suppression

    public boolean supprimerCategorie(int id) {
        String url = Statics.BASE_URL + "categorie/deleteCategorieJson/" + id;

        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseCodeListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    // Fonction de modification

    public boolean modifierCategorie(String nomCategorie, int idCategorie) {
        String url = Statics.BASE_URL +"categorie/editCategorieJson/edit/"+idCategorie+"?nomCategorie="+nomCategorie;
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
        // Fonction d'ajout

    public boolean ajouterCategorie(String nomCategorie) {
        String url = Statics.BASE_URL +"categorie/addCategorieJson/add?nomCategorie="+nomCategorie;
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
