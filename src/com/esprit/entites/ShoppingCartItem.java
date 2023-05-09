/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entites;

/**
 *
 * @author ALPHA
 */
public class ShoppingCartItem {

    private int id;
    private int userId;
    private Product product;
    private int quantity;

    public ShoppingCartItem(int id, int userId, Product p, int quantity) {
        this.id = id;
        this.userId = userId;
        this.product = p;
        this.quantity = quantity;
    }

    public ShoppingCartItem() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
