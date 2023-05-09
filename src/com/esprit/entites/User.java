/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entites;

import java.util.ArrayList;

/**
 *
 * @author ALPHA
 */
public class User {

    private int id;
    private String email;
    private String address;
    private ArrayList<ShoppingCartItem> cart;

    public User(int id, String email, String address) {
        this.id = id;
        this.email = email;
        this.address = address;
        this.cart = new ArrayList<ShoppingCartItem>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<ShoppingCartItem> getCart() {
        return cart;
    }

    public void setCart(ArrayList<ShoppingCartItem> cart) {
        this.cart = cart;
    }

    public void addToCart(ShoppingCartItem item) {
        this.cart.add(item);
    }

    public void removeFromCart(ShoppingCartItem item) {
        this.cart.remove(item);
    }

}
