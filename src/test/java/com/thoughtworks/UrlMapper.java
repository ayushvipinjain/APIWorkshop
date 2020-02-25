package com.thoughtworks;

public enum  UrlMapper {

    CREATEBOARD("/1/Boards/"),
    GETBOARD("/1/Boards/%s");

    private String urlPath;

    UrlMapper(String urlPath){
        this.urlPath = urlPath;
    }

    public String getUrlPath(){
        return this.urlPath;
    }
}
