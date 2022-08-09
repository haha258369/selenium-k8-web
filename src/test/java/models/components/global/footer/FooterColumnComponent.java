package models.components.global.footer;

import models.components.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FooterColumnComponent extends Component {

    public FooterColumnComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    // Pay attention: which locator should be used!
    private final static By headerSelector = By.tagName("h3");
    private final static By linkSelector = By.cssSelector("li a");

    public WebElement headerElem() {
        return component.findElement(headerSelector);
    }

    public List<WebElement> linksElem(){
        return component.findElements(linkSelector);
    }
}
