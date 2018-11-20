package mentoring.automation.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {
    private static final Logger logger = LogManager.getLogger( ContactsPage.class );
    private static final String PAGE_TITLE_FRAGMENT = "Контактна інформація";
    private static final String EXCEPTION_MESSAGE = "Webdriver does not point to the HomePage, current location is ";
    private WebDriver driver;

    @FindBy(tagName = "title")
    WebElement contactsPageTitle;

    public ContactsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        if(!contactsPageTitle.getText().equals( PAGE_TITLE_FRAGMENT )){
            throw new IllegalArgumentException( EXCEPTION_MESSAGE + driver.getCurrentUrl() );
        }
    }
}
