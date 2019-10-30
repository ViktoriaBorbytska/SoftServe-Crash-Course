import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverManager extends DriverManager {

    @Override
    public void startService() {
        WebDriverManager.firefoxdriver().setup();
    }

    @Override
    public void createDriver() {
        driver = new FirefoxDriver();
    }
}