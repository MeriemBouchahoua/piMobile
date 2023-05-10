///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.myapp.services;
//import com.codename1.io.CharArrayReader;
//import com.codename1.io.ConnectionRequest;
//import com.codename1.io.JSONParser;
//import com.codename1.io.Log;
//import com.codename1.io.NetworkEvent;
//import com.codename1.io.NetworkManager;
//import com.codename1.ui.events.ActionListener;
//import com.mycompany.myapp.entities.Equipe;
//import com.mycompany.myapp.utils.Statics;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//
///**
// *
// * @author Ace River
// */
//public class ServiceEquipe {
//    
//      public ArrayList<Equipe> equipes;
//
//    public static ServiceEquipe instance = null;
//    public boolean resultOK;
//    private ConnectionRequest req;
//
//    public ServiceEquipe() {
//        req = new ConnectionRequest();
//    }
//
//    public static ServiceEquipe getInstance() {
//        if (instance == null) {
//            instance = new ServiceEquipe();
//        }
//        return instance;
//    }
//    
//    
//    public boolean addEquipe(Equipe e) {
//
//        String name = e.getNom();
//        String joueurs = e. getJoueurs();
//        int classement =  e.getClassement();
//        String entraineur = e.getEntraineur();
//        String categorie = e.getCategorie();
//      
//       
//        
//        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
//      String url = Statics.BASE_URL1 + "addEquipeJSON/new?nom=" + name + "&joueurs=" + joueurs + "&classement=" + classement + "&entraineur=" + entraineur + "&categorie=" + categorie;
//
//
//        req.setUrl(url);
//        req.setPost(false);
//        
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return resultOK;
//    }
//    
//public ArrayList<Equipe> parseEquipes(String jsonText) {
//    try {
//        equipes = new ArrayList<>();
//        JSONParser j = new JSONParser();
//        Map<String, Object> equipesListJson
//                = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//
//        List<Map<String, Object>> list = (List<Map<String, Object>>) equipesListJson.get("root");
//        for (Map<String, Object> obj : list) {
//            Equipe e = new Equipe();
//            float id = Float.parseFloat(obj.get("id").toString());
//            e.setId((int) id);
//            e.setClassement(((int) Float.parseFloat(obj.get("classement").toString())));
//            e.setJoueurs(obj.get("joueurs").toString());
//            e.setEntraineur(obj.get("entraineur").toString());
//            e.setCategorie(obj.get("categorie").toString());
//           String picture = obj.get("picture") != null ? obj.get("picture").toString() : "";
//            e.setPicture(picture);
//            if (obj.get("nom") == null) {
//                e.setNom("null");
//            } else {
//                e.setNom(obj.get("nom").toString());
//
//            }
//            
//            equipes.add(e);
//        }
//
//    } catch (IOException ex) {
//        System.out.println(ex.getMessage());
//    }
//    return equipes;
//}
//
//public ArrayList<Equipe> getAllEquipes() {
//    String url = Statics.BASE_URL1 + "equipemobile/list";
//    req.setUrl(url);
//    req.setPost(false);
//    req.addResponseListener(new ActionListener<NetworkEvent>() {
//        @Override
//        public void actionPerformed(NetworkEvent evt) {
//            equipes = parseEquipes(new String(req.getResponseData()));
//            req.removeResponseListener(this);
//        }
//    });
//    NetworkManager.getInstance().addToQueueAndWait(req);
//    return equipes;
//}
//
//
//
//}
