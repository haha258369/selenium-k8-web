package models.components.cart;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector(".cart-item-row")
public class CartItemRowComponent extends Component {

    private final static By unitPriceSelector =By.cssSelector(".product-unit-price");
    private final static By quantityInputSelector =By.cssSelector(".qty-input");
    private final static By subTotalSelector =By.cssSelector(".product-subtotal");

    public CartItemRowComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public double unitPrice() {
        return getValue(unitPriceSelector);
    }

    public double quantity() {
        return Double.parseDouble(component.findElement(quantityInputSelector).getAttribute("value").trim());
    }

    public double subTotal() {
        return getValue(subTotalSelector);
    }

    private double getValue(By selector) {
        return Double.parseDouble(component.findElement(selector).getText().trim());
    }
}
