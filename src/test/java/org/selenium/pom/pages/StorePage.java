package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BasePage;

public class StorePage extends BasePage {

    private final By searchFld = By.id("woocommerce-product-search-field-0");
    private final By searchBtn = By.cssSelector("button[value='Search']");
    private final By title = By.cssSelector(".woocommerce-products-header__title.page-title");
    private final By viewCartLnk = By.cssSelector("a[title='View cart']");

    public StorePage(WebDriver driver) {
        super(driver);
    }

    public Boolean isLoaded(){
        return waitForUrlToContain("/store");
    }

    public StorePage load(){
        load("/store");
        return  this;
    }

    private StorePage enterTxtInSearchFld(String searchTxt){
        waitForElementToBeVisible(searchFld).sendKeys(searchTxt);
        return this;
    }

    private StorePage clickSearchBtn(){
        waitForElementToBeClickable(searchBtn).click();

        // wait for the page url to load before executing next command (eg asserting for search term on resulting page)
        waitForUrlToContain("&post_type=product");
        return this;
    }

    /**
     * Functional vs Structural page object (method)
     * @param searchTxt - search item
     * @return StorePage
     */
    public StorePage search(String searchTxt){
        return enterTxtInSearchFld(searchTxt).
                clickSearchBtn();
    }

    public String getTitle(){
        return waitForElementToBeVisible(title).getText();
    }

    /**
     * Handle dynamically generated UI elements
     * @param productName - product name to add
     * @return By
     */
    private By getAddToCartBtnElement(String productName){
        return By.cssSelector("a[aria-label='Add “" + productName + "” to your cart']");
    }

    public StorePage clickAddToCartBtn(String productName){
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
