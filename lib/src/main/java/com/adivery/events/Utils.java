package com.adivery.events;

import android.text.TextUtils;

import java.io.Closeable;
import java.net.HttpURLConnection;
import java.net.URL;

public class Utils {

    public static String joinString(String sep, String[] items){
        if (items.length == 0){
            return null;
        }
        StringBuilder builder = new StringBuilder();
        builder.append(items[0]);
        for (int i=1; i<items.length; i++){
            builder.append(sep);
            builder.append(items[i]);
        }
        return builder.toString();
    }

    public static void httpGetURL(final String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }

        HttpURLConnection conn = null;
        try {
            URL httpUrl = new URL(url);
            conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setConnectTimeout(5000);
            conn.setInstanceFollowRedirects(true);
            conn.setRequestProperty("Connection", "close");
            conn.setRequestMethod("GET");
            conn.setRequestProperty("sdk_version", BuildConfig.SDK_VERSION);
            conn.getResponseCode();
        } catch (Exception e) {
            Logger.error("Failed to get url", e);
        } finally {
            if (conn != null) {
                try {
                    close(conn.getInputStream());
                    conn.disconnect();
                } catch (Exception e) {
                    Logger.error("Failed to disconnect", e);
                }
            }
        }
    }
    private static void close(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (Exception e) {
            Logger.error("Failed to close", e);
        }
    }
}
