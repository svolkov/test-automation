package mentoring.automation;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testFireFoxDriver()
    {
        WebDriver driver = WebDriverBuilder.getFireFoxWebDriver();
        assertNotNull( driver );
        driver.get( "https://testng.org" );
        driver.quit();
    }
}
