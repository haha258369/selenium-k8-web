package models.components.order;

import models.components.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BaseItemDetailComponent extends Component {

    private final static By notificationBarSelector = By.cssSelector("#bar-notification");
    private final static By productPriceSelector = By.cssSelector(".product-price");
    private final static By addToCartBtnSelector = By.cssSelector(".button-1.add-to-cart-button");

    public void waitUntilItemAddedToCart() {
        String successMsg = "The product has been added to your ";
        try {
            wait.until(ExpectedConditions.textToBePresentInElementLocated(notificationBarSelector, successMsg));
        } catch (TimeoutException e) {
            clickOnAddToCartBtn();
        }
    }

    public BaseItemDetailComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public double productPrice() {
        String productPriceText = component.findElement(productPriceSelector).getText().trim();
        return Double.parseDouble(productPriceText);
    }

    public void clickOnAddToCartBtn() {
        component.findElement(addToCartBtnSelector).click();
    }
}
