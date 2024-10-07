package org.selenium.pom.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {

    /**
     * Note the method is static (a class method) AND does not use any instance variables - for parallel execution
     *
     * @param filePath - path .properties file
     * @return Properties
     */
    public static Properties propertyLoader(String filePath) {
        Properties properties = new Properties();


        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            properties.load(reader);
        } catch (IOException e) {
            throw new RuntimeException("properties file failed to load " + e);
        }

        return properties;
    }
}
