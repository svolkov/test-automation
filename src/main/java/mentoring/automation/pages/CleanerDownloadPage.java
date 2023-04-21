package mentoring.automation.pages;

import mentoring.automation.utils.SysUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CleanerDownloadPage {
    private static final Logger logger = LogManager.getLogger(CleanerDownloadPage.class);

    private WebDriver driver;
    private By freeDownloadButtonLocator = By.id("GTM__link--CC-piriform_com");

    public CleanerDownloadPage(WebDriver driver) {
        this.driver = driver;
    }

    public CleanerDownloadPage clickFreeDownload() {
        logger.info("Open Free download page");
        WebElement downloadBtn = driver.findElement(freeDownloadButtonLocator);
        new Actions(driver)
                .scrollToElement(downloadBtn)
                .perform();
//        downloadBtn.click();
        SysUtils.executeCommand();
        return this;
    }
}
