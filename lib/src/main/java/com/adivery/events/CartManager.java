package com.adivery.events;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CartManager {

    private List<String> products = new ArrayList<>();

    private String id;

    public CartManager() {

    }

    public String createNewCart() {
        products.clear();
        id = UUID.randomUUID().toString();
        return id;
    }

    public String getId() {
        if (id == null) {
            return createNewCart();
        }
        return id;
    }

    public void addToCart(String sku) {
        products.add(sku);
    }

    public void removeFromCart(String sku){
        if (products.contains(sku)){
            products.remove(sku);
        }
    }

}
