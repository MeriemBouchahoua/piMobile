/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.services.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.ProfileForm;
import com.codename1.uikit.cleanmodern.SessionManager;
import com.esprit.utils.Statics;
import java.util.Map;


/**
 *
 * @author rayen
 */
public class ServiceUtilisateur {
    
     public static ServiceUtilisateur instance = null ;
    
    public static boolean resultOk = true;
    String json;

    private ConnectionRequest req;
    
    
    public static ServiceUtilisateur getInstance() {
        if(instance == null )
            instance = new ServiceUtilisateur();
        return instance ;
    }
    
    
    
    public ServiceUtilisateur() {
        req = new ConnectionRequest();
        
    }
    
     public void signup(TextField password,TextField email,TextField confirmPassword, ComboBox<String> roles , Resources res ) {
        
        String url = Statics.BASE_URL+"/signup?email="+email.getText().toString()+
                "&password="+password.getText().toString()+"&roles="+roles.getSelectedItem().toString();
        
        req.setUrl(url);
        
         if(password.getText().equals(" ") && email.getText().equals(" ")) {
            
            Dialog.show("Erreur","Veuillez remplir les champs","OK",null);
            
        }
         
          req.addResponseListener((e)-> {
         
            byte[]data = (byte[]) e.getMetaData();
            String responseData = new String(data); 
            
            System.out.println("data ===>"+responseData);
        }
        );
          
           NetworkManager.getInstance().addToQueueAndWait(req);
    }
     
     
     public void signin(TextField email,TextField password, Resources rs ) {
        
        
        String url = Statics.BASE_URL+"/signin?email="+email.getText().toString()+"&password="+password.getText().toString();
        req = new ConnectionRequest(url, false); 
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) + "";
            
            
            try {
            
            if(json.equals("failed")) {
                Dialog.show("Echec d'authentification","Username ou mot de passe éronné","OK",null);
            }
            else {
                System.out.println("data =="+json);
                
                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                //Session 
                float id = Float.parseFloat(user.get("id").toString());
                SessionManager.setId((int)id);
                SessionManager.setPassowrd(user.get("password").toString());
                SessionManager.setEmail(user.get("email").toString());
                
                
                 
                if(user.size() >0 ) 
                   
                    new ProfileForm(rs).show();

            }
                
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
        });
         
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
     public void editUser(TextField password,TextField email) {
        
        String url = Statics.BASE_URL+"/editApi?email="+email.getText().toString()+
                "&password="+password.getText().toString();
        
        
        MultipartRequest req = new  MultipartRequest();
        req.setUrl(url);
        req.setPost(true);
        
        req.addArgument("id",String.valueOf(SessionManager.getId()));
        req.addArgument("password", password + "");
        req.addArgument("email", email + "");
        
        System.out.println(email);
        req.addResponseListener((response)->
        {
        
        byte[] data = (byte[]) response.getMetaData();
        String s = new String(data);
        System.out.println(s);
        if (s.equals("success")) {
        }
        
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
     }
     
}
