package com.adivery.events;

import android.util.Log;

public class Logger {
    private static final String TAG = "AdiveryUserEvents";

    public static void debug(String message) {
        Log.d(TAG, message);
    }

    public static void error(String message) {
        Log.e(TAG, message);
    }

    public static void error(String message, Throwable throwable) {
        Log.e(TAG, message, throwable);
    }

}
