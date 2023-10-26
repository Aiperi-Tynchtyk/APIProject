package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import utilities.CashWiseAuthorizationToken;
import utilities.Config;
import java.util.HashMap;
import java.util.Map;


public class CashWiseSalesProductTest {
    // we need token

    @Test
    public void verifyProductList() {

        String token = CashWiseAuthorizationToken.getToken();

        //we need endpoint
        String url = Config.getProperty("cashWiseURI") + "/api/myaccount/products";
        // we need parameters
        Map<String, Integer> parameters = new HashMap<>();
        parameters.put("page", 1);
        parameters.put("size", 4);
        Response response= RestAssured.given().auth().oauth2(token).params(parameters).get(url);
        System.out.println("Status code: "+response.statusCode());
        response.prettyPrint();






    }
}

