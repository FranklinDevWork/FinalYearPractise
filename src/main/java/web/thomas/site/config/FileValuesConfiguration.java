package web.thomas.site.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileValuesConfiguration {

  private static final Logger logger = LoggerFactory.getLogger(FileValuesConfiguration.class);

  public static Properties properties = new Properties();

  public void setValuesFromConfigurationFile() {

    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("configuration.txt");

    if (inputStream != null) {

      try {
        properties.load(inputStream);
      } catch (IOException e) {
        logger.error("Issue reading properties from configuration file");
      }
    }
  }
}
