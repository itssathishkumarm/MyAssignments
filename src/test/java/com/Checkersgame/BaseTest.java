package com.Checkersgame;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;

    @BeforeTest
    public void setup() {
        // Set up the driver ,using chrome browser
        WebDriverManager .chromedriver().setup();
            driver = new ChromeDriver();

    }

    @AfterTest
    public void teardown() {
        // Close the browser
        driver.quit();
    }

    public Wait<WebDriver> getFluentWait() {
        return new FluentWait<>(driver)
                .pollingEvery(Duration.ofNanos(500))
                .withTimeout(Duration.ofSeconds(15));
    }
}
