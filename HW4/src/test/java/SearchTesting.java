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
    private final String SearchLink = "//a[@aria-label='Search']";
    private final String InputField = "//input[@type='text' and @maxlength=1000]";
    private final String SearchButton = "//input[@type='submit' and @value=' Пошук']";
    private final String searchResultTitle = "//h2[@class='search-result-title']";

    // Method 1: driver setup
    @BeforeSuite
    public void beforeSuite() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    // Method 2: url launch
    @BeforeClass
    public void beforeClass() {
        driver.get(urlMain);
    }

    // Method 3: profile setup
    @BeforeTest
    public void beforeTest() {
        driver.manage().window().maximize();
    }

    // Method 4: search field testing
    @Test
    public void searchFieldTesting(){
        driver.navigate().refresh();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("page-preloader")));
        WebElement searchLink = driver.findElement(By.xpath(SearchLink));
        searchLink.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("page-preloader")));
        WebElement searchInput = driver.findElement(By.xpath(InputField));
        searchInput.sendKeys(searchQuery);

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("page-preloader")));
        WebElement searchButton  = driver.findElement(By.xpath(SearchButton));
        searchButton.click();
    }

    // Method 5: checking whether the number of strings in the list is correct
    @Test(dependsOnMethods = "searchFieldTesting")
    public void resultFulfillmentCheck() {
        searchResultsList = driver.findElements(By.xpath(searchResultTitle));
        boolean actual = searchResultsList.size() > 0;
        Assert.assertEquals(actual, true);
    }

    // Method 6: checking whether the result is correct
    @Test(dependsOnMethods = "searchFieldTesting")
    public void resultAccuracyCheck () {
        searchResultsList = driver.findElements(By.xpath(searchResultTitle));
        List<String> resultsList = new ArrayList<>();

        boolean actual = true;

        for (int i = 0; i < searchResultsList.size(); i++)
            resultsList.add(searchResultsList.get(i).getAttribute("innerText"));

        for (String value : resultsList) {
            if (!value.toLowerCase().contains(searchQuery.toLowerCase())) {
                actual = false;
                break;
            }
        }

        Assert.assertEquals(actual, true);
    }

    // Method 7: quiting the driver
    @AfterClass
    public final void afterClass() {
        driver.quit();
    }
}