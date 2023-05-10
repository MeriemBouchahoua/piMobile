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
import com.esprit.entites.Mood;
import com.services.services.ServiceMood;

/**
 *
 * @author Ace River
 */
public class AddMoodForm extends Form {

    public AddMoodForm(Form previous) {
        setTitle("Add a new Mood");
        setLayout(BoxLayout.y());

        TextField tfMoodId = new TextField("", "MoodId");
        TextField tfUserId = new TextField("", "UserId");
        TextField tfMood = new TextField("", "Mood");
        TextField tfDescription = new TextField("", "Description");
        TextField tfDate = new TextField("", "Date");

        Button btnValider = new Button("Add Mood");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfMoodId.getText().length() == 0 || tfUserId.getText().length() == 0 || tfMood.getText().length() == 0 || tfDescription.getText().length() == 0 || tfDate.getText().length() == 0) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Mood e = new Mood();
                        e.setMoodId(Integer.parseInt(tfMoodId.getText()));
                        e.setUserId(Integer.parseInt(tfUserId.getText()));
                        e.setMood(tfMood.getText());
                        e.setDescription(tfDescription.getText());
                        e.setDate(tfDate.getText());

                        if (ServiceMood.getInstance().addMood(e)) {
                            Dialog.show("Success", "Mood added successfully", new Command("OK"));
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
        addAll(tfMoodId, tfUserId, tfMood, tfDescription, tfDate, btnValider, btnHome);
//        getToolbar().addMaterialCommandToLeftBar("hhhh", FontImage.MATERIAL_ARROW_BACK_IOS, e -> previous.showBack());
    }

}
