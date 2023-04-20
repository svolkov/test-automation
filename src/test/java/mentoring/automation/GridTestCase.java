package mentoring.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class GridTestCase {
    private final static String testUrl = "http://translate.google.com.ua";
    private final static String nodeUrl = "http://10.24.64.185:5566/wd/hub";//http://192.168.0.104:5566/wd/hub";
    private final static String testFileUploadUrl = "http://demo.guru99.com/test/upload/";
    private final static String filePath = "C:\\Users\\sergey.volkov\\Pictures\\ScreenshotGridConsole.png";//"""/home/sergey/Pictures/ScreenshotGridConsole.png";
    //private WebDriver driver;
    private RemoteWebDriver driver;
    private By chooseFileButton = By.id("uploadfile_0");
    private By termsButton = By.id("terms");
    private By submitButton = By.id("submitbutton");
    private By resultMessage = By.id("res");


    @BeforeMethod
    public void beforeMethod(){
        /*Start Hub: java -jar selenium-server-standalone-3.141.59.jar -role hub
          Start Node: java -Dwebdriver.gecko.driver="/home/sergey/projects/webdrivers/geckodriver" -jar selenium-server-standalone-3.141.59.jar -role webdriver -hub http://192.168.0.104:4444/grid/register -port 5566*/

        DesiredCapabilities capabilities = new DesiredCapabilities();
        //capabilities.setPlatform(Platform.LINUX);
        capabilities.setPlatform(Platform.WINDOWS);
        capabilities.setBrowserName("firefox");
        capabilities.setVersion("64.0");
        try {
            driver = new RemoteWebDriver(new URL(nodeUrl), capabilities);
        }catch (MalformedURLException ex){
            assert false : ex.getMessage();
        }
        driver.setFileDetector( new LocalFileDetector() );
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
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.titleIs("Google Перекладач"));
    }

    @Test
    public void testGridFileUpload(){
        driver.get(testFileUploadUrl);
        driver.findElement(chooseFileButton).sendKeys(filePath);
        driver.findElement(termsButton).click();
        driver.findElement(submitButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBePresentInElementLocated(resultMessage, "has been successfully uploaded."));
    }
}
