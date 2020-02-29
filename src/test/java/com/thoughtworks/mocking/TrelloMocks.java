package com.thoughtworks.mocking;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static io.restassured.RestAssured.given;


public class TrelloMocks {


    WireMockServer wireMockServer = new WireMockServer(wireMockConfig().port(8080).bindAddress("localhost"));

    @BeforeMethod
    public  void setUP(){
        wireMockServer.start();

        stubFor(post(urlMatching("/1/lists.*"))
             .willReturn(aResponse()
                     .withHeader("Content-Type","application/json")
                     .withStatus(200)
                     .withBodyFile("mockResponse.json")
//                     .withBody("{\n" +
//                             "            \"id\": \"5e58c086ea89a154ca0179c3\",\n" +
//                             "            \"name\": \"Mocked_Backlog\",\n" +
//                             "            \"closed\": false,\n" +
//                             "            \"idBoard\": \"5e54b1b7685b376f1fdd0b3a\",\n" +
//                             "            \"pos\": 1,\n" +
//                             "            \"limits\": {}\n" +
//                             "        }")
             ));
    }


    @Test
    public  void validateListCreated(){

        Map<String,String> queryParams = new HashMap();
        queryParams.put("name","Mocked");
        queryParams.put("idBoard","Mocked");

        RestAssured.baseURI="http://localhost:8080/";
        Response response =given().when()
                .header("Content-Type","json")
                .queryParams(queryParams)
                .post("/1/lists/");

       Assert.assertEquals(response.then().extract().path("name").toString(),"Mocked_Backlog");
    }

    @AfterMethod
    public void stopWireMock()
    {
        wireMockServer.stop();
    }
}

