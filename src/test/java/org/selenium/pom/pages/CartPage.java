package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class CartPage extends BasePage {
/*
    private final By productName = By.cssSelector("td[class='product-name'] a");
    private final By checkoutBtn = By.cssSelector(".checkout-button");
    private final By cartTitle = By.xpath("//h1[text()='Cart']");
*/
    @FindBy(how=How.CSS, using="td[class='product-name'] a") private WebElement productName;
    @FindBy(css = ".checkout-button") @CacheLookup private WebElement checkoutBtn;
    @FindBy(xpath = "//h1[text()='Cart']") private WebElement cartTitle;


    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
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
