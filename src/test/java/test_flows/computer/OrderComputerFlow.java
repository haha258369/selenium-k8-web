package test_flows.computer;

import models.components.order.ComputerEssentialComponent;
import models.pages.ComputerItemDetailPage;
import org.openqa.selenium.WebDriver;

public class OrderComputerFlow <T extends ComputerEssentialComponent> {

    private final WebDriver driver;
    private final Class<T> computerEssentialComponent;

    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialComp) {
        this.driver = driver;
        this.computerEssentialComponent = computerEssentialComp;
    }

    public void buildComputerSpec() {
        ComputerItemDetailPage computerItemDetailPage = new ComputerItemDetailPage(driver);
        T computerEssentialComp = computerItemDetailPage.computerComp(computerEssentialComponent);
        computerEssentialComp.selectProcessorType("2.2GHz");
        computerEssentialComp.selectRAMType("4GB");

        try {
            Thread.sleep(3000);
        } catch (Exception ignore) {}
    }

    public void addToCart() {

    }

}
