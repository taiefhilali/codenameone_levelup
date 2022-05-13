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
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Post;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author msi
 */
public class ServicePost {
    public ArrayList<Post> Posts;
    
    public static ServicePost instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ServicePost() {
         req = new ConnectionRequest();
    }

    public static ServicePost getInstance() {
        if (instance == null) {
            instance = new ServicePost();
        }
        return instance;
    }
    


    public ArrayList<Post> parsePosts(String jsonText){
                try {

                    System.out.println(jsonText);
            Posts=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Post a = new Post();
                                
                float id = Float.parseFloat(obj.get("id").toString());
                a.setId((int) id);
                a.setContent(obj.get("content").toString());
                a.setTitle(obj.get("title").toString());

                               
                Posts.add(a);


            }
        } catch (IOException ex) {
            
        }
        return Posts;
    }
    public ArrayList<Post> getAllPosts(){
        String url = Statics.BASE_URL+"post/backpostmobile";
        req.setUrl(url);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Posts = parsePosts(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return Posts;
    }

    public ArrayList<Post> getUser(int id){
        String url = Statics.BASE_URL+"post/backpostmobile/?id="+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Posts = parsePosts(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return Posts;
    }


    public boolean addPosts(Post u) {
        String url = Statics.BASE_URL + "post/addPostJSON/new?content="+u.getContent()+"&title="+u.getTitle(); //création de l'URL
               req.setUrl(url);
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

        public boolean editPosts(Post u) {
        String url = Statics.BASE_URL + "post/updatePostJSON"+u.getId()+"?content="+u.getContent()+"&title="+u.getTitle(); //création de l'URL
               req.setUrl(url);
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

    public boolean deletePost(int id) {
        String url = Statics.BASE_URL + "post/deletePosttJSON"+id;
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
public String calculer_nbp(String title) {
        String l = null;
        String requete = "SELECT COUNT(*) FROM post where title='" + title + "'";
        

          
           
                String chaine = String.valueOf(l);
                l = chaine;
                return l;
            
        
        
    }


}
