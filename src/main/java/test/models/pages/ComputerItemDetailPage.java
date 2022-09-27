package test.models.pages;

import test.models.components.order.ComputerEssentialComponent;
import org.openqa.selenium.WebDriver;

public class ComputerItemDetailPage extends BasePage {

    public ComputerItemDetailPage(WebDriver driver) {
        super(driver);
    }

    public<T extends ComputerEssentialComponent> T computerComp(Class<T> computerEssentialCompClass) {
        return findComponent(computerEssentialCompClass, driver);
    }
}
