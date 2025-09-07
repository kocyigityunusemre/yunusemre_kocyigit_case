package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions opts = new ChromeOptions();
        opts.addArguments("--disable-notifications");
        opts.addArguments("--start-maximized");
        driver = new ChromeDriver(opts);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        if (driver != null) driver.quit();
    }
}
