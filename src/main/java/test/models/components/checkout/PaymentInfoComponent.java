package test.models.components.checkout;

import test.models.components.Component;
import test.models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import test.test_data.CreditCardType;

@ComponentCssSelector("#opc-payment_info")
public class PaymentInfoComponent extends Component {

    private final static By codVerifyInfoSelector = By.cssSelector("#checkout-payment-info-load");
    private final static By creditCardDropdownSelector = By.cssSelector("#CreditCardType");

    private final static By cardholderNameSelector = By.cssSelector("#CardholderName");
    private final static By cardNumberSelector = By.cssSelector("#CardNumber");
    private final static By expireMonthSelector = By.cssSelector("#ExpireMonth");
    private final static By expireYearSelector = By.cssSelector("#ExpireYear");
    private final static By cardCodeSelector = By.cssSelector("#CardCode");
    private final static By purchaseOrderSelector = By.cssSelector("#PurchaseOrderNumber");
    private final static By continueBtnSelector = By.cssSelector(".payment-info-next-step-button");

    public PaymentInfoComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public PaymentInfoComponent verifyCodInfo() {
        String paymentInfoMsg = component.findElement(codVerifyInfoSelector).getText();
        String defaultPaymentInfoMsg = "You will pay by COD";
        if (paymentInfoMsg != null) {
            Assert.assertEquals(paymentInfoMsg, defaultPaymentInfoMsg, "[ERR] Payment information message is incorrect.");
            System.out.println("You will pay by COD.");
        }
        return this;
    }

    public PaymentInfoComponent selectCardType(CreditCardType creditCardType) {
        if (creditCardType == null) {
            throw new IllegalArgumentException("[ERR] Credit card type cannot be null.");
        }
        Select select = new Select(component.findElement(creditCardDropdownSelector));
        switch (creditCardType) {
            case VISA:
                select.selectByVisibleText("Visa");
                break;
            case MASTER_CARD:
                select.selectByVisibleText("Master card");
                break;
            case DISCOVER:
                select.selectByVisibleText("Discover");
                break;
            case AMEX:
                select.selectByVisibleText("Amex");
                break;
        }
        return this;
    }

    public PaymentInfoComponent inputCardholderName(String name) {
        component.findElement(cardholderNameSelector).sendKeys(name);
        return this;
    }

    public PaymentInfoComponent inputCardNumber(String number) {
        component.findElement(cardNumberSelector).sendKeys(number);
        return this;
    }

    public PaymentInfoComponent inputExpiredMonth(String month) {
        new Select(component.findElement(expireMonthSelector)).selectByValue(month);
        return this;
    }

    public PaymentInfoComponent inputExpiredYear(String year) {
        new Select(component.findElement(expireYearSelector)).selectByVisibleText(year);
        return this;
    }

    public PaymentInfoComponent inputCardCode(String code) {
        component.findElement(cardCodeSelector).sendKeys(code);
        return this;
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
