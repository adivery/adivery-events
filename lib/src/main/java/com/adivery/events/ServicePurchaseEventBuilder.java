package com.adivery.events;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.LinkedList;

public class ServicePurchaseEventBuilder implements EventBuilder {

    private String serviceName;
    private PurchaseStatus purchaseStatus;
    private PaymentMethod paymentMethod;
    private Integer price;
    private Currency currency;
    private String paymentGateway;

    private float discount;

    public ServicePurchaseEventBuilder setServiceName(String serviceName) {
        this.serviceName = serviceName;
        return this;
    }

    public ServicePurchaseEventBuilder setDiscount(float discount) {
        this.discount = discount;
        return this;
    }

    public ServicePurchaseEventBuilder setPurchaseStatus(PurchaseStatus status) {
        this.purchaseStatus = status;
        return this;
    }

    public ServicePurchaseEventBuilder setPaymentMethod(PaymentMethod method) {
        this.paymentMethod = method;
        return this;
    }

    public ServicePurchaseEventBuilder setPrice(int price) {
        this.price = price;
        return this;
    }

    public ServicePurchaseEventBuilder setCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public ServicePurchaseEventBuilder setPaymentGateway(String paymentGateway) {
        this.paymentGateway = paymentGateway;
        return this;
    }

    @Override
    public Event build() {
        HashMap<String, String> params = new HashMap<>();
        LinkedList<String> errors = new LinkedList<>();

        if (!TextUtils.isEmpty(serviceName)){
            params.put("service_name", serviceName);
        } else {
            errors.push("service_name cannot be empty");
        }

        if (purchaseStatus != null) {
            params.put("purchase_status", purchaseStatus.toString());
        }

        if (paymentMethod != null) {
            params.put("payment_method", paymentMethod.toString());
        }

        if (!TextUtils.isEmpty(paymentGateway)) {
            params.put("payment_gateway", paymentGateway);
        }

        if (price != null && currency != null) {
            params.put("price", price.toString());
            params.put("currency", currency.toString());
        }

        if (discount> 0) {
            params.put("discount", String.valueOf(discount));
        }


        return new Event("__service_purchase", params, errors.toArray(new String[0]));
    }
}
