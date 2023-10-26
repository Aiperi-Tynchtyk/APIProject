package api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.CustomResponses;
import entities.RequestBody;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import utilities.CashWiseAuthorizationToken;
import utilities.Config;

public class CashWiseCategoriesTest {
    @Test
    public void createCategory() throws JsonProcessingException {
        String token=CashWiseAuthorizationToken.getToken();
        String url= Config.getProperty("cashWiseURI")+"/api/myaccount/categories";

        RequestBody requestBody=new RequestBody();
        requestBody.setCategory_title("bank");
        requestBody.setCategory_description("bank services");
        requestBody.setFlag(false);
        //create the categories

        Response response= RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON)
                .body(requestBody).post(url);
        System.out.println("status code: "+response.statusCode());
        response.prettyPrint();

        String jsonResponse=response.asString();

        ObjectMapper mapper=new ObjectMapper();
        CustomResponses customResponses=mapper.readValue(jsonResponse, CustomResponses.class);
      //lines 32-33  we are storing response as a String then we are using mapper which comes from Jackson library
        //library we are mapping that response that we stored as String to out POJO CustomResponse.class
        //
    }



}
