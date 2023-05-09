/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.esprit.entites.Comments;
import com.esprit.entites.Post;

import com.esprit.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author z.maryem
 */
public class CommentService {

    private ConnectionRequest req;
    public static boolean resultOk = true;

    public static CommentService instance = null;

    public static CommentService getInstance() {
        if (instance == null) {
            instance = new CommentService();
        }
        return instance;
    }

    public CommentService() {
        req = new ConnectionRequest();
    }
    
    
        public void ajoutComm(Comments com,int id) {
        String url = Statics.BASE_URL + "/APIaddC/"+id+"?ID_user=" + com.getId_user()+"&Reponse=" + com.getResponse();
        req.setUrl(url);
        req.addResponseListener((e) -> {

            String str = new String(req.getResponseData());
            System.err.println("data=" + str);

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
        
        
        
        public ArrayList<Comments> afficheComm(int id) {

        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date createdAt;
        ArrayList<Comments> comm = new ArrayList<>();

        String url = Statics.BASE_URL + "/APIafficheReponse/"+id;
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;

                jsonp = new JSONParser();

                Map<String, Object> mapComm;
                try {
                    mapComm = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) mapComm.get("root");
                    for (Map<String, Object> obj : list) {
                        Comments com = new Comments();                      
                        float id = Float.parseFloat(obj.get("id").toString());
                      //  float commentaire_id = Float.parseFloat(obj.get("commentairesId").toString());
                        float id_user = Float.parseFloat(obj.get("Id_user").toString());
                        String response = obj.get("Reponse").toString();
                        String date = obj.get("Date").toString();
                        com.setId((int) id);
                        com.setId_user((int) id_user);
                      //  com.setCommentaire_id((int)commentaire_id);
                        com.setResponse(response);
                        com.setDate(date);
                        comm.add(com);
                    }

                } catch (IOException ex) {
                }
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return comm;
    }
        
        
        
        /*        //Update 
    public boolean modifierComm(Comments comment) {
         String url = Statics.BASE_URL + "/APIupdateCom?id="+comment.getId()+"id_user=" + comment.getId_user() + "&commentaires_id="
                + comment.getCommentaire_id()+ "&reponse=" + comment.getResponse()+ "&date=" + comment.getDate();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
        
    }
    
    
           //Delete 
    public boolean deleteComm(int id ) {
        String url = Statics.BASE_URL +"/suppCom?id="+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
*/
}
