package support.ui;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class SelectExt extends Select {

    // For maintenance
    private final String OPTION_2 = "Option 2";

    public SelectExt(WebElement element) {
        super(element);
    }

    public void selectOption2() {
        selectByVisibleText(OPTION_2);
    }
}
