package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    public static Properties getProperties() {
        Properties properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream(
                "src/test/resources/config/config.properties");
            properties.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }
}