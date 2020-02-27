package com.thoughtworks;

public enum UriMapper {

    CREATEBOARD("/1/Boards/"),
    GETBOARD("/1/Boards/%s"),
    UPDATEBOARD("/1/Boards/%s"),
    DELETEBOARD("/1/Boards/%s"),
    CREATELIST("/1/lists/");
    private String uriPath;

    UriMapper(String uriPath){
        this.uriPath = uriPath;
    }

    public String getUriPath(){
        return this.uriPath;
    }
}
