package test.global.footer;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.BaseTest;
import test_flows.global.FooterTestFlow;
import url.Urls;

public class FooterTestLab22 extends BaseTest {

    @Test
    public void testFooterCategoryPage() {
        driver.get(Urls.demoBaseUrl);
        FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
        footerTestFlow.verifyProductCategoryFooterComponent();
        Assert.fail("It's a test!");
    }

    @Test
    public void testFooterHomePage() {
        driver.get(Urls.demoBaseUrl);
        FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
        footerTestFlow.verifyFooterComponent();
    }

    @Test
    public void testFooterRegisterPage() {
        driver.get(Urls.demoBaseUrl + Urls.registerSlug);
        FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
        footerTestFlow.verifyFooterComponent();
    }

    @Test
    public void testFooterLoginPage() {
        driver.get(Urls.demoBaseUrl + Urls.loginSlug);
        FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
        footerTestFlow.verifyFooterComponent();
    }
}
