package utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.CustomResponses;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.Getter;

import java.util.Map;

public class APIRunner {
    // create method for HTTP GET
    @Getter
    private static CustomResponses customResponses;

    public static void runGet(String path) {

        //url domain and path
        //parameter and without parameters we will use overloading
        //token
        String url = Config.getProperty("cashWiseURI") + path;
        String token = CashWiseAuthorizationToken.getToken();

        Response response = RestAssured.given().auth().oauth2(token)
                .get(url);
        System.out.println("status code: " + response.statusCode());

        ObjectMapper mapper = new ObjectMapper();

        try {
            customResponses = mapper.readValue(response.asString(), CustomResponses.class);
        } catch (JsonProcessingException e) {
            System.out.println("This is most likely List of response");
        }
    }

    public static void runGet(String path, Map<String, Object> parameters){
        String url = Config.getProperty("cashWiseURI") + path;
        String token = CashWiseAuthorizationToken.getToken();

        Response response = RestAssured.given().auth().oauth2(token).params(parameters)
                .get(url);
        System.out.println("status code: " + response.statusCode());

        ObjectMapper mapper = new ObjectMapper();
        try {
            customResponses = mapper.readValue(response.asString(), CustomResponses.class);
            customResponses.setResponseBody(response.asString());

        } catch (JsonProcessingException e) {
            System.out.println("This is most likely List of response");
        }
    }



}












