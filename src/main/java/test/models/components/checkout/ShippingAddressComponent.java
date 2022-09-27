package test.models.components.checkout;

import test.models.components.Component;
import test.models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

@ComponentCssSelector("#opc-shipping")
public class ShippingAddressComponent extends Component {

    private final static By continueBtnSelector = By.cssSelector(".new-address-next-step-button");

    public ShippingAddressComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public ShippingAddressComponent clickOnContinueBtn(){
        WebElement continueBtn = component.findElement(continueBtnSelector);
        continueBtn.click();
        wait.until(ExpectedConditions.invisibilityOf(continueBtn));
        return this;
    }
}
