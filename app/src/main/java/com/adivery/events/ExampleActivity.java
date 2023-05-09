package com.adivery.events;

import android.app.Activity;
import android.os.Bundle;


public class ExampleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        // create your app in panel.adivery.com to obtain your app id
        AdiveryUserEvents.configure(getApplication(), "0d53f80c-8607-4d36-9ff6-5775c450ed26");

        // log product view event
        AdiveryUserEvents.logEvent(
                new ProductDetailViewEventBuilder()
                        .setSku("12345")
                        .setTitle("کفش روزمره مردانه")
                        .setImage(
                                "https://dkstatics-public.digikala.com/digikala-products/ff2f076be6ded436a14cc1d4e5e48e79b78508ef_1610993916.jpg?x-oss-process=image/resize,m_lfit,h_300,w_300/quality,q_80")
                        .setPrice(50000)
                        .setCurrency(Currency.IRT)
                        .setCategories(new String[]{"کفش مردانه", "پوشاک مردانه"})
                        .setAvailable(true)
                        .setDiscount(10f)
                        .setBrand("نایک")
                        .build());

        // log article view event
        AdiveryUserEvents.logEvent(
                new ArticleViewEventBuilder()
                        .setTitle("خبر مهم")
                        .setKeywords(new String[]{"سیاسی", "اجتماعی"})
                        .setUrl(
                                "https://akharinkhabar.ir/cinema/9234078/%D8%B9%D9%84%D8%AA-%D8%AE%D8%AF%D8%A7%D8%AD%D8%A7%D9%81%D8%B8%DB%8C-%DA%A9%D8%A7%D8%B1%DA%AF%D8%B1%D8%AF%D8%A7%D9%86-%D8%AF%DB%8C%D8%B1%DB%8C%D9%86-%D8%AF%DB%8C%D8%B1%DB%8C%D9%86-%D8%A7%D8%B2-%D8%B2%D8%A8%D8%A7%D9%86-%D8%AE%D9%88%D8%AF%D8%B4")
                        .build());

        // log predefined user events
        AdiveryUserEvents.logEvent(
                new UserActionEventBuilder()
                        .setAction(UserActionEventBuilder.Action.LOGIN)
                        .build());

        AdiveryUserEvents.logEvent(
                new UserActionEventBuilder()
                        .setAction(UserActionEventBuilder.Action.REGISTER)
                        .build());

        AdiveryUserEvents.logEvent(
                new InAppPurchaseEventBuilder().setPurchaseStatus(PurchaseStatus.SUCCESS)
                        .setCurrency(Currency.IRT)
                        .setPrice(5000)
                        .setItemName("1k gold")
                        .build()
        );
        AdiveryUserEvents.logEvent(
                new ProductAddToCartEventBuilder()
                        .setCartValue(1200000)
                        .setCurrency(Currency.IRT)
                        .setProductPrice(300000)
                        .setBrand("نایک")
                        .setSku("sk1245")
                        .setCategories(new String[] { "ورزشی" })
                        .setCartId("10")
                        .setDiscount(13f)
                        .setTitle("کفش ورزشی نایک")
                        .setImage("https://example.com/icon.png")
                        .build()
        );

        AdiveryUserEvents.logEvent(
                new ProductRemoveFromCartEventBuilder()
                        .setCartValue(900000)
                        .setCurrency(Currency.IRT)
                        .setProductPrice(300000)
                        .setBrand("نایک")
                        .setSku("sk1245")
                        .setCategories(new String[] { "ورزشی" })
                        .setCartId("10")
                        .setDiscount(13f)
                        .setTitle("کفش ورزشی نایک")
                        .setImage("https://example.com/icon.png")
                        .build()
        );

        AdiveryUserEvents.logEvent(
                new CartPurchaseEventBuilder()
                        .setCartId("10")
                        .setPaymentMethod(PaymentMethod.ONLINE)
                        .setCurrency(Currency.IRT)
                        .setDiscount(13f)
                        .setPurchaseStatus(PurchaseStatus.SUCCESS)
                        .setPrice(900000)
                        .build()
        );

        AdiveryUserEvents.logEvent(
                new SearchEventBuilder()
                        .setKeyword("لپتاپ")
                        .setCategory("لوازم الکترونیکی")
                        .addFilter("قیمت", "15M-25M")
                        .addFilter("برند", "lenovo")
                        .build()
        );

        AdiveryUserEvents.logEvent(
                new ServicePurchaseEventBuilder()
                        .setPurchaseStatus(PurchaseStatus.SUCCESS)
                        .setCurrency(Currency.IRT)
                        .setPaymentGateway("Mellat")
                        .setServiceName("اشتراک ۳ ماهه")
                        .setDiscount(12f)
                        .setPrice(150000)
                        .build()
        );

        // log custom user events with lowercase dash separated alphanumeric slug
        AdiveryUserEvents.logEvent(
                new UserActionEventBuilder().setAction("level-complete").build());

    }
}
