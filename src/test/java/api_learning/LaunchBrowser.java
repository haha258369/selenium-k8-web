package api_learning;

import org.apache.commons.exec.OS;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LaunchBrowser {

    public static void main(String[] args) {

        // Setting chrome driver
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
        webDriver.manage().window().maximize();
        webDriver.get("https://google.com");

        // For debugging
        try {
            Thread.sleep(2000);
        } catch (Exception e){
            e.printStackTrace();
        }

        // Quit browser session
        webDriver.quit();
    }
}

