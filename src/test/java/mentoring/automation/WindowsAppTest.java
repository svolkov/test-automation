package mentoring.automation;

import mentoring.automation.helpers.WebDriverBuilder;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class WindowsAppTest
{
    @Test
    public void testEdgeDriver()
    {
        WebDriver driver = WebDriverBuilder.getEdgeDriver();
        driver.get( "https://www.ccleaner.com/" );
        driver.quit();
    }
}
