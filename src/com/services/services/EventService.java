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
import com.esprit.entites.Evenements;
import com.esprit.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author b.maryem
 */
public class EventService {

    private ConnectionRequest req;
    public static boolean resultOk = true;

    public static EventService instance = null;

    public static EventService getInstance() {
        if (instance == null) {
            instance = new EventService();
        }
        return instance;
    }

    public EventService() {
        req = new ConnectionRequest();
    }

    public void ajoutEvent(Evenements event) {
        String url = Statics.BASE_URL + "/ApiaddE?NomEvenement=" + event.getNom_evenement() + "&DescriptionEvenement="
                + event.getDescription_evenement() + "&LieuEvenement=" + event.getLieu_evenement() + "&DateEvenement=" + event.getDate_evenement()
                + "&Heure=" + event.getHeure() + "&NbrDePlaces=" + event.getNbr_de_places() + "&type=" + event.getType();
                //+"$Image="+event.getImage();
        req.setUrl(url);
        req.addResponseListener((e) -> {

            String str = new String(req.getResponseData());
            System.err.println("data=" + str);

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    //Update 
    public boolean modifierEvent(Evenements event, int id) {
        String url = Statics.BASE_URL + "/APIupdateEvent/"+id+"?NomEvenement=" + event.getNom_evenement() + "&DescriptionEvenement="
                + event.getDescription_evenement() + "&LieuEvenement=" + event.getLieu_evenement() + "&DateEvenement=" + event.getDate_evenement()
                + "&Heure=" + event.getHeure() + "&NbrDePlaces=" + event.getNbr_de_places() + "&type=" + event.getType() ;
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        return resultOk;

    }

    public ArrayList<Evenements> afficheEvent() {

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date createdAt;
        ArrayList<Evenements> eventsList = new ArrayList<>();

        String url = Statics.BASE_URL + "/ApiafficheE";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;

                jsonp = new JSONParser();

                Map<String, Object> mapEvents;
                try {
                    mapEvents = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) mapEvents.get("root");
                    for (Map<String, Object> obj : list) {

                        Evenements event = new Evenements();

                        float id = Float.parseFloat(obj.get("id").toString());
                        String nom_evenement = obj.get("NomEvenement").toString();
                        String lieu_evenement = obj.get("LieuEvenement").toString();
                        String date_evenement = obj.get("DateEvenement").toString();
                        String description_evenement = obj.get("DescriptionEvenement").toString();
                        float nbr_de_places = Float.parseFloat(obj.get("NbrDePlaces").toString());
                        String type = obj.get("Type").toString();
                        String image = obj.get("Image").toString();
                        String heure = obj.get("Heure").toString();
                        String created_at = obj.get("createdAt").toString();
                        String updated_at = obj.get("updatedAt").toString();
                     //   String likes = obj.get("likes").toString();
                      //  String dislikes = obj.get("dislike").toString();

                        event.setId((int) id);
                        event.setNom_evenement(nom_evenement);
                        event.setLieu_evenement(lieu_evenement);
                        event.setDate_evenement(date_evenement);
                        event.setDescription_evenement(description_evenement);
                        event.setNbr_de_places((int) nbr_de_places);
                        event.setType(type);
                        event.setImage(image);
                        event.setHeure(heure);
                        event.setCreated_at(created_at);
                        event.setUpdated_at(updated_at);
                        event.setLikes(0);
                        event.setDislikes(0);

                        eventsList.add(event);

                    }

                } catch (IOException ex) {
                }

            }

        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return eventsList;
    }



 public boolean deleteEvent(int id ) {
        String url = Statics.BASE_URL +"/ApisuppEvenement/"+id;
        
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


}