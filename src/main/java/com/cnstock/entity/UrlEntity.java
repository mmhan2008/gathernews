package com.cnstock.entity;

/**
 * @author user01
 * @create 2019/1/29
 */
public class UrlEntity {
    private String baseUrl;
    private String title;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "UrlEntity{" +
                "baseUrl='" + baseUrl + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}

