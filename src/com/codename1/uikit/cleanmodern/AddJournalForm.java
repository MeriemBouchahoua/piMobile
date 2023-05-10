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
import com.esprit.entites.Journal;
import com.services.services.ServiceJournal;

/**
 *
 * @author Ace River
 */
public class AddJournalForm extends Form {

    public AddJournalForm(Form previous) {
        setTitle("Add a new JournalMood");
        setLayout(BoxLayout.y());

        TextField tfIdUser = new TextField("", "IdUser");
        TextField tfmoods_id = new TextField("", "moods_id");

        Button btnValider = new Button("Add Journal Mood");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfIdUser.getText().length() == 0 || tfmoods_id.getText().length() == 0) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Journal e = new Journal();
                        e.setIdUser(Integer.parseInt(tfIdUser.getText()));
                        e.setMoods_id(Integer.parseInt(tfmoods_id.getText()));

                        if (ServiceJournal.getInstance().addJournal(e)) {
                            Dialog.show("Success", "Journal Mood added successfully", new Command("OK"));
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
        addAll(tfIdUser, tfmoods_id, btnValider, btnHome);
//        getToolbar().addMaterialCommandToLeftBar("hhhhhhh", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}
