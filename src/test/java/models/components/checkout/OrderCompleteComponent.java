package models.components.checkout;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@ComponentCssSelector(".master-wrapper-main")
public class OrderCompleteComponent extends Component {

    private final static By orderCompletedMsgSelector = By.cssSelector(".title");
    private final static By continueBtnSelector = By.cssSelector(".order-completed-continue-button");

    public OrderCompleteComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public OrderCompleteComponent verifyOrderComplete() {
        String orderCompletedMsg = component.findElement(orderCompletedMsgSelector).getText();
        String defaultOrderCompletedMsg = "Your order has been successfully processed!";
        if (orderCompletedMsg == null) {
            throw new NullPointerException("[ERR] Order completed message cannot be null!");
        } else {
            Assert.assertEquals(orderCompletedMsg, defaultOrderCompletedMsg, "[ERR] Order completed message is incorrect!");
        }
        return this;
    }

    public void clickOnContinueBtn() {
        component.findElement(continueBtnSelector).click();
    }
}
