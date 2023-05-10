/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entites.Mood;
import com.services.services.ServiceMood;
import java.util.ArrayList;

/**
 *
 * @author Ace River
 */
public class ListMoodForm extends Form {

    public ListMoodForm(Form previous) {
        setTitle("List Mood");
        setLayout(BoxLayout.y());

        /*SpanLabel sp = new SpanLabel();
        sp.setText(ServiceTask.getInstance().getAllTasks().toString());
        add(sp);
         */
        ArrayList<Mood> tasks = ServiceMood.getInstance().getAllMoods();
        for (Mood e : tasks) {
            addElement(e);
        }
        Button btnHome = new Button("Home");
        btnHome.addActionListener(e -> previous.showBack());
        add(btnHome);

//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new HomeForm().show());
    }

    public void addElement(Mood mood) {
        Container c = new Container(new BorderLayout());

        Label nomLabel = new Label(mood.getMood());
        nomLabel.addPointerPressedListener(evt -> {
            Dialog popup = new Dialog();
            popup.setLayout(new BorderLayout());

            Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));

            container.add(new Label("Mood:"));
            container.add(new Label(mood.getMood()));
            container.add(new Label("Description:"));
            container.add(new Label(mood.getDescription()));

            popup.add(BorderLayout.CENTER, container);
            popup.showPopupDialog(nomLabel);
        });

        c.add(BorderLayout.CENTER, nomLabel);
        add(c);
    }

}
