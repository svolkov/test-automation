package mentoring.automation;

import mentoring.automation.constants.SiteLanguage;
import mentoring.automation.helpers.ConfigPropertiesReader;
import mentoring.automation.helpers.WebDriverBuilder;
import mentoring.automation.listeners.ReportListener;
import mentoring.automation.listeners.TestListener;
import mentoring.automation.pages.ContactsPage;
import mentoring.automation.pages.HomePage;
import mentoring.automation.pages.ProductCatalogPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ TestListener.class, ReportListener.class })
public class ContactsPageTestCase {
    private WebDriver webdriver;
    private HomePage homePage;

    @BeforeMethod(groups = {"ProductSelection"})
    public void initWebDriver(){
        webdriver = WebDriverBuilder.getForPredefinedBrowser();
        webdriver.get(ConfigPropertiesReader.getSite());
    }

    @BeforeMethod(dependsOnMethods = {"initWebDriver"}, groups = {"ProductSelection"})
    public void initPage(){
        homePage = new HomePage(webdriver);
        if(homePage.getActiveLanguage().equals(SiteLanguage.RU.getName())) {
            homePage.clickSwitchLanguage(SiteLanguage.UA);
        }
    }

    @Test
    public void testCheckSecondKyivPhone(){
        ContactsPage contactsPage = homePage.openContactsPage();
        Assert.assertEquals(contactsPage.getSecondKyivPhone(), "(044) 503-80-80", "Wrong second Kyiv phone number.");
    }

    @Test(groups = {"ProductSelection"})
    public void testSelectProductsInCatalogue(){
        ContactsPage contactsPage = homePage.openContactsPage();
        ProductCatalogPage productsPage = contactsPage.openSmartphonesCatalogue();
        Assert.assertEquals( productsPage.getProductGroupName(), "Смартфони", "Product catalog page has wrong name.");
    }

    @AfterMethod(groups = {"ProductSelection"})
    public void afterMethod(){
        if(webdriver != null){
            webdriver.quit();
        }
    }


}
