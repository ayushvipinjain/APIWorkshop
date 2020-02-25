package com.thoughtworks;

import com.thoughtworks.framework.APIClient;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class TrelloClient {

    @Test
    public void ValidateTheBoardCreatedSuccessfully() throws URISyntaxException {

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

    @Test
    public void ValidateBoardUpdatedSuccessfully(){

        String boardID="5e535c9c44ebaa0f1fc86992";
        String newBoardName ="Board_" + LocalDateTime.now().toString();
        String newDescription = "Desc_" + LocalDateTime.now().toString();

        Map<String,String> queryParams = new HashMap();
        queryParams.put("name",newBoardName);
        queryParams.put("desc",newDescription);

        Response response =APIClient.updateRequest(String.format(UrlMapper.UPDATEBOARD.getUrlPath(),boardID),queryParams);
        Assert.assertEquals(APIClient.getStatusCode(response),200);
        Assert.assertEquals(APIClient.getValueFromPath(response,"name"),newBoardName);
        Assert.assertEquals(APIClient.getValueFromPath(response,"desc"),newDescription);
    }

    @Test
    public void ValidateBoardDeleted(){

        String boardName  = "API_" + LocalDateTime.now().toLocalTime().toString() ;
        Map<String,String> queryParams = new HashMap();
        queryParams.put("name",boardName);
        queryParams.put("defaultLists","false");
        Response response =APIClient.postRequest(UrlMapper.CREATEBOARD.getUrlPath(),queryParams);
        String boardID = APIClient.getValueFromPath(response,"id");

        Response deleteRequest = APIClient.deleteRequest(String.format(UrlMapper.DELETEBOARD.getUrlPath(),boardID));
        Assert.assertEquals(APIClient.getStatusCode(deleteRequest),200);
    }
}
