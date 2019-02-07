package pl.allegro.PageObject.Pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class PortableDiscsAndMemories extends PageObject {

    @FindBy(xpath = "//a[text()='Dyski zewnętrzne i przenośne']")
    private WebElement externalAndPortableDrivesLink;

    public PortableDiscsAndMemories(WebDriver driver) {
        super(driver);
    }
    public ExternalAndPortableDrives navigateToExternalAndPortableDrives(){
        externalAndPortableDrivesLink.click();
        logger.info("Navigate to External and portable Drives");
        return new ExternalAndPortableDrives(getDriver());
    }
}
