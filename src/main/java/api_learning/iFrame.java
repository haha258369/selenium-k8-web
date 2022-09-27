package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static url.Urls.baseUrl;
import static url.Urls.iFrameSlug;

public class iFrame {

    // Define selector
    private final static By iFrameSelector = By.cssSelector("[id$='ifr']");
    private final static By editorInputSelector = By.id("tinymce");
    private final static By poweredBySelector = By.linkText("Elemental Selenium");

    public static void main(String[] args) {

        // Get a chrome session
        WebDriver webDriver = DriverFactory.getChromeDriver();

        try {
            // Navigate to base
            webDriver.get(baseUrl + iFrameSlug);

            // Locate iframe
            WebElement iFrameElem = webDriver.findElement(iFrameSelector);

            // Switch into iFrame
            webDriver.switchTo().frame(iFrameElem);

            // Locate elements inside iframe
            WebElement editorInputElem = webDriver.findElement(editorInputSelector);

            // Interact with elements inside iframe
            editorInputElem.click();
            editorInputElem.clear();
            editorInputElem.sendKeys("This is an iFrame test.");

            Thread.sleep(1000);

            // Switch back to parent frame
            webDriver.switchTo().defaultContent();

            // Find link text
            webDriver.findElement(poweredBySelector).click();
            Thread.sleep(1000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Quit driver session
        webDriver.quit();
    }
}
