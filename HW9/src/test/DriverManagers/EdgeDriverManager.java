package test.DriverManagers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.concurrent.TimeUnit;

public class EdgeDriverManager extends DriverManager {

    @Override
    public void startService() {
        WebDriverManager.edgedriver().setup();
    }

    @Override
    public void createDriver() {
        EdgeDriverService service = EdgeDriverService.createDefaultService();
        EdgeOptions options = new EdgeOptions();
        driver = new EdgeDriver(service, options);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
    }
}