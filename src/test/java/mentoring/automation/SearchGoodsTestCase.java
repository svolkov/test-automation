package mentoring.automation;

import mentoring.automation.constants.SiteLanguage;
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
    public void afterMethod(){
        if(webdriver != null){
            webdriver.quit();
        }
    }
}
