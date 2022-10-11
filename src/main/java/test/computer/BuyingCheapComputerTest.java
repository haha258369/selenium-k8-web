package test.computer;

import org.openqa.selenium.WebDriver;
import test.models.components.order.CheapComputerComponent;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.BaseTest;
import test.test_data.computer.ComputerData;
import test.test_data.CreditCardType;
import test.test_data.DataObjectBuilder;
import test.test_data.PaymentMethod;
import test_flows.computer.OrderComputerFlow;
import url.Urls;

public class BuyingCheapComputerTest extends BaseTest implements Urls {

    @Test(dataProvider = "computerData")
    public void testBuyingCheapComputer(ComputerData computerData) {
        WebDriver driver = getDriver();
        driver.get(demoBaseUrl.concat(cheapComputer));
        OrderComputerFlow<CheapComputerComponent> orderComputerFlow =
                new OrderComputerFlow<>(driver, CheapComputerComponent.class, computerData);
        orderComputerFlow
                .buildComputerSpec()
                .addToCartAndNavigateToShoppingCartPage()
                .verifyShoppingCartPage()
                .agreeTOSAndCheckout()
                .inputBillingAddress()
                .inputShippingAddress()
                .selectShippingMethod()
                .selectPaymentMethod(PaymentMethod.CREDIT_CARD)
                .inputPaymentInfo(CreditCardType.VISA);
//                .confirmOrder();
//                .continueToShopping();
    }

    @DataProvider
    public ComputerData[] computerData() {
        String fileLocation = "\\src\\main\\java\\test\\test_data\\computer\\CheapComputerData.json";
        return DataObjectBuilder.buildDataObjectFrom(fileLocation, ComputerData[].class);
    }
}
