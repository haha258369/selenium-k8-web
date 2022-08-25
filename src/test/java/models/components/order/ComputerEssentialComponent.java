package models.components.order;

import models.components.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class ComputerEssentialComponent extends Component {

    public ComputerEssentialComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public abstract String selectProcessorType(String type);
    public abstract String selectRAMType(String type);

    public String selectHDD(String type) {
        return selectComputerOption(type);
    }

    public String selectOS(String type) {
        return selectComputerOption(type);
    }

    protected String selectComputerOption(String type) {
        String selectorString = "//label[contains(text(),\""+ type + "\")]";
        By optionSelector = By.xpath(selectorString);

        // Cach 1
/*        WebElement optionElem = null;

        try {
            optionElem = component.findElement(optionSelector);
        } catch (Exception ignore) {}

        if (optionElem != null) {
            optionElem.click();
            return optionElem.getText();
        } else {
            throw new RuntimeException("[ERR1] The option " + type + " is not existing!");
        }*/

        // Cach 2
        List<WebElement> optionElems = component.findElements(optionSelector);
        if (!optionElems.isEmpty()) {
            WebElement chosenOption = optionElems.get(0);
            chosenOption.click();
            return chosenOption.getText();
        } else {
            throw new RuntimeException("[ERR1] The option " + type + " is not existing!");
        }

    }
}
