package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    @Test
    public void searchWithPartialMatch(){
        String searchTxt = "Blue";
        StorePage storePage = new StorePage(getDriver()).
                load().
                search(searchTxt);

        Assert.assertEquals(storePage.getTitle(), "Search results: “" + searchTxt + "”");
    }

    @Test(enabled = false, groups={"TODO"})
    public void searchWithExactMatch(){
        // TODO add test
    }

    @Test(enabled = false, groups={"TODO"})
    public void searchNonExistingProduct(){
        // TODO add test
    }
}
