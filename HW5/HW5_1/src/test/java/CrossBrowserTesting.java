import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;
import test.DriverManagers.*;


public class CrossBrowserTesting {
    DriverManager driverManager;
    WebDriver driver;

    final String TITLEEXPECTED = "Школа програмування : SoftServe провідна IT-компанія";
    final String URLEXPECTED = "https://career.softserveinc.com/uk-ua/technology";
    final String FIRSTLINKCLASS = "LC20lb";

    @BeforeTest
    @Parameters("Browser")
    public void beforeTest(String Browser) throws Exception{
        if(Browser.equalsIgnoreCase("firefox")) {
            driverManager = DriverManagerFactory.getManager(DriverType.FIREFOX);
        } else if (Browser.equalsIgnoreCase("chrome")) {
            driverManager = DriverManagerFactory.getManager(DriverType.CHROME);
        } else {
            driverManager = DriverManagerFactory.getManager(DriverType.EDGE);
        }
        driver = driverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @AfterTest
    public void afterTest() {
        driverManager.quitDriver();
    }

    @Test
    public void setUp() {
        driver.manage().window().maximize();
        driver.get("https://google.com/");

        WebElement softServeElement =  driver.findElement(By.name("q"));
        softServeElement.sendKeys("softserve it academy");
        softServeElement.sendKeys(Keys.ENTER);
    }

    @Test
    public void testNameOfLink() {
        String titleActual = driver.findElement(By.className(FIRSTLINKCLASS)).getText();
        Assert.assertEquals(titleActual, TITLEEXPECTED);
    }

    @Test
    public void testUrl() {
        driver.findElement(By.className(FIRSTLINKCLASS)).click();
        Assert.assertEquals(driver.getCurrentUrl(), URLEXPECTED);
    }
}
