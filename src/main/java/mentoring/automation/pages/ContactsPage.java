package mentoring.automation.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.TemporalUnit;

public class ContactsPage {
    private static final Logger logger = LogManager.getLogger( ContactsPage.class );
    private static final String PAGE_TITLE = "Контактна інформація";
    private WebDriver driver;

    @FindBy(xpath = "//li[@class='c-i-service-l-i'][2]")
    WebElement secondKyivPhone;
    @FindBy(id="fat_menu_btn")
    WebElement catalog;
    @FindBy(id = "3361")
    WebElement smartphonesCatalog;
    @FindBy(xpath = "//a[@class=\"f-menu-sub-l-i-link blacklink\" and text()=\" Смартфони \"]")
    WebElement smartphonesSubcatalog;

    public ContactsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        new WebDriverWait( driver, Duration.ofSeconds(5)).until( ExpectedConditions.titleIs( PAGE_TITLE ) );
    }

    public String getSecondKyivPhone(){
        logger.info( "Get second Kyiv phone number" );
        return secondKyivPhone.getText();
    }

    public ProductCatalogPage openSmartphonesCatalogue(){
        Actions actionBuilder = new Actions( driver);
        Action openCataloguePage = actionBuilder.moveToElement( catalog)
                                            .pause( 500 )
                                            .moveToElement(smartphonesCatalog)
                                            .pause( 500 )
                                            .moveToElement(smartphonesSubcatalog)
                                            .click()
                                            .build();
        openCataloguePage.perform();
        return new ProductCatalogPage( driver );
    }

}
