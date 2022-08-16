package models.components.order;

import models.components.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BaseItemDetailComponent extends Component {

    private final static By productPriceSelector = By.cssSelector(".product-price");

    public BaseItemDetailComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public double productPrice() {
        String productPriceText = component.findElement(productPriceSelector).getText().trim();
        return Double.parseDouble(productPriceText);
    }


}
