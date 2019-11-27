import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;
import test.DriverManagers.*;
import test.parameters.*;


public class CrossBrowserTesting {
    DriverManager driverManager;
    WebDriver driver;


    @BeforeTest
    @Parameters("Browser")
    public void beforeTest(String Browser) throws Exception{
        driverManager = DriverTypeManager.getManagerType(Browser);
        driver = driverManager.getDriver();
        driver.get(TestingParameters.startLink);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @AfterTest
    public void afterTest() {
        driverManager.quitDriver();
    }

    @Test(dataProvider="SearchProvider", dataProviderClass = DataProviderClass.class)
    public void TestGithub(String query, String searchLink) throws InterruptedException {
        WebElement searchField = driver.findElement(By.name(TestingParameters.searchField));
        searchField.clear();
        searchField.sendKeys(query);
        searchField.sendKeys(Keys.ENTER);
        String actualResult = driver.findElement(By.className(TestingParameters.resultField)).getText();
        Assert.assertEquals(actualResult, searchLink);
    }
}
