package pl.allegro.PageObject.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.allegro.PageObject.utils.Url;


public class MainPageWithPopup extends PageObject{


    private WebDriverWait wait;
    @FindBy(xpath = " //a[text()=' Elektronika']")
    private WebElement linkElektroncs;
    @FindBy(xpath ="//button[text()='przejdź dalej']" )
    private WebElement rodoConsent;

    public MainPageWithPopup(WebDriver driver){
        super(driver);
        this.wait = new WebDriverWait(driver,5);
    }

    public MainPageWithPopup navigateTo(){
        getDriver().manage().window().maximize();
        getDriver().get(Url.ALLEGRO_URL);
        return this;
    }
    public ElectronicsPage navigateToElectronicsPage(){
        linkElektroncs.click();
        logger.info("Navigate to Electronics");
        return new ElectronicsPage(getDriver());
    }

    public boolean closeConsent() {
        if (isDisplayed()) {
            rodoConsent.click();
            logger.info("Rodo consent confirmed");
        }
        return false;
    }

    private boolean isDisplayed() {
            WebElement popup = wait.until(ExpectedConditions.visibilityOf(rodoConsent));
            return popup.isDisplayed();
    }
}
