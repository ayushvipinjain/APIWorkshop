package com.thoughtworks.actions;

import com.thoughtworks.UriMapper;
import static com.thoughtworks.framework.APIClient.*;

import com.thoughtworks.Utility.JsonUtils;
import com.thoughtworks.models.BoardList;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class TrelloClient extends TrelloBase{

    @Test
    public void validateTheBoardCreatedSuccessfully() throws URISyntaxException {

        String boardName  = "API Learning Workshop";
        Map<String,String> queryParams = new HashMap();
        queryParams.put("name",boardName);
        queryParams.put("defaultLists","false");
        Response response =postRequest(UriMapper.CREATEBOARD.getUriPath(),queryParams);
        Assert.assertEquals(getStatusCode(response),200);
        Assert.assertEquals(getValueFromPath(response,"name"),boardName);
    }

    @Test
    public void validateTheBoardRetrivedSuccessfully() throws URISyntaxException {
        Response response = getRequest(String.format(UriMapper.GETBOARD.getUriPath(),testBoard));
        Assert.assertEquals(getStatusCode(response),200);
        Assert.assertEquals(getValueFromPath(response,"id"),testBoard);
    }

    @Test
    public void validateBoardUpdatedSuccessfully(){

        String newBoardName ="Board_" + LocalDateTime.now().toString();
        String newDescription = "Desc_" + LocalDateTime.now().toString();

        Map<String,String> queryParams = new HashMap();
        queryParams.put("name",newBoardName);
        queryParams.put("desc",newDescription);

        Response response =updateRequest(String.format(UriMapper.UPDATEBOARD.getUriPath(),testBoard),queryParams);
        Assert.assertEquals(getStatusCode(response),200);
        Assert.assertEquals(getValueFromPath(response,"name"),newBoardName);
        Assert.assertEquals(getValueFromPath(response,"desc"),newDescription);
    }

    @Test
    public void validateBoardDeleted(){

        String boardName  = "API_" + LocalDateTime.now().toLocalTime().toString() ;
        Map<String,String> queryParams = new HashMap();
        queryParams.put("name",boardName);
        queryParams.put("defaultLists","false");
        Response response =postRequest(UriMapper.CREATEBOARD.getUriPath(),queryParams);
        String boardID = getValueFromPath(response,"id");

        Response deleteRequest = deleteRequest(String.format(UriMapper.DELETEBOARD.getUriPath(),boardID));
        Assert.assertEquals(getStatusCode(deleteRequest),200);
    }

    @Test(dataProvider = "boardList")
    public void ValidateListIdCreatedForBoard(String list){

        Map<String,String> queryParams = new HashMap();
        queryParams.put("name",list);
        queryParams.put("idBoard",testBoard);
        Response response =postRequest(UriMapper.CREATELIST.getUriPath(),queryParams);
        BoardList boardList = (BoardList) JsonUtils.convertFromJson(getResponseAsString(response),BoardList.class);
        Assert.assertEquals(boardList.getName(),list);
    }

    @Test(dataProvider = "boardList")
    public void validateJsonSchemaForListCreation(String list) throws FileNotFoundException {

        Map<String,String> queryParams = new HashMap();
        queryParams.put("name",list);
        queryParams.put("idBoard",testBoard);
        Response response =postRequest(UriMapper.CREATELIST.getUriPath(),queryParams);
        validateJsonSchema(response, "listSchema.json");

    }
}
