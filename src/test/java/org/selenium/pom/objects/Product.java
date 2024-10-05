package org.selenium.pom.objects;

import org.selenium.pom.utils.JacksonUtils;

import java.io.IOException;

public class Product {
    private int id;
    private String name;

    /**
     * to avoid issues with deserialization, When dealing with Jackson - always create a default constructor
     */
    public Product(){}

    /**
     * Parameterized constructor : based on the id sent to constructor we need to set the Product{id and name}
     *      for the Product object so that we can use it for our test class
     *  We have an array of objects
     *      so First - we need to parse the JSON array - then extract the product from the json object based on the id
     *
     * @param id
     */
    public Product(int id) throws IOException {
        // extract the Products array
        Product[] products = JacksonUtils.deserialize("products.json", Product[].class);

        // extract our desired product
        for(Product product: products){
            if(product.getId() == id){
                this.id = id;
                this.name = product.name;
            }
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
