package com.adivery.events;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.LinkedList;

public class CartPurchaseEventBuilder implements EventBuilder {

    private String cartId;
    private PurchaseStatus purchaseStatus;
    private PaymentMethod paymentMethod;
    private Integer price;
    private float discount;

    private Currency currency;

    public CartPurchaseEventBuilder setCartId(String cartId) {
        this.cartId = cartId;
        return this;
    }

    public CartPurchaseEventBuilder setPurchaseStatus(PurchaseStatus status) {
        this.purchaseStatus = status;
        return this;
    }

    public CartPurchaseEventBuilder setPaymentMethod(PaymentMethod method) {
        this.paymentMethod = method;
        return this;
    }

    public CartPurchaseEventBuilder setPrice(int price) {
        this.price = price;
        return this;
    }

    public CartPurchaseEventBuilder setDiscount(float discount) {
        this.discount = discount;
        return this;
    }

    public CartPurchaseEventBuilder setCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }


    @Override
    public Event build() {
        HashMap<String, String> params = new HashMap<>();
        LinkedList<String> errors = new LinkedList<>();

        if (paymentMethod != null) {
            params.put("payment_method", paymentMethod.toString());
        } else {
            errors.push("payment_method cannot be null");
        }

        if (purchaseStatus != null) {
            params.put("purchase_status", purchaseStatus.toString());
        } else {
            errors.push("purchase_status cannot be null");
        }

        if (price != null && currency != null) {
            params.put("price", price.toString());
            params.put("currency", currency.toString());
        }

        if (discount > 0) {
            params.put("discount", String.valueOf(discount));
        }

        if (!TextUtils.isEmpty(cartId)) {
            params.put("cart_id", cartId);
        } else {
            params.put("cart_id", AdiveryUserEvents.getCartManager().getId());
        }

        AdiveryUserEvents.getCartManager().createNewCart();

        return new Event("__cart_purchase", params, errors.toArray(new String[0]));
    }
}
