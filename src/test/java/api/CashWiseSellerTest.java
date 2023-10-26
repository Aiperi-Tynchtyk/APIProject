package api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import entities.CustomResponses;
import entities.RequestBody;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.APIRunner;
import utilities.CashWiseAuthorizationToken;
import utilities.Config;
import java.util.HashMap;
import java.util.Map;


public class CashWiseSellerTest {
    //get single seller
    @Test
    public void getSingleSeller() {
    int id = 1743;
    String token = CashWiseAuthorizationToken.getToken();
    String url = Config.getProperty("cashWiseURI") + "/api/myaccount/sellers/" + id;

    Response response = RestAssured.given().auth().oauth2(token).get(url);
        System.out.println("status code: "+response.statusCode());
        response.prettyPrint();


}
    //get all sellers
    @Test
    public void getAllSeller(){
        String token = CashWiseAuthorizationToken.getToken();
        String url = Config.getProperty("cashWiseURI") + "/api/myaccount/sellers";

        Map<String, Object> parameters=new HashMap<>();
       // parameters.put("isArchived", true); // true show me the archived seller
        parameters.put("isArchived", false); //false show me active sellers
        parameters.put("page", 1);
        parameters.put("size",4);

        Response response=RestAssured.given().auth().oauth2(token).params(parameters).get(url);
        System.out.println("status code: "+response.statusCode());
        response.prettyPrint();

    }
    @Test
    public void createSeller() throws JsonProcessingException {
        String token=CashWiseAuthorizationToken.getToken();
        String url= Config.getProperty("cashWiseURI")+"/api/myaccount/sellers";

        RequestBody requestBody=new RequestBody();
        requestBody.setCompany_name("CodeWise");
        requestBody.setSeller_name("Bena");
        requestBody.setEmail("mag@gmail.com");
        requestBody.setPhone_number("123456");
        requestBody.setAddress("1005 devon");

        Response response=RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON)
                .body(requestBody).post(url);

        System.out.println("status code: "+response.statusCode());
        response.prettyPrint();

        //we hit API which returns us created seller with seller id, now we want to hit
        //get seller API with that id that we received from response
        ObjectMapper mapper=new ObjectMapper();
        CustomResponses customResponses=mapper.readValue(response.asString(),CustomResponses.class);
        System.out.println("seller id: "+customResponses.getSeller_id());

        Assert.assertEquals("it doesn't match ",200, response.statusCode());
    }

    @Test
    public void createManySeller(){
        String token=CashWiseAuthorizationToken.getToken();
        String url= Config.getProperty("cashWiseURI")+"/api/myaccount/sellers";

        //create 10 users we need faker and loop
        Faker faker=new Faker();
        RequestBody requestBody=new RequestBody();

        for (int i = 0; i <10 ; i++) {
            requestBody.setCompany_name(faker.company().name());
            requestBody.setSeller_name(faker.name().fullName());
            requestBody.setEmail(faker.internet().emailAddress());
            requestBody.setPhone_number(faker.phoneNumber().phoneNumber());
            requestBody.setAddress(faker.address().fullAddress());

            Response response=RestAssured.given().auth().oauth2(token)
                    .contentType(ContentType.JSON).body(requestBody).post(url);

            System.out.println("status code: "+response.statusCode());
            response.prettyPrint();
        }
    }

    @Test

    public void getEmailOfAllSellers() throws JsonProcessingException {
        String token=CashWiseAuthorizationToken.getToken();
        String url= Config.getProperty("cashWiseURI")+"/api/myaccount/sellers";

        Map<String, Object> parameters=new HashMap<>();
        parameters.put("isArchived", false);
        parameters.put("page", 1);
        //parameters.put("page", 2);
        parameters.put("size", 10);

        Response response=RestAssured.given().auth().oauth2(token).params(parameters)
                .get(url);

        System.out.println("status code: "+response.statusCode());

        ObjectMapper mapper=new ObjectMapper();
        CustomResponses customResponses=mapper.readValue(response.asString(), CustomResponses.class);

        int size=customResponses.getResponses().size();

        for (int i = 0; i < size; i++) {
            System.out.println("user's email: "+customResponses.getResponses().get(i)
                    .getEmail());
        }
    }

    @Test
    public void getSeller(){
        String path="/api/myaccount/sellers/1826";
        APIRunner.runGet(path);
        System.out.println("sellers name: "+APIRunner.getCustomResponses().getSeller_name());
        System.out.println("sellers email: "+APIRunner.getCustomResponses().getEmail());
    }

    @Test
    public void getSellersList(){
        String path="/api/myaccount/sellers";
        Map<String, Object> parameters=new HashMap<>();

        for (int i = 1; i <= 2; i++) {
            parameters.put("isArchived",false);
            parameters.put("page",i);
            parameters.put("size",10);
            APIRunner.runGet(path,parameters);
            System.out.println(APIRunner.getCustomResponses().getResponseBody());

            int size = APIRunner.getCustomResponses().getResponses().size();
            for (int j = 0; j < size; j++) {
                System.out.println(APIRunner.getCustomResponses().getResponses().get(j).getCompany_name());
            }
        }




//        APIRunner.runGet(path,parameters);
//        System.out.println(APIRunner.getCustomResponses().getResponseBody());
        //get company name of each seller
        // use for loop, use POJO from CustomResponse class to get company name
        //print total number of sellers 


    }




}