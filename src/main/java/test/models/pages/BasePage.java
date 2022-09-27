package test.models.pages;

import test.models.components.Component;
import test.models.components.cart.CartItemRowComponent;
import test.models.components.cart.TotalComponent;
import test.models.components.global.header.TopMenuComponent;
import test.models.components.global.footer.FooterComponent;
import test.models.components.global.header.HeaderComponent;
import test.models.components.product.ProductGridComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class BasePage extends Component {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        super(driver, driver.findElement(By.tagName("html")));
        this.driver = driver;
    }

    public HeaderComponent headerComp() {
        return findComponent(HeaderComponent.class, driver);
    }

    public TopMenuComponent topMenuComp() {
        return findComponent(TopMenuComponent.class, driver);
    }

    public ProductGridComponent productGridComp() {
        return findComponent(ProductGridComponent.class, driver);
    }

    public FooterComponent footerComp(){
        return findComponent(FooterComponent.class, driver);
    }

    public List<CartItemRowComponent> cartItemRowComponents() {
        return findComponents(CartItemRowComponent.class, driver);
    }

    public TotalComponent totalComp() {
        return findComponent(TotalComponent.class, driver);
    }
}
