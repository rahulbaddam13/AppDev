package com.example.numad22fa_rahulreddybaddam;


public class LinkCollectorItem {
    private final String name;
    private final String url;

    public LinkCollectorItem(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName()
    {
        return name;
    }

    public String getUrl()
    {
        return url;
    }
}