import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FirstTest {
    WebDriver driver;

    final String TITLEEXPECTED = "Школа програмування : SoftServe провідна IT-компанія";
    final String URLEXPECTED = "https://career.softserveinc.com/uk-ua/technology";
    final String FIRSTLINKCLASS = "LC20lb";

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

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

    @AfterClass
    public void shutDown() {
        driver.quit();
    }
}
