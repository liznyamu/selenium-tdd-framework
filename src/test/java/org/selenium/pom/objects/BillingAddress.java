package org.selenium.pom.objects;

public class BillingAddress {
    private String firstName;
    private String lastName;
    private String country;
    private String addressLineOne;
    private String city;
    private String state;
    private String postCode;
    private String email;

    public BillingAddress() {

    }

    public BillingAddress(String firstName, String lastName, String country, String addressLineOne, String city,
                          String state, String postCode, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.addressLineOne = addressLineOne;
        this.city = city;
        this.state = state;
        this.postCode = postCode;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public BillingAddress setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public BillingAddress setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getAddressLineOne() {
        return addressLineOne;
    }

    public BillingAddress setAddressLineOne(String addressLineOne) {
        this.addressLineOne = addressLineOne;
        return this;
    }

    public String getCity() {
        return city;
    }

    public BillingAddress setCity(String city) {
        this.city = city;
        return this;
    }

    public String getPostCode() {
        return postCode;
    }

    public BillingAddress setPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public BillingAddress setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public BillingAddress setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getState() {
        return state;
    }

    public BillingAddress setState(String state) {
        this.state = state;
        return this;
    }
}
