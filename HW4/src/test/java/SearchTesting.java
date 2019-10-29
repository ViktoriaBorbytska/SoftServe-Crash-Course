import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SearchTesting {
    private WebDriver driver;
    private WebDriverWait wait;

    List<WebElement> searchResultsList;
    private final String urlMain = "https://www.softserveinc.com/uk-ua/";
    private final String searchQuery = "application";
    private final String xSearchLink = "//a[@aria-label='Search']";
    private final String xInputField = "//input[@type='text' and @maxlength=1000]";
    private final String xSearchButton = "//input[@type='submit' and @value=' Пошук']";
    private final String searchResultTitle = "//h2[@class='search-result-title']";

    // Method 1: driver setup
    @BeforeSuite
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 1);
    }

    // Method 2: url launch
    @BeforeClass
    public void appSetup() {
        driver.get(urlMain);
    }

    // Method 3: profile setup
    @BeforeTest
    public void profileSetup() {
        driver.manage().window().maximize();
    }

    // Method 4: search field testing
    @Test(priority = 0,
            groups = {"searchTesting"})
    public void searchFieldTesting(){
        driver.navigate().refresh();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("page-preloader")));
        WebElement searchLink = driver.findElement(By.xpath(xSearchLink));
        searchLink.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("page-preloader")));
        WebElement searchInput = driver.findElement(By.xpath(xInputField));
        searchInput.sendKeys(searchQuery);

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("page-preloader")));
        WebElement searchButton  = driver.findElement(By.xpath(xSearchButton));
        searchButton.click();
    }

    // Method 5: checking whether the number of strings in the list is correct
    @Test(priority = 1,
            groups = {"searchTesting"},
            dependsOnMethods = "searchFieldTesting")
    public void resultFulfillmentCheck() {
        searchResultsList = driver.findElements(By.xpath(searchResultTitle));

        boolean expected = true;
        boolean actual = searchResultsList.size() > 0;

        Assert.assertEquals(actual, expected);
    }

    // Method 6: checking whether the result is correct
    @Test(priority = 2,
            groups = {"searchTesting"},
            dependsOnMethods = "searchFieldTesting")
    public void resultAccuracyCheck () {
        searchResultsList = driver.findElements(By.xpath(searchResultTitle));
        List<String> resultsList = new ArrayList<>();

        boolean expected = true;
        boolean actual = true;

        for (int i = 0; i < searchResultsList.size(); i++)
            resultsList.add(searchResultsList.get(i).getAttribute("innerText"));

        for (String value : resultsList) {
            if (!value.toLowerCase().contains(searchQuery.toLowerCase())) {
                actual = false;
                break;
            }
        }

        Assert.assertEquals(actual, expected);
    }

    // Method 7: quiting the driver
    @AfterClass
    public final void shutDown() {
        driver.quit();
    }
}