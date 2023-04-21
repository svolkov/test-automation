package mentoring.automation.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CleanerHomePage {
    private static final Logger logger = LogManager.getLogger(CleanerHomePage.class);

    private WebDriver driver;
    private JavascriptExecutor js;
    private By logoLocator = By.id("GTM__header__ccleaner-logo");
    private By cookiesPolicyPopupTitleLocator = By.id("onetrust-policy-title");
    private By cookiesPolicyPopupOkButtonLocator = By.id("onetrust-accept-btn-handler");
    private By downloadButtonLocator = By.id("GTM__link--CC-comparisonDownloadBtn");
    private By freeDownloadButtonLocator = By.xpath("//*[@href='/ccleaner/download/standard']");

    public CleanerHomePage(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        acceptCookiesPolicy();
        waitPageLoaded();
    }

    public CleanerDownloadPage openDownloadPage() {
        logger.info("Open Download page");
        WebElement downloadBtn = driver.findElement(downloadButtonLocator);
        scrollTillElement(downloadBtn);
        downloadBtn.click();
        return new CleanerDownloadPage(driver);
    }

    private CleanerHomePage acceptCookiesPolicy() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(cookiesPolicyPopupTitleLocator));
        } catch (TimeoutException ex) {
            return this;
        }
        driver.findElement(cookiesPolicyPopupOkButtonLocator).click();
        return this;
    }

    private void scrollTillElement( WebElement element ){
        logger.info( "Scroll till element" );
        js.executeScript("arguments[0].scrollIntoView();",element);
    }

    private CleanerHomePage waitPageLoaded(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(logoLocator));
        } catch (TimeoutException ex) {
            throw new AssertionError("CCleaner Home page was NOT loaded");
        }
        return this;
    }
}
