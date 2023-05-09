package com.adivery.events;

import java.util.Map;
import java.util.UUID;

public class Event {
    public final String name;
    public final Map<String, String> params;
    public final String[] errors;

    public final String uid;

    Event(String name, Map<String, String> params, String[] errors) {
        this.name = name;
        this.params = params;
        this.errors = errors;
        this.uid = UUID.randomUUID().toString();
    }
}