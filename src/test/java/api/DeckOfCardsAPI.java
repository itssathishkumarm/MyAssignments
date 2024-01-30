package api;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
public class DeckOfCardsAPI {

    private static final String BASE_URI = "https://deckofcardsapi.com/api/deck";

    public static Response createNewDeck() {
        return given().baseUri(BASE_URI).get("/new");
    }

    public static Response shuffleDeck(String deckId) {
        return given().baseUri(BASE_URI).get("/" + deckId + "/shuffle/");
    }

    public static Response drawCards(String deckId,int count)
    {

        return given().baseUri(BASE_URI).get("/" + deckId + "/draw/?count="+count);

    }
}
