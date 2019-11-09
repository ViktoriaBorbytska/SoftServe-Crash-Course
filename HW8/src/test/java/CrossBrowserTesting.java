import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
    }

    @AfterTest
    public void afterTest() {
        driverManager.quitDriver();
    }

    @Test
    public void setUp() {
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
        new WebDriverWait(driver, 20).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        Assert.assertEquals(driver.getCurrentUrl(), URLEXPECTED);
    }
}
