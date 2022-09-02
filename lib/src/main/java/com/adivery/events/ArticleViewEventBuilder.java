package com.adivery.events;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.LinkedList;

public class ArticleViewEventBuilder implements EventBuilder {
    private String title;
    private String[] keywords;
    private String url;

    public ArticleViewEventBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public ArticleViewEventBuilder setKeywords(String[] keywords) {
        this.keywords = keywords;
        return this;
    }

    public ArticleViewEventBuilder setUrl(String url) {
        this.url = url;
        return this;
    }

    @Override
    public Event build() {
        HashMap<String, String> params = new HashMap<>();
        LinkedList<String> errors = new LinkedList<>();

        if (!TextUtils.isEmpty(title)) {
            params.put("title", title);
        } else {
            errors.add("Article title is required");
        }

        if (keywords != null && keywords.length > 0) {
            params.put("keywords", Utils.joinString(",", keywords));
        }

        if (!TextUtils.isEmpty(url)) {
            params.put("url", url);
        }

        return new Event("__article_view", params, errors.toArray(new String[0]));
    }
}
