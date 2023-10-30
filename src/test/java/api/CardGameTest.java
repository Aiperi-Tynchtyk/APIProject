package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class CardGameTest {
    @Test
    public void blackJack(){
        //hit new deck and get deck_id
        String newDeckUrl="https://www.deckofcardsapi.com/api/deck/new";
        Response response= RestAssured.given().get(newDeckUrl);
        response.prettyPrint();
        System.out.println("status code: "+response.statusCode());

        //use Json path
        String deckID=response.jsonPath().getString("deck_id");
        System.out.println("deck ID: "+deckID);
        response.prettyPrint();

        //assert that our deck of cards shuffled or not
        boolean shuffled=response.jsonPath().getBoolean("shuffled");
        Assert.assertFalse(shuffled); //it hasn't shuffled yet

        //shuffled our deck of cards

        String shuffleURL="https://www.deckofcardsapi.com/api/deck/"+deckID+"/shuffle/";
        response=RestAssured.given().get(shuffleURL);
        response.prettyPrint();
        shuffled=response.jsonPath().getBoolean("shuffled");

        //we need to asser that our deck of cards shuffled
        Assert.assertTrue(shuffled);

        //now deal 3 cards to 2 students
        String drawURL="https://www.deckofcardsapi.com/api/deck/"+deckID+"/draw/";
        Map<String, Object> parameters=new HashMap<>();
        parameters.put("count",3);
        response=RestAssured.given().params(parameters).get(drawURL);
        response.prettyPrint();
        System.out.println("first players card:"+ response.jsonPath().getString("cards[0].value"));
        System.out.println("first players card:"+ response.jsonPath().getString("cards[1].value"));
        System.out.println("first players card:"+ response.jsonPath().getString("cards[2].value"));

        response=RestAssured.given().params(parameters).get(drawURL);
        response.prettyPrint();

        System.out.println("second players card:"+ response.jsonPath().getString("cards[0].value"));
        System.out.println("second players card:"+ response.jsonPath().getString("cards[1].value"));
        System.out.println("second players card:"+ response.jsonPath().getString("cards[2].value"));

        //verify that we have less than 52 cards

        int remaining=response.jsonPath().getInt("remaining");
        Assert.assertEquals(remaining,46);





    }


}
