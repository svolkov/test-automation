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
    private By cookiesPolicyPopupTitleLocator = By.id("onetrust-policy-title");
    private By cookiesPolicyPopupOkButtonLocator = By.id("onetrust-accept-btn-handler");
    private By downloadButtonLocator = By.id("GTM__link--CC-comparisonDownloadBtn");

    public CleanerHomePage(WebDriver driver) {
        this.driver = driver;
        acceptCookiesPolicy();
    }

    public CleanerDownloadPage openDownloadPage() {
        logger.info("Open Download page");
        WebElement downloadBtn = driver.findElement(downloadButtonLocator);
        new Actions(driver)
                .scrollToElement(downloadBtn)
                .perform();
        downloadBtn.click();
        return new CleanerDownloadPage(driver);
    }

    private CleanerHomePage acceptCookiesPolicy() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(cookiesPolicyPopupTitleLocator));
        } catch (TimeoutException ex) {
            return this;
        }
        driver.findElement(cookiesPolicyPopupOkButtonLocator).click();
        return this;
    }
}
