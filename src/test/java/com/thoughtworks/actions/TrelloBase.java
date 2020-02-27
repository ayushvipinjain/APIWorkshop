package com.thoughtworks.actions;

import com.google.gson.JsonArray;
import com.thoughtworks.UriMapper;
import com.thoughtworks.Utility.JsonUtils;
import com.thoughtworks.framework.APIClient;
import io.restassured.response.Response;
import org.testng.annotations.*;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class TrelloBase {

    public String testBoard = null;

    @BeforeTest
    public void CreateTestData() {

        String boardName  = "API_"+ LocalDateTime.now().toLocalTime().toString();
        Map<String,String> queryParams = new HashMap();
        queryParams.put("name",boardName);
        queryParams.put("defaultLists","false");
        Response response = APIClient.postRequest(UriMapper.CREATEBOARD.getUriPath(),queryParams);
        testBoard = APIClient.getValueFromPath(response,"id");
    }

    @AfterTest
    public void DeleteTestData(){
        APIClient.deleteRequest(String.format(UriMapper.DELETEBOARD.getUriPath(),testBoard));
    }


    @DataProvider(name = "boardList", parallel = true)
    public Object[] getBoardList() throws FileNotFoundException {
        String jsonFilePath = ClassLoader.getSystemResource("list.json").getFile();
        String jsonContent = JsonUtils.convertToJsonString(jsonFilePath);
        JsonArray listItems = JsonUtils.convertToJsonArray(jsonContent);

        Object[] list = new Object[listItems.size()];
        for (int index =0;index<listItems.size();index++){
            list[index]= listItems.get(index).getAsJsonObject().get("name").getAsString();
        }
        return list;
    }

}
