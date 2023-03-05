/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.entites.Evenements;
import com.esprit.entites.Reservation;
import com.services.services.ReservationService;

/**
 *
 *@author b.maryem
 */
public class ReservEventForm extends BaseForm {
    
    
    private Reservation reservation ; 

    public ReservEventForm(Reservation reservation) {
        this.reservation = reservation;
    }
    

    public ReservEventForm(Resources res,Evenements ev ){
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
        TextField event_id = new TextField(String.valueOf(ev.getId()), "Event_id", 20, TextField.ANY);
        TextField email = new TextField("", "email", 20, TextField.ANY);
        TextField nbrplace = new TextField("", "nombre de place", 20, TextField.ANY);
        
       
        Button signIn = new Button("headback");
        signIn.addActionListener(e -> previous.showBack());
        event_id.setSingleLineTextArea(false);
        email.setSingleLineTextArea(false);
        nbrplace.setSingleLineTextArea(false);
        Button next = new Button("add");
        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                new FloatingHint(event_id),
                createLineSeparator(),
                new FloatingHint(email),
                createLineSeparator(),
                new FloatingHint(nbrplace),
                createLineSeparator(),
                createLineSeparator()
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,signIn
        ));
        next.requestFocus();
         next.addActionListener(e -> {
            Reservation reservation = new Reservation();
            reservation.setEvenements_id(Integer.valueOf(event_id.getText()));
            reservation.setNombre_de_place_areserve(Integer.valueOf(nbrplace.getText()));
            reservation.setEmail(email.getText());
            
             ReservationService rs = new ReservationService();
            
            rs.ajoutReservation(reservation);

            new MesReservationsForm(res,reservation.getEmail(),reservation).show();
        });
    }
}
