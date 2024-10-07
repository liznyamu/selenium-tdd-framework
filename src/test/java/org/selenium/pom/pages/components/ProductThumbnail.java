package org.selenium.pom.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.HomePage;

public class ProductThumbnail extends BasePage {
    private final By viewCartLnk = By.cssSelector("a[title='View cart']");

    public ProductThumbnail(WebDriver driver) {
        super(driver);
    }

    /**
     * Handle dynamically generated UI elements
     * @param productName - product name to add
     * @return By
     */
    private By getAddToCartBtnElement(String productName){
        return By.cssSelector("a[aria-label='Add “" + productName + "” to your cart']");
    }

    public ProductThumbnail clickAddToCartBtn(String productName){
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
