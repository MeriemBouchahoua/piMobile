package com.codename1.uikit.cleanmodern;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.esprit.entites.Product;
import com.services.services.ProductService;
import com.services.services.ShoppingCartService;
import java.util.ArrayList;

/**
 * The newsfeed form
 *
 * @author Shai Almog
 */
public class ShowProductsList extends BaseForm {

    public ShowProductsList(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Nos Produits");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        tb.addSearchCommand(e -> {
        });

        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        addTab(swipe, res.getImage("products.jpg"), spacer1, "", "", "");

        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, spacer1);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

//        addButton(res.getImage("news-item-1.jpg"), "Morbi per tincidunt tellus sit of amet eros laoreet.", false, 26, 32);
//        addButton(res.getImage("news-item-2.jpg"), "Fusce ornare cursus masspretium tortor integer placera.", true, 15, 21);
//        addButton(res.getImage("news-item-3.jpg"), "Maecenas eu risus blanscelerisque massa non amcorpe.", false, 36, 15);
//        addButton(res.getImage("news-item-4.jpg"), "Pellentesque non lorem diam. Proin at ex sollicia.", false, 11, 9);
        ProductService ps = ProductService.getInstance();
        ArrayList<Product> products = ProductService.getInstance().getAllProducts();
        for (Product p : products) {
            int productId = p.getId();
            String productName = p.getName();
            String productCategory = p.getCategory().getName();
            float price = p.getPrice();
            String a = p.getName();

            // Use the full URL to retrieve the product image
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(300, 300, 0xffffff), true);
            URLImage urlImage = URLImage.createToStorage(placeholder, a, getProductImageUrl(p.getImage()));
            ImageViewer imageViewer = new ImageViewer(urlImage);
            addButton(imageViewer, productName, productCategory, price, p);
        }

    }

    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();

    }

    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if (img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);

        likes.setTextPosition(RIGHT);

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");

        Container page1
                = LayeredLayout.encloseIn(
                        image,
                        overlay,
                        BorderLayout.south(
                                BoxLayout.encloseY(
                                        new SpanLabel(text, "LargeWhiteText"),
                                        FlowLayout.encloseIn(likes, comments),
                                        spacer
                                )
                        )
                );

        swipe.addTab("", page1);
    }

    private String getProductImageUrl(String imageName) {
        // TODO: replace the following base URL with the actual URL of your server
        String baseUrl = "http://127.0.0.1:8000/uploads/products/";
        System.out.println(baseUrl + imageName);
        return baseUrl + imageName;
    }

    private void addButton(ImageViewer img, String title, String category, double price, Product product) {
        int height = Display.getInstance().convertToPixels(19.5f);
        int width = Display.getInstance().convertToPixels(19.5f);
        Image image = img.getImage().fill(width, height);
        Button button = new Button(image);
        button.setUIID("Label");
        Container cnt = BorderLayout.west(button);
        cnt.setLeadComponent(button);
        TextArea ta = new TextArea(title);
        ta.setUIID("NewsTopLine");
        ta.setEditable(false);

        Label categoryLbl = new Label(category, "NewsBottomLine");
        categoryLbl.setTextPosition(LEFT);

        Label priceLbl = new Label(String.valueOf(price) + "Dt", "NewsBottomLine");
        priceLbl.setTextPosition(RIGHT);

        // Create the "ajouter au panier" button
        Button addBtn = new Button("ajouter au panier");
        addBtn.getAllStyles().setFgColor(0xffffff); // Set text color to white
        addBtn.getAllStyles().setBgColor(0x649bff); // Set background color to light blue
        addBtn.getAllStyles().setAlignment(Component.CENTER); // Center the button
        button.addActionListener(evt -> {
            // Remove the item from the cart
            ShoppingCartService.getInstance().addItem(product);
            Dialog.show("Success", "Produit ajouté avec success", "OK", null);
            // Remove the product container from the UI

        });
        // Add the "ajouter au panier" button under the category label
        cnt.add(BorderLayout.SOUTH, addBtn);
        cnt.add(BorderLayout.CENTER,
                BoxLayout.encloseY(
                        ta,
                        BoxLayout.encloseX(categoryLbl, priceLbl)
                ));
        add(cnt);
    }

}
