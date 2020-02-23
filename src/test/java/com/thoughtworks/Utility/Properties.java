package com.thoughtworks.Utility;

public class Properties {

    private static final ConfigReader propertiesReader = new ConfigReader();

    public static final String baseURL = propertiesReader.getBaseURL();

    public static final String apiKEY= propertiesReader.getAPIKey();

    public static final String token = propertiesReader.getToken();
}
