package mentoring.automation;

import mentoring.automation.constants.SiteLanguage;
import mentoring.automation.helpers.ConfigPropertiesReader;
import mentoring.automation.helpers.WebDriverBuilder;
import mentoring.automation.pages.HomePage;
import mentoring.automation.pages.SearchResultsPage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class SearchGoodsTestCase {
    private final static String SCREENSHOT_FOLDER = "screenshots";
    private final static String SCREENSHOT_PATH = WebDriverBuilder.class.getClassLoader()
            .getResource(SCREENSHOT_FOLDER).getPath();
    private final static String SEARCH_RESULTS_MESSAGE = "Знайдено 260 товарів";

    private WebDriver webdriver;
    private HomePage homePage;

    @BeforeMethod
    public void beforeClass(){
        webdriver = WebDriverBuilder.getForPredefinedBrowser();
        webdriver.get(ConfigPropertiesReader.getSite());
        homePage = new HomePage(webdriver);
        if(homePage.getActiveLanguage().equals(SiteLanguage.RU.getName())) {
            homePage.clickSwitchLanguage(SiteLanguage.UA);
        }
    }

    @Test
    public void testFoundGoodsNumber(){
        SearchResultsPage resultsPage = homePage.searchGoodsByName("sony playstation");
        Assert.assertEquals( resultsPage.getSearchResultsMessage(), SEARCH_RESULTS_MESSAGE,
                        "Wrong Search Results message");
    }

    @Test
    public void testUnsuccessfulSearch(){
        SearchResultsPage resultsPage = homePage.searchGoodsByName("abrakadabra");
        Assert.assertTrue(resultsPage.isNothingFoundMessageDisplayed(), "Unsuccessful search message is not displayed");
    }

    @AfterMethod
    public void afterMethod(ITestResult results){
        if(webdriver != null){
            if(results.getStatus() == ITestResult.FAILURE){
                takeScreenshot(results.getName());
            }
            webdriver.quit();
        }
    }

    private void takeScreenshot(String fileName){
        TakesScreenshot screenshot = (TakesScreenshot) webdriver;
        File source = screenshot.getScreenshotAs(OutputType.FILE);
        File destination = new File(SCREENSHOT_PATH);
        try {
            Files.copy(source.toPath(), destination.toPath(), REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
