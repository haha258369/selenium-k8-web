package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.ui.WaitForElementEnabled;
import url.Urls;

import java.time.Duration;

public class DynamicControls implements Urls {

    // Define selectors
    // Parents
    private final static By checkboxFormSelector = By.id("checkbox-example");
    private final static By inputFormSelector = By.id("input-example");

    // Children
    private final static By inputSelector = By.tagName("input");
    private final static By inputBtnSelector = By.tagName("button");
    private final static By loadingBarSelector = By.id("loading");


    public static void main(String[] args) {

        // Get a Chrome session
        WebDriver webDriver = DriverFactory.getChromeDriver();

        try {
            // Navigate to target base
            webDriver.get(baseUrl + dynamicControlSlug);

            // Checkbox Form Interaction
            WebElement checkboxFormElem = webDriver.findElement(checkboxFormSelector);
            WebElement checkboxElem = checkboxFormElem.findElement(inputSelector);
            WebElement removeBtnElem = checkboxFormElem.findElement(inputBtnSelector);
            if (!checkboxElem.isSelected()) {
                checkboxElem.click();
                removeBtnElem.click();
            }

            // Explicit wait
//            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
//            wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingBarSelector));

            // Input form interaction
            WebElement inputFormElem = webDriver.findElement(inputFormSelector);
            WebElement inputTextBoxElem = inputFormElem.findElement(inputSelector);
            WebElement inputBtnElem = inputFormElem.findElement(inputBtnSelector);
            if (!inputTextBoxElem.isEnabled()) inputBtnElem.click();

            // Explicit wait
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
//            wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingBarSelector));
            wait.until(new WaitForElementEnabled(inputFormSelector, inputSelector));
            inputTextBoxElem.sendKeys("Hello, there is a bug here!");

            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Quit driver session
        webDriver.quit();
    }
}
