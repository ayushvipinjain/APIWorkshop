package com.thoughtworks.framework;

import com.thoughtworks.Utility.Properties;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseLogSpecification;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;

public class APIClient {

    private static RequestSpecification getRequestSpecification() {
        RestAssured.baseURI= Properties.baseURL;
         RequestSpecification requestSpecification =
                 given()
                        .when()
                         .header("Content-Type","json")
                        .queryParam("key",Properties.apiKEY)
                        .queryParam("token",Properties.token);
        return requestSpecification;
    }

    public static Response getRequest(String uriPath){
         return getRequestSpecification()
                 .get(uriPath);
    }

    public static Response postRequest(String URI,Map<String, String> queryParams) {

        try {
            Response response = getRequestSpecification()
                    .queryParams(queryParams)
                    .post(URI);
            System.out.println( response.body().prettyPrint());
            return response;

        } catch (Exception ex) {
           throw new NullPointerException();
        }
    }

    public static int getStatusCode(Response response){
       return response.statusCode();
    }

    public static String getValueFromPath(Response response, String jsonPath){
        return response.then().extract().path(jsonPath).toString();
    }
    public static String getResponseAsString(Response response){
        return response.thenReturn().body().asString();
    }
}