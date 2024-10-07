package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.dataproviders.MyDataProvider;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddToCartTest extends BaseTest {

    @Test
    public void addToCartFromStorePage() throws IOException {
        Product product = new Product(1215);
        CartPage cartPage = new StorePage(getDriver()).load()
                .getProductThumbnail()
                .addToCart(product.getName());
        Assert.assertEquals(cartPage.getProductName(), product.getName());
    }

    @Test(groups = {"TODO"},
            dataProvider = "getFeaturedProducts", dataProviderClass = MyDataProvider.class)
    public void addFeaturedProductToCart(Product product) {
        //TODO  (`*hint*` add featured:true/false attribute on Product POJO)

        CartPage cartPage = new HomePage(getDriver()).
                load().
                getProductThumbnail().
                addToCart(product.getName());
        Assert.assertEquals(cartPage.getProductName(), product.getName());

    }

    @Test(enabled = false, groups = {"TODO"})
    public void addToCartFromProductPage() {

    }

}
