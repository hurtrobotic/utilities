package org.hurtrobotic.tess4J.utilities;

import java.util.Properties;

public class TesseractUtils {

    public static Properties loadPropertiesFromClasspath(String path) {
        try {
            Properties prop = new Properties();
            prop.load(Class.class.getResourceAsStream(path));
            return prop;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }	

}
