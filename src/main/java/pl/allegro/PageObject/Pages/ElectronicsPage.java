package pl.allegro.PageObject.Pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;



public class ElectronicsPage extends PageObject{
    @FindBy(xpath = "//div[@data-box-name=\"Nagłówek kategorii Komputery i tablety\"]/descendant::a[2]")
    private WebElement computersLink;

    public ElectronicsPage(WebDriver driver) {
        super(driver);

    }
    public ComputersPage navigateToComputersPage(){
        computersLink.click();
        logger.info("Navigate to Computers");
        return new ComputersPage(getDriver());
    }

}
