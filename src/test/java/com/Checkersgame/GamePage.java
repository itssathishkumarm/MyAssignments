package com.Checkersgame;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GamePage {

    private WebDriver driver;

    public GamePage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToCheckers() {
        driver.get("https://www.gamesforthebrain.com/game/checkers/");
        String pageTitle = driver.getTitle();
        assert (pageTitle.equals("Checkers - Games for the Brain"));
    }

    private WebElement getElement(String name) {
        return driver.findElement(By.xpath("//img[@name='" + name + "']"));
    }

    public void selectPiece(String name) {
        WebElement piece = getElement(name);
        piece.click();
    }

    public void clickTarget(String name) {
        WebElement target = getElement(name);
        target.click();
    }
}
