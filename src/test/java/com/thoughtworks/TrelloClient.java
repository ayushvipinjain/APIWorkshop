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
                .queryParam("key","7cf9c0a884ce4fe5367c569404f363dc")
                .queryParam("token","ed12559cb94109ff2042ad408f24d1b39586cc4b06583742833cf67eac0777c9")
                .post("/1/Boards/");

        Assert.assertTrue(response.statusCode()==200);

    }
}
