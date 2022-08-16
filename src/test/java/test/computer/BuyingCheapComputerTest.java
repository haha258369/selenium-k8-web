package test.computer;

import models.components.order.CheapComputerComponent;
import org.testng.annotations.Test;
import test.BaseTest;
import test_flows.computer.OrderComputerFlow;
import url.Urls;

public class BuyingCheapComputerTest extends BaseTest implements Urls {

    @Test
    public void testBuyingCheapComputer() {
        driver.get(demoBaseUrl.concat(cheapComputer));
        OrderComputerFlow<CheapComputerComponent> orderComputerFlow = new OrderComputerFlow<>(driver, CheapComputerComponent.class);
        orderComputerFlow.buildComputerSpec();
        orderComputerFlow.addToCart();

    }
}
