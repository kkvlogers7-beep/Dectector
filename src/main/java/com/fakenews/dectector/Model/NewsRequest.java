package com.fakenews.dectector.Model;

public class NewsRequest {
    private String text;
    private String url;

    // getters and setters
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
