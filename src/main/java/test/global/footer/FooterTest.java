package test.global.footer;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.BaseTest;
import test_flows.global.FooterTestFlow;
import url.Urls;

public class FooterTest extends BaseTest {

    @Test
    public void testFooterCategoryPage() {
        WebDriver driver = getDriver();
        driver.get(Urls.demoBaseUrl);
        FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
        footerTestFlow.verifyProductCategoryFooterComponent();
    }

    @Test
    public void testFooterHomePage() {
        WebDriver driver = getDriver();
        driver.get(Urls.demoBaseUrl);
        FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
        footerTestFlow.verifyFooterComponent();
    }

    @Test
    public void testFooterRegisterPage() {
        WebDriver driver = getDriver();
        driver.get(Urls.demoBaseUrl + Urls.registerSlug);
        FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
        footerTestFlow.verifyFooterComponent();
    }

    @Test
    public void testFooterLoginPage() {
        WebDriver driver = getDriver();
        driver.get(Urls.demoBaseUrl + Urls.loginSlug);
        FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
        footerTestFlow.verifyFooterComponent();
    }
}
