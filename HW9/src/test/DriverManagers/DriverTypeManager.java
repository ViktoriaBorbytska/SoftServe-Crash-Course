package test.DriverManagers;

public class DriverTypeManager {
    public static DriverManager getManagerType(String browser) {

        DriverType driverType;

        if (browser.equalsIgnoreCase("firefox")) {
            driverType = DriverType.FIREFOX;
        } else if (browser.equalsIgnoreCase("chrome")) {
            driverType = DriverType.CHROME;
        } else {
            driverType = DriverType.EDGE;
        }

        return DriverManagerFactory.getManager(driverType);
    }
}