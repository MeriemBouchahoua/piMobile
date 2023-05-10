/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form{

    public HomeForm() {
        
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
       // Button btnAddTask = new Button("Add Task");
        Button btnAddCons = new Button("Add Consultation");

       // Button btnAddEquipe = new Button("Add Equipe");
       // Button btnListTasks = new Button("List Tasks");
          Button btnListEquipes = new Button("List Equipes");
        
       // btnAddTask.addActionListener(e-> new AddTaskForm(this).show());
        btnAddCons.addActionListener(e-> new addCons(this).show());

       // btnAddEquipe.addActionListener(e-> new AddEquipeForm(this).show());
       /// btnListTasks.addActionListener(e-> new ListTasksForm(this).show());
       // btnListEquipes.addActionListener(e-> new ListEquipesForm(this).show());
       // addAll(btnAddTask,btnListTasks);
        ////addAll(btnAddEquipe,btnListEquipes);
        addAll(btnAddCons);

        
        
    }
    
    
}
