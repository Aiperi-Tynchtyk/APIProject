package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

public class Reqres {
    public static void main(String[] args) {
        Response response= RestAssured.get("https://reqres.in/api/users/2");
        System.out.println("status code: "+response.statusCode());
        response.prettyPrint();
        String email= response.jsonPath().get("data.email").toString();
        String firstname=response.jsonPath().get("data.first_name").toString();
        String lastname=response.jsonPath().get("data.last_name").toString();
        String avatar=response.jsonPath().get("data.avatar").toString();
        System.out.println("Email:"+email);
        System.out.println("firstname:"+firstname);
        System.out.println("lastname:"+lastname);
        System.out.println("avatar:"+avatar);

        Assert.assertFalse("email is empty",email.isEmpty());
        System.out.println("email is  not empty: "+email);

        Assert.assertFalse("first name is empty", firstname.isEmpty());
        System.out.println("firstname is: "+firstname);

        Assert.assertFalse("last name is empty", lastname.isEmpty());
        System.out.println("lastname is: "+lastname);

        Assert.assertFalse("avatar is empty", avatar.isEmpty());
        System.out.println("avatar is: "+avatar);
        System.out.println();

        Assert.assertTrue("avatar is not jpg or png",
                avatar.endsWith(".jpg")|| avatar.endsWith(".png"));
        System.out.println("avatar contains jpg or png");
        System.out.println();

        Assert.assertTrue(email.contains("reqres.in"));
        System.out.println("email contains reqres.in");

    }
}
//assert that fields are not empty and avatar is jpg or png