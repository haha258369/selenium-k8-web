package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.ui.WaitMoreThanOneTab;
import url.Urls;

import java.time.Duration;

public class ExplicitWait implements Urls {

    // Define selectors
    private final static By miscSelector = By.cssSelector("#teo");

    public static void main(String[] args) {

        // Get a Chrome session
        WebDriver webDriver = DriverFactory.getChromeDriver();

        try {
            // Navigate to base
            webDriver.get(baseUrl + loginSlug);

            // Explicit wait
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
            wait.until(new WaitMoreThanOneTab());


        } catch (Exception e) {
            e.printStackTrace();
        }

        // Quit driver session
        webDriver.quit();
    }
}
