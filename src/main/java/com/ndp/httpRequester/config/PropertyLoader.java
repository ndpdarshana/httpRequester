package com.ndp.httpRequester.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by darshana on 4/19/16.
 */
public class PropertyLoader {
    private static Properties properties;
    private static InputStream input;

    private PropertyLoader(){
        loadProperty();
    }

    private static class PropertyLoaderHolder{
        private static final PropertyLoader INSTANCE = new PropertyLoader();
    }

    public static PropertyLoader getInstace(){
        return PropertyLoaderHolder.INSTANCE;
    }

    private static void loadProperty(){
        try {
            properties = new Properties();
            input = PropertyLoader.class.getClassLoader().getResourceAsStream("application.properties");
            properties.load(input);
        }catch(IOException ioe){
            System.err.println(ioe.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Properties getProperties() {
        return properties;
    }

    public String getProperty(String key){
        return properties.getProperty(key);
    }

    public Object getPropertyObject(Object key){
        return properties.get(key);
    }

}
