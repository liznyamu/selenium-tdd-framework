package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.components.ProductThumbnail;

public class StorePage extends BasePage {

    private final By searchFld = By.id("woocommerce-product-search-field-0");
    private final By searchBtn = By.cssSelector("button[value='Search']");
    private final By title = By.cssSelector(".woocommerce-products-header__title.page-title");
    private final By viewCartLnk = By.cssSelector("a[title='View cart']");

    private ProductThumbnail productThumbnail;

    public StorePage(WebDriver driver) {
        super(driver);
        productThumbnail = new ProductThumbnail(driver);
    }

    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
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
}
