package utilities;

    import java.io.FileInputStream;
    import java.io.IOException;
    import java.util.Properties;

    /**
     * Utility class for reading configuration properties from a file.
     * <p>
     * Loads properties from the specified file when the class is first loaded.
     * Provides a static method to retrieve property values by key.
     */
    public class ConfigReader {
        /**
         * The loaded configuration properties.
         * <p>
         * This field is initialized once in the static block and is immutable.
         */
        final private static Properties properties;

        // Static block to load properties from the configuration file at class loading time.
        static {
            String filePath = "src/test/resources/properties/cura.properties";
            properties = new Properties();
            try (FileInputStream input = new FileInputStream(filePath)) {
                properties.load(input);
            } catch (IOException e) {
                // Throws a runtime exception if the properties file cannot be loaded.
                throw new RuntimeException("Failed to load properties file: " + filePath, e);
            }
        }

        /**
         * Retrieves the value of the specified property key.
         *
         * @param key the property key to look up
         * @return the value associated with the key, or null if not found
         */
        public static String getProperty(String key) {
            return properties.getProperty(key);
        }
    }