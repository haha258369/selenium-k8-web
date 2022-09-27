package test.models.pages;

import test.models.components.checkout.OrderCompleteComponent;
import org.openqa.selenium.WebDriver;

public class CompletedPage extends BasePage{

    public CompletedPage(WebDriver driver) {
        super(driver);
    }

    public OrderCompleteComponent orderCompleteComp() {
        return findComponent(OrderCompleteComponent.class, driver);
    }
}
