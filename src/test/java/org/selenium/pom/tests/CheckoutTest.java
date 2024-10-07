package org.selenium.pom.tests;

import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.api.actions.SignUpApi;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.utils.FakerUtils;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CheckoutTest extends BaseTest {
    @Test
    public void guestCheckoutUsingDirectBankTransfer() throws IOException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        Product product = new Product(1215);


        // Step 1/3:
        // load the checkout page
        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();

        // Step 2/3 add product to cart
        CartApi cartApi = new CartApi();
        cartApi.addToCart(product.getId(), 1);
        injectCookiesToBrowser(cartApi.getCookies());

        // Step 3/3 enter billing address and place the order
        checkoutPage.
                load().
                enterBillingAddress(billingAddress).
                selectDirectBankTransfer().
                placeOrder();
        Assert.assertEquals(checkoutPage.getNotice(),"Thank you. Your order has been received.");

    }

    @Test
    public void loginAndCheckoutUsingDirectBankTransfer() throws IOException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json",
                BillingAddress.class);
        String username = "agatha_harkness" + new FakerUtils().generateRandomNumber();
        User user = new User().
                setUsername(username).
                setPassword("test@1Test").
                setEmail(username + "@salem.com");

        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);
        CartApi cartApi = new CartApi(signUpApi.getCookies());
        Product product = new Product(1215);
        cartApi.addToCart(product.getId(), 1);

        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        injectCookiesToBrowser(signUpApi.getCookies());
        checkoutPage.load();
        checkoutPage.enterBillingAddress(billingAddress).
                selectDirectBankTransfer().
                placeOrder();
        Assert.assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");
    }

    @Test(enabled = false, groups={"TODO"})
    public void guestCheckoutUsingCashOnDelivery(){
        // TODO add test
    }

    @Test(enabled = false, groups={"TODO"})
    public void loginAndCheckoutUsingCashOnDelivery(){
        // TODO add test
    }
}
