package com.thoughtworks;

import com.thoughtworks.Utility.Properties;
import com.thoughtworks.framework.APIClient;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import static io.restassured.RestAssured.given;

public class TrelloClient {

    @Test
    public void ValidateTheBoardCreatedAndRetirvedSuccessfully() throws URISyntaxException {

        String boardName  = "API Learning Workshop";
        Map<String,String> queryParams = new HashMap();
        queryParams.put("name",boardName);
        queryParams.put("defaultLists","false");
        Response response =APIClient.postRequest(UrlMapper.CREATEBOARD.getUrlPath(),queryParams);
        Assert.assertEquals(APIClient.getStatusCode(response),200);
        Assert.assertEquals(APIClient.getValueFromPath(response,"name"),boardName);



    }

    @Test
    public void ValidateTheBoardRetrivedSuccessfully() throws URISyntaxException {
        String boardID = "5e535c9c44ebaa0f1fc86992";
        Response response = APIClient.getRequest(String.format(UrlMapper.GETBOARD.getUrlPath(),boardID));
        Assert.assertEquals(APIClient.getStatusCode(response),200);
        Assert.assertEquals(APIClient.getValueFromPath(response,"id"),boardID);
    }
}
