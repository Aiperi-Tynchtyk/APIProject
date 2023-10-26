package utilities;

import entities.RequestBody;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CashWiseAuthorizationToken {
    public static String getToken (){
        RequestBody requestBody=new RequestBody();
        requestBody.setEmail(Config.getProperty("emailCashWise"));
        requestBody.setPassword(Config.getProperty("passwordCashWise"));
        Response response= RestAssured.given().contentType(ContentType.JSON).body(requestBody).
                post(Config.getProperty("cashWiseURI")+"/api/myaccount/auth/login");
        response.prettyPrint();
        String token=response.jsonPath().getString("jwt_token");
        System.out.println("token "+token);
        return token;
    }
}
