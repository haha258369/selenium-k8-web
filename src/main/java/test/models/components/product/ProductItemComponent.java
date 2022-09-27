package test.models.components.product;

import test.models.components.Component;
import test.models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector(value = ".product-item")
public class ProductItemComponent extends Component {

    public ProductItemComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    private static final By productTitleSelector = By.cssSelector(".product-title");

    public WebElement productTittleElem(){
        return component.findElement(productTitleSelector);
    }
}
