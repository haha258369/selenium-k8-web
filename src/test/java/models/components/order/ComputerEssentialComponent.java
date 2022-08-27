package models.components.order;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class ComputerEssentialComponent extends BaseItemDetailComponent {

    private final static By allOptionsSelector = By.cssSelector(".option-list input");
    public abstract String selectProcessorType(String type);
    public abstract String selectRamType(String type);
    public String selectHDD(String type) {
        return selectComputerOption(type);
    }

    public String selectOS(String type) {
        return selectComputerOption(type);
    }
    public String selectSoftware(String type) {
        return selectComputerOption(type);
    }

    public ComputerEssentialComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void unselectDefaultOptions() {
        List<WebElement> allOptionElems = component.findElements(allOptionsSelector);
        allOptionElems.forEach(option -> {
            if (option.getAttribute("checked") != null) {
                option.click();
            }
        });
    }

    protected String selectComputerOption(String type) {
        String selectorString = "//label[contains(text(),\"" + type + "\")]";
        By optionSelector = By.xpath(selectorString);

        List<WebElement> optionElems = component.findElements(optionSelector);
        if (!optionElems.isEmpty()) {
            WebElement chosenOption = optionElems.get(0);
            chosenOption.click();
            return chosenOption.getText();
        } else {
            throw new RuntimeException("[ERR] The option " + type + " is not existing.");
        }
    }
}

