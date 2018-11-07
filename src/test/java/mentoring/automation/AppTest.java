package mentoring.automation;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class AppTest
{
    @Test
    public void testFireFoxDriver()
    {
        WebDriver driver = WebDriverBuilder.getFireFoxDriver();
        assertNotNull( driver );
        driver.get( "https://testng.org" );
        driver.quit();
    }

    @Test
    public void testChromeDriver(){
        WebDriver driver = WebDriverBuilder.getChromeDriver();
        assertNotNull( driver );
        driver.get( "https://testng.org" );
        driver.quit();
    }
}
