package test.parameters;

import org.testng.annotations.DataProvider;

public class DataProviderClass {
    @DataProvider(name="SearchProvider")
    public static Object[][] getDataFromDataProvider(){
        return new Object[][] {
                { "Github", "https://github.com"},
                { "Wikipedia", "https://www.wikipedia.org" }
        };
    }}