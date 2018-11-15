package mentoring.automation.pages;

import org.apache.logging.log4j.core.util.Integers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultsPage {
    private final static String PAGE_TITLE_FRAGMENT = "ROZETKA — Результати пошуку:";
    private static final String EXCEPTION_MESSAGE = "Webdriver does not point to the SearchResultsPage, current location is ";
    private WebDriver driver;
    private By searchResultsMessage = By.className( "rz-search-result-qnty" );

    public SearchResultsPage(WebDriver driver){
        this.driver = driver;
        if(!driver.getTitle().contains( PAGE_TITLE_FRAGMENT )){
            throw new IllegalArgumentException( EXCEPTION_MESSAGE + driver.getCurrentUrl() );
        }
    }

    public int getFoundGoodsNumber(){
        WebDriverWait wait = new WebDriverWait( driver, 10 );
        wait.until( ExpectedConditions.presenceOfElementLocated( searchResultsMessage ) );
        String searchResultMessage = getElementSearchResultsMessage().getText();
        String result = searchResultMessage.replaceAll( "\\D", "" );
        return Integers.parseInt(result);
    }

    private WebElement getElementSearchResultsMessage(){
        return driver.findElement( searchResultsMessage );
    }
}
