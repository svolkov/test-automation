package mentoring.automation.helpers;

import mentoring.automation.constants.Browsers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import static mentoring.automation.constants.Browsers.*;

public class WebDriverBuilder {
    private final static Logger logger = LogManager.getLogger( WebDriverBuilder.class.getName() );
    private final static String WEBDRIVER_FOLDER = "webdrivers";
    private final static String WEBDRIVER_PATH = WebDriverBuilder.class.getClassLoader()
                                                .getResource(WEBDRIVER_FOLDER).getPath();
    private static String executableFileExtension = "";

    static{
        if(System.getProperty("os.name").toLowerCase().contains("win")){
            executableFileExtension = ".exe";
            System.setProperty(INTERNET_EXPLORER.getSystemProperty(), WEBDRIVER_PATH + INTERNET_EXPLORER.getPath());
        }
        System.setProperty( FIREFOX.getSystemProperty(), WEBDRIVER_PATH + FIREFOX.getPath() + executableFileExtension );
        System.setProperty( CHROME.getSystemProperty(), WEBDRIVER_PATH + CHROME.getPath() + executableFileExtension );
    }

    public static WebDriver getFireFoxDriver(){
        logger.info("Creating webdriver for FireFox");
        FirefoxOptions options = new FirefoxOptions(  );
        options.setCapability( "marionette", false );
        return new FirefoxDriver( options );
    }

    public static WebDriver getChromeDriver(){
        logger.info("Creating webdriver for Chrome");
        return new ChromeDriver();
    }

    public static WebDriver getIEDriver(){
        if(executableFileExtension.isEmpty()){
            logger.error( "InternetExplorer is applicable only for OS Windows" );
            assert false;
        }
        logger.info("Creating webdriver for InternetExplorer");
        return new InternetExplorerDriver();
    }

    public static WebDriver getForPredefinedBrowser() {
        WebDriver driver = null;
        Browsers browser = Browsers.valueOf(ConfigPropertiesReader.getBrowser());
        switch (browser){
            case CHROME : driver = getChromeDriver();
                          break;
            case FIREFOX : driver = getFireFoxDriver();
                           break;
            case INTERNET_EXPLORER : driver = getIEDriver();
                                     break;
            default : assert false : "Browser is not supported! Check config.properties.";
        }
        driver.manage().window().maximize();
        return driver;
    }
}
