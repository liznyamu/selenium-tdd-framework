package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Now we can add all tests related to navigation on this class AND reduce code duplication on tests
 */
public class NavigationTest extends BaseTest {
    @Test
    public  void navigateFromHomeToStoreUsingMainMenu(){
        StorePage storePage = new HomePage(getDriver()).
                load().
                goToStoreUsingMenu();
        Assert.assertEquals(storePage.getTitle(), "Store");
    }

    @Test(enabled = false, groups={"TODO"})
    public  void navigateFromStoreToProductPage(){
        // TODO add test
    }

    @Test(enabled = false, groups={"TODO"})
    public  void navigateFromHomeToFeaturedProductPage(){
        // TODO add test
    }

}
