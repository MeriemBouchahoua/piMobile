/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.Resources;
import com.services.services.ProductService;
import java.util.ArrayList;
import com.esprit.entites.Product;

/**
 *
 * @author ALPHA
 */
public class productsListForm extends BaseForm {

    public productsListForm(Resources res) {
        super("Liste des produits", BoxLayout.y());
        setTitle("Liste des produits");
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        setLayout(BoxLayout.y());
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {
        });

        ArrayList<Product> products = ProductService.getInstance().getAllProducts();
        for (Product p : products) {
            addElement(p);
        }

//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    public void addElement(Product product) {
        Container productContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        productContainer.setPreferredW(getWidth());
        productContainer.setPreferredH(700);
        productContainer.getAllStyles().setBorder(Border.createLineBorder(1, 0xbbbbbb));

        // Create an image for the product
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(300, 300, 0xffffff), true);
        URLImage urlImage = URLImage.createToStorage(placeholder, product.getName(), getProductImageUrl(product.getImage()));
        ImageViewer imageViewer = new ImageViewer(urlImage);

        // Create a label for the product name
        Label nameLabel = new Label(product.getName());
        nameLabel.getAllStyles().setFgColor(0x333333);
        nameLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
        nameLabel.getAllStyles().setAlignment(CENTER);

        // Create a label for the product price
        Label priceLabel = new Label(String.valueOf(product.getPrice()) + " Dt");
        priceLabel.getAllStyles().setFgColor(0x888888);
        priceLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        priceLabel.getAllStyles().setAlignment(CENTER);

        // Create a button for adding the product to the cart
        Button button = new Button("Add to cart");
        button.getAllStyles().setFgColor(0xffffff);
        button.getAllStyles().setBgColor(0x0088cc);
        button.getAllStyles().setPaddingTop(2);
        button.getAllStyles().setPaddingBottom(2);
        button.getAllStyles().setPaddingLeft(10);
        button.getAllStyles().setPaddingRight(10);
//        button.getAllStyles().setBorder(Border.createEmpty());

        // Add the components to the product container using a BoxLayout
        productContainer.add(nameLabel);
        productContainer.add(imageViewer);
        productContainer.add(priceLabel);
        productContainer.add(button);
        productContainer.getAllStyles().setMarginBottom(50);
        productContainer.getAllStyles().setMarginLeft(30);
        productContainer.getAllStyles().setMarginRight(30);

        // Add the product container to the form
        add(productContainer);
    }

    private String getProductImageUrl(String imageName) {
        // TODO: replace the following base URL with the actual URL of your server
        String baseUrl = "http://127.0.0.1:8000/uploads/products/";
        System.out.println(baseUrl + imageName);
        return baseUrl + imageName;
    }
}
