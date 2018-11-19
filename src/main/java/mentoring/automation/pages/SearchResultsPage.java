package mentoring.automation.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.util.Integers;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultsPage {
    private static final Logger logger = LogManager.getLogger( SearchResultsPage.class );
    private final static String PAGE_TITLE_FRAGMENT = "ROZETKA — Результати пошуку:";
    private static final String EXCEPTION_MESSAGE = "Webdriver does not point to the SearchResultsPage, current location is ";
    private static final String SEARCH_RESULTS_MESSAGE_CLASSNAME = "rz-search-result-qnty";

    private WebDriver driver;
    private By searchResultsMessage = By.className( "rz-search-result-qnty" );
    private By nothingFoundMessage = By.className( "search-result-title-nothing-text" );

    public SearchResultsPage(WebDriver driver){
        this.driver = driver;
        if(!driver.getTitle().contains( PAGE_TITLE_FRAGMENT )){
            throw new IllegalArgumentException( EXCEPTION_MESSAGE + driver.getCurrentUrl() );
        }
    }

    public String getSearchResultsMessage(){
        waitPresenceOfElement(searchResultsMessage, 10);
        return getElementSearchResultsMessage().getText();
    }

    public boolean isNothingFoundMessageDisplayed(){
        return waitPresenceOfElement(nothingFoundMessage, 10).isDisplayed();
    }

    private WebElement getElementSearchResultsMessage(){
        return driver.findElement( searchResultsMessage );
    }

    private WebElement waitPresenceOfElement(By elementLocator, long timeInSec){
        return new WebDriverWait( driver, timeInSec )
                  .until(ExpectedConditions.presenceOfElementLocated( elementLocator ));
    }

    private String getElementSearchResultsMessageByJS(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String text = (String) js.executeScript("return document.getElementsByClassName(arguments[0])[0].getText()" , SEARCH_RESULTS_MESSAGE_CLASSNAME );
        return text;
    }
}
