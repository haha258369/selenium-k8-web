package test.computer;

import models.components.order.StandardComputerComponent;
import org.testng.annotations.Test;
import test.BaseTest;
import test_flows.computer.OrderComputerFlow;
import url.Urls;

public class BuyingStandardComputerTest extends BaseTest implements Urls {

    @Test
    public void testBuyingStandardComputer() {
        driver.get(demoBaseUrl.concat(standardComputer));
        OrderComputerFlow<StandardComputerComponent> orderComputerFlow = new OrderComputerFlow<>(driver, StandardComputerComponent.class);
        orderComputerFlow.buildComputerSpec();
        orderComputerFlow.addToCart();
    }
}
