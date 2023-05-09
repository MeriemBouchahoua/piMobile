/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.esprit.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.esprit.entites.ShoppingCartItem;
import com.esprit.entites.Product;
import com.esprit.entites.Category;

/**
 *
 * @author ALPHA
 */
public class ShoppingCartService {

    public ArrayList<ShoppingCartItem> items;

    public static ShoppingCartService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ShoppingCartService() {
        req = new ConnectionRequest();
    }

    public static ShoppingCartService getInstance() {
        if (instance == null) {
            instance = new ShoppingCartService();
        }
        return instance;
    }

    public boolean addItem(Product p) {

        int productId = p.getId();
        int userId = Statics.user.getId();

        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL + "api/addToCart/" + productId + "/" + userId;

        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 201; //Code HTTP 201 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean removeItem(ShoppingCartItem item) {
        int itemtId = item.getId();
        int userId = Statics.user.getId();

        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL + "api/removeItem/" + itemtId + "/" + userId;

        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 201 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<ShoppingCartItem> parseItems(String jsonText) {
        ArrayList<ShoppingCartItem> shoppingCartItems = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> itemsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) itemsListJson.get("root");
            if (list == null) {
                throw new RuntimeException("Invalid JSON: 'root' key not found or value is not a list");
            }
            for (Map<String, Object> obj : list) {
                ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
                shoppingCartItem.setId((int) Float.parseFloat(obj.get("id").toString()));
                float userId = Float.parseFloat(obj.get("userId").toString());
                shoppingCartItem.setUserId((int) userId);
                Map<String, Object> productMap = (Map<String, Object>) obj.get("product");
                Product product = new Product();
                product.setId((int) Float.parseFloat(productMap.get("id").toString()));
                product.setName(productMap.get("name").toString());
                product.setDescription(productMap.get("description").toString());
                product.setQuantity((int) Float.parseFloat(productMap.get("quantity").toString()));
                product.setPrice(Float.parseFloat(productMap.get("price").toString()));
                String image = productMap.get("image") != null ? productMap.get("image").toString() : null;
                product.setImage(image);
                Map<String, Object> categoryMap = (Map<String, Object>) productMap.get("category");
                Category category = new Category();
                category.setId((int) Float.parseFloat(categoryMap.get("id").toString()));
                category.setName(categoryMap.get("categoryName").toString());
                product.setCategory(category);
                shoppingCartItem.setProduct(product);
                shoppingCartItem.setQuantity((int) Float.parseFloat(obj.get("quantity").toString()));
                shoppingCartItems.add(shoppingCartItem);

            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return shoppingCartItems;

    }

    public ArrayList<ShoppingCartItem> getCart() {
        int userId = Statics.user.getId();
        String url = Statics.BASE_URL + "api/cart/" + userId;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                items = parseItems(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return items;
    }
}
