package models.components.checkout;

import models.components.Component;
import models.components.ComponentCssSelector;
import models.components.cart.CartItemRowComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

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
