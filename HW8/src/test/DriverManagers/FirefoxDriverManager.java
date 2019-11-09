package test.DriverManagers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class FirefoxDriverManager extends DriverManager {

    @Override
    public void startService() {
        WebDriverManager.firefoxdriver().setup();
    }

    @Override
    public void createDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless", "--start-maximized", "--ignore-certificate-errors", "test-type");
        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
    }
}