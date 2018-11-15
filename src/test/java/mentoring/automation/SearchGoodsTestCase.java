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

    @BeforeMethod
    public void beforeClass(){
        webdriver = WebDriverBuilder.getForPredefinedBrowser();
        webdriver.get(ConfigPropertiesReader.getSite());
    }

    @Test
    public void testFoundGoodsNumber(){
        HomePage homePage = new HomePage(webdriver);
        SearchResultsPage resultsPage = homePage.searchGoodsByName("sony playstation4");
        int number = resultsPage.getFoundGoodsNumber();
        //Assert.assertEquals(1, 1);
    }

    @AfterMethod
    public void afterMethod(){
        if(webdriver != null){
            webdriver.quit();
        }
    }
}
