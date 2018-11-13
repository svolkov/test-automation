package mentoring.automation.pages;

import mentoring.automation.constants.SiteLanguage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private static final String PAGE_TITLE_FRAGMENT = "ROZETKA";
    private static final String EXCEPTION_MESSAGE = "Webdriver does not point to the HomePage, current location is ";
    private WebDriver driver;
    private By searchField = By.xpath("//input[@class=\"rz-header-search-input-text passive\"]");
    private By submitButton = By.xpath("//button[@class=\"btn-link-i js-rz-search-button\"]");
    private By activeLanguage = By.className( "" );
    private By linkSwitchLanguageUA = By.linkText( "/ua/" );
    private By linkSwitchLanguageRU = By.linkText( "/ru/" );

    public HomePage(WebDriver driver){
        this.driver = driver;
        if(!driver.getTitle().contains( PAGE_TITLE_FRAGMENT )){
            throw new IllegalArgumentException( EXCEPTION_MESSAGE + driver.getCurrentUrl() );
        }
        clickSwitchLanguage( SiteLanguage.UA );
    }

    public SearchResultsPage searchGoodsByName(String goodsName){
        typeSearchField( goodsName ).clickSearchSubmit();
        return new SearchResultsPage(driver);
    }

    private HomePage typeSearchField(String text){
        WebElement inputField = driver.findElement(searchField);
        inputField.sendKeys(text);
        return this;
    }

    private HomePage clickSearchSubmit(){
        WebElement button = driver.findElement(submitButton);
        button.click();
        return this;
    }

    private HomePage clickSwitchLanguage(SiteLanguage language){
        By linkLocator;
        if(language == SiteLanguage.UA){
                linkLocator = linkSwitchLanguageUA;
        }else{
            linkLocator = linkSwitchLanguageRU;
        }

        if(isLinkToSwitchLanguageDisplayed( linkLocator )){
            WebElement link = driver.findElement( linkLocator );
            link.click();
        }
        return this;
    }

    private boolean isLinkToSwitchLanguageDisplayed( By locator ){
        boolean result = false;
        WebElement link = driver.findElement( locator );
        if(link.getText().equals( language.getName() )){
            result = true;
        }
        return result;
    }
}
