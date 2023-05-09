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
import com.codename1.ui.Dialog;
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
        Label alreadHaveAnAccount = new Label("List Of Events");

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
    // Création de l'objet Evenements et initialisation de ses propriétés
    Evenements event = new Evenements();
    String nom_evenement = Nom_evenement.getText();
    String description_evenement = DescriptionEvenement.getText();
    String lieu_evenement = LieuEvenement.getText();
    String date_evenement = datePicker.getText();
    String heure = Heure.getText();
    String nbr_de_places_text = NbrDePlaces.getText();
    event.setType(type.getText());
    
    // Contrôle de saisie pour le champ Nom_evenement
    if (nom_evenement.isEmpty()) {
        Dialog.show("Erreur", "Veuillez entrer un nom pour l'événement.", "OK", null);
        return; // Arrête la méthode si le champ est vide
    }
    event.setNom_evenement(nom_evenement);
    
    // Contrôle de saisie pour le champ DescriptionEvenement
    if (description_evenement.isEmpty()) {
        Dialog.show("Erreur", "Veuillez entrer une description pour l'événement.", "OK", null);
        return; // Arrête la méthode si le champ est vide
    }
    event.setDescription_evenement(description_evenement);
    
    // Contrôle de saisie pour le champ LieuEvenement
    if (lieu_evenement.isEmpty()) {
        Dialog.show("Erreur", "Veuillez entrer un lieu pour l'événement.", "OK", null);
        return; // Arrête la méthode si le champ est vide
    }
    event.setLieu_evenement(lieu_evenement);
    
    // Contrôle de saisie pour le champ datePicker
    if (date_evenement.isEmpty()) {
        Dialog.show("Erreur", "Veuillez sélectionner une date pour l'événement.", "OK", null);
        return; // Arrête la méthode si le champ est vide
    }
    event.setDate_evenement(date_evenement);
    
    // Contrôle de saisie pour le champ Heure
    if (heure.isEmpty()) {
        Dialog.show("Erreur", "Veuillez entrer une heure pour l'événement.", "OK", null);
        return; // Arrête la méthode si le champ est vide
    }
    event.setHeure(heure);
    
    // Contrôle de saisie pour le champ NbrDePlaces
    if (nbr_de_places_text.isEmpty()) {
        Dialog.show("Erreur", "Veuillez entrer un nombre de places.", "OK", null);
        return; // Arrête la méthode si le champ est vide
    }
    try {
        int nbr_de_places = Integer.parseInt(nbr_de_places_text);
        if (nbr_de_places <= 0) {
            Dialog.show("Erreur", "Le nombre de places doit être supérieur à zéro.", "OK", null);
            return; // Arrête la méthode si le nombre est inférieur ou égal à zéro
        }
        event.setNbr_de_places(nbr_de_places);
    } catch (NumberFormatException ex) {
        Dialog.show("Erreur", "Veuillez entrer un nombre valide pour le nombre de places.", "OK", null);
        return; // Arrête la méthode si le champ ne contient pas un nombre valide
    }

    // Ajout de l'événement à la base de données
    EventService es = new EventService();
    es.ajoutEvent(event);

    // Affichage du formulaire d'administration des événements
    new EventFormAdmin(res).show();
});
    }

}
