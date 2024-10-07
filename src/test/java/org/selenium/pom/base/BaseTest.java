package org.selenium.pom.base;

import org.openqa.selenium.WebDriver;
import org.selenium.pom.factory.DriverManager;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private void setDriver(WebDriver driver1){
        driver.set(driver1);
    }

    protected WebDriver getDriver(){
        return  driver.get();
    }

    @Parameters("browser")
    @BeforeMethod
    protected void startDriver(String browser) {
        // TestNG parameter takes precedence over JVM TestNG template or maven system properties
        browser = System.getProperty("browser", browser);
        setDriver(new DriverManager().initializeDriver(browser));
        System.out.println("CURRENT THREAD: " + Thread.currentThread().threadId() + ", " +
                "DRIVER = " + getDriver());
    }

    @AfterMethod
    protected void quitDriver() {
        System.out.println("CURRENT THREAD: " + Thread.currentThread().threadId() + ", " +
                "DRIVER = " + getDriver());
        getDriver().quit();
        setDriver(null);
    }
}
