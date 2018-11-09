package mentoring.automation.pages;

import org.openqa.selenium.WebDriver;

public class HomePage {
    private static final String PAGE_TITLE_FRAGMENT = "ROZETKA";
    private static final String EXCEPTION_MESSAGE = "Webdriver does not point to the HomePage, current location is ";
    private WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
        if(!driver.getTitle().contains( PAGE_TITLE_FRAGMENT )){
            throw new IllegalArgumentException( EXCEPTION_MESSAGE + driver.getCurrentUrl() );
        }
    }
}
