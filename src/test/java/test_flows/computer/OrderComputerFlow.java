package test_flows.computer;

import models.components.order.ComputerEssentialComponent;
import models.pages.ComputerItemDetailPage;
import org.openqa.selenium.WebDriver;
import test_data.ComputerData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderComputerFlow <T extends ComputerEssentialComponent> {

    private final WebDriver driver;
    private final Class<T> computerEssentialComponent;
    private final ComputerData computerData;

    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialComp, ComputerData computerData) {
        this.driver = driver;
        this.computerEssentialComponent = computerEssentialComp;
        this.computerData = computerData;
    }

    public void buildComputerSpec() {
        ComputerItemDetailPage computerItemDetailPage = new ComputerItemDetailPage(driver);
        T computerEssentialComp = computerItemDetailPage.computerComp(computerEssentialComponent);

        String processorFullStr = computerEssentialComp.selectProcessorType(computerData.getProcessorType());
        double processorAdditionalPrice = extractAdditionalPrice(processorFullStr);
        System.out.println("processorAdditionalPrice: " + processorAdditionalPrice);

        String ramFullStr = computerEssentialComp.selectRAMType(computerData.getRam());
        double ramAdditionalPrice = extractAdditionalPrice(ramFullStr);
        System.out.println("ramAdditionalPrice: " + ramAdditionalPrice);

        double osAdditionalPrice = 0;
        if (computerData.getOs() != null) {
            String osFullStr = computerEssentialComp.selectOS(computerData.getOs());
            osAdditionalPrice = extractAdditionalPrice(osFullStr);
        }
        System.out.println("osAdditionalPrice: " + osAdditionalPrice);

        double hddAdditionalPrice = 0;
        if (computerData.getHDD() != null) {
            String hddFullStr = computerEssentialComp.selectOS(computerData.getHDD());
            hddAdditionalPrice = extractAdditionalPrice(hddFullStr);
        }
        System.out.println("hddAdditionalPrice: " + hddAdditionalPrice);
    }

    public void addToCart() {

    }

    private double extractAdditionalPrice(String itemStr) {
        double price = 0;
        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(itemStr);
        if (matcher.find()) {
            price = Double.parseDouble(matcher.group(1).replaceAll("[+-]", ""));
        }
        return price;
    }
}
