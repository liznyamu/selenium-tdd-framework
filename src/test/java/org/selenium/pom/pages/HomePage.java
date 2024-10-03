package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BasePage;

public class HomePage extends BasePage {

    // TODO use a better locator strategy
    private final By storeMenuLink = By.cssSelector("#menu-item-1227 > a");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public StorePage goToStoreUsingMenu(){
        driver.findElement(storeMenuLink).click();
        return new StorePage(driver);
    }

    public HomePage load(){
        load("/");
        return this;
    }

}
