package test.computer;

import org.openqa.selenium.WebDriver;
import test.models.components.order.StandardComputerComponent;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.BaseTest;
import test.test_data.computer.ComputerData;
import test.test_data.CreditCardType;
import test.test_data.DataObjectBuilder;
import test.test_data.PaymentMethod;
import test_flows.computer.OrderComputerFlow;
import url.Urls;

public class BuyingStandardComputerTest extends BaseTest implements Urls {

    @Test(dataProvider = "computerData")
    public void testBuyingStandardComputer(ComputerData computerData) {
        WebDriver driver = getDriver();
        driver.get(demoBaseUrl.concat(standardComputer));
        OrderComputerFlow<StandardComputerComponent> orderComputerFlow =
                new OrderComputerFlow<>(driver, StandardComputerComponent.class, computerData);
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
    }

    @DataProvider
    public ComputerData[] computerData() {
        String fileLocation = "/src/main/java/test/test_data/computer/StandardComputerData.json";
        return DataObjectBuilder.buildDataObjectFrom(fileLocation, ComputerData[].class);
    }
}
