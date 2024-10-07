package org.selenium.pom.base;

import org.openqa.selenium.WebDriver;
import org.selenium.pom.factory.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {
    protected WebDriver driver;

    @Parameters("browser")
    @BeforeMethod
    protected void startDriver(String browser) throws InterruptedException {
        driver = new DriverManager().initializeDriver(browser);
    }

    @AfterMethod
    protected void quitDriver() {
        driver.quit();
        driver = null;
    }
}
