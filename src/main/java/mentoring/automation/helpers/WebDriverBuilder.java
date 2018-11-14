package mentoring.automation.helpers;

import mentoring.automation.constants.Browsers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class WebDriverBuilder {
    private final static Logger logger = LogManager.getLogger( WebDriverBuilder.class.getName() );
    private final static String WEBDRIVER_FOLDER = "webdrivers";
    private final static String WEBDRIVER_PATH = WebDriverBuilder.class.getClassLoader()
                                                .getResource(WEBDRIVER_FOLDER).getPath();
    private static String executableFileExtension = "";

    static{
        if(System.getProperty("os.name").toLowerCase().contains("win")){
            executableFileExtension = ".exe";
        }
    }

    public static WebDriver getFireFoxDriver(){
        logger.info("Creating webdriver for FireFox");
        System.setProperty( "webdriver.gecko.driver", WEBDRIVER_PATH + "/geckodriver.exe" );
        FirefoxOptions options = new FirefoxOptions(  );
        options.setCapability( "marionette", false );
        return new FirefoxDriver( options );
    }

    public static WebDriver getChromeDriver(){
        logger.info("Creating webdriver for Chrome");
        System.setProperty("webdriver.chrome.driver", WEBDRIVER_PATH + "/chromedriver" + executableFileExtension);
//        WebDriver webDriver = new ChromeDriver();
//        webDriver.manage().window().maximize();
//        return webDriver;
        return new ChromeDriver();
    }

    public static WebDriver getIEDriver(){
        logger.info("Creating webdriver for InternetExplorer");
        System.setProperty("webdriver.ie.driver", WEBDRIVER_PATH + "/IEDriverServer.exe");
        return new InternetExplorerDriver();
    }

    public static WebDriver getForPredefinedBrowser() {
        WebDriver driver;
        String browserName = ConfigPropertiesReader.getBrowser();
        if(browserName.equals(Browsers.CHROME.getName())){
            driver = getChromeDriver();
        }else if(browserName.equals(Browsers.FIREFOX.getName())){
                    driver = getFireFoxDriver();
        }else if(browserName.equals(Browsers.INTERNET_EXPLORER.getName())){
                    driver = getIEDriver();
        }else {
            logger.error("Check 'config.properties': undefined browser was found.");
            throw new IllegalArgumentException();
        }
        driver.manage().window().maximize();
        return driver;
    }
}
