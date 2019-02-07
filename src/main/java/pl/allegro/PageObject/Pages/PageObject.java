package pl.allegro.PageObject.Pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageObject {
    public static final Logger logger = LoggerFactory.getLogger(PageObject.class);
    private WebDriver driver;

    public PageObject(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public WebDriver getDriver(){
        return driver;
    }
}
