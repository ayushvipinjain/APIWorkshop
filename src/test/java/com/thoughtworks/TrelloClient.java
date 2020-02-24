package com.thoughtworks;

import com.thoughtworks.Utility.Properties;
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
        RestAssured.baseURI= Properties.baseURL;
        Response response =
                given().when()
                        .header("Content-Type","json")
                .queryParam("name","My New Board")
                .queryParam("defaultLists","false")
                .queryParam("key",Properties.apiKEY)
                .queryParam("token",Properties.token)
                .post("/1/Boards/");

        Assert.assertTrue(response.statusCode()==200);

    }

    @Test
    public void ValidateTheBoardRetrivedSuccessfully() throws URISyntaxException {
        RestAssured.baseURI= Properties.baseURL;
        Response response =
                given().when()
                        .header("Content-Type","json")
                        .queryParam("key",Properties.apiKEY)
                        .queryParam("token",Properties.token)
                        .get("1/Boards/5e535c9c44ebaa0f1fc86992");
        Assert.assertTrue(response.statusCode()==200);
    }
}
