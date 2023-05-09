/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.services.services.ShoppingCartService;
import java.util.ArrayList;
import com.esprit.entites.ShoppingCartItem;
import com.esprit.utils.Statics;

/**
 *
 * @author ALPHA
 */
public class ShoppingCartListForm extends Form {

    public ShoppingCartListForm() {
        setTitle("Panier");
        setLayout(BoxLayout.y());

        ArrayList<ShoppingCartItem> items = ShoppingCartService.getInstance().getCart();
        if (!items.isEmpty()) {
            for (ShoppingCartItem i : items) {
                addElement(i);
            }
        }

    }

    public void addElement(ShoppingCartItem item) {
        Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));

        c1.getAllStyles().setBorder(RoundBorder.createLineBorder(2, 0x808080));
        c1.getStyle().setPadding(10, 10, 10, 10);
        c1.getStyle().setMargin(10, 10, 10, 10);
        Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        Container nameContainer = new Container(new FlowLayout(LEFT, LEFT));
        Label productNameLabel = new Label("Nom du produit");
        Label productName = new Label(item.getProduct().getName());
        nameContainer.addAll(productNameLabel, productName);

        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(400, 400, 0xffffff), true);
        URLImage urlImage = URLImage.createToStorage(placeholder, item.getProduct().getName(), getProductImageUrl(item.getProduct().getImage()));
        ImageViewer imageViewer = new ImageViewer(urlImage);

        //declare a container for the price
        Container priceContainer = new Container(new FlowLayout(LEFT, LEFT));
        Label productPriceLabel = new Label("Prix");
        String price = Float.toString(item.getProduct().getPrice());
        Label productPrice = new Label(price);
        priceContainer.addAll(productPriceLabel, productPrice);

        //quantity container
        Container quantityContainer = new Container(new FlowLayout(LEFT, LEFT));
        Label productQuantityLabel = new Label("Quantité");
        String quantity = Float.toString(item.getQuantity());
        Label productQuantity = new Label(quantity);
        quantityContainer.addAll(productQuantityLabel, productQuantity);

        Button removeItem = new Button("supprimer ");
//        removeItem.setUIID("MyCustomButton"); // set custom UIID for the button
//        removeItem.getAllStyles().setBgColor(0x8b0000); // set background color to red
        removeItem.getAllStyles().setFgColor(0xffffff);
        removeItem.getAllStyles().setBorder(
                RoundBorder.create().color(0xff0000).rectangle(true).strokeOpacity(120)
        ); // set a rounded border with white color and 120 stroke opacity
        removeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    if (ShoppingCartService.getInstance().removeItem(item)) {
                        Statics.user.removeFromCart(item);
                        Dialog.show("Success", "Produit supprimé", new Command("OK"));

                        // clear the form and repopulate it with the updated cart list
                        removeAll();
                        ArrayList<ShoppingCartItem> items = ShoppingCartService.getInstance().getCart();
                        if (!items.isEmpty()) {
                            for (ShoppingCartItem i : items) {
                                addElement(i);
                            }
                        }

                        // revalidate and repaint the form to reflect the changes
                        revalidate();
                        repaint();
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                }

            }
        });

        c2.addAll(nameContainer, priceContainer, quantityContainer, removeItem);
        c1.addAll(c2, imageViewer);
        add(c1);
    }

    private String getProductImageUrl(String imageName) {
        // TODO: replace the following base URL with the actual URL of your server
        String baseUrl = "http://127.0.0.1:8000/uploads/products/";
        System.out.println(baseUrl + imageName);
        return baseUrl + imageName;
    }
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
}
