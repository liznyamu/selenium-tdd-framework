package org.selenium.pom.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    protected WebDriver driver;
    private final WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;

        // explicit wait of 15 seconds -- reusable in the page classes
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void load(String endpoint){
        driver.get("https://askomdch.com" + endpoint);
    }

    /**
     *  Handle ElementClickInterceptedException - for example on clicking on CheckoutPage.placeOrderBtn
     *      intercepted by Overlays
     *  OR
     *  Handle StaleElementReferenceException - CheckoutPage.placeOrderBtn not on DOM due to Overlays
     *      displayed on entering billing address  details
     *  Other solutions :
     *    - Refer  to <a href="https://stackoverflow.com/questions/44912203/selenium-web-driver-java-element-is-not-clickable-at-point-x-y-other-elem">...</a>
     *
     * @param overlay - element overlaying other elements
     */
    public void waitForOverlayToDisappear(By overlay){
        List<WebElement> overlays = driver.findElements(overlay);
        System.out.println("OVERLAYS SIZE: " + overlays.size());
        if(!overlays.isEmpty()){
            wait.until(ExpectedConditions.invisibilityOfAllElements(overlays));
        }
        System.out.println("OVERLAYS ARE INVISIBLE");

    }

    /**
     * Note the `wait` variable is private
     *  - this way no will be able to use the wait variable in teh page objects
     *  - only option is to use the Explicit wait strategies waitForElementToBeVisible, waitForElementToBeClickable, ...
     *      in the BasePage
     *
     * @param locator - By locator
     * @return WebElement
     */
    public WebElement waitForElementToBeVisible(By locator){
       return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementToBeVisible(WebElement element){
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForElementToBeClickable(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementToBeClickable(WebElement element){
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Another explicit wait strategy - page loads | title contains
     * @param title
     */
    public void waitForTitleToContain(String title){
        wait.until(ExpectedConditions.titleContains(title));
    }

    /**
     * Another explicit wait strategy - url contains
     * @param urlTxt
     * @return
     */
    public Boolean waitForUrlToContain(String urlTxt){
        return wait.until(ExpectedConditions.urlContains(urlTxt));
    }

    /**
     * Another explicit wait strategy - wait for slowest element to show
     *
     * @param locator - Slowest locator to loaded on the page
     * @param txt - text to match
     * @return Boolean
     */
    public Boolean waitForTextToMatch(By locator, String txt){
        return wait.until(ExpectedConditions.textToBe(locator, txt));
    }

    public Boolean waitForTextToMatch(WebElement element, String txt){
        return wait.until(ExpectedConditions.textToBePresentInElement(element, txt));
    }
}
