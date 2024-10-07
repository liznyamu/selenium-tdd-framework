package org.selenium.pom.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {

    public WebDriver initializeDriver(String browser) throws InterruptedException {
        // TestNG parameter takes precedence over JVM TestNG template or maven system properties
        String localBrowser = (!browser.isEmpty())? browser: System.getProperty("browser", "CHROME");
        WebDriver driver;

        switch(String.valueOf(localBrowser)){
            case "CHROME":
                WebDriverManager.chromedriver().cachePath("drivers").setup();
                driver = new ChromeDriver();
                break;
            case "FIREFOX":
                WebDriverManager.firefoxdriver().cachePath("drivers").setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalStateException("Invalid browser name: " + localBrowser);
        }

        driver.manage().window().maximize();
        return driver;
    }

}
