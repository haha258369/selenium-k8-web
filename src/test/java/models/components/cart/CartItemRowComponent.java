package models.components.cart;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@ComponentCssSelector(".cart-item-row")
public class CartItemRowComponent extends Component {

    private final static By unitPriceSelector =By.cssSelector(".product-unit-price");
    private final static By quantityInputSelector =By.cssSelector(".qty-input");
    private final String quantityInputAttribute = "value";
    private final static By quantityConfirmSelector =By.cssSelector(".qty.nobr span");
    private final String quantityConfirmAttribute = "span";
    private final static By subTotalSelector =By.cssSelector(".product-subtotal");

    public CartItemRowComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public double unitPrice() {
        return getValue(unitPriceSelector);
    }

    public double cartPageQuantity() {
        return Double.parseDouble(component.findElement(quantityInputSelector).getAttribute(quantityInputAttribute).trim());
    }

    public double checkoutPageQuantity() {
        return Double.parseDouble(component.findElements(quantityConfirmSelector).get(1).getText().trim());
    }

    public double subTotal() {
        return getValue(subTotalSelector);
    }

    private double getValue(By selector) {
        return Double.parseDouble(component.findElement(selector).getText().trim());
    }
}
