package org.selenium.pom.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.User;

public class CheckoutPage extends BasePage {
    private final By billingFirstNameFld = By.id("billing_first_name");
    private final By billingLastNameFld = By.id("billing_last_name");
    private final By billingCountryDropDown = By.id("billing_country");
    private final By alternateCountryDropdown = By.id("select2-billing_country-container");
    private final By billingAddressOneFld = By.id("billing_address_1");
    private final By billingCityFld = By.id("billing_city");
    private final By billingStateDropDown = By.id("billing_state");
    private final By alternateStateDropdown = By.id("select2-billing_state-container");
    private final By billingPostCodeFld = By.id("billing_postcode");
    private final By billingEmailFld = By.id("billing_email");

    private final By overlay = By.cssSelector(".blockOverlay");
    private final By directBankTransferRadioBtn = By.id("payment_method_bacs");
    private final By placeOrderBtn = By.id("place_order");
    private final By successNotice = By.cssSelector(".woocommerce-notice");

    private final By clickHereToLoginLnk = By.cssSelector(".showlogin");
    private final By usernameFld = By.id("username");
    private final By passwordFld = By.id("password");
    private final By loginBtn = By.name("login");


    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Clear firstName field to remove user state dependency on the tests
     *
     * @param firstName - billing address first name
     * @return CheckoutPage
     */
    public CheckoutPage enterFirstName(String firstName) {
        WebElement element = waitForElementToBeVisible(billingFirstNameFld);
        element.clear();
        element.sendKeys(firstName);
        return this;
    }

    public CheckoutPage enterLastName(String lastName) {
        WebElement element = waitForElementToBeVisible(billingLastNameFld);
        element.clear();
        element.sendKeys(lastName);
        return this;
    }

    /**
     *  Select state - to remove user state dependency on the tests
     *
     * @param countryName - billing country name
     * @return CheckoutPage
     */
    public CheckoutPage selectCountry(String countryName){
        /*
        // Handling Firefox - ElementNotInteractable Element <option> could not be scrolled into view
        WebElement element = waitForElementToBeVisible(billingCountryDropDown);
        Select select = new Select(element);
        select.selectByVisibleText(countryName);
        */

        waitForElementToBeClickable(alternateCountryDropdown).click();
        WebElement element = waitForElementToBeClickable(By.xpath("//li[text()='" + countryName + "']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
        return this;
    }

    public CheckoutPage enterAddressOne(String addressOne) {
        WebElement element = waitForElementToBeVisible(billingAddressOneFld);
        element.clear();
        element.sendKeys(addressOne);
        return this;
    }

    public CheckoutPage enterCity(String city) {
        WebElement element = waitForElementToBeVisible(billingCityFld);
        element.clear();
        element.sendKeys(city);
        return this;
    }

    /**
     * Select state - to remove/avoid user state dependency on the tests
     *
     * @param stateName - billing state name
     * @return CheckoutPage
     */
    public CheckoutPage selectState(String stateName){

        /*
        // Handling Firefox - ElementNotInteractable Element <option> could not be scrolled into view
        WebElement element = waitForElementToBeVisible(billingStateDropDown);
        Select select = new Select(element);
        select.selectByVisibleText(stateName);
        */

        waitForElementToBeClickable(alternateStateDropdown).click();
        WebElement element = waitForElementToBeClickable(By.xpath("//li[text()='" + stateName + "']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
        return this;
    }

    public CheckoutPage enterPostCode(String postCode) {
        WebElement element = waitForElementToBeVisible(billingPostCodeFld);
        element.clear();
        element.sendKeys(postCode);
        return this;
    }

    public CheckoutPage enterEmail(String email) {
        WebElement element = waitForElementToBeVisible(billingEmailFld);
        element.clear();
        element.sendKeys(email);
        return this;
    }

    public CheckoutPage enterBillingAddress(BillingAddress billingAddress) {
        enterFirstName(billingAddress.getFirstName()).
                enterLastName(billingAddress.getLastName()).
                selectCountry(billingAddress.getCountry()).
                enterAddressOne(billingAddress.getAddressLineOne()).
                enterCity(billingAddress.getCity()).
                selectState(billingAddress.getState()).
                enterPostCode(billingAddress.getPostCode()).
                enterEmail(billingAddress.getEmail());
        return this;
    }

    /**
     * Select direct bank transfer payment option - to remove/avoid application state dependency on the tests
     *
     * @return CheckoutPage
     */
    public CheckoutPage selectDirectBankTransfer(){
        WebElement element = waitForElementToBeVisible(directBankTransferRadioBtn);
        if(!element.isSelected()){
            element.click();
        }
        return this;
    }

    public CheckoutPage placeOrder() {
        waitForOverlayToDisappear(overlay);
        waitForElementToBeClickable(placeOrderBtn)
                .click();
        return this;
    }

    public String getNotice() {
        return waitForElementToBeVisible(successNotice).getText();
    }

    public CheckoutPage clickHereToLogin() {
        waitForElementToBeClickable(clickHereToLoginLnk).click();
        return this;
    }

    public CheckoutPage enterUsername(String username) {
        waitForElementToBeVisible(usernameFld).sendKeys(username);
        return this;
    }

    public CheckoutPage enterPassword(String password) {
        waitForElementToBeVisible(passwordFld).sendKeys(password);
        return this;
    }

    public CheckoutPage clickLoginBtn() {
        waitForElementToBeClickable(loginBtn).click();
        return this;
    }

    /**
     * Functional method to login
     *
     * @param user - User to login
     * @return CheckoutPage
     */
    public CheckoutPage login(User user) {
        return enterUsername(user.getUsername()).
                enterPassword(user.getPassword()).clickLoginBtn();
    }

}
