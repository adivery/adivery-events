package com.adivery.events;

import android.text.TextUtils;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.LinkedList;

public class InAppPurchaseEventBuilder implements EventBuilder {

    private String itemName;
    private Integer price;
    private Currency currency;
    private PurchaseStatus purchaseStatus;

    public InAppPurchaseEventBuilder setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public InAppPurchaseEventBuilder setPrice(int price) {
        this.price = price;
        return this;
    }

    public InAppPurchaseEventBuilder setCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public InAppPurchaseEventBuilder setPurchaseStatus(PurchaseStatus status) {
        this.purchaseStatus = status;
        return this;
    }

    @Override
    public Event build() {
        HashMap<String, String> params = new HashMap<>();
        LinkedList<String> errors = new LinkedList<>();

        if (!TextUtils.isEmpty(itemName)) {
            params.put("item_name", itemName);
        } else {
            errors.push("item_name cannot be null or empty");
        }

        if (price != null && currency != null) {
            params.put("price", price.toString());
            params.put("currency", currency.toString());
        } else {
            errors.push("price and currency cannot be null");
        }

        if (purchaseStatus != null) {
            params.put("purchase_status", purchaseStatus.toString());
        } else {
            errors.push("purchase_status cannot be null");
        }

        return new Event("__in_app_purchase", params, errors.toArray(new String[0]));
    }
}
