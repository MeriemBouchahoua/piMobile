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
import com.esprit.entites.Article;
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
public class ArticleService {
    
      private ConnectionRequest req;
    public static boolean resultOk = true;

    public static ArticleService instance = null;

    public static ArticleService getInstance() {
        if (instance == null) {
            instance = new ArticleService();
        }
        return instance;
    }

    public ArticleService() {
        req = new ConnectionRequest();
    }


     
     
     
     public ArrayList<Article> afficheArticle() {

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date createdAt;
        ArrayList<Article> articles = new ArrayList<>();

        String url = Statics.BASE_URL + "/APIafficheArticle";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;

                jsonp = new JSONParser();

                Map<String, Object> mapArticle;
                try {
                    mapArticle = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) mapArticle.get("root");
                    for (Map<String, Object> obj : list) {
                        Article article = new Article();

                        float id = Float.parseFloat(obj.get("id").toString());
                        float id_user = Float.parseFloat(obj.get("Id_user").toString());
                        String articleD = obj.get("article").toString();
                        String nom_utilisateur = obj.get("NomUtilisateur").toString();
                        String iamge = obj.get("image").toString();
                   
                        String created_at = obj.get("createdAt").toString();
                        String updated_at = obj.get("updatedAt").toString();

                        article.setId((int) id);
                        article.setId_user((int) id_user);
                        article.setNom_utilisateur(nom_utilisateur);
                        article.setArticle(articleD);
                        article.setImage(iamge);
                  

                        try {
                            article.setCreated_at(sf.parse(created_at));
                            article.setCreated_at(sf.parse(created_at));
                        } catch (ParseException ex) {
                            System.out.println(ex.toString());
                        }
                        
                        articles.add(article);

                    }

                } catch (IOException ex) {
                }

            }

        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return articles;
    }
     
     
}
