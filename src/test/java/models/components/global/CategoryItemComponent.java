package models.components.global;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector(value = ".sublist li a")
public class CategoryItemComponent extends Component {

    public CategoryItemComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }


}
