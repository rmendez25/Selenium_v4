package com.framework.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private final Properties properties;

    public ConfigReader() {
        properties = new Properties();

        try{
            InputStream input = new FileInputStream("src/test/resources/config.properties");
            properties.load(input);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPropertyValue(String key) {
        return properties.getProperty(key);
    }


}
