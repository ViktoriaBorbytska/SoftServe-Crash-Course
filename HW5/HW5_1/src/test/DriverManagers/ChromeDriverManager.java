package test.DriverManagers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverManager extends DriverManager {

    @Override
    public void startService() {
        WebDriverManager.chromedriver().setup();
    }

    @Override
    public void createDriver() {
        driver = new ChromeDriver();
    }
}