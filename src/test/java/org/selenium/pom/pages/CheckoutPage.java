package org.selenium.pom.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.User;

public class CheckoutPage extends BasePage {
    private final By billingFirstNameFld = By.id("billing_first_name");
    private final By billingLastNameFld = By.id("billing_last_name");
    private final By billingAddressOneFld = By.id("billing_address_1");
    private final By billingCityFld = By.id("billing_city");
    private final By billingPostCodeFld = By.id("billing_postcode");
    private final By billingEmailFld = By.id("billing_email");
    private final By placeOrderBtn = By.id("place_order");
    private final By successNotice = By.cssSelector(".woocommerce-notice");
    private final By clickHereToLoginLnk = By.cssSelector(".showlogin");
    private final By usernameFld = By.id("username");
    private final By passwordFld = By.id("password");
    private final By loginBtn = By.name("login");
    private final By overlay = By.cssSelector(".blockOverlay");


    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Note we are getting rid or handling some of the user state dependency
     * by clearing text fields before filling them for registered users
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
                enterAddressOne(billingAddress.getAddressLineOne()).
                enterCity(billingAddress.getCity()).
                enterPostCode(billingAddress.getPostCode()).
                enterEmail(billingAddress.getEmail());
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
