/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.entites.Consultation;
import com.services.services.ServiceConsultation;

/**
 *
 * @author Mongi
 */
public class addCons extends Form {

    public addCons(Form previous, Resources res) {
        setTitle("Add a new Consultation");
        setLayout(BoxLayout.y());

        TextField tfName = new TextField("", "nom");
        TextField tfprenom = new TextField("", "prenom");
        TextField tfcause = new TextField("", "cause");
        TextField tfdecription = new TextField("", "description");
        TextField tfdate = new TextField("", "date");
        TextField tfmedecin = new TextField("", "medecin");
        TextField tfcabinet = new TextField("", "cabinet");

        Button btnValider = new Button("Add Cons");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfName.getText().length() == 0 || tfName.getText().length() == 0 || tfprenom.getText().length() == 0 || tfcause.getText().length() == 0 || tfdecription.getText().length() == 0) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Consultation e = new Consultation();
                        e.setNom(tfName.getText());
                        e.setPrenom(tfprenom.getText());
                        e.setCause(tfcause.getText());
                        e.setDate(tfdate.getText());
                        e.setDescription(tfdecription.getText());
                        e.setMedecin(tfmedecin.getText());
                        e.setCabinet(tfcabinet.getText());

                        if (ServiceConsultation.getInstance().addCons(e)) {
                            Dialog.show("Success", "consultation added successfully", new Command("OK"));
                            new HomeFormM(res).show();
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Classement must be a number", new Command("OK"));
                    }
                }
            }

        });
        Button btnHome = new Button("Home");
        btnHome.addActionListener(e -> previous.showBack());
        addAll(tfName, tfprenom, tfcause, tfdecription, tfdate, tfmedecin, tfcabinet, btnValider, btnHome);
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}
