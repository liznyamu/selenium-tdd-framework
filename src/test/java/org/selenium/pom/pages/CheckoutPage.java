package org.selenium.pom.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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


    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Note we are getting rid or handling some of the user state dependency
     * by clearing text fields before filling them for registered users
     *
     * @param firstName
     * @return
     */
    public CheckoutPage enterFirstName(String firstName) {
        driver.findElement(billingFirstNameFld).clear();
        driver.findElement(billingFirstNameFld).sendKeys(firstName);
        return this;
    }

    public CheckoutPage enterLastName(String lastName) {
        driver.findElement(billingLastNameFld).clear();
        driver.findElement(billingLastNameFld).sendKeys(lastName);
        return this;
    }

    public CheckoutPage enterAddressOne(String addressOne) {
        driver.findElement(billingAddressOneFld).clear();
        driver.findElement(billingAddressOneFld).sendKeys(addressOne);
        return this;
    }

    public CheckoutPage enterCity(String city) {
        driver.findElement(billingCityFld).clear();
        driver.findElement(billingCityFld).sendKeys(city);
        return this;
    }

    public CheckoutPage enterPostCode(String postCode) {
        driver.findElement(billingPostCodeFld).clear();
        driver.findElement(billingPostCodeFld).sendKeys(postCode);
        return this;
    }

    public CheckoutPage enterEmail(String email) {
        driver.findElement(billingEmailFld).clear();
        driver.findElement(billingEmailFld).sendKeys(email);
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
        driver.findElement(placeOrderBtn).click();
        return this;
    }

    public String getNotice() {
        return driver.findElement(successNotice).getText();
    }

    public CheckoutPage clickHereToLogin() {
        driver.findElement(clickHereToLoginLnk).click();
        return this;
    }

    public CheckoutPage enterUsername(String username) {
        driver.findElement(usernameFld).sendKeys(username);
        return this;
    }

    public CheckoutPage enterPassword(String password) {
        driver.findElement(passwordFld).sendKeys(password);
        return this;
    }

    public CheckoutPage clickLoginBtn() {
        driver.findElement(loginBtn).click();
        return this;
    }

    /**
     * Functional method to login
     *
     * @param user
     * @return
     */
    public CheckoutPage login(User user) {
        return enterUsername(user.getUsername()).
                enterPassword(user.getPassword()).clickLoginBtn();
    }

}
