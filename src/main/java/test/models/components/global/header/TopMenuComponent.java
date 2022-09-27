package test.models.components.global.header;

import test.models.components.Component;
import test.models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

@ComponentCssSelector(".top-menu")
public class TopMenuComponent extends Component {

    public TopMenuComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    @ComponentCssSelector(".top-menu > li")
    public static class MainCategoryItem extends Component {

        public MainCategoryItem(WebDriver driver, WebElement component) {
            super(driver, component);
        }

        // Mouse hover to top menu item

        public List<SublistComponent> categoryItemComps() {
            Actions actions = new Actions(driver);
            actions.moveToElement(component).perform();
            return findComponents(SublistComponent.class, driver);
        }

        public WebElement categoryItemLinkElem() {
            return component.findElement(By.tagName("a"));
        }

    }

    public List<MainCategoryItem> mainItemElems() {
        return findComponents(MainCategoryItem.class, driver);
    }

    @ComponentCssSelector(".sublist li a")
    public static class SublistComponent extends Component {

        public SublistComponent(WebDriver driver, WebElement component) {
            super(driver, component);
        }
    }
}
