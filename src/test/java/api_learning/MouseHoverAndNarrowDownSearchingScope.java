package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import url.Urls;
import java.util.List;

public class MouseHoverAndNarrowDownSearchingScope implements Urls {

    // Define selector
    private final static By figureSelector = By.className("figure");
    private final static By profileNameSelector = By.cssSelector(".figcaption h5");
    private final static By profileLinkSelector = By.cssSelector(".figcaption a");

    public static void main(String[] args) {

        // Get a Chrome session
        WebDriver webDriver = DriverFactory.getChromeDriver();

        try {
            // Navigate to base
            webDriver.get(baseUrl + hoverSlug);

            // Locate parent elements
            List<WebElement> figuresElem = webDriver.findElements(figureSelector);
            if (figuresElem.isEmpty()) throw new RuntimeException("There is no profile image displayed.");

            // Define Actions object
            Actions actions = new Actions(webDriver);

            // Mouse hover interaction
            for (WebElement webElement : figuresElem) {

                // Locate profile name and link
                WebElement profileNameElem = webElement.findElement(profileNameSelector);
                WebElement profileLinkElem = webElement.findElement(profileLinkSelector);

                // Before mouse hover
                System.out.println("Is profile name displaying: " + profileNameElem.isDisplayed());
                System.out.println("Is profile link displaying: " + profileLinkElem.isDisplayed());

                // Mouse hover
                actions.moveToElement(webElement).perform();

                // After mouse hover
                System.out.println(profileNameElem.getText() + ": " + profileNameElem.isDisplayed());
                System.out.println(profileLinkElem.getText() + ": " + profileLinkElem.isDisplayed());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Quit driver session
        webDriver.quit();

    }
}