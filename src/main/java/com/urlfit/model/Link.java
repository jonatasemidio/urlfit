package com.urlfit.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Link {
    private String url;
    private String fullUrl;
    /**
     * responsible for controll the number of access for each url shorten process
     */
    private int access;

    public Link(String fullLink){
        this.fullUrl = fullLink;
        this.access = 1;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public String getUrl(){
        return this.url;
    }

    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }

    public void setUrl(String shortUrl) {
        this.url = shortUrl;
    }
}
