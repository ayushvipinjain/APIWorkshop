package com.thoughtworks;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static io.restassured.RestAssured.given;

public class TrelloClient {

    @Test
    public void ValidateTheBoardIsCreated() throws URISyntaxException {
        RestAssured.baseURI="https://api.trello.com";
        Response response =
                given().when()
                        .header("Content-Type","json")
                .queryParam("name","My Board")
                .queryParam("defaultLists","false")
                .queryParam("key","<--key-->}")
                .queryParam("token","<--token>")
                .post("/1/Boards/");

        Assert.assertTrue(response.statusCode()==200);

    }
}
