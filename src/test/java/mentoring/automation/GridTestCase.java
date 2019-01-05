package mentoring.automation;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class GridTestCase {
    private final static String testUrl = "http://translate.google.com.ua";
    private final static String nodeUrl = "http://192.168.0.104:5566/wd/hub";
    private WebDriver driver;

    @BeforeMethod
    public void beforeClassMethod(){
        /*Start Hub: java -jar selenium--standalone-3.141.59.jar -role hub
          Start Node: java -Dwebdriver.gecko.driver="/home/sergey/projects/webdrivers/geckodriver" -jar selenium-server-standalone-3.141.59.jar -role webdriver -hub http://192.168.0.104:4444/grid/register -port 5566*/

        DesiredCapabilities capabilities = DesiredCapabilities.firefox();//.chrome();
        capabilities.setPlatform(Platform.LINUX);
        capabilities.setBrowserName("firefox");
        capabilities.setVersion("64.0");
        try {
            driver = new RemoteWebDriver(new URL(nodeUrl), capabilities);
        }catch (MalformedURLException ex){
            assert false : ex.getMessage();
        }

    }

    @AfterMethod
    public void afterMethod(){
        if(driver != null){
            driver.quit();
        }
    }

    @Test
    public void testGrid(){
        driver.get(testUrl);
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.titleIs("Google Перекладач"));
    }
}
