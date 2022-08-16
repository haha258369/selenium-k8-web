package models.components.global;

import models.components.Component;
import models.components.ComponentCssSelector;
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

    public List<MainCategoryItem> mainItemElems() {
        return findComponents(MainCategoryItem.class, driver);
    }

    @ComponentCssSelector(".top-menu > li")
    public static class MainCategoryItem extends Component {

        public MainCategoryItem(WebDriver driver, WebElement component) {
            super(driver, component);
        }

        // Mouse hover to top menu item
        public List<CategoryItemComponent> categoryItemComps() {
            Actions actions = new Actions(driver);
            actions.moveToElement(component).perform();
            return findComponents(CategoryItemComponent.class, driver);
        }

        public WebElement categoryItemLinkElem() {
            return component.findElement(By.tagName("a"));
        }
    }

    @ComponentCssSelector(".sublist li a")
    public static class CategoryItemComponent extends Component {

        public CategoryItemComponent(WebDriver driver, WebElement component) {
            super(driver, component);
        }
    }
}
