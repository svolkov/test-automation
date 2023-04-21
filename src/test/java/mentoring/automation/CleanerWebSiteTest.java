package mentoring.automation;

import mentoring.automation.helpers.ConfigPropertiesReader;
import mentoring.automation.helpers.Screenshoter;
import mentoring.automation.helpers.WebDriverBuilder;
import mentoring.automation.pages.CleanerDownloadPage;
import mentoring.automation.pages.CleanerHomePage;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CleanerWebSiteTest {
    private final static Screenshoter screenshoter = new Screenshoter();
    private WebDriver webdriver;
    private CleanerHomePage cleanerHomePage;

    @BeforeMethod()
    public void beforeClass() {
        webdriver = WebDriverBuilder.getForPredefinedBrowser();
        webdriver.get(ConfigPropertiesReader.getSite());
        cleanerHomePage = new CleanerHomePage(webdriver);
    }

    @Test
    public void testCleanerHomePage() {
        CleanerDownloadPage downloadPage = cleanerHomePage.openDownloadPage();
        downloadPage.clickFreeDownload();
    }

    @AfterMethod(groups = {"searching", "negative"})
    public void afterMethod(ITestResult results) {
        if (webdriver != null) {
            if (results.getStatus() == ITestResult.FAILURE) {
                screenshoter.takeScreenshot(webdriver, results.getName());
            }
            webdriver.quit();
        }
    }
}
