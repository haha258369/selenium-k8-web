package models.components.checkout;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

@ComponentCssSelector("#opc-billing")
public class BillingAddressComponent extends Component {

    private final static By billingAddressDropdownSel = By.id("billing-address-select");
    private final static By firstnameSel = By.id("BillingNewAddress_FirstName");
    private final static By lastnameSel = By.id("BillingNewAddress_LastName");
    private final static By emailSel = By.id("BillingNewAddress_Email");
    private final static By countryDropdownSel = By.id("BillingNewAddress_CountryId");
    private final static By stateDropdownSel = By.id("BillingNewAddress_StateProvinceId");
    private final static By statesLoadingProgressBarSel = By.id("states-loading-progress");
    private final static By citySel = By.id("BillingNewAddress_City");
    private final static By address1Sel = By.id("BillingNewAddress_Address1");
    private final static By zipcodeSel = By.id("BillingNewAddress_ZipPostalCode");
    private final static By phoneNumberSel = By.id("BillingNewAddress_PhoneNumber");
    private final static By continueBtnSel = By.cssSelector(".new-address-next-step-button");

    public BillingAddressComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public BillingAddressComponent selectNewAddress() {
        List<WebElement> billingAddressElems = component.findElements(billingAddressDropdownSel);
        if (!billingAddressElems.isEmpty()){
            Select select = new Select(billingAddressElems.get(0));
            select.selectByVisibleText("New Address");
        }
        return this;
    }

    public BillingAddressComponent inputFirstname(String firstname) {
        component.findElement(firstnameSel).sendKeys(firstname);
        return this;
    }

    public BillingAddressComponent inputLastname(String lastname) {
        component.findElement(lastnameSel).sendKeys(lastname);
        return this;
    }

    public BillingAddressComponent inputEmail(String email) {
        component.findElement(emailSel).sendKeys(email);
        return this;
    }

    public BillingAddressComponent selectCountry(String country) {
        Select select = new Select(component.findElement(countryDropdownSel));
        select.selectByVisibleText(country);
        wait.until(ExpectedConditions.invisibilityOf(component.findElement(statesLoadingProgressBarSel)));
        return this;
    }

    public BillingAddressComponent selectState(String state) {
        Select select = new Select(component.findElement(stateDropdownSel));
        select.selectByVisibleText(state);
        return this;
    }

    public BillingAddressComponent inputCity(String city) {
        component.findElement(citySel).sendKeys(city);
        return this;
    }

    public BillingAddressComponent inputAddress1(String address1) {
        component.findElement(address1Sel).sendKeys(address1);
        return this;
    }

    public BillingAddressComponent inputZipcode(String zipcode) {
        component.findElement(zipcodeSel).sendKeys(zipcode);
        return this;
    }

    public BillingAddressComponent inputPhoneNumber(String phoneNumber) {
        component.findElement(phoneNumberSel).sendKeys(phoneNumber);
        return this;
    }

    public BillingAddressComponent clickOnContinueBtn() {
        WebElement continueBtn = component.findElement(continueBtnSel);
        continueBtn.click();
        wait.until(ExpectedConditions.invisibilityOf(continueBtn));
        return this;
    }
}
