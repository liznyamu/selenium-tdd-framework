package org.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class MyFirstTestCase {

    @Test
    public void dummyTest() {
        WebDriverManager.chromedriver().cachePath("drivers").setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://askomdch.com/");
    }
}
