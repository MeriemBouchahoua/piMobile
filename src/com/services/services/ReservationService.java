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
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.esprit.entites.Evenements;
import com.esprit.entites.Reservation;
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
public class ReservationService {
    
     private ConnectionRequest req;
    public static boolean resultOk = true;

    public static ReservationService instance = null;

    public static ReservationService getInstance() {
        if (instance == null) {
            instance = new ReservationService();
        }
        return instance;
    }

    public ReservationService() {
        req = new ConnectionRequest();
    }
    
    
        public void ajoutReservation(Reservation res) {
        String url = Statics.BASE_URL + "/ApiAddR?NombreDePlaceAReserver="+res.getNombre_de_place_areserve()+"&Email="+res.getEmail()+"&id="+res.getEvenements_id();
        req.setUrl(url);
        req.addResponseListener((e) -> {

            String str = new String(req.getResponseData());
            System.err.println("data=" + str);

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
        
        
         public List<Reservation> DetailReservation( String email , Reservation reservation) {
        
        String url = Statics.BASE_URL+"/ApiAfficheR/"+email;
        req.setUrl(url);
         
          ArrayList<Reservation> reservationList = new ArrayList<>();

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;

                jsonp = new JSONParser();

                Map<String, Object> mapReservation;
                try {
                    mapReservation = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) mapReservation.get("root");
                    for (Map<String, Object> obj : list) {

                        Reservation reservation = new Reservation();

                        float id = Float.parseFloat(obj.get("id").toString());
                        float nombre_de_place_areserver = Float.parseFloat(obj.get("NombreDePlaceAReserver").toString());
                        String email = obj.get("Email").toString();
                        float evenements_id = Float.parseFloat(obj.get("event").toString());
                       

                        reservation.setId((int) id );
                        reservation.setNombre_de_place_areserve((int) nombre_de_place_areserver);
                        reservation.setEvenements_id((int) evenements_id);
                        reservation.setEmail(email);
                        

                        reservationList.add(reservation);

                    }

                } catch (IOException ex) {
                    System.out.println(ex);
                }

            }

        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return reservationList;
    }

         
          public boolean deleteReservation(int id ) {
        String url = Statics.BASE_URL +"/ApiSuppR/"+id;
        
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
          
              public boolean modifierReservation(Reservation res,int id) {
         String url = Statics.BASE_URL + "/ApiUpdateR/"+id+"?NombreDePlaceAReserver="+res.getNombre_de_place_areserve()+
                 "&Email="+res.getEmail();
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

        
        
    
}
