package support.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class WaitForElementEnabled implements ExpectedCondition<Boolean> {

    private final By parentSelector;
    private final By childSelector;

    public WaitForElementEnabled(By parentSelector, By childSelector) {
        this.parentSelector = parentSelector;
        this.childSelector = childSelector;
    }

    @Override
    public Boolean apply(WebDriver webDriver) {
        WebElement parentElem = webDriver.findElement(parentSelector);
        return parentElem.findElement(childSelector).isEnabled();
    }

    @Override
    public String toString() {
        return "element located by " + this.childSelector.toString();
    }
}
