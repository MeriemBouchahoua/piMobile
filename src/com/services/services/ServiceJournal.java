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
import com.esprit.entites.Journal;
import com.esprit.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ace River
 */
public class ServiceJournal {

    public ArrayList<Journal> journals;

    public static ServiceJournal instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceJournal() {
        req = new ConnectionRequest();
    }

    public static ServiceJournal getInstance() {
        if (instance == null) {
            instance = new ServiceJournal();
        }
        return instance;
    }

    public boolean addJournal(Journal j) {

        int IdUser = j.getIdUser();
        int Moods_id = j.getMoods_id();

        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL + "/addJournalJSON/new?IdUser=" + IdUser + "&Moods_id=" + Moods_id;

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

    public ArrayList<Journal> parseJournals(String jsonText) {
        try {
            journals = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> journalsListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) journalsListJson.get("root");
            for (Map<String, Object> obj : list) {
                Journal e = new Journal();
                float id = Float.parseFloat(obj.get("id").toString());
                e.setId((int) id);
                e.setIdUser(((int) Float.parseFloat(obj.get("IdUser").toString())));
                e.setMoods_id(((int) Float.parseFloat(obj.get("Moods_id").toString())));
                journals.add(e);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return journals;
    }

    public ArrayList<Journal> getAllJournals() {
        String url = Statics.BASE_URL + "/affichePI";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                journals = parseJournals(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return journals;
    }

}
