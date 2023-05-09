package com.adivery.events;

import android.text.TextUtils;
import android.view.TextureView;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.LinkedList;

public class SearchEventBuilder implements EventBuilder {

    private String keyword;
    private String category;

    private HashMap<String, String> filters = new HashMap<>();

    public SearchEventBuilder setKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }

    public SearchEventBuilder setCategory(String category) {
        this.category = category;
        return this;
    }

    public SearchEventBuilder addFilter(String filterName, String filterValue) {
        this.filters.put(filterName, filterValue);
        return this;
    }

    @Override
    public Event build() {
        HashMap<String, String> params = new HashMap<>();
        LinkedList<String> errors = new LinkedList<>();

        if (!TextUtils.isEmpty(this.keyword)){
            params.put("keyword", keyword);
        } else {
            errors.push("keyword cannot be null or empty");
        }

        if (!TextUtils.isEmpty(category)){
            params.put("category", category);
        }

        if (filters!= null && !filters.isEmpty()) {
            for (String key: filters.keySet()){
                params.put("filter_" + key, filters.get(key));
            }
        }

        return new Event("__search", params, errors.toArray(new String[0]));
    }
}
