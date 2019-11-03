import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ServicesTesting {
    private WebDriver driver;

    private final String urlMain = "https://www.softserveinc.com/uk-ua/";

    private List<String> servicesListExpected;
    private List<WebElement> servicesListActual;
    private List<String> servicesListActualContent;

    // Method 1: driver setup
    @BeforeSuite
    public void beforeSuite() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        servicesListExpected = new ArrayList<String>();
        servicesListExpected.add("програмне забезпечення");
        servicesListExpected.add("хмарні технології");
        servicesListExpected.add("великі дані");
        servicesListExpected.add("штучний інтелект");
        servicesListExpected.add("інтернет речей (iot)");
        servicesListExpected.add("сервісний дизайн");
        servicesListExpected.add("кібербезпека");
        servicesListExpected.add("цифрові платформи");
        servicesListExpected.add("розширена реальність (xr)");
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

    // Method 4: getting the services list
    @Test(priority = 0,
            groups = {"servicesTesting"})
    public void getServicesList() {
        servicesListActual = driver.findElements(By.className("services-nav-link"));
    }

    // Method 5: checking whether the number of strings in the list is correct
    @Test(priority = 1,
            groups = {"servicesTesting"},
            dependsOnMethods = "getServicesList")
    public void checkListFulfillment() {
        int expected = servicesListExpected.size();
        int actual = servicesListActual.size();

        Assert.assertEquals(actual, expected);
    }

    // Method 6: checking whether the result is correct
    @Test(priority = 1,
            groups = {"servicesTesting"},
            dependsOnMethods = "getServicesList")
    public void compareServiceLists() {
        servicesListActualContent = new ArrayList<String>();

        for (WebElement webElement : servicesListActual) {
            servicesListActualContent.add(webElement.getText().toLowerCase());
        }

        List<String> expected = servicesListExpected;
        List<String> actual = servicesListActualContent;

        Assert.assertEquals(actual, expected);
    }

    // Method 7: quiting the driver
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}