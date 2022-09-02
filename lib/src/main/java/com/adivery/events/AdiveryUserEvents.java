package com.adivery.events;

import static com.adivery.events.Utils.httpGetURL;

import android.app.Application;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AdiveryUserEvents {

  public static void logEvent(Event event) {
    executor.execute(() -> logEventImmediate(event));
  }

  private static void logEventImmediate(Event event) {
    if (event == null) {
      Logger.error("Event discarded: Event should not be null");
      return;
    }

    if (event.errors != null && event.errors.length > 0) {
      Logger.error("Event discarded: " + Arrays.toString(event.errors));
      return;
    }

    Logger.debug("Logging event " + event.name);

    httpGetURL(buildEventUrl(event));
  }

  private static String buildEventUrl(Event event) {
    Uri.Builder builder = Uri.parse("https://events.adivery.com/api/v1/user-event").buildUpon();

    if (!TextUtils.isEmpty(event.name)) {
      builder.appendQueryParameter("name", event.name);
    }

    builder.appendQueryParameter("app", appId);

    if (!TextUtils.isEmpty(AdiveryUserEvents.advertisingId)) {
      builder.appendQueryParameter("gps_adid", AdiveryUserEvents.advertisingId);
    }

    builder.appendQueryParameter("uid", UUID.randomUUID().toString());

    for (Map.Entry<String, String> entry : event.params.entrySet()) {
      String key = entry.getKey();
      String value = entry.getValue();

      if (TextUtils.isEmpty(key) || TextUtils.isEmpty(value)) {
        continue;
      }

      builder.appendQueryParameter("p." + key, value);
    }

    return builder.build().toString();
  }

  private static String advertisingId;
  private static String appId;

  private static volatile Executor executor;

  public static void configure(Application application, String appId) {
    if (executor == null) {
      synchronized (AdiveryUserEvents.class) {
        if (executor == null) {
          if (application == null) {
            throw new NullPointerException("Application is null");
          }

          if (TextUtils.isEmpty(appId)) {
            throw new NullPointerException("App id is null or empty");
          }

          AdiveryUserEvents.appId = appId;

          executor = Executors.newSingleThreadExecutor();
          executor.execute(
              () -> {
                try {
                  AdvertisingId.init(application);
                  advertisingId = AdvertisingId.getAdvertisingTrackingId();
                } catch (Exception e) {
                  Logger.error("Failed to fetch advertising id", e);
                }

                Map<String, String> params = new HashMap<>();
                params.put("api_level", Integer.toString(Build.VERSION.SDK_INT));
                params.put("brand", Build.BRAND);
                params.put("model", Build.MODEL);
                params.put("os", "android");
                params.put("package_name", application.getPackageName());
                logEventImmediate(new Event("__app_open", params, null));
              });
        }
      }
    }
  }

}
