import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumDriver {
    static WebDriver driver;
    static WebDriverWait wait;

    public static void initDriver(String url) {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(500, 800));
        driver.get(url);
        wait = new WebDriverWait(driver, 15);
    }

    public static void closeDriver() {
        driver.close();
        driver.quit();
    }
}
