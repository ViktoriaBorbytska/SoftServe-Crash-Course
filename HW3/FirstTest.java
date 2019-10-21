import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class FirstTests {
    @Test
    public void FirstTest(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\synne\\Downloads\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://www.google.com/");

        driver.findElement(By.name("q")).sendKeys("softserve it academy");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);

        String nameOfFirstLink = driver.findElement(By.className("LC201b")).getText();
        Assert.assertEquals(nameOfFirstLink, "Школа програмування : SoftServe провідна IT-компанія");

        driver.findElement(By.linkText("Школа програмування : SoftServe провідна IT-компанія")).click();

        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL, "https://career.softserveinc.com/uk-ua/technology");

        driver.close();
    }
}