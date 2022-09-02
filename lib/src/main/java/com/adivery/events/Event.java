package com.adivery.events;

import java.util.Map;

public class Event {
    public final String name;
    public final Map<String, String> params;
    public final String[] errors;

    Event(String name, Map<String, String> params, String[] errors) {
        this.name = name;
        this.params = params;
        this.errors = errors;
    }
}