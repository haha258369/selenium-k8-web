package models.components.checkout;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.security.SecureRandom;

@ComponentCssSelector("#opc-payment_info")
public class PaymentInfoComponent extends Component {

    private final static By purchaseOrderSelector = By.cssSelector("#PurchaseOrderNumber");
    private final static By continueBtnSelector = By.cssSelector(".payment-info-next-step-button");

    public PaymentInfoComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void inputPurchaseOrderNumber(String poNumber) {
        component.findElement(purchaseOrderSelector).sendKeys(poNumber);
    }

    public void clickOnContinueBtn() {
        WebElement continueBtn = component.findElement(continueBtnSelector);
        continueBtn.click();
        wait.until(ExpectedConditions.invisibilityOf(continueBtn));
    }
}
