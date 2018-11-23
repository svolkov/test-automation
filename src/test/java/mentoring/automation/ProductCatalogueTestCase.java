package mentoring.automation;

import mentoring.automation.constants.SiteLanguage;
import mentoring.automation.helpers.ConfigPropertiesReader;
import mentoring.automation.helpers.WebDriverBuilder;
import mentoring.automation.pages.HomePage;
import mentoring.automation.pages.ProductCatalogPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ProductCatalogueTestCase {
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

    @DataProvider(name = "products")
    public Object[][] createTestData(){
        return new Object[][]{
                {"Інструменти та автотовари", "Шини", "Автошини"},
                {"Спорт і захоплення", "Човни й аксесуари", "Човни та аксесуари"}
        };
    }

    @Test(dataProvider = "products")
    public void testChooseProductsGroupInCatalogue(String subCatalogueName, String productGroupName, String productGroupPageName){
        ProductCatalogPage productsPage = homePage.selectProductsGroupInCatalogue(subCatalogueName, productGroupName);
        Assert.assertEquals(productsPage.getProductGroupName(), productGroupPageName, "Product catalog page has wrong name.");
    }
/*
    @Test
    public void testChooseProductsGroupInCatalogue(){
        ProductCatalogPage productsPage = homePage.selectProductsGroupInCatalogue("Інструменти та автотовари", "Шини");
        Assert.assertEquals(productsPage.getProductGroupName(), "Автошини", "\"Product catalog page has wrong name.\"");
    }*/

    @AfterMethod
    public void afterMethod(){
        if(webdriver != null){
            webdriver.quit();
        }
    }

}
