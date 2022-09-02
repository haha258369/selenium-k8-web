package test.computer;

import models.components.order.CheapComputerComponent;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.BaseTest;
import test_data.ComputerData;
import test_data.CreditCardType;
import test_data.DataObjectBuilder;
import test_data.PaymentMethod;
import test_flows.computer.OrderComputerFlow;
import url.Urls;

public class BuyingCheapComputerTest extends BaseTest implements Urls {

    @Test(dataProvider = "computerData")
    public void testBuyingCheapComputer(ComputerData computerData) {
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
                .inputPaymentInfo(CreditCardType.VISA)
                .confirmOrder()
                .continueToShopping();
    }

    @DataProvider
    public ComputerData[] computerData() {
        String fileLocation = "/src/test/java/test_data/computer/CheapComputerData.json";
        return DataObjectBuilder.buildDataObjectFrom(fileLocation, ComputerData[].class);
    }
}
