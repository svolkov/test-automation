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

public class SearchGoodsByJsTestCase {
    private final static String SEARCH_RESULTS_MESSAGE_JS = "Знайдено 204 товарів";

    private WebDriver webdriver;
    private HomePage homePage;

    @BeforeMethod
    public void beforeClass(){
        webdriver = WebDriverBuilder.getForPredefinedBrowserWithImplicitWait( 3 );
        webdriver.get( ConfigPropertiesReader.getSite());
        homePage = new HomePage(webdriver);
        if(homePage.getActiveLanguage().equals( SiteLanguage.RU.getName())) {
            homePage.clickSwitchLanguage(SiteLanguage.UA);
        }
    }

    @AfterMethod
    public void afterMethod(){
        if(webdriver != null){
            webdriver.quit();
        }
    }

    @Test
    public void testGetActiveLanguageByJS(){
        Assert.assertEquals(homePage.getActiveLanguageByJS(), "UA", "Active site language is wrong.");
    }

    @Test(groups = {"ProductsSearching"})
    public void testSearchGoodsByJS(){
        SearchResultsPage resultsPage = homePage.searchGoodsByNameJS( "sony playstation4");
        Assert.assertEquals( resultsPage.getSearchResultsMessage(), SEARCH_RESULTS_MESSAGE_JS,
                             "Wrong Search Results message");
    }
}
