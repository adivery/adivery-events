package com.adivery.events;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.LinkedList;

public class UserActionEventBuilder implements EventBuilder {
    public enum Action {
        REGISTER,
        LOGIN
    }

    String action;

    public UserActionEventBuilder setAction(Action action) {
        this.setAction(action.toString());
        return this;
    }

    public UserActionEventBuilder setAction(String action) {
        this.action = action;
        return this;
    }


    private static char normalizeCharacter(char c) {
        if ('A' <= c && c <= 'Z') {
            return (char) (c - 'A' + 'a');
        }

        if ('a' <= c && c <= 'z') {
            return c;
        }

        if ('0' <= c && c <= '9') {
            return c;
        }

        String separators = "_- ";

        for (int i = 0; i < separators.length(); i++) {
            if (c == separators.charAt(i)) {
                return '-';
            }
        }

        return 0;
    }

    private String getNormalizedActionValue() {
        if (TextUtils.isEmpty(this.action)) {
            return null;
        }

        char[] chars = new char[action.length()];
        for (int i = 0; i < action.length(); i++) {
            chars[i] = normalizeCharacter(action.charAt(i));
            if (chars[i] == 0) {
                return null;
            }
        }
        return new String(chars);
    }

    @Override
    public Event build() {
        HashMap<String, String> params = new HashMap<>();
        LinkedList<String> errors = new LinkedList<>();

        if (action != null) {
            String actionValue = getNormalizedActionValue();
            if (actionValue != null) {
                params.put("action", actionValue);
            } else {
                errors.add("Empty or invalid action name provided");
            }
        } else {
            errors.add("User action type is required");
        }

        return new Event("__user_action", params, errors.toArray(new String[0]));
    }
}

