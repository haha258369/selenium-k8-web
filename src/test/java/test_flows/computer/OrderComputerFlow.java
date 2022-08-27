package test_flows.computer;

import models.components.cart.TotalComponent;
import models.components.order.ComputerEssentialComponent;
import models.pages.ComputerItemDetailPage;
import models.pages.ShoppingCartPage;
import org.openqa.selenium.WebDriver;
import test_data.ComputerData;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderComputerFlow <T extends ComputerEssentialComponent> {

    private final WebDriver driver;
    private final Class<T> computerEssentialComponent;
    private final ComputerData computerData;
    private final int quantity;
    private double totalItemPrice;

    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialComp, ComputerData computerData) {
        this.driver = driver;
        this.computerEssentialComponent = computerEssentialComp;
        this.computerData = computerData;
        this.quantity = 1;
    }

    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialComponent, ComputerData computerData, int quantity) {
        this.driver = driver;
        this.computerEssentialComponent = computerEssentialComponent;
        this.computerData = computerData;
        this.quantity = quantity;
    }

    public void buildComputerSpec() {
        ComputerItemDetailPage computerItemDetailPage = new ComputerItemDetailPage(driver);
        T computerEssentialComp = computerItemDetailPage.computerComp(computerEssentialComponent);

        //Unselect all default options
        computerEssentialComp.unselectDefaultOptions();

        String processorFullStr = computerEssentialComp.selectProcessorType(computerData.getProcessorType());
        double processorAdditionalPrice = extractAdditionalPrice(processorFullStr);

        String ramFullStr = computerEssentialComp.selectRamType(computerData.getRam());
        double ramAdditionalPrice = extractAdditionalPrice(ramFullStr);

        double osAdditionalPrice = 0;
        if (computerData.getOS() != null) {
            String osFullStr = computerEssentialComp.selectOS(computerData.getOS());
            osAdditionalPrice = extractAdditionalPrice(osFullStr);
        }

        double hddAdditionalPrice = 0;
        if (computerData.getHDD() != null) {
            String hddFullStr = computerEssentialComp.selectHDD(computerData.getHDD());
            hddAdditionalPrice = extractAdditionalPrice(hddFullStr);
        }

        String softwareFullStr = computerEssentialComp.selectSoftware(computerData.getSoftware());
        double softwareAdditionalPrice = extractAdditionalPrice(softwareFullStr);

        //Calculate item's price
        double basePrice = computerEssentialComp.productPrice();
        double allAdditionalPrice =
                processorAdditionalPrice + ramAdditionalPrice + hddAdditionalPrice + osAdditionalPrice + softwareAdditionalPrice;
        totalItemPrice = (basePrice + allAdditionalPrice) * quantity;

        System.out.println("processorAdditionalPrice: " + processorAdditionalPrice);
        System.out.println("ramAdditionalPrice: " + ramAdditionalPrice);
        System.out.println("osAdditionalPrice: " + osAdditionalPrice);
        System.out.println("hddAdditionalPrice: " + hddAdditionalPrice);
        System.out.println("softwareAdditionalPrice: " + softwareAdditionalPrice);
        System.out.println(basePrice);
        System.out.println(allAdditionalPrice);
        System.out.println(totalItemPrice);
    }

    public void addToCartAndNavigateToShoppingCartPage() {
        ComputerItemDetailPage computerItemDetailPage = new ComputerItemDetailPage(driver);
        T computerEssentialComp = computerItemDetailPage.computerComp(computerEssentialComponent);
        computerEssentialComp.clickOnAddToCartBtn();
        computerEssentialComp.waitUntilItemAddedToCart();

        // Navigate to shopping cart page
        computerItemDetailPage.headerComp().clickOnShoppingCartLink();
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


    public void verifyShoppingCartPage() {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        TotalComponent totalComp = shoppingCartPage.totalComp();
        Map<String, Double> priceCategories = totalComp.priceCategories();
        for (String priceType : priceCategories.keySet()) {
            System.out.println(priceType + ": " + priceCategories.get(priceType));
        }
    }
}
