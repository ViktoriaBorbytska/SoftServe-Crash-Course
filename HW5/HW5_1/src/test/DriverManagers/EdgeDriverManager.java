package test.DriverManagers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.edge.EdgeDriver;

public class EdgeDriverManager extends DriverManager {

    @Override
    public void startService() {
        WebDriverManager.edgedriver().setup();
    }

    @Override
    public void createDriver() {
        driver = new EdgeDriver();
    }
}