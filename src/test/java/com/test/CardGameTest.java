package com.test;

import static com.cards.util.Utils.*;

import api.DeckOfCardsAPI;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CardGameTest {

    String deckId = null;

    @BeforeTest
    public void CreateandShuffleDeck() {


        // Step 1: Create a new deck
        Response newDeckResponse = DeckOfCardsAPI.createNewDeck();
        Assert.assertEquals(newDeckResponse.getStatusCode(), 200);

        deckId = newDeckResponse.jsonPath().getString("deck_id");

        // Step 2: Shuffle the deck
        Response shuffleResponse = DeckOfCardsAPI.shuffleDeck(deckId);
        Assert.assertEquals(shuffleResponse.getStatusCode(), 200);
    }

    @Test
    public void draw2Cards() {

        // Step 3: Deal three cards to each of two players

        Response player1Response = DeckOfCardsAPI.drawCards(deckId, 2);
        Response player2Response = DeckOfCardsAPI.drawCards(deckId, 2);

        Assert.assertEquals(player1Response.getStatusCode(), 200);
        Assert.assertEquals(player2Response.getStatusCode(), 200);

        // Step 4: Check for blackjack
        checkBlackjack(player1Response, "Player 1");
        checkBlackjack(player2Response, "Player 2");


    }

    @Test
    public void draw3Cards() {

        // Step 3: Deal three cards to each of two players

        Response player1Response = DeckOfCardsAPI.drawCards(deckId, 3);
        Response player2Response = DeckOfCardsAPI.drawCards(deckId, 3);

        Assert.assertEquals(player1Response.getStatusCode(), 200);
        Assert.assertEquals(player2Response.getStatusCode(), 200);

        // Step 4: Check for blackjack
        checkBlackjack(player1Response, "Player 1");
        checkBlackjack(player2Response, "Player 2");


    }

    @Test

    public void TestBlackwithFirst2Cards()
    {

        Response player1Response = DeckOfCardsAPI.drawCards(deckId, 3);
        Response player2Response = DeckOfCardsAPI.drawCards(deckId, 3);

        Assert.assertEquals(player1Response.getStatusCode(), 200);
        Assert.assertEquals(player2Response.getStatusCode(), 200);

        // Step 4: Check for blackjack
        checkBlackjackwith2Cards(player1Response, "Player 1");
        checkBlackjackwith2Cards(player2Response, "Player 2");

    }


}
