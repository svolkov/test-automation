package mentoring.automation.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Screenshoter {
    private final static Logger logger = LogManager.getLogger( Screenshoter.class.getName() );
    private final static Path TEST_RESOURCES_PATH = Paths.get( "src", "test", "resources", "screenshots");
    private TakesScreenshot screenshoter;

    public void takeScreenshot(WebDriver webdriver, String fileName){
        if(screenshoter == null) {
            screenshoter = (TakesScreenshot) webdriver;
        }
        File source = screenshoter.getScreenshotAs( OutputType.FILE);
        if(!Files.exists( TEST_RESOURCES_PATH )){
            try {
                Files.createDirectory( TEST_RESOURCES_PATH );
            } catch ( IOException e ) {
                logger.error( "Error while creating folder: " + TEST_RESOURCES_PATH.toString());
                e.printStackTrace();
            }
        }
        File destination = new File(TEST_RESOURCES_PATH.toString() + File.separator + fileName + ".jpg");
        try {
            Files.copy(source.toPath(), destination.toPath(), REPLACE_EXISTING);
        } catch (IOException e) {
            logger.error( "Error while moving screenshot file to the folder: " + TEST_RESOURCES_PATH.toString());
            e.printStackTrace();
        }
    }
}
