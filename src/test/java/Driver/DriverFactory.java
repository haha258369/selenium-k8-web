package Driver;

import org.apache.commons.exec.OS;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

public class DriverFactory {

    public static WebDriver getChromeDriver() {
        String currentProjectLocation = System.getProperty("user.dir");
        String chromeDriverLocation = "";
        if (OS.isFamilyWindows()){
            chromeDriverLocation = currentProjectLocation + "/src/test/resources/driver/chromedriver.exe";
        }

        if (chromeDriverLocation.isEmpty()){
            throw new IllegalArgumentException("Cannot detect OS type");
        }

        System.setProperty("webdriver.chrome.driver", chromeDriverLocation);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");

        WebDriver webDriver = new ChromeDriver(chromeOptions);

        //Implicit wait (for findElement)
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        return webDriver;
    }
}
