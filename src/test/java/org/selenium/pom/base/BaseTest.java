package org.selenium.pom.base;

import io.restassured.http.Cookies;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.factory.DriverManager;
import org.selenium.pom.utils.CookieUtils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.util.List;

public class BaseTest {
    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

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

    public void injectCookiesToBrowser(Cookies restAssuredCookies){
        List<Cookie> seleniumCookies = new CookieUtils().convertRestAssuredCookiesToSeleniumCookies(restAssuredCookies);
        for(Cookie cookie: seleniumCookies){
            getDriver().manage().addCookie(cookie);
        }

    }

}
