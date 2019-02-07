package pl.allegro.PageObject.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ComputersPage extends PageObject {
    @FindBy(xpath = "//a[text()='Dyski i pamięci przenośne']")
    private WebElement portableDiscsAndMemoriesLink;

    public ComputersPage(WebDriver driver) {
        super(driver);
    }

    public PortableDiscsAndMemories navigateToExternalDrivesPage(){
        portableDiscsAndMemoriesLink.click();
        logger.info("Navigate to Portable disc and memories");
        return new PortableDiscsAndMemories(getDriver());
    }
}
