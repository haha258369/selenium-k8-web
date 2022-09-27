package test.models.components.checkout;

import test.models.components.Component;
import test.models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

@ComponentCssSelector("#opc-shipping_method")
public class ShippingMethodComponent extends Component {

    private final static By continueBtnSelector = By.cssSelector(".shipping-method-next-step-button");

    public ShippingMethodComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public ShippingMethodComponent selectShippingMethod(String shippingMethod) {
        component.findElement(By.xpath("//label[contains(text(), \"" + shippingMethod + "\")]")).click();
        return this;
    }

    public void clickOnContinueButton() {
        WebElement continueBtn = component.findElement(continueBtnSelector);
        continueBtn.click();
        wait.until(ExpectedConditions.invisibilityOf(continueBtn));
    }
}
