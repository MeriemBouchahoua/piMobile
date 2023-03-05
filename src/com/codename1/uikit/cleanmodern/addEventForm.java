/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.components.FloatingHint;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.esprit.entites.Evenements;
import com.services.services.EventService;

/**
 *
 * @author b.maryem
 */
public class addEventForm extends BaseForm {

    public addEventForm(Resources res) {

        super(new BorderLayout());
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");

        TextField Nom_evenement = new TextField("", "Nom_evenement", 20, TextField.ANY);
        TextField DescriptionEvenement = new TextField("", "DescriptionEvenement", 20, TextField.ANY);
        TextField LieuEvenement = new TextField("", "LieuEvenement", 20, TextField.ANY);
        TextField DateEvenement = new TextField("", "DateEvenement", 20, TextField.ANY);
        TextField Heure = new TextField("", "Heure", 20, TextField.ANY);
        TextField NbrDePlaces = new TextField("", "NbrDePlaces", 20, TextField.ANY);
        TextField type = new TextField("", "type", 20, TextField.ANY);
        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        datePicker.setFormatter(sf);
        Nom_evenement.setSingleLineTextArea(false);
        DescriptionEvenement.setSingleLineTextArea(false);
        LieuEvenement.setSingleLineTextArea(false);
        DateEvenement.setSingleLineTextArea(false);
        Heure.setSingleLineTextArea(false);
        NbrDePlaces.setSingleLineTextArea(false);
        Button next = new Button("add Event");
        Button signIn = new Button("headback");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("List Of Question");

        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                new FloatingHint(Nom_evenement),
                createLineSeparator(),
                new FloatingHint(DescriptionEvenement),
                createLineSeparator(),
                new FloatingHint(LieuEvenement),
                createLineSeparator(),
                datePicker,
                createLineSeparator(),
                new FloatingHint(Heure),
                createLineSeparator(),
                new FloatingHint(NbrDePlaces),
                createLineSeparator(),
                new FloatingHint(type),
                createLineSeparator()
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        next.requestFocus();
        next.addActionListener(e -> {

            Evenements event = new Evenements();
            event.setNom_evenement(Nom_evenement.getText());
            event.setDescription_evenement(DescriptionEvenement.getText());
            event.setLieu_evenement(LieuEvenement.getText());
            event.setDate_evenement(datePicker.getText());
            event.setHeure(Heure.getText());
            event.setNbr_de_places(Integer.parseInt(NbrDePlaces.getText()));
            event.setType(type.getText());

            EventService es = new EventService();
            es.ajoutEvent(event);

            new EventFormAdmin(res).show();

//            Post post = new Post();
//            post.setId_user(Integer.valueOf(id_user.getText()));
//            post.setDescription(description.getText());
//            post.setPublication(publication.getText());
//            post.setNom_utilisateur(nom_utilisateur.getText());
//        
//           
//            post.setCreated_at(created_at.getText());
//            post.setUpdated_at(updated_at.getText());
//            
//            PostService ps = new PostService();
//            
//            ps.ajoutPost(post);
//
//            new PostForm(res).show();
        });
    }

}
