package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import url.Urls;

import java.time.Duration;

public class JsAlerts implements Urls {

    // Define selectors
    private final static By jsAlertSelector = By.cssSelector("[onclick=\"jsAlert()\"]");
    private final static By jsConfirmSelector = By.cssSelector("[onclick=\"jsConfirm()\"]");
    private final static By jsPromptSelector = By.cssSelector("[onclick=\"jsPrompt()\"]");
    private final static By resultSelector = By.cssSelector("#result");
    private final static String inputPrompt = "Hello there!";


    public static void main(String[] args) {

        // Get a Chrome session
        WebDriver webDriver = DriverFactory.getChromeDriver();

        try {
            // Navigate to base
            webDriver.get(baseUrl + alertSlug);

            // JS_ALERT | ok is true, cancel is false
            handleAlert(webDriver, jsAlertSelector, resultSelector, true);

            // JS_CONFIRM | ok is true, cancel is false
            handleAlert(webDriver, jsConfirmSelector, resultSelector, false);

            // JS_PROMPT | ok is true, cancel is false
            handlePrompt(webDriver, jsPromptSelector, resultSelector, false, inputPrompt);


        } catch (Exception e) {
            e.printStackTrace();
        }

        // Quit driver session
        webDriver.quit();
    }

    public static void handleAlert(WebDriver webDriver, By jsAlertSelector, By resultSelector, Boolean isAccepting) {

        WebElement triggerJsAlertBtnElem = webDriver.findElement(jsAlertSelector);
        triggerJsAlertBtnElem.click();

        Alert alert = explicitWait(webDriver);

        if (isAccepting) alert.accept();
        else alert.dismiss();

        System.out.println("Result: " + webDriver.findElement(resultSelector).getText());
    }

    public static void handlePrompt(WebDriver webDriver, By jsPromptSelector, By resultSelector, Boolean isAccepting, String inputPrompt) {
        WebElement triggerJsAlertBtnElem = webDriver.findElement(jsPromptSelector);
        triggerJsAlertBtnElem.click();

        Alert alert = explicitWait(webDriver);

        alert.sendKeys(inputPrompt);

        if (isAccepting) alert.accept();
        else alert.dismiss();

        System.out.println("Result: " + webDriver.findElement(resultSelector).getText());
    }

    public static Alert explicitWait(WebDriver webDriver) {

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        System.out.println("Alert content: " + alert.getText());
        return alert;
    }
}
