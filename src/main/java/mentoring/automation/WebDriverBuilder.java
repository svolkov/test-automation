package mentoring.automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverBuilder {
    public static WebDriver getFireFoxDriver(){
        System.setProperty( "webdriver.gecko.driver", "C:\\Users\\sergey.volkov\\Projects\\FireFoxDriver\\geckodriver.exe" );
        FirefoxOptions options = new FirefoxOptions(  );
        options.setCapability( "marionette", false );
        return new FirefoxDriver( options );
    }

    public static WebDriver getChromeDriver(){
        System.setProperty("webdriver.chrome.driver",
                            "/home/sergey/projects/webdrivers/for_chrome/chromedriver");
        return new ChromeDriver();
    }
}
