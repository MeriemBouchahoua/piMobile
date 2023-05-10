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
import com.codename1.ui.events.ActionListener;
import com.esprit.entites.Mood;
import com.esprit.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ace River
 */
public class ServiceMood {

    public ArrayList<Mood> moods;

    public static ServiceMood instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceMood() {
        req = new ConnectionRequest();
    }

    public static ServiceMood getInstance() {
        if (instance == null) {
            instance = new ServiceMood();
        }
        return instance;
    }

    public boolean addMood(Mood e) {

        int MoodId = e.getMoodId();
        int UserId = e.getUserId();
        String Mood = e.getMood();
        String Description = e.getDescription();
        String date = e.getDate();

        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL + "/addMoodJSON/new?MoodId=" + MoodId + "&UserId=" + UserId + "&Mood=" + Mood + "&Description=" + Description + "&Date=" + date;

        req.setUrl(url);
        req.setPost(false);

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

    public ArrayList<Mood> parseMoods(String jsonText) {
        try {
            moods = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> moodsListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) moodsListJson.get("root");
            for (Map<String, Object> obj : list) {
                Mood e = new Mood();
                float id = Float.parseFloat(obj.get("id").toString());
                e.setId((int) id);
                e.setMoodId(((int) Float.parseFloat(obj.get("MoodId").toString())));
                e.setUserId(((int) Float.parseFloat(obj.get("UserId").toString())));
                e.setMood(obj.get("Mood").toString());
                e.setDescription(obj.get("Description").toString());

                moods.add(e);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return moods;
    }

    public ArrayList<Mood> getAllMoods() {
        String url = Statics.BASE_URL + "/afficheApi";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                moods = parseMoods(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return moods;
    }

}
