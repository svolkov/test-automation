package mentoring.automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


public class WebDriverBuilder {
    public static WebDriver getFireFoxWebDriver(){
        System.setProperty( "webdriver.gecko.driver", "C:\\Users\\sergey.volkov\\Projects\\FireFoxDriver\\geckodriver.exe" );
        FirefoxOptions options = new FirefoxOptions(  );
        options.setCapability( "marionette", false );

        return new FirefoxDriver( options );
    }
}
