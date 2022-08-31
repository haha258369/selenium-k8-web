package models.components.cart;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ComponentCssSelector(".cart-footer > .totals")
public class TotalComponent extends Component {

    private final static By priceTableRowSelector = By.cssSelector("table tr");
    private final static By priceTypeSelector = By.cssSelector(".cart-total-left");
    private final static By priceValueSelector = By.cssSelector(".cart-total-right");
    private final static By termsSelector = By.cssSelector("#termsofservice");
    private final static By checkoutBtnSelector = By.cssSelector("#checkout");

    public TotalComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public Map<String, Double> priceCategories() {
        Map<String, Double> priceCategories = new HashMap<>();
        List<WebElement> priceTableRowElems = component.findElements(priceTableRowSelector);
        for (WebElement tableRowElem : priceTableRowElems) {
            WebElement priceTypeElem = tableRowElem.findElement(priceTypeSelector);
            WebElement priceValueElem = tableRowElem.findElement(priceValueSelector);
            String priceType = priceTypeElem.getText().trim().replace(":", "");
            double priceValue = Double.parseDouble(priceValueElem.getText().trim());
            priceCategories.put(priceType, priceValue);
        }
        return priceCategories;
    }

    public TotalComponent agreeTerms() {
        component.findElement(termsSelector).click();
        return this;
    }

    public void clickOnCheckoutBtn() {
        component.findElement(checkoutBtnSelector).click();
    }
}
