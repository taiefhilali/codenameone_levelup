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
import com.mycompany.myapp.entities.Comment;
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
public class ServiceComment {
     public ArrayList<Comment> comments;
    
    public static ServiceComment instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ServiceComment() {
         req = new ConnectionRequest();
    }

    public static ServiceComment getInstance() {
        if (instance == null) {
            instance = new ServiceComment();
        }
        return instance;
    }
    


    public ArrayList<Comment> parseComments(String jsonText){
                try {

                    System.out.println(jsonText);
            comments=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Comment a = new Comment();
                                
                float id = Float.parseFloat(obj.get("idc").toString());
                float resp = Float.parseFloat(obj.get("resp").toString());
                a.setIdc((int) id);
                a.setContenu(obj.get("contenu").toString());
                a.setLabel(obj.get("label").toString());
                a.setResp((int) resp);


                               
                comments.add(a);


            }
        } catch (IOException ex) {
            
        }
        return comments;
    }
    public ArrayList<Comment> getAllcomments(){
        String url = Statics.BASE_URL+"comment/backcommentmobile";
        req.setUrl(url);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                comments = parseComments(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return comments;
    }

    public ArrayList<Comment> getUser(int id){
        String url = Statics.BASE_URL+"comment/backcommentmobile/?id="+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                comments = parseComments(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return comments;
    }
    


    public boolean addcomments(Comment u) {
        String url = Statics.BASE_URL + "comment/addCommentJSON/new?contenu="+u.getContenu()+"&label="+u.getLabel()+"&resp="+u.getResp(); //création de l'URL
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

        public boolean editcomments(Comment u) {
        String url = Statics.BASE_URL + "comment/updateCommentJSON/"+u.getIdc()+"?contenu="+u.getContenu()+"&label="+u.getLabel()+"&resp="+u.getResp(); //création de l'URL
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

    public boolean deletecomment(int idc) {
        String url = Statics.BASE_URL + "comment/deleteCommentJSON/"+idc;
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

}
