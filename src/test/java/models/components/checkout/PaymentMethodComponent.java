package models.components.checkout;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

@ComponentCssSelector("#opc-payment_method")
public class PaymentMethodComponent extends Component {

    private final static By codSelector = By.cssSelector("[value=\"Payments.CashOnDelivery\"]");
    private final static By checkMoneyOrderSelector = By.cssSelector("[value=\"Payments.CheckMoneyOrder\"]");
    private final static By creditCardSelector = By.cssSelector("[value=\"Payments.Manual\"]");
    private final static By purchaseOrderSelector = By.cssSelector("[value=\"Payments.PurchaseOrder\"]");
    private final static By continueBtnSelector = By.cssSelector(".payment-method-next-step-button");

    public PaymentMethodComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void selectCOD() {
        component.findElement(codSelector).click();
    }

    public void selectCheckMoneyOrder() {
        component.findElement(checkMoneyOrderSelector).click();
    }

    public void selectCreditCard() {
        component.findElement(creditCardSelector).click();
    }

    public void selectPurchaseOrder() {
        component.findElement(purchaseOrderSelector).click();
    }

    public void clickOnContinueBtn() {
        WebElement continueBtn = component.findElement(continueBtnSelector);
        continueBtn.click();
        wait.until(ExpectedConditions.invisibilityOf(continueBtn));
    }
}
