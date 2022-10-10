package test_flows.computer;

import test.models.components.cart.CartItemRowComponent;
import test.models.components.cart.TotalComponent;
import test.models.components.checkout.*;
import test.models.components.order.ComputerEssentialComponent;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import test.models.pages.*;
import test.test_data.computer.ComputerData;
import test.test_data.CreditCardType;
import test.test_data.DataObjectBuilder;
import test.test_data.PaymentMethod;
import test.test_data.user.UserDataObject;

import java.security.SecureRandom;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderComputerFlow<T extends ComputerEssentialComponent> {

    private static WebDriver driver;
    private final Class<T> computerEssentialComponent;
    private final ComputerData computerData;
    private final int quantity;
    private double totalItemPrice;
    private UserDataObject defaultCheckoutUser;
    private PaymentMethod paymentMethod;
    private CreditCardType creditCardType;

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

    public OrderComputerFlow buildComputerSpec() {
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

        return this;
    }

    public OrderComputerFlow addToCartAndNavigateToShoppingCartPage() {
        ComputerItemDetailPage computerItemDetailPage = new ComputerItemDetailPage(driver);
        T computerEssentialComp = computerItemDetailPage.computerComp(computerEssentialComponent);
        computerEssentialComp
                .clickOnAddToCartBtn()
                .waitUntilItemAddedToCart();

        // Navigate to shopping cart page
        computerItemDetailPage.headerComp().clickOnShoppingCartLink();
        return this;
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


    public OrderComputerFlow verifyShoppingCartPage() {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        List<CartItemRowComponent> cartItemRowComps = isItemDisplay(shoppingCartPage);

        // Verify sub-total
        double currentSubTotal = 0;
        double currentTotalUnitPrices = 0;
        for (CartItemRowComponent cartItemRowComp : cartItemRowComps) {
            currentSubTotal += cartItemRowComp.subTotal();
            currentTotalUnitPrices += cartItemRowComp.unitPrice() * cartItemRowComp.cartPageQuantity();
        }
        Assert.assertEquals(currentSubTotal, currentTotalUnitPrices, "[ERR] Shopping cart's sub-total is incorrect.");

        // Verify total
        verifyTotalPrice(currentSubTotal);
        return this;
    }

    public OrderComputerFlow agreeTOSAndCheckout() {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage.totalComp()
                .agreeTerms()
                .clickOnCheckoutBtn();
        new CheckoutOptionsPage(driver).checkoutAsGuest();
        return this;
    }

    public OrderComputerFlow inputBillingAddress() {
        String defaultCheckoutUserJsonLocation = "/src/main/java/test/test_data/user/DefaultCheckoutUser.json";
        defaultCheckoutUser = DataObjectBuilder.buildDataObjectFrom(defaultCheckoutUserJsonLocation, UserDataObject.class);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        BillingAddressComponent billingAddressComp = checkoutPage.billingAddressComp();
        billingAddressComp
                .selectNewAddress()
                .inputFirstname(defaultCheckoutUser.getFirstname())
                .inputLastname(defaultCheckoutUser.getLastname())
                .inputEmail(defaultCheckoutUser.getEmail())
                .selectCountry(defaultCheckoutUser.getCountry())
                .selectState(defaultCheckoutUser.getState())
                .inputCity(defaultCheckoutUser.getCity())
                .inputAddress1(defaultCheckoutUser.getAdd1())
                .inputZipcode(defaultCheckoutUser.getZipCode())
                .inputPhoneNumber(defaultCheckoutUser.getPhoneNum())
                .clickOnContinueBtn();
        return this;
    }

    public OrderComputerFlow inputShippingAddress() {
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.shippingAddressComp().clickOnContinueBtn();
        return this;
    }

    public OrderComputerFlow selectShippingMethod() {
        List<String> shippingMethods = Arrays.asList("Ground", "Next Day Air", "2nd Day Air");
        String randomMethod = shippingMethods.get(new SecureRandom().nextInt(shippingMethods.size()));
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        ShippingMethodComponent shippingMethodComp = checkoutPage.shippingMethodComp();
        shippingMethodComp
                .selectShippingMethod(randomMethod)
                .clickOnContinueButton();
        return this;
    }

    public OrderComputerFlow selectPaymentMethod() {
        this.paymentMethod = PaymentMethod.COD;
        return this;
    }

    public OrderComputerFlow selectPaymentMethod(PaymentMethod paymentMethod) {
        if (paymentMethod == null) {
            throw new IllegalArgumentException("[ERR] Payment method cannot be null.");
        }
        this.paymentMethod = paymentMethod;

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        PaymentMethodComponent paymentMethodComp = checkoutPage.paymentMethodComp();
        switch (paymentMethod) {
            case CHECK_MONEY_ORDER:
                paymentMethodComp.selectCheckMoneyOrder();
                break;
            case CREDIT_CARD:
                paymentMethodComp.selectCreditCard();
                break;
            case PURCHASE_ORDER:
                paymentMethodComp.selectPurchaseOrder();
                break;
            default:
                paymentMethodComp.selectCOD();
        }
        paymentMethodComp.clickOnContinueBtn();
        return this;
    }

    // Test Card Number: https://www.paypalobjects.com/en_GB/vhelp/paypalmanager_help/credit_card_numbers.htm
    public OrderComputerFlow inputPaymentInfo(CreditCardType creditCardType) {
        this.creditCardType = creditCardType;
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        PaymentInfoComponent paymentInfoComp = checkoutPage.paymentInfoComp();

        switch (paymentMethod) {
            case CHECK_MONEY_ORDER:
                System.out.println("Using check/money order.");
                break;
            case CREDIT_CARD:
                this.creditCardType = creditCardType;
                paymentInfoComp.selectCardType(creditCardType);
                String cardholderFirstname = defaultCheckoutUser.getFirstname();
                String cardholderLastname = defaultCheckoutUser.getLastname();
                paymentInfoComp.inputCardholderName(cardholderFirstname + " " + cardholderLastname);

                switch (creditCardType) {
                    case VISA:
                        paymentInfoComp.inputCardNumber(defaultCheckoutUser.getVisa());
                        break;
                    case MASTER_CARD:
                        paymentInfoComp.inputCardNumber(defaultCheckoutUser.getMastercard());
                        break;
                    case DISCOVER:
                        paymentInfoComp.inputCardNumber(defaultCheckoutUser.getDiscover());
                        break;
                    case AMEX:
                        paymentInfoComp.inputCardNumber(defaultCheckoutUser.getAmex());
                        break;
                }

                // Select current month and next year
                Calendar calendar = new GregorianCalendar();
                paymentInfoComp
                        .inputExpiredMonth(String.valueOf(calendar.get(Calendar.MONTH) + 1))
                        .inputExpiredYear(String.valueOf(calendar.get(Calendar.YEAR) + 1));

                String randomCardCode = String.valueOf(new SecureRandom().nextInt(900) + 100);
                paymentInfoComp.inputCardCode(randomCardCode);
                break;

            case PURCHASE_ORDER:
                String randomPoNumber = String.valueOf(new SecureRandom().nextInt(900) + 100);
                paymentInfoComp.inputPurchaseOrderNumber(randomPoNumber);
                break;
            default:
                paymentInfoComp.verifyCodInfo();
        }
        paymentInfoComp.clickOnContinueBtn();

        try {
            Thread.sleep(3000);
        } catch (Exception e) {
        }

        return this;
    }

    public OrderComputerFlow confirmOrder() {
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        List<CartItemRowComponent> cartItemRowComps = isItemDisplay(checkoutPage);

        // Verify sub-total
        double currentSubTotal = 0;
        double currentTotalUnitPrices = 0;
        for (CartItemRowComponent cartItemRowComp : cartItemRowComps) {
            currentSubTotal += cartItemRowComp.subTotal();
            currentTotalUnitPrices += cartItemRowComp.unitPrice() * cartItemRowComp.checkoutPageQuantity();
        }
        Assert.assertEquals(currentSubTotal, currentTotalUnitPrices, "[ERR] Shopping cart's sub-total is incorrect.");

        // Verify total
        verifyTotalPrice(currentSubTotal);

        ConfirmOrderComponent confirmOrderComp = checkoutPage.confirmOrderComp();
        confirmOrderComp.clickOnConfirmBtn();
        return this;
    }

    public void continueToShopping() {
        CompletedPage completedPage = new CompletedPage(driver);
        OrderCompleteComponent orderCompleteComp = completedPage.orderCompleteComp();
        orderCompleteComp
                .verifyOrderComplete()
                .clickOnContinueBtn();
    }

    private static void verifyTotalPrice(double currentSubTotal) {
        BasePage basePage = new BasePage(driver);
        List<CartItemRowComponent> cartItemRowComps = isItemDisplay(basePage);

        TotalComponent totalComp = basePage.totalComp();
        Map<String, Double> priceCategories = totalComp.priceCategories();
        double checkoutSubTotal = 0;
        double checkoutOtherFeesTotal = 0;
        double checkoutTotal = 0;
        for (String priceType : priceCategories.keySet()) {
            double priceValue = priceCategories.get(priceType);
            if (priceType.startsWith("Sub-Total")) {
                checkoutSubTotal = priceValue;
            } else if (priceType.startsWith("Total")) {
                checkoutTotal = priceValue;
            } else {
                checkoutOtherFeesTotal += priceValue;
            }
        }
        Assert.assertEquals(checkoutSubTotal, currentSubTotal, "[ERR] Sub-total is incorrect.");
        Assert.assertEquals(checkoutTotal, (checkoutSubTotal + checkoutOtherFeesTotal), "[ERR] Total price is incorrect.");
    }

    private static List<CartItemRowComponent> isItemDisplay(BasePage basePage) {
        List<CartItemRowComponent> cartItemRowComps = basePage.cartItemRowComponents();
        if (cartItemRowComps.isEmpty()) {
            Assert.fail("[ERR] There is no item display in the shopping cart.");
        }
        return cartItemRowComps;
    }
}
