package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FormInteraction {

    public static void main(String[] args) {

        // Get a chrome session
        WebDriver webDriver = DriverFactory.getChromeDriver();

        try {
            // Navigate to the target page
            webDriver.get("https://the-internet.herokuapp.com/login");

            // Define selectors' values
            By userNameSelector = By.id("username");
            By passwordSelector = By.cssSelector("#password");
            By loginBtnSelector = By.cssSelector("[type='submit']");

            // Find elements
            WebElement userNameElem = webDriver.findElement(userNameSelector);
            WebElement passwordElem = webDriver.findElement(passwordSelector);
            WebElement loginBtnElem = webDriver.findElement(loginBtnSelector);

            // interaction
            userNameElem.sendKeys("tomsmith");
            passwordElem.sendKeys("SuperSecretPassword!");
            loginBtnElem.click();
            Thread.sleep(2000);

            // Back to previous page
            webDriver.navigate().back();

            // Refresh page
            webDriver.navigate().refresh();

            // Re-interact with the elements
            // Redefine values
            userNameElem = webDriver.findElement(userNameSelector);
            passwordElem = webDriver.findElement(passwordSelector);
            loginBtnElem = webDriver.findElement(loginBtnSelector);

            // Get attribute value
            System.out.println("Login button type: " + loginBtnElem.getAttribute("type"));
            System.out.println("Background color of login button: " + loginBtnElem.getCssValue("background-color"));

            // Interaction
            userNameElem.sendKeys("tom");
            passwordElem.sendKeys("123456");
            loginBtnElem.click();
            Thread.sleep(2000);

            // Use of findElements
            By loginInputFieldSelectors = By.tagName("input");
            List<WebElement> loginFormFieldsElem = webDriver.findElements(loginInputFieldSelectors);

            final int USERNAME_INDEX = 0;
            final int PASSWORD_INDEX = 1;

            loginFormFieldsElem.get(USERNAME_INDEX).sendKeys("tom");
            loginFormFieldsElem.get(PASSWORD_INDEX).sendKeys("123456");
            Thread.sleep(2000);

            // Find element by linkText
            By poweredByLinkTextSelector = By.linkText("Elemental Selenium");
            WebElement poweredByLinkTextElem = webDriver.findElement(poweredByLinkTextSelector);
            poweredByLinkTextElem.click();

            // Get page title and current url
            System.out.println(webDriver.getTitle());
            System.out.println(webDriver.getCurrentUrl());


            // For debugging
            Thread.sleep(5000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Quit session
        webDriver.quit();

    }
}
