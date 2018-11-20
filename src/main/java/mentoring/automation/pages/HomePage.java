package mentoring.automation.pages;

import mentoring.automation.constants.SiteLanguage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private static final Logger logger = LogManager.getLogger( HomePage.class );
    private static final String PAGE_TITLE_FRAGMENT = "ROZETKA";
    private static final String EXCEPTION_MESSAGE = "Webdriver does not point to the HomePage, current location is ";
    private static final String SITE_LANGUAGE_CLASSNAME = "lang-switcher-link active";
    private static final String SEARCH_FIELD_CLASSNAME = "rz-header-search-input-text passive";
    private static final String SUBMIT_SEARCH_CLASSNAME = "btn-link-i js-rz-search-button";

    private WebDriver driver;
    private JavascriptExecutor js;
    private By searchField = By.xpath("//input[@class=\"" + SEARCH_FIELD_CLASSNAME + "\"]");
    private By submitButton = By.xpath("//button[@class=\"" + SUBMIT_SEARCH_CLASSNAME + "\"]");
    private By activeLanguage = By.xpath( "//span[@class=\"lang-switcher-link active\" or @class=\"header-topline__language-item_state_active\"]" );
    private By linkSwitchLanguageUA = By.linkText( "UA" );
    private By linkSwitchLanguageRU = By.linkText( "RU" );
    private By newcomersGreeting = By.className("auth-b-title");
    private By linkToContactsPage = By.xpath("//a[text()=\" Контакти \"]");

    public HomePage(WebDriver driver){
        this.driver = driver;
        if(!driver.getTitle().contains( PAGE_TITLE_FRAGMENT )){
            throw new IllegalArgumentException( EXCEPTION_MESSAGE + driver.getCurrentUrl() );
        }
        js = (JavascriptExecutor) driver;
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

    public String getActiveLanguage(){
        logger.info( "Get active language" );
        return driver.findElement(activeLanguage).getText();
    }

    public SearchResultsPage searchGoodsByNameJS( String goodsName ){
        typeSearchFieldByJS( goodsName ).clickSearchSubmitByJS();
        return new SearchResultsPage( driver );
    }

    public ContactsPage openContactsPage(){
        driver.findElement(linkToContactsPage).click();
        return new ContactsPage(driver);
    }

    private HomePage typeSearchField( String text ){
        logger.info( "Type '" + text + "' into Search field in the HomePage" );
        WebElement inputField = driver.findElement( searchField );
        inputField.sendKeys(text);
        return this;
    }

    private HomePage clickSearchSubmit(){
        logger.info( "Submit Search field in the HomePage" );
        WebElement button = driver.findElement( submitButton );
        button.click();
        return this;
    }

    private HomePage switchLanguage( By locator ){
        logger.info( "Switch language" );
        driver.findElement( locator ).click();
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(newcomersGreeting));
        return this;
    }

    public String getActiveLanguageByJS(){
        logger.info( "Get active language using JavaScript" );
        return (String) js.executeScript("return document.getElementsByClassName(arguments[0])[0].getText()",
                SITE_LANGUAGE_CLASSNAME);
    }

    private HomePage typeSearchFieldByJS( String text ){
        logger.info( "Type '" + text + "' into Search field in the HomePage using JavaScript" );
        js.executeScript("document.getElementsByClassName(arguments[0])[0].value = arguments[1]",
                SEARCH_FIELD_CLASSNAME, text);
        return this;
    }

    private HomePage clickSearchSubmitByJS(){
        logger.info( "Submit Search field in the HomePage using JavaScript" );
        js.executeScript( "document.getElementsByClassName(arguments[0])[0].click()", SUBMIT_SEARCH_CLASSNAME );
        return this;
    }
}
