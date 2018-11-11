package mentoring.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private static final String PAGE_TITLE_FRAGMENT = "ROZETKA";
    private static final String EXCEPTION_MESSAGE = "Webdriver does not point to the HomePage, current location is ";
    private WebDriver driver;
    private By searchField = By.className("rz-header-search-input-text passive");
    private By submitButton = By.className("btn-link-i js-rz-search-button");

    public HomePage(WebDriver driver){
        this.driver = driver;
        if(!driver.getTitle().contains( PAGE_TITLE_FRAGMENT )){
            throw new IllegalArgumentException( EXCEPTION_MESSAGE + driver.getCurrentUrl() );
        }
    }

    public int getFoundGoodsNumber(String goodsName){
        int number = 0;
        WebElement inputField = driver.findElement(searchField);
        inputField.sendKeys(goodsName);
        WebElement button = driver.findElement(submitButton);
        button.click();

        return number;
    }
}
