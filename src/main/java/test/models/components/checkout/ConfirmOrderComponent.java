package test.models.components.checkout;

import test.models.components.Component;
import test.models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector("#opc-confirm_order")
public class ConfirmOrderComponent extends Component {

    private final static By confirmBtnSelector = By.cssSelector(".confirm-order-next-step-button");

    public ConfirmOrderComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void clickOnConfirmBtn() {
        component.findElement(confirmBtnSelector).click();
    }
}
