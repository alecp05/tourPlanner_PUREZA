package dataaccesslayer.configuration;

import lombok.Getter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class configurationHandler {
    @Getter
    String url = "";
    @Getter
    String user = "";
    @Getter
    String password = "";

    InputStream inputStream;
    public configurationHandler() throws IOException {
        try {
            Properties prop = new Properties();
            String configPath = "./config.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(configPath);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("Config File '" + configPath + "' not found.");
            }

            // get the property value and print it out
             this.url = prop.getProperty("url");
             this.user = prop.getProperty("user");
             this.password = prop.getProperty("password");

            //System.out.println(url + " "+ user + " " + password);

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
    }
}
