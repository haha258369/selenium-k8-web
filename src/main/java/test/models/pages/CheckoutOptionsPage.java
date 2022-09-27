package test.models.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOptionsPage extends BasePage{

    private final static By checkoutAsGuestBtnSelector = By.cssSelector(".checkout-as-guest-button");

    public CheckoutOptionsPage(WebDriver driver) {
        super(driver);
    }

    public void checkoutAsGuest() {
        component.findElement(checkoutAsGuestBtnSelector).click();
    }
}
