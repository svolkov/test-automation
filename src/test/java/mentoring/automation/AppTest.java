package mentoring.automation;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class AppTest
{
    @Test
    public void testFireFoxDriver()
    {
        WebDriver driver = WebDriverBuilder.getFireFoxDriver();
        driver.get( "https://testng.org" );
        driver.quit();
    }

    @Test
    public void testChromeDriver(){
        WebDriver driver = WebDriverBuilder.getChromeDriver();
        driver.get( "https://testng.org" );
        driver.quit();
    }

    @Test
    public void testInternetExplorerDriver(){
        WebDriver driver = WebDriverBuilder.getIEDriver();
        driver.get( "https://testng.org" );
        driver.quit();
    }
}
