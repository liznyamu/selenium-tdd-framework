package org.selenium;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MyFirstTestCase extends BaseTest {

    @Test
    public void guestCheckoutUsingDirectBankTransfer() throws InterruptedException {
        StorePage storePage = new HomePage(driver).
                load().
                goToStoreUsingMenu().
                search("Blue");
        Assert.assertEquals(storePage.getTitle(), "Search results: “Blue”");


        // TODO add functional method to add product to cart (clickAddToCartBtn,clickViewCartLnk)  after handling synchronization
        storePage.clickAddToCartBtn("Blue Shoes");
        Thread.sleep(5000);
        CartPage cartPage = storePage.clickViewCartLnk();
        Assert.assertEquals(cartPage.getProductName(),"Blue Shoes");

        CheckoutPage checkoutPage = cartPage.checkout();
        checkoutPage.
                enterFirstName("Agatha").
                enterLastName("Harkness").
                enterAddressOne("Salem").
                enterCity("Massachusetts").
                enterPostCode("94188").
                enterEmail("agatha.harkness@gmail.com");

        Thread.sleep(5000);
                checkoutPage.placeOrder();

        Thread.sleep(5000);
        Assert.assertEquals(checkoutPage.getNotice(),"Thank you. Your order has been received.");
    }


    @Test
    public void loginAndCheckoutUsingDirectBankTransfer() throws InterruptedException {
        StorePage storePage = new HomePage(driver).
                load().
                goToStoreUsingMenu().
                search("Blue");
        Assert.assertEquals(storePage.getTitle(), "Search results: “Blue”");

        // Click add to cart
        storePage.clickAddToCartBtn("Blue Shoes");
        Thread.sleep(5000);
        CartPage cartPage = storePage.clickViewCartLnk();
        Assert.assertEquals(cartPage.getProductName(),"Blue Shoes");

        CheckoutPage checkoutPage = cartPage.checkout();
        checkoutPage.clickHereToLogin();

        Thread.sleep(5000);
        checkoutPage.login("agatha", "Test@1Test");

        checkoutPage.
                enterFirstName("Agatha").
                enterLastName("Harkness").
                enterAddressOne("Salem").
                enterCity("Massachusetts").
                enterPostCode("94188").
                enterEmail("agatha.harkness@gmail.com");

        Thread.sleep(5000);
        checkoutPage.placeOrder();

        Thread.sleep(5000);
        Assert.assertEquals(checkoutPage.getNotice(),"Thank you. Your order has been received.");
    }
}
