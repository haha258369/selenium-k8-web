package models.components.global.header;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector(".header")
public class HeaderComponent extends Component {

    private final static By shoppingCartLinkSelector = By.cssSelector("#topcartlink");

    public HeaderComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void clickOnShoppingCartLink() {
        WebElement shoppingCartLinkElem = component.findElement(shoppingCartLinkSelector);
        scrollUpToElement(shoppingCartLinkElem);
        shoppingCartLinkElem.click();
    }
}
