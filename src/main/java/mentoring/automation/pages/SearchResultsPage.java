package mentoring.automation.pages;

import org.openqa.selenium.WebDriver;

public class SearchResultsPage {
    private final static String PAGE_TITLE_FRAGMENT = "ROZETKA — Результати пошуку:";
    private static final String EXCEPTION_MESSAGE = "Webdriver does not point to the SearchResultsPage, current location is ";
    private WebDriver driver;

    public SearchResultsPage(WebDriver driver){
        this.driver = driver;
        if(!driver.getTitle().contains( PAGE_TITLE_FRAGMENT )){
            throw new IllegalArgumentException( EXCEPTION_MESSAGE + driver.getCurrentUrl() );
        }
    }
}
