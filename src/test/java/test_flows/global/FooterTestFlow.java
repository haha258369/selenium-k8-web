package test_flows.global;

import models.components.global.CategoryItemComponent;
import models.components.global.TopMenuComponent;
import static models.components.global.TopMenuComponent.MainCategoryItem;
import models.components.global.footer.FooterColumnComponent;
import models.components.global.footer.FooterComponent;
import models.pages.BasePage;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import url.Urls;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class FooterTestFlow {

    private final WebDriver driver;
    private final String baseUrl = Urls.demoBaseUrl;

    public FooterTestFlow(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyFooterComponent() {
        BasePage basePage = new BasePage(driver);
        FooterComponent footerComp = basePage.footerComp();

        verifyInformationColumn(footerComp.informationColumnComp());
        verifyCustomerServiceColumn(footerComp.customerServiceColumnComp());
        verifyMyAccountColumn(footerComp.myAccountColumnComp());
        verifyFollowUsColumn(footerComp.followUsColumnComp());
    }

    private void verifyInformationColumn(FooterColumnComponent footerColumnComp) {
        List<String> expectedLinkTexts = Arrays.asList(
                "Sitemap", "Shipping & Returns", "Privacy Notice",
                "Conditions of Use", "About us", "Contact us"
        );
        List<String> expectedHrefs = Arrays.asList(
                "/sitemap", "/shipping-returns", "/privacy-policy",
                "/conditions-of-use", "/about-us", "/contactus"
        );
        expectedHrefs.replaceAll(href -> baseUrl + href);
        verifyFooterColumn(footerColumnComp, expectedLinkTexts, expectedHrefs);
    }

    private void verifyCustomerServiceColumn(FooterColumnComponent footerColumnComp) {
        List<String> expectedLinkTexts = Arrays.asList(
                "Search", "News", "Blog",
                "Recently viewed products", "Compare products list", "New products"
        );
        List<String> expectedHrefs = Arrays.asList(
                "/search", "/news", "/blog",
                "/recentlyviewedproducts", "/compareproducts", "/newproducts"
        );
        expectedHrefs.replaceAll(href -> baseUrl + href);
        verifyFooterColumn(footerColumnComp, expectedLinkTexts, expectedHrefs);
    }

    private void verifyMyAccountColumn(FooterColumnComponent footerColumnComp) {
        List<String> expectedLinkTexts = Arrays.asList(
                "My account", "Orders",
                "Addresses", "Shopping cart", "Wishlist"
        );
        List<String> expectedHrefs = Arrays.asList(
                "/customer/info", "/customer/orders",
                "/customer/addresses", "/cart", "/wishlist"
        );
        expectedHrefs.replaceAll(href -> baseUrl + href);
        verifyFooterColumn(footerColumnComp, expectedLinkTexts, expectedHrefs);
    }

    private void verifyFollowUsColumn(FooterColumnComponent footerColumnComp) {
        List<String> expectedLinkTexts = Arrays.asList(
                "Facebook", "Twitter",
                "RSS", "YouTube", "Google+"
                );
        List<String> expectedHrefs = Arrays.asList(
                "http://www.facebook.com/nopCommerce",
                "https://twitter.com/nopCommerce", "http://demowebshop.tricentis.com/news/rss/1",
                "http://www.youtube.com/user/nopCommerce",
                "https://plus.google.com/+nopcommerce"
        );
        verifyFooterColumn(footerColumnComp, expectedLinkTexts, expectedHrefs);
    }

    private void verifyFooterColumn(FooterColumnComponent footerColumnComp,
                                    List<String> expectedLinkTexts, List<String> expectedHrefs) {

        List<String> actualLinkTexts = new ArrayList<>();
        List<String> actualHrefs = new ArrayList<>();

        // Get lists of actual elements
        for (WebElement link : footerColumnComp.linksElem()) {
            actualLinkTexts.add(link.getText().trim());
            actualHrefs.add(link.getAttribute("href"));
        }
        if (actualLinkTexts.isEmpty() || actualHrefs.isEmpty()) {
            Assert.fail("[ERR] There is no Text or Hyperlink in footer column!");
        }

        // Verify link text
        Assert.assertEquals(actualLinkTexts, expectedLinkTexts,
                "[ERR] Actual and expected link texts are different!");

        // Verify hrefs
        Assert.assertEquals(actualHrefs, expectedHrefs, "[ERR] Actual and expected hyperlinks are different!");

    }

    public void verifyProductCategoryFooterComponent() {

        // Random pickup an item on main list
        BasePage basePage = new BasePage(driver);
        TopMenuComponent topMenuComp = basePage.topMenuComp();
        List<MainCategoryItem> mainCategoryElems = topMenuComp.mainItemElems();

        if (mainCategoryElems.isEmpty()) {
            Assert.fail("[ERR] There is no item on top menu!");
        }

        int randomIndex = new SecureRandom().nextInt(mainCategoryElems.size());
        MainCategoryItem randomMainItemElem = mainCategoryElems.get(randomIndex);
        String randomCategoryHref = randomMainItemElem.categoryItemLinkElem().getAttribute("href");

        // Random pickup on sub list
        List<CategoryItemComponent> categoryItemComps = randomMainItemElem.categoryItemComps();
        if (categoryItemComps.isEmpty()){
            randomMainItemElem.categoryItemLinkElem().click();
        } else {
            randomIndex = new SecureRandom().nextInt(categoryItemComps.size());
            CategoryItemComponent randomCategoryItemComp = categoryItemComps.get(randomIndex);
            randomCategoryHref = randomCategoryItemComp.getComponent().getAttribute("href");
            randomCategoryItemComp.getComponent().click();
        }

        // After click on component, wait until hyperlink is present in url
        try {
            basePage.wait.until(ExpectedConditions.urlContains(randomCategoryHref));
        } catch (TimeoutException e) {
            Assert.fail("[ERR] Target page is not matched!");
        }

        // Verify footer component
        verifyFooterComponent();
    }
}