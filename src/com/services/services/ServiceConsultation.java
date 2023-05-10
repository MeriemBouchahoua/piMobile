/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.esprit.entites.Consultation;
import com.esprit.utils.Statics;
import java.util.ArrayList;

/**
 *
 * @author Mongi
 */
public class ServiceConsultation {

    public ArrayList<Consultation> consultaions;

    public static ServiceConsultation instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceConsultation() {
        req = new ConnectionRequest();
    }

    public static ServiceConsultation getInstance() {
        if (instance == null) {
            instance = new ServiceConsultation();
        }
        return instance;
    }

    public boolean addCons(Consultation e) {

        String nom = e.getNom();
        String prenom = e.getPrenom();
        String cause = e.getCause();
        String date = e.getDate();
        String description = e.getDescription();
        String medecin = e.getMedecin();
        String cabinet = e.getCabinet();

        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL + "/mobile/consultation/add?nom=" + nom + "&prenom=" + prenom + "&cause=" + cause + "&date=" + date + "&description=" + description + "&medecin=" + medecin + "&cabinet=" + cabinet;

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

}
