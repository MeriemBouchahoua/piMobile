///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.myapp.gui;
//
//import com.codename1.ui.Button;
//
//import com.codename1.ui.Command;
//import com.codename1.ui.Dialog;
//import com.codename1.ui.FontImage;
//import com.codename1.ui.Form;
//import com.codename1.ui.TextField;
//import com.codename1.ui.events.ActionEvent;
//import com.codename1.ui.events.ActionListener;
//import com.codename1.ui.layouts.BoxLayout;
//import com.mycompany.myapp.entities.Equipe;
//import com.mycompany.myapp.services.ServiceEquipe;
//
//
///**
// *
// * @author Ace River
// */
//public class AddEquipeForm  extends Form {
//    
//      public AddEquipeForm(Form previous) {
//        setTitle("Add a new Equipe");
//        setLayout(BoxLayout.y());
//        
//        TextField tfName = new TextField("", "Nom");
//        TextField tfjoueurs = new TextField("", "joueurs");
//        TextField tfclassement = new TextField("", "classement");
//        TextField tfentraineur = new TextField("", "entraineur");
//        TextField tfcategorie = new TextField("", "categorie");
//      
//        Button btnValider = new Button("Add Equipe");
//        
//        btnValider.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                if (tfName.getText().length() == 0 || tfjoueurs.getText().length() == 0 || tfclassement.getText().length() == 0 || tfentraineur.getText().length() == 0 || tfcategorie.getText().length() == 0) {
//                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
//                } else {
//                    try {
//                        Equipe e = new Equipe();
//                        e.setNom(tfName.getText());
//                        e.setJoueurs(tfjoueurs.getText());
//                        e.setClassement(Integer.parseInt(tfclassement.getText()));
//                        e.setEntraineur(tfentraineur.getText());
//                        e.setCategorie(tfcategorie.getText());
//                        
//                        if (ServiceEquipe.getInstance().addEquipe(e)) {
//                           Dialog.show("Success", "Equipe added successfully", new Command("OK"));
//                        } else {
//                            Dialog.show("ERROR", "Server error", new Command("OK"));
//                        }
//                    } catch (NumberFormatException e) {
//                        Dialog.show("ERROR", "Classement must be a number", new Command("OK"));
//                    }
//                }
//            }
//        });
//        
//        addAll(tfName, tfjoueurs, tfclassement, tfentraineur, tfcategorie, btnValider);
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
//    }
//    
//                
//    
//    
//}
