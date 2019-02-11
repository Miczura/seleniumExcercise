package pl.allegro;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pl.allegro.PageObject.Pages.ExternalAndPortableDrives;
import pl.allegro.PageObject.Pages.MainPageWithPopup;


import java.util.List;

import static pl.allegro.PageObject.Pages.PageObject.logger;


public class AllegroSortDescendantTest {
    private MainPageWithPopup mainPageWithPopup;
    private ExternalAndPortableDrives externalAndPortableDrives;
    private static WebDriver driver;

    @BeforeClass
    public static void setup() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    @Before
    public void openAllegro(){
        mainPageWithPopup = new MainPageWithPopup(driver).navigateTo();
        mainPageWithPopup.closeConsentIfVisible();
    }

    @Test
    public void externalAndPortableDiscDescendantTest() {
        //G
        navigateToThePortableDiscAndMemoriesPage();
        //W
        externalAndPortableDrives.setfilterDiscCapacity("500","1000");
        externalAndPortableDrives.setSortingProductsByPriceDescendant();
        //T
        checkIfPricesAreSorted(externalAndPortableDrives.getListOfPrices());

    }

    @AfterClass
    public static void teardown(){
        driver.close();
        driver.quit();
    }

    private void navigateToThePortableDiscAndMemoriesPage(){
        externalAndPortableDrives = mainPageWithPopup.navigateToElectronicsPage()
                .navigateToComputersPage().navigateToExternalDrivesPage().navigateToExternalAndPortableDrives();
    }

    private void checkIfPricesAreSorted(List<Double> list){
        logger.info("Checking if sorting is descendant");
        double element=Double.POSITIVE_INFINITY;
        for(Double e:list){
            if(element>=e) {
                element=e;
                logger.debug("Product prices {}",e);
            } else if(element<e){
                logger.error("List is not sorted descendant: Price: {} ",element+" is smaller than next price : "+e);
                Assert.fail("List is not sorted descendant: Price: "+element+" is smaller than next price : "+e);
            }
        }
        logger.info("Sorting checked");
    }

}
