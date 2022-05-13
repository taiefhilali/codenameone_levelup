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
import Models.User;
import com.mycompany.myapp.utils.Statics;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author ASUS
 */
public class ServiceProduits {

    public boolean resultOK;

    ArrayList<Produit> result = new ArrayList<>();
    ArrayList<Categorie> result1 = new ArrayList<>();
    ArrayList<User> result2 = new ArrayList<>();

    public static ServiceProduits instance = null;

    private ConnectionRequest req;

    public static ServiceProduits getInstance() {
        if (instance == null) {
            instance = new ServiceProduits();
        }
        return instance;
    }

    public ServiceProduits() {
        req = new ConnectionRequest();
    }

    public ArrayList<Produit> parseProduits(String jsonText) {
        try {
            result = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {

                // Déclaration des attributs affichés
                Produit t = new Produit();
          float id = Float.parseFloat(obj.get("idProduit").toString());
                String ref = obj.get("reference").toString();
                String nom = obj.get("nom").toString();
                String desc = obj.get("description").toString();
                float prix = Float.parseFloat(obj.get("prix").toString());
                float promotion = Float.parseFloat(obj.get("promotion").toString());
                String image = obj.get("image").toString();
                float prixFinal = Float.parseFloat(obj.get("prixFinal").toString());
//                String cat = obj.get("idCategorie").toString();
//                float prixFinal = Float.parseFloat(obj.get("prixFinal").toString());

                t.setId_produit((int)id);
                t.setReference(ref);
                t.setNom(nom);
                t.setDescription(desc);
                t.setPrix(prix);
                t.setPromotion(promotion);
                t.setImage(image);
                t.setPrixFinal(prixFinal);
                
//                t.setCategorie(cat);
                result.add(t);
            }

        } catch (IOException ex) {

        }
        return result;
    }

    public ArrayList<Categorie> parseCategories(String jsonText) {
        try {
            result1 = new ArrayList<Categorie>();
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
                result1.add(t);
            }

        } catch (IOException ex) {

        }
        return result1;
    }

    public ArrayList<User> parseUser(String jsonText) {
        try {
            result2 = new ArrayList<User>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {

                // Déclaration des attributs affichés
                User t = new User();
                float id = Float.parseFloat(obj.get("idUser").toString());
                String nom = obj.get("nom").toString();
                String prenom = obj.get("prenom").toString();

                t.setId_user(id);
                t.setNom(nom);
                t.setPrenom(prenom);
                result2.add(t);
            }

        } catch (IOException ex) {

        }
        return result2;
    }

    public ArrayList<User> getUserJoint() {
        //String url = Statics.BASE_URL+"/tasks/";

        req = new ConnectionRequest();
        String url = Statics.BASE_URL + "produit/FournisseurJoint";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                result2 = parseUser(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result2;
    }

    public ArrayList<Categorie> getAllCategories() {
        //String url = Statics.BASE_URL+"/tasks/";

        req = new ConnectionRequest();
        String url = Statics.BASE_URL + "produit/CategoriesListJoint";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                result1 = parseCategories(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result1;
    }

    public ArrayList<Produit> getAllProducts() {
        //String url = Statics.BASE_URL+"/tasks/";

        req = new ConnectionRequest();
        String url = Statics.BASE_URL + "produit/ProductsList";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                result = parseProduits(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }

    public boolean supprimerProduit(int idProduit) {
        String url = Statics.BASE_URL + "produit/deleteProductJson/" + idProduit;

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

    // Fonction ajout produit 
    public boolean ajouterProduit(Produit p) {
//               * @Route("/addProduitJson/add/{idUser}/{idCategorie}" , name="addProduitjson")
        float d = p.getPrix() - ((p.getPrix() * p.getPromotion()) / 100);
        String url = Statics.BASE_URL + "produit/addProduitJson/add/" + (int) p.getUser().getId()
                + "/" + (int) p.getCategorie().getId_categorie()
                + "?nom=" + p.getNom() + "&reference=" + p.getReference() + "&prix=" + p.getPrix() + "&description=" + p.getDescription()
                + "&promotion=" + p.getPromotion() + "&image=" + p.getImage() + "&prixFinal=" + d;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    // Fonction ajout produit 
    public boolean ModifierProduit(Produit p) {
//               * @Route("/addProduitJson/add/{idUser}/{idCategorie}" , name="addProduitjson")
        float d = p.getPrix() - ((p.getPrix() * p.getPromotion()) / 100);
        String url = Statics.BASE_URL + "produit/modifierProduitJson/modifier/" + (int) p.getId_produit() + "/" + (int) p.getUser().getId()
                + "/" + (int) p.getCategorie().getId_categorie()
                + "?nom=" + p.getNom() + "&reference=" + p.getReference() + "&prix=" + p.getPrix() + "&description=" + p.getDescription()
                + "&promotion=" + p.getPromotion() + "&image=" + p.getImage() + "&prixFinal=" + d;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    // La jointure pour affichage des catégories lors de l'ajout et l'edit

    public ArrayList<Categorie> getCategoriesJoint() {
        //String url = Statics.BASE_URL+"/tasks/";

        req = new ConnectionRequest();
        String url = Statics.BASE_URL + "produit/listCategories";

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                result1 = parseCategories(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result1;

    }

}
