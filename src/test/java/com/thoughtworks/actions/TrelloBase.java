package com.thoughtworks.actions;

import com.thoughtworks.UrlMapper;
import com.thoughtworks.framework.APIClient;
import io.restassured.response.Response;
import org.testng.annotations.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class TrelloBase {

    public String testBoard = null;

    @BeforeTest
    public void CreateTestData(){

        String boardName  = "API_"+ LocalDateTime.now().toLocalTime().toString();
        Map<String,String> queryParams = new HashMap();
        queryParams.put("name",boardName);
        queryParams.put("defaultLists","false");
        Response response = APIClient.postRequest(UrlMapper.CREATEBOARD.getUrlPath(),queryParams);
        testBoard = APIClient.getValueFromPath(response,"id");
    }

    @AfterTest
    public void DeleteTestData(){
        APIClient.deleteRequest(String.format(UrlMapper.DELETEBOARD.getUrlPath(),testBoard));
    }
}
