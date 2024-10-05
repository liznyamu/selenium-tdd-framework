package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class CartPage extends BasePage {
    private final By productName = By.cssSelector("td[class='product-name'] a");
    private final By checkoutBtn = By.cssSelector(".checkout-button");
    private final By cartTitle = By.xpath("//h1[text()='Cart']");


    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName(){
       return waitForElementToBeVisible(productName).
               getText();
    }

    public CheckoutPage checkout(){
        waitForElementToBeClickable(checkoutBtn).
                click();
        return new CheckoutPage(driver);
    }

    /**
     * Wait for slowest element to load
     *
     * @return Boolean
     */
    public Boolean isLoaded(){
        return waitForTextToMatch(cartTitle, "Cart");
    }

}
