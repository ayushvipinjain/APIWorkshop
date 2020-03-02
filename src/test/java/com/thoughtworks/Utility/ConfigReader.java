package com.thoughtworks.Utility;

import javax.security.auth.login.Configuration;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    Properties properties = new Properties();

    public ConfigReader() {
        try {

            String propertiesFilePath = "config.properties";
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(propertiesFilePath);
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getBaseURL() {
        return properties.getProperty("BaseURL");
    }

    public String getToken(){
        return System.getenv("TOKEN");
    }

    public String getAPIKey(){
        return System.getenv("APIKEY");
    }

}
