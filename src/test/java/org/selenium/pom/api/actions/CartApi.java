package org.selenium.pom.api.actions;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.selenium.pom.objects.User;
import org.selenium.pom.utils.ConfigLoader;

import java.util.HashMap;
import java.util.Objects;

import static io.restassured.RestAssured.given;

public class CartApi {

    private Cookies cookies;

    public CartApi(){}

    public CartApi(Cookies cookies){
        this.cookies = cookies;
    }

    public Cookies getCookies(){
        return cookies;
    }

    public Response addToCart(int productId, int quantity){
        Cookies emptyCookies = new Cookies();
        Header header = new Header("content-type", "application/x-www-form-urlencoded");
        Headers headers = new Headers(header);

        HashMap<String, Object> formParams = new HashMap<>();
        formParams.put("product_sku", "");
        formParams.put("product_id", productId);
        formParams.put("quantity", quantity);


        // check if cookies are set OR not (ie customer is logged-in OR not logged-in) -- see course notes
        if(cookies == null){
            cookies = new Cookies();
        }

        Response response = given().
                baseUri(ConfigLoader.getInstance().getBaseUrl()).
                headers(headers).
                formParams(formParams).
                cookies(emptyCookies).
//                log().all().
        when().
                post("/?wc-ajax=add_to_cart").
                then().
//                log().all().
        extract().
                response();

        if(response.getStatusCode() != 200){
            throw new RuntimeException("Failed to add product" + productId + " to the cart" +
                    ", HTTP Status Code: " + response.statusCode());
        }

        this.cookies = response.getDetailedCookies();
        return response;
    }
}
