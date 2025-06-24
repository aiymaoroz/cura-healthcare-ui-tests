package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Utility class for reading configuration properties from a file.
 * Loads properties from the specified file at class initialization and provides
 * a static method to retrieve property values by key.
 */
public class ConfigReader {
    final private static Properties properties;

    static {
        String filePath = "src/test/resources/properties/cura.properties";
        properties = new Properties();
        try (FileInputStream input = new FileInputStream(filePath)) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file: " + filePath, e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}