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

    private StorePage enterTxtInSearchFld(String searchTxt){
        driver.findElement(searchFld).sendKeys(searchTxt);
        return this;
    }

    private StorePage clickSearchBtn(){
        driver.findElement(searchBtn).click();
        return this;
    }

    /**
     * Functional vs Structural page object (method)
     * @param searchTxt
     * @return
     */
    public StorePage search(String searchTxt){
        return enterTxtInSearchFld(searchTxt).
                clickSearchBtn();
    }

    public String getTitle(){
        return driver.findElement(title).getText();
    }

    /**
     * Handle dynamically generated UI elements
     * @param productName
     * @return
     */
    private By getAddToCartBtnElement(String productName){
        return By.cssSelector("a[aria-label='Add “" + productName + "” to your cart']");
    }

    public StorePage clickAddToCartBtn(String productName){
        By addToCartBtn = getAddToCartBtnElement(productName);
        driver.findElement(addToCartBtn).click();
        return this;
    }

    public CartPage clickViewCartLnk(){
        driver.findElement(viewCartLnk).click();
        return new CartPage(driver);
    }
}
