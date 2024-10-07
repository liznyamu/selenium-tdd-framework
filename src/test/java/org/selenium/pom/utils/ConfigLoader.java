package org.selenium.pom.utils;

import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader;

    /**
     * Singleton Config loader to set the global configs
     *  by initializing/ loading them once, sharing them across the framework
     */
    private ConfigLoader(){
        properties = PropertyUtils.propertyLoader("src/test/resources/config.properties");
    }

    /**
     * Ensures there's only one instance of the ConfigLoader class throughout the program
     *
     * @return ConfigLoader
     */
    public static ConfigLoader getInstance(){
        if(configLoader == null){
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    /**
     * Get config.properties - baseUrl
     *  if property is not found abort test execution
     *
     * @return String : baseUrl
     */
    public String getBaseUrl(){
        String prop  = properties.getProperty("baseUrl");
        if(prop !=null) {
            return prop;
        }
        else {
            throw new RuntimeException("property baseUrl is not specified in the config.properties file");
        }
    }

    /**
     * Get config.properties - username
     *  if property is not found abort test execution
     * @return String : baseUrl
     */
    public String getUsername(){
        String prop  = properties.getProperty("username");
        if(prop !=null) {
            return prop;
        }
        else {
            throw new RuntimeException("property username is not specified in the config.properties file");
        }
    }

    /**
     * Get config.properties - password
     *  if property is not found abort test execution
     * @return String : baseUrl
     */
    public String getPassword(){
        String prop  = properties.getProperty("password");
        if(prop !=null) {
            return prop;
        }
        else {
            throw new RuntimeException("property password is not specified in the config.properties file");
        }
    }
}
