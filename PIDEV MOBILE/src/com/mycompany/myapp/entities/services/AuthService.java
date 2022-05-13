/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import entities.User;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.lang.Object;
import java.util.Arrays;

import utils.Session;

import utils.Statics;

/**
 *
 * @author LENOVO
 */
public class AuthService {
String result ;
    public static AuthService instance = null;
    public int resultCode;
    private ConnectionRequest req;

    private AuthService() {
        req = new ConnectionRequest() {
            @Override
            protected void handleErrorResponseCode(int code, String message) {
                switch (code) {
                    //Do what you want here
                    case 400:
                        break;
                    case 404:
                        break;
                    case 409:
                        break;
                    default:
                        Dialog.show("Error", code + ": " + message, "Retry", "Cancel");
                        break;
                }
            }
        };
    }

    public static AuthService getInstance() {
        if (instance == null) {
            instance = new AuthService();
        }
        return instance;
    }

    public int login(User u) throws Exception {

        System.out.println("********");
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL_USER + "login/" + u.getEmail() + "/" + u.getPassword();

        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String result = new String(req.getResponseData());
                if (result.equals("Email incorrect")) {
                    resultCode = 400;
                } else if (result.equals("Mot de passe incorrect")) {
                    resultCode = 404;
                } else {
                    resultCode = 200;
                    try {
                        
                        String jsonText = new String(req.getResponseData());
                        JSONParser j = new JSONParser();
                        Map<String, Object> tasksListJson;
                        tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
                        List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
                       
                            float id = Float.parseFloat(tasksListJson.get("idUser").toString());
                            Session.setId((int) id);
                            Session.setNom(tasksListJson.get("nom").toString());
                            Session.setPrenom(tasksListJson.get("prenom").toString());
                            Session.setEmail(tasksListJson.get("email").toString());
                            Session.setRole(tasksListJson.get("role").toString());
                            Session.setPassword(tasksListJson.get("password").toString());
                            Session.setAdresse(tasksListJson.get("adresse").toString());
                            Session.setTel(tasksListJson.get("tel").toString());
                            Session.setDns(tasksListJson.get("dns").toString());
                            Session.setImage(tasksListJson.get("image").toString());
                        

                    } catch (IOException ex) {

                    }

                }
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return resultCode;
    }

    public int Password(String email, int code) throws Exception {

        System.out.println("********");
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL_USER + "password/" + email + "/" + code;

        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String result = new String(req.getResponseData());
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return 0;

    }

    public int Reset(String email, String password) throws Exception {

        System.out.println("********");
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL_USER + "passwordReset/" + email + "/" + password;

        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                 result = req.getResponseData().toString();
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return 0;

    }

    public int register(User u) throws Exception {

        System.out.println("********");
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL_USER + "inscri/" + u.getEmail() + "/" + u.getPassword() + "/" + u.getNom() + "/" + u.getPrenom() + "/" + u.getAdresse() + "/" + u.getTel() + "/" + u.getDns();

        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String result = new String(req.getResponseData());
                if (result.equals("User ajouter")) {
                    resultCode = 200;
                } else {
                    resultCode = 404;
                }
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return resultCode;
    }
     static String key = null;

    public void printId(Object object) {
        key = (String) object;
    }
     public String Recaptcha()  {

        System.out.println("********");
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url ="https://www.google.com/recaptcha/api/siteverify?secret=6Lf-fLYeAAAAALntWUcfkc5ZOikC5IzZtrbtZLEA&response="+key;
       
        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                 result = new String(req.getResponseData());
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;

    }
    

}
