package com.adivery.events;

import android.app.Activity;
import android.os.Bundle;

public class ExampleActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_example);

    // create your app in panel.adivery.com to obtain your app id
    AdiveryUserEvents.configure(getApplication(), "YOUR_ADIVERY_APP_ID");

    // log product view event
    AdiveryUserEvents.logEvent(
        new ProductDetailViewEventBuilder()
            .setSku("12345")
            .setTitle("کفش روزمره مردانه")
            .setImage(
                "https://dkstatics-public.digikala.com/digikala-products/ff2f076be6ded436a14cc1d4e5e48e79b78508ef_1610993916.jpg?x-oss-process=image/resize,m_lfit,h_300,w_300/quality,q_80")
            .setPrice(50000)
            .setCurrency(ProductDetailViewEventBuilder.Currency.IRT)
            .setCategories(new String[] {"کفش مردانه", "پوشاک مردانه"})
            .setAvailable(true)
            .setDiscount(10f)
            .setBrand("نایک")
            .build());

    // log article view event
    AdiveryUserEvents.logEvent(
        new ArticleViewEventBuilder()
            .setTitle("خبر مهم")
            .setKeywords(new String[] {"سیاسی", "اجتماعی"})
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

    // log custom user events with lowercase dash separated alphanumeric slug
    AdiveryUserEvents.logEvent(
        new UserActionEventBuilder().setAction("level-complete").build());

    AdiveryUserEvents.logEvent(
        new UserActionEventBuilder().setAction("add-to-cart").build());
  }
}
