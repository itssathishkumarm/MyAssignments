package com.Checkersgame;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class GamePageTest extends BaseTest {

    private GamePage gamePage;

    @Test
    public void testPlay5MovesAsOrange() {

        gamePage = new GamePage(driver);
        gamePage.navigateToCheckers();

        List<Move> moves = List.of(
                new Move("space62", "space73"),
                new Move("space71", "space62"),
                new Move("space60", "space71"),
                new Move("space22", "space13"),
                new Move("space31", "space22")
        );

        for (Move move : moves) {
            executeMove(move,moves);
        }

    }



    private void executeMove(Move move,List<Move> moves) {
        gamePage.selectPiece(move.currentPlace);
        gamePage.clickTarget(move.moveToPlace);

        // Wait for the move to complete and verify its success
        Wait<WebDriver> fluentWait = getFluentWait();
        fluentWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                By.xpath("//img[@src='https://www.gamesforthebrain.com/game/checkers/gray.gif']"),
                moves.indexOf(move) + 1));
    }

    @Test(priority = 2)
    public void reStartGame() {
        gamePage = new GamePage(driver);
        gamePage.navigateToCheckers();
    WebElement refresh = driver.findElement(By.xpath("//a[@href='./' and text()='Restart...']"));

    refresh.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            // Wait for the old element to become stale, indicating a refresh
            wait.until(ExpectedConditions.stalenessOf(refresh));

            System.out.println("Restart Completed");
        } catch (TimeoutException e) {
            e.printStackTrace();
        }


    }
}


