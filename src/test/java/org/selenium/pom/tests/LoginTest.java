package org.selenium.pom.tests;

import com.fasterxml.jackson.databind.ser.Serializers;
import io.restassured.http.Cookies;
import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.api.actions.SignUpApi;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.utils.FakerUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest {
    @Test
    public void loginDuringCheckout() throws IOException {

        // Step 1/4 :
        // set up the application state - a new user has added product to cart via API
        //  new user - to remove dependency on system ie using existing accounts

        // fake user details
        String username = "agatha_harkness" + new FakerUtils().generateRandomNumber();
        User user = new User().
                setUsername(username).
                setPassword("test@1Test").
                setEmail(username + "@salem.com");

        // register the user
        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);

        // add product to the cart
        CartApi cartApi = new CartApi(new Cookies());
        Product product = new Product(1215);
        cartApi.addToCart(product.getId(), 1);



        // Step 2/4 :
        // load the checkout page
        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).
                load();

        // Step 3/4 :
        // start UI at the Checkout page - inject the cartApi cookies to the browser AND refresh the Checkout page
        injectCookiesToBrowser(cartApi.getCookies());
        checkoutPage.load();

        // Step 4/4 :
        // Proceed with UI test and validations ie login via UI AND check if product was added
        checkoutPage.
                clickHereToLogin().
                login(user);
        Assert.assertTrue(checkoutPage.getProductName().contains(product.getName()));

    }

    @Test(enabled = false, groups={"TODO"})
    public void loginFails(){

    }
}
