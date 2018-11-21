package mentoring.automation.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactsPage {
    private static final Logger logger = LogManager.getLogger( ContactsPage.class );
    private static final String PAGE_TITLE = "Контактна інформація";
    private static final String EXCEPTION_MESSAGE = "Webdriver does not point to the HomePage, current location is ";
    private WebDriver driver;

    @FindBy(tagName = "title")
    WebElement contactsPageTitle;

    @FindBy(xpath = "//li[@class='c-i-service-l-i'][2]")
    WebElement secondKyivPhone;

    public ContactsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        new WebDriverWait( driver, 5).until( ExpectedConditions.titleIs( PAGE_TITLE ) );
    }

    public String getSecondKyivPhone(){
        logger.info( "Get second Kyiv phone number" );
        return secondKyivPhone.getText();
    }
}
