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
import com.esprit.entites.Product;
import com.esprit.entites.Category;
import com.esprit.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ALPHA
 */
public class ProductService {

    public ArrayList<Product> products;
    public Product product;
    public static ProductService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ProductService() {
        req = new ConnectionRequest();
    }

    public static ProductService getInstance() {
        if (instance == null) {
            instance = new ProductService();
        }
        return instance;
    }

    public Product parseProduct(String jsonText) throws IOException {
        JSONParser j = new JSONParser();
        Map<String, Object> json = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

        int id = ((Double) json.get("id")).intValue();
        String name = (String) json.get("name");
        String description = (String) json.get("description");
        int quantity = ((Double) json.get("quantity")).intValue();
        float price = ((Double) json.get("price")).floatValue();
        String image = (String) json.get("image");
        Map<String, Object> categoryJson = (Map<String, Object>) json.get("category");
        int categoryId = ((Double) categoryJson.get("id")).intValue();
        String categoryName = (String) categoryJson.get("name");
        Category category = new Category(categoryId, categoryName);

        // create and return Product object
        return new Product(id, name, description, quantity, price, image, category);
    }

    public ArrayList<Product> parseProducts(String jsonText) {
        try {
            products = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> productsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) productsListJson.get("root");
            if (list == null) {
                throw new RuntimeException("Invalid JSON: 'root' key not found or value is not a list");
            }
            for (Map<String, Object> obj : list) {
                int id = ((Double) obj.get("id")).intValue();
                String name = (String) obj.get("name");
                String description = (String) obj.get("description");
                int quantity = ((Double) obj.get("quantity")).intValue();
                float price = ((Double) obj.get("price")).floatValue();
                String image = (String) obj.get("image");

                // create category object
                Map<String, Object> categoryJson = (Map<String, Object>) obj.get("category");
                int categoryId = ((Double) obj.get("id")).intValue();

                String categoryName = (String) categoryJson.get("name");
                Category category = new Category(categoryId, categoryName);

                Product product = new Product(id, name, description, quantity, price, image, category);
                products.add(product);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return products;

    }

    public ArrayList<Product> getAllProducts() {
        String url = Statics.BASE_URL + "/api/getProducts";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                products = parseProducts(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return products;
    }

}
