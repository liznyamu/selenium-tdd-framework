package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddToCartTest extends BaseTest {

    @Test
    public void addToCartFromStorePage() throws IOException {
        Product product = new Product(1215);
        CartPage cartPage= new StorePage(getDriver()).
                load().
                addToCart(product.getName());
        Assert.assertEquals(cartPage.getProductName(),product.getName());
    }

    @Test(enabled = false, groups={"TODO"})
    public void addFeaturedProductToCart(){
        // (`*hint*` add feature attribute on Product POJO)
    }

    @Test(enabled = false, groups={"TODO"})
    public void addToCartFromProductPage(){

    }

}
