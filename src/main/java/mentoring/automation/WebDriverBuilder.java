package mentoring.automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class WebDriverBuilder {
    private static final String WEBDRIVER_PATH =  "C:\\Users\\sergey.volkov\\Projects\\webdrivers\\";

    public static WebDriver getFireFoxDriver(){
        System.setProperty( "webdriver.gecko.driver", WEBDRIVER_PATH + "geckodriver.exe" );
        FirefoxOptions options = new FirefoxOptions(  );
        options.setCapability( "marionette", false );
        return new FirefoxDriver( options );
    }

    public static WebDriver getChromeDriver(){
        System.setProperty("webdriver.chrome.driver", WEBDRIVER_PATH + "chromedriver.exe");
        return new ChromeDriver();
    }

    public static WebDriver getIEDriver(){
        System.setProperty("webdriver.ie.driver", WEBDRIVER_PATH + "IEDriverServer.exe");
        return new InternetExplorerDriver();
    }
}
