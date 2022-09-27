package test.models.components.order;

import test.models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

@ComponentCssSelector(".product-essential")
public class StandardComputerComponent extends ComputerEssentialComponent{

    private static final By productAttributeSelector = By.cssSelector("select[id^=\"product_attribute\"]");

    public StandardComputerComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    @Override
    public String selectProcessorType(String type) {
        final int PROCESSOR_DROPDOWN_INDEX = 0;
        WebElement processorDropdownElem = component.findElements(productAttributeSelector).get(PROCESSOR_DROPDOWN_INDEX);
        return selectOption(processorDropdownElem, type);
    }

    @Override
    public String selectRamType(String type) {
        final int RAM_DROPDOWN_INDEX = 1;
        WebElement ramDropdownElem = component.findElements(productAttributeSelector).get(RAM_DROPDOWN_INDEX);
        return selectOption(ramDropdownElem, type);
    }

    private String selectOption(WebElement dropdownElem, String type) {
        Select select = new Select(dropdownElem);
        List<WebElement> allOptions = select.getOptions();
        String fullStringOption = null;

        for (WebElement option : allOptions) {
            String currentOptionText = option.getText();
            String optionTextNoSpaces = currentOptionText.trim().replace(" ", "");
            if (optionTextNoSpaces.startsWith(type)) {
                fullStringOption = currentOptionText;
                break;
            }
        }

        if (fullStringOption == null) {
            throw new RuntimeException("[ERR2] The option " + type + " is not existing!");
        }

        select.selectByVisibleText(fullStringOption);
        return fullStringOption;
    }
}
