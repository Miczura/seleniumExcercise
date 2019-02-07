package pl.allegro.PageObject.Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class ExternalAndPortableDrives extends PageObject {
    WebDriverWait wait;

    @FindBy(xpath = "//input[@id='pojemnosc-dysku-od']")
    private WebElement inputFromDiscCapacityFilter;
    @FindBy(xpath = "//input[@id='pojemnosc-dysku-do']")
    private WebElement inputToDiscCapacityFilter;
    @FindBy(xpath = "//select[@data-value='m']")
    private WebElement mainSorter;
    @FindBy(xpath = "//option[text()=' cena: od najwyższej ']")
    private WebElement fromMaxPrice;
    @FindBy(xpath = "//section[descendant::h2[text()='Lista promowanych ofert']]//span[@class='fee8042']")
    private WebElement listOfProducts;

    public ExternalAndPortableDrives(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver,2);
    }
    public void setfilterDiscCapacity(String minCapacity,String maxCapacity){
        logger.info("Selecting disc capacity");
        inputFromDiscCapacityFilter.clear();
        inputFromDiscCapacityFilter.sendKeys(minCapacity);
        inputToDiscCapacityFilter.clear();
        inputToDiscCapacityFilter.sendKeys(maxCapacity);
        logger.info("Disc capacity selected");
    }
    public void setSortingProductsByPriceDescendant(){
        logger.info("Sorting products by desired contitions");
        wait.until(ExpectedConditions.visibilityOf(mainSorter));
        Assert.assertTrue("Not visible main sorting element",mainSorter.isDisplayed());
        mainSorter.click();

        Assert.assertTrue("Not visible from highest price sorter",fromMaxPrice.isDisplayed());
        fromMaxPrice.click();
        logger.info("Products sorted");
    }


    public List<Double> getListOfPrices(){
        //below is ugly Workaround.... need guidance
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("Collecting produts prices");
        List<WebElement> listOfWebElements= listOfProducts.
                findElements(By.xpath("//section[descendant::h2[text()='Lista promowanych ofert']]//span[@class='fee8042']"));
        List<String> listOfStringsPrises =listOfWebElements.
                stream().
                map(webElement -> webElement.getText().replaceAll("zł","").replaceAll(",",".").trim()).
                collect(Collectors.toList());
        List<Double> listOfPrisesInDouble =listOfStringsPrises.
                stream().
                map(stringElement->Double.parseDouble(stringElement.replaceAll(" ",""))).
                collect(Collectors.toList());
        logger.info("Prices collected");
        return listOfPrisesInDouble;
    }

}
