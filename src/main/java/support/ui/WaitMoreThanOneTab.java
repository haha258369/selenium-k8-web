package support.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class WaitMoreThanOneTab implements ExpectedCondition<Boolean> {


    @Override
    public Boolean apply(WebDriver webDriver) {
        return webDriver.getWindowHandles().size() > 1;
    }

    @Override
    public String toString() {
        return "more tabs to be opened";
    }
}
