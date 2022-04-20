package com.example.demo;

public class Supplier {
    private int id;
    private String title;
    private String thumbnail_url;
    private String url;


    public Supplier() {
    }


    public Supplier(int id, String title, String thumbnail_url, String url) {
        this.id = id;
        this.title = title;
        this.thumbnail_url = thumbnail_url;
        this.url = url;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail_url() {
        return this.thumbnail_url;
    }

    public void setThumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }

    public String getLink() {
        return this.url;
    }

    public void setLink(String link) {
        this.url = link;
    }
}