package test.models.components.global.footer;

import test.models.components.Component;
import test.models.components.ComponentCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector(value = ".footer")
public class FooterComponent extends Component {

    public FooterComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public InformationColumnComponent informationColumnComp(){
        return findComponent(InformationColumnComponent.class, driver);
    }

    public CustomerServiceColumnComponent customerServiceColumnComp(){
        return findComponent(CustomerServiceColumnComponent.class, driver);
    }

    public MyAccountColumnComponent myAccountColumnComp(){
        return findComponent(MyAccountColumnComponent.class, driver);
    }

    public FollowUsColumnComponent followUsColumnComp() {
        return findComponent(FollowUsColumnComponent.class, driver);
    }
}
