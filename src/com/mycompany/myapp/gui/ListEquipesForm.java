///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.myapp.gui;
//import com.codename1.components.ImageViewer;
//import com.codename1.io.FileSystemStorage;
//import com.codename1.ui.Container;
//import com.codename1.ui.Dialog;
//import com.codename1.ui.EncodedImage;
//import com.codename1.ui.FontImage;
//import com.codename1.ui.Form;
//import com.codename1.ui.Image;
//import com.codename1.ui.Label;
//import com.codename1.ui.URLImage;
//import com.codename1.ui.events.ActionEvent;
//import com.codename1.ui.events.ActionListener;
//import com.codename1.ui.layouts.BorderLayout;
//import com.codename1.ui.layouts.BoxLayout;
//import com.mycompany.myapp.entities.Equipe;
//import com.mycompany.myapp.services.ServiceEquipe;
//import java.util.ArrayList;
///**
// *
// * @author Ace River
// */
//public class ListEquipesForm extends Form {
//  
//    public ListEquipesForm(Form previous) {
//        setTitle("List Equipe");
//        setLayout(BoxLayout.y());
//
//        /*SpanLabel sp = new SpanLabel();
//        sp.setText(ServiceTask.getInstance().getAllTasks().toString());
//        add(sp);
//         */
//        ArrayList<Equipe> tasks = ServiceEquipe.getInstance().getAllEquipes();
//        for (Equipe e : tasks) {
//            addElement(e);
//        }
//
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
//
//    }
//
//public void addElement(Equipe equipe) {
//    Container c = new Container(new BorderLayout());
//    int classement = equipe.getClassement();
//
//Label nomLabel = new Label(equipe.getNom());
//nomLabel.addPointerPressedListener(evt -> {
//    Dialog popup = new Dialog();
//    popup.setLayout(new BorderLayout());
//
//    Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
//    container.add(new Label("Liste des Joueurs:"));
//    container.add(new Label(equipe.getJoueurs()));
//    container.add(new Label("Classement:"));
//    container.add(new Label(String.valueOf(classement)));
//    container.add(new Label("Entraineur:"));
//    container.add(new Label(equipe.getEntraineur()));
//    container.add(new Label("Categorie:"));
//    container.add(new Label(equipe.getCategorie()));
//     container.add(new Label("picture:"));
//    // Load the image using URLImage
//   String imageURL = "file:///" + FileSystemStorage.getInstance().getAppHomePath() + "../../../uploads/" + equipe.getPicture();
//
//    EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(100, 100, 0xffff0000), true);
//    URLImage image = URLImage.createToStorage(placeholder, "", imageURL, URLImage.RESIZE_SCALE_TO_FILL);
//    ImageViewer imageViewer = new ImageViewer(image);
//    container.add(imageViewer);
// 
//
//    popup.add(BorderLayout.CENTER, container);
//    popup.showPopupDialog(nomLabel);
//});
//
//c.add(BorderLayout.CENTER, nomLabel);
//add(c);
//}
//
//
//}
