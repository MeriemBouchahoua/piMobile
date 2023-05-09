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
import com.codename1.ui.util.Resources;
import com.esprit.entites.Post;
import com.services.services.PostService;

/**
 *
 * @author z.maryem
 */
public class UpdatePostForm extends BaseForm {

    
    
    public UpdatePostForm(Resources res,int id) {

        super(new BorderLayout());
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");

        TextField id_user = new TextField("", "id_user", 20, TextField.ANY);
        TextField description = new TextField("", "description", 20, TextField.ANY);
        TextField publication = new TextField("", "publication", 20, TextField.ANY);
        TextField nom_utilisateur = new TextField("", "nom_utilisateur", 20, TextField.ANY);
        
        id_user.setSingleLineTextArea(false);
        description.setSingleLineTextArea(false);
        publication.setSingleLineTextArea(false);
        nom_utilisateur.setSingleLineTextArea(false);
      
        Button next = new Button("update Post");
        Button signIn = new Button("headback");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("List Of Question");

        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                new FloatingHint(id_user),
                createLineSeparator(),
                new FloatingHint(description),
                createLineSeparator(),
                new FloatingHint(publication),
                createLineSeparator(),
                new FloatingHint(nom_utilisateur),
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
            Post post = new Post();
            post.setId_user(Integer.valueOf(id_user.getText()));
            post.setDescription(description.getText());
            post.setPublication(publication.getText());
            post.setNom_utilisateur(nom_utilisateur.getText());
            
           
           
            
            PostService ps = new PostService();
            
            ps.modifierPost(post,id);

            new PostForm(res).show();
        });
    }

}
