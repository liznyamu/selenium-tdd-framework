package org.selenium.pom.api.actions;

import org.selenium.pom.objects.User;
import org.selenium.pom.utils.FakerUtils;

/**
 * This is just a Dummy class to validate the API responses
 */
public class Dummy {
    public static void main(String[] args) {
        // change access modifiers to protected / public to test


       /* // Parse HTML response
        System.out.println(new SignUpApi().fetchRegisterNonceValueUsingGroovy());
        System.out.println(new SignUpApi().fetchRegisterNonceValueUsingJsoup());
*/

/*
        // Register new user
       String username = "agatha_harkness" + new FakerUtils().generateRandomNumber();
        User user = new User().
                setUsername(username).
                setPassword("test@1Test").
                setEmail(username + "@salem.com");

        SignUpApi signUpApi = new SignUpApi();
        System.out.println(signUpApi.register(user));
        System.out.println(signUpApi.getCookies());*/


      /*  // Customer not logged in / not registered
        CartApi cartApi = new CartApi();
        cartApi.addToCart(1215, 1);
        System.out.println(cartApi.getCookies());*/


        // Customer is logged in / is registered
        String username = "agatha_harkness" + new FakerUtils().generateRandomNumber();
        User user = new User().
                setUsername(username).
                setPassword("test@1Test").
                setEmail(username + "@salem.com");

        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);
        System.out.println("REGISTER API COOKIES: " + signUpApi.getCookies());

        CartApi cartApi = new CartApi(signUpApi.getCookies());
        cartApi.addToCart(1215, 1);
        System.out.println("CART API COOKIES: " + cartApi.getCookies());

    }
}
