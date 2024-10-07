package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.ConfigLoader;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class MyFirstTestCase extends BaseTest {

    @Test
    public void guestCheckoutUsingDirectBankTransfer() throws IOException {
        // extract billing address and product test data from json
        BillingAddress billingAddress = JacksonUtils.deserialize("myBillingAddress.json", BillingAddress.class);
        Product product = new Product(1215);

        // use variables
        String searchTxt = "Blue";

        // using the Builder pattern
        StorePage storePage = new HomePage(getDriver()).
                load().
                goToStoreUsingMenu().
                search(searchTxt);
        Assert.assertEquals(storePage.getTitle(), "Search results: “" + searchTxt + "”");

        CartPage cartPage = storePage.addToCart(product.getName());
        Assert.assertEquals(cartPage.getProductName(),product.getName());

        // using the Builder pattern
        CheckoutPage checkoutPage = cartPage.
                checkout().
                enterBillingAddress(billingAddress).
                selectDirectBankTransfer().
                placeOrder();
        Assert.assertEquals(checkoutPage.getNotice(),"Thank you. Your order has been received.");
    }


    @Test
    public void loginAndCheckoutUsingDirectBankTransfer() throws InterruptedException, IOException {
        // extract billing address and product test data from json
        BillingAddress billingAddress = JacksonUtils.deserialize("myBillingAddress.json", BillingAddress.class);
        Product product = new Product(1215);

        // we'll extract the single user from a config file  ---> then register a new user via API
        User user = new User(
                ConfigLoader.getInstance().getUsername(),
                ConfigLoader.getInstance().getPassword());

        // use variables
        String searchTxt = "Blue";

        // using the Builder pattern
        StorePage storePage = new HomePage(getDriver()).
                load().
                goToStoreUsingMenu().
                search(searchTxt);
        Assert.assertEquals(storePage.getTitle(), "Search results: “" + searchTxt + "”");

        CartPage cartPage = storePage.addToCart(product.getName());
        Assert.assertEquals(cartPage.getProductName(),product.getName());

        // using the Builder pattern
        CheckoutPage checkoutPage = cartPage.
                checkout().
                clickHereToLogin().
                login(user).
                enterBillingAddress(billingAddress).
                selectDirectBankTransfer().
                placeOrder();
        Assert.assertEquals(checkoutPage.getNotice(),"Thank you. Your order has been received.");
    }
}
