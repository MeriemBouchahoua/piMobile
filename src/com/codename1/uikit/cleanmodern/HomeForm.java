/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form {

    public HomeForm(Resources res) {

        setTitle("Home");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));
        Button btnAddMood = new Button("Add Mood");
        Button btnListMoods = new Button("List Moods");
        Button btnAddJournal = new Button("Add Journal");
        Button btnListJournals = new Button("List Journals");
        Button btnHome = new Button("Home");
        btnHome.addActionListener(e -> new ArticleDoc(res).show());

        btnAddMood.addActionListener(e -> new AddMoodForm(this).show());
        btnListMoods.addActionListener(e -> new ListMoodForm(this).show());

        btnAddJournal.addActionListener(e -> new AddJournalForm(this).show());

        addAll(btnListMoods, btnAddMood);

        addAll(btnListJournals, btnAddJournal, btnHome);

    }

}
