package mentoring.automation.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigPropertiesReader {
    private final static Logger logger = LogManager.getLogger( ConfigPropertiesReader.class.getName() );
    private final static String PROPS_FILE = "/config.properties";

    public static Properties getProperties() throws IOException {
        logger.info("Reading properties from the file " + PROPS_FILE);
        Properties props = new Properties(  );
        InputStream is;

        if ((is = ConfigPropertiesReader.class.getResourceAsStream(PROPS_FILE)) == null) {
            logger.error("Error has happened while locating the file " + PROPS_FILE);
            throw new FileNotFoundException();
        }
        try {
            props.load(is);
        } catch (IOException ex) {
            logger.error("Error has happened while getting properties from " + PROPS_FILE);
            logger.error(ex.getMessage());
            throw ex;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return props;
    }

}
