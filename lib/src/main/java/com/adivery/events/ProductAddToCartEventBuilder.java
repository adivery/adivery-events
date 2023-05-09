package com.adivery.events;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.LinkedList;

public class ProductAddToCartEventBuilder implements EventBuilder {

    private String cartId;
    private String sku;
    private String title;
    private String image;
    private String[] categories;
    private Integer productPrice;
    private Integer cartValue;
    private Float discount;
    private String brand;
    private Currency currency;

    public ProductAddToCartEventBuilder setCartId(String cartId) {
        this.cartId = cartId;
        return this;
    }

    public ProductAddToCartEventBuilder setSku(String sku) {
        this.sku = sku;
        return this;
    }

    public ProductAddToCartEventBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public ProductAddToCartEventBuilder setImage(String image) {
        this.image = image;
        return this;
    }

    public ProductAddToCartEventBuilder setCategories(String[] categories) {
        this.categories = categories;
        return this;
    }

    public ProductAddToCartEventBuilder setProductPrice(int price){
        this.productPrice = price;
        return this;
    }

    public ProductAddToCartEventBuilder setCartValue(int value) {
        this.cartValue = value;
        return this;
    }

    public ProductAddToCartEventBuilder setDiscount(float discount) {
        this.discount = discount;
        return this;
    }

    public ProductAddToCartEventBuilder setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public ProductAddToCartEventBuilder setCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    @Override
    public Event build() {
        HashMap<String, String> params = new HashMap<>();
        LinkedList<String> errors = new LinkedList<>();

        if (!TextUtils.isEmpty(cartId)) {
            params.put("cart_id", cartId);
        } else {
            params.put("cart_id", AdiveryUserEvents.getCartManager().getId());
        }

        if (!TextUtils.isEmpty(sku)) {
            params.put("sku", sku);
            AdiveryUserEvents.getCartManager().addToCart(sku);
        } else {
            errors.add("Product SKU is required");
        }

        if (!TextUtils.isEmpty(title)) {
            params.put("title", title);
        } else {
            errors.add("Product title is required");
        }

        if (!TextUtils.isEmpty(image)) {
            params.put("image", image);
        } else {
            errors.add("Product image is required");
        }

        if (!TextUtils.isEmpty(brand)) {
            params.put("brand", brand);
        }

        if (productPrice != null && currency != null) {
            params.put("product_price", productPrice.toString());
            params.put("currency", currency.toString());
        }

        if (discount != null) {
            params.put("discount", discount.toString());
        }

        if (cartValue != null && currency !=null) {
            params.put("cart_value", cartValue.toString());
            params.put("currency", currency.toString());
        }

        return new Event("__product_add_to_cart", params, errors.toArray(new String[0]));
    }
}
