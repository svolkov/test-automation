package mentoring.automation.pages;

import mentoring.automation.constants.SiteLanguage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private static final Logger logger = LogManager.getLogger( HomePage.class );
    private static final String PAGE_TITLE_FRAGMENT = "ROZETKA";
    private static final String EXCEPTION_MESSAGE = "Webdriver does not point to the HomePage, current location is ";
    private WebDriver driver;
    private By searchField = By.xpath("//input[@class=\"rz-header-search-input-text passive\"]");
    private By submitButton = By.xpath("//button[@class=\"btn-link-i js-rz-search-button\"]");
    private By activeLanguage = By.xpath( "//span[@class=\"lang-switcher-link active\" or @class=\"header-topline__language-item_state_active\"]" );
    private By linkSwitchLanguageUA = By.linkText( "UA" );
    private By linkSwitchLanguageRU = By.linkText( "RU" );
    private By newcomersGreeting = By.className("auth-b-title");

    public HomePage(WebDriver driver){
        this.driver = driver;
        if(!driver.getTitle().contains( PAGE_TITLE_FRAGMENT )){
            throw new IllegalArgumentException( EXCEPTION_MESSAGE + driver.getCurrentUrl() );
        }
        if(getActiveLanguage().equals(SiteLanguage.RU.getName())) {
            clickSwitchLanguage(SiteLanguage.UA);
        }
    }

    public SearchResultsPage searchGoodsByName( String goodsName ){
        typeSearchField( goodsName ).clickSearchSubmit();
        return new SearchResultsPage( driver );
    }

    public HomePage clickSwitchLanguage( SiteLanguage language ){
        By linkLocator;
        if(language == SiteLanguage.UA){
            linkLocator = linkSwitchLanguageUA;
        }else{
            linkLocator = linkSwitchLanguageRU;
        }
        return switchLanguage( linkLocator );
    }

    private HomePage typeSearchField( String text ){
        logger.info( "Type '" + text + "' into Search field " );
        WebElement inputField = driver.findElement( searchField );
        inputField.sendKeys(text);
        return this;
    }

    private HomePage clickSearchSubmit(){
        logger.info( "Submit Search" );
        WebElement button = driver.findElement( submitButton );
        button.click();
        return this;
    }

    private String getActiveLanguage(){
        logger.info( "Get active language" );
        return driver.findElement(activeLanguage).getText();
    }

    private HomePage switchLanguage( By locator ){
        logger.info( "Switch language" );
        driver.findElement( locator ).click();
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(newcomersGreeting));
        return this;
    }
}
