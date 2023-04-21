package mentoring.automation.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductCatalogPage {
    private static final Logger logger = LogManager.getLogger( ProductCatalogPage.class );
    private WebDriver driver;

    @FindBy(id="sort_view")
    WebElement sortingContainer;
    @FindBy(xpath = "//div[@class=\"pos-fix\"]//h1")
    WebElement productGroupName;
    @FindBy(className = "lb")
    WebElement leftPriceSlider;
    @FindBy(id = "price[min]")
    WebElement minPriceInput;

    public ProductCatalogPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements( driver, this);
        new WebDriverWait( driver, Duration.ofSeconds(5).getSeconds()).until( ExpectedConditions.visibilityOf(sortingContainer) );
    }

    public String getProductGroupName(){
        logger.info( "Locating the name of Product group" );
        return productGroupName.getText();
    }

    public ProductCatalogPage moveLeftPriceSliderToRight(){
        logger.info( "Moving left price slider to the left" );
        new Actions(driver).moveToElement(leftPriceSlider)
                .clickAndHold()
                .moveByOffset(20, 0)
                .release()
                .perform();
        return this;
    }

    public String getMinPriceFromFilter(){
        logger.info( "Get value of the min price filter" );
        return minPriceInput.getAttribute( "value" );
    }
}
