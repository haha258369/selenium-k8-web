package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import support.ui.SelectExt;
import url.Urls;

public class DropDown implements Urls {

    public static void main(String[] args) {

        // Get a chrome session
        WebDriver webDriver = DriverFactory.getChromeDriver();

        try {
            // Navigate to base
            webDriver.get(baseUrl + dropdownSlug);

            // Selector & element
            By dropdownSelector = By.id("dropdown");
            WebElement dropdownElem = webDriver.findElement(dropdownSelector);

            // Select dropdown
            Select select = new Select(dropdownElem);
            SelectExt select02 = new SelectExt(dropdownElem);

            // Select by visible text | Option 1
            select.selectByVisibleText("Option 1");
            Thread.sleep(1000);

            // Select by index | Option 2
            select.selectByIndex(2);
            Thread.sleep(1000);

            // Select by value | Option 1
            select.selectByValue("1");
            Thread.sleep(1000);

            // Select by customize select method | Option 2
            select02.selectOption2();
            Thread.sleep(1000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        webDriver.quit();
    }
}
