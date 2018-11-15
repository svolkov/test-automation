package mentoring.automation;

import mentoring.automation.helpers.ConfigPropertiesReader;
import mentoring.automation.helpers.WebDriverBuilder;
import mentoring.automation.pages.HomePage;
import mentoring.automation.pages.SearchResultsPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchGoodsTestCase {
    private WebDriver webdriver;
    private HomePage homePage;

    @BeforeMethod
    public void beforeClass(){
        webdriver = WebDriverBuilder.getForPredefinedBrowser();
        webdriver.get(ConfigPropertiesReader.getSite());
        homePage = new HomePage(webdriver);
    }

    @Test
    public void testFoundGoodsNumber(){
        SearchResultsPage resultsPage = homePage.searchGoodsByName("sony playstation");
        Assert.assertEquals( resultsPage.getFoundGoodsNumber(), 260, "Wrong number of goods found by Search");
    }

    @Test
    public void testUnsuccessfulSearch(){
        SearchResultsPage resultsPage = homePage.searchGoodsByName("abrakadabra");
        Assert.assertTrue(resultsPage.isNothingFound(), "Unsuccessful search message is not displayed");
    }

    @AfterMethod
    public void afterMethod(){
        if(webdriver != null){
            webdriver.quit();
        }
    }
}
