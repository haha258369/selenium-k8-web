package test.models.components.order;

import test.models.components.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BaseItemDetailComponent extends Component {

    private final static By notificationBarSelector = By.cssSelector("#bar-notification");
    private final static By productPriceSelector = By.cssSelector(".product-price");
    private final static By addToCartBtnSelector = By.cssSelector(".add-to-cart-button");

    public BaseItemDetailComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void waitUntilItemAddedToCart() {
        String successMsg = "The product has been added to your shopping cart";
        try {
            wait.until(ExpectedConditions.textToBePresentInElementLocated(notificationBarSelector, successMsg));
        } catch (TimeoutException e) {
            clickOnAddToCartBtn();
        }
    }

    public double productPrice() {
        String productPriceText = component.findElement(productPriceSelector).getText().trim();
        return Double.parseDouble(productPriceText);
    }

    public BaseItemDetailComponent clickOnAddToCartBtn() {
        component.findElement(addToCartBtnSelector).click();
        return this;
    }
}
