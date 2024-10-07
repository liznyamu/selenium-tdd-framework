package org.selenium.pom.dataproviders;

import org.selenium.pom.objects.Product;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class MyDataProvider {

    @DataProvider(name = "getFeaturedProducts", parallel = true)
    public Product[] getFeaturedProducts() throws IOException {
        // extract the Products array
        return JacksonUtils.deserializeJson("products.json", Product[].class);

        //TODO  (`*hint*` after adding the  featured:true/false attribute on Product POJO
        //  loop through the list and filter out only those products marked as `featured: true`

    }
}
