package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BasePage;

public class HomePage extends BasePage {

    // TODO use a better locator strategy
    private final By storeMenuLink = By.cssSelector("#menu-item-1227 > a");
    private final By viewCartLnk = By.cssSelector("a[title='View cart']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public StorePage goToStoreUsingMenu(){
        waitForElementToBeClickable(storeMenuLink).click();
        return new StorePage(driver);
    }

    public HomePage load(){
        load("/");
        waitForTitleToContain("AskOmDch");
        return this;
    }

    /**
     * Handle dynamically generated UI elements
     * @param productName - product name to add
     * @return By
     */
    private By getAddToCartBtnElement(String productName){
        return By.cssSelector("a[aria-label='Add “" + productName + "” to your cart']");
    }

    public HomePage clickAddToCartBtn(String productName){
        By addToCartBtn = getAddToCartBtnElement(productName);
        waitForElementToBeClickable(addToCartBtn).click();
        return this;
    }

    public CartPage clickViewCartLnk(){
        waitForElementToBeClickable(viewCartLnk).click();
        return new CartPage(driver);
    }

    /**
     * Add product to cart - (Functional vs Structural methods on POM)
     *
     * @param productName - product name to add
     * @return CartPage
     */
    public CartPage addToCart(String productName){
        return clickAddToCartBtn(productName).
                clickViewCartLnk();
    }

}
