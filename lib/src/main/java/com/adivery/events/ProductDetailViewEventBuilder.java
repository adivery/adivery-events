package com.adivery.events;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.LinkedList;

public class ProductDetailViewEventBuilder implements EventBuilder {
    public enum Currency {
        IRT
    }

    private String sku;
    private String title;
    private String image;
    private String[] categories;
    private Integer price;
    private Float discount;
    private String brand;
    private Currency currency;
    private Boolean isAvailable;

    public ProductDetailViewEventBuilder setSku(String sku) {
        this.sku = sku;
        return this;
    }

    public ProductDetailViewEventBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public ProductDetailViewEventBuilder setImage(String image) {
        this.image = image;
        return this;
    }

    public ProductDetailViewEventBuilder setCategories(String[] categories) {
        this.categories = categories;
        return this;
    }

    public ProductDetailViewEventBuilder setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public ProductDetailViewEventBuilder setDiscount(Float discount) {
        this.discount = discount;
        return this;
    }

    public ProductDetailViewEventBuilder setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public ProductDetailViewEventBuilder setCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public ProductDetailViewEventBuilder setAvailable(Boolean available) {
        isAvailable = available;
        return this;
    }

    @Override
    public Event build() {
        HashMap<String, String> params = new HashMap<>();
        LinkedList<String> errors = new LinkedList<>();

        if (!TextUtils.isEmpty(sku)) {
            params.put("sku", sku);
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

        if (categories != null && categories.length > 0) {
            params.put("categories", Utils.joinString(",", categories));
        }

        if (!TextUtils.isEmpty(brand)) {
            params.put("brand", brand);
        }

        if (price != null && currency != null) {
            params.put("price", price.toString());
            params.put("currency", currency.toString());
        }

        if (discount != null) {
            params.put("discount", discount.toString());
        }

        if (isAvailable != null) {
            params.put("is_available", isAvailable.toString());
        }

        return new Event("__product_detail_view", params, errors.toArray(new String[0]));
    }
}
