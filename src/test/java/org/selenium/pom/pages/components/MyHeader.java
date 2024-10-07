package org.selenium.pom.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.StorePage;

public class MyHeader extends BasePage {

    // TODO use a better locator strategy
    private final By storeMenuLink = By.cssSelector("#menu-item-1227 > a");

    public MyHeader(WebDriver driver) {
        super(driver);
    }

    public StorePage goToStoreUsingMenu(){
        waitForElementToBeClickable(storeMenuLink).click();
        return new StorePage(driver);
    }

}
