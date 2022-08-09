package test.global.footer;

import driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import test_flows.global.FooterTestFlow;
import url.Urls;

public class FooterTestLab22 implements Urls {

    @Test
    public void testFooterCategoryPage() {
        WebDriver driver = DriverFactory.getChromeDriver();

        try {
            driver.get(demoBaseUrl);
            FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
            footerTestFlow.verifyProductCategoryFooterComponent();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    @Test
    public void testFooterHomePage() {
        WebDriver driver = DriverFactory.getChromeDriver();

        try {
            driver.get(demoBaseUrl);
            FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
            footerTestFlow.verifyFooterComponent();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    @Test
    public void testFooterRegisterPage() {
        WebDriver driver = DriverFactory.getChromeDriver();

        try {
            driver.get(demoBaseUrl + registerSlug);
            FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
            footerTestFlow.verifyFooterComponent();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    @Test
    public void testFooterLoginPage() {
        WebDriver driver = DriverFactory.getChromeDriver();

        try {
            driver.get(demoBaseUrl + loginSlug);
            FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
            footerTestFlow.verifyFooterComponent();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
