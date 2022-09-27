package test.global.footer;

import driver.DriverFactory;
import test.models.components.product.ProductItemComponent;
import org.openqa.selenium.WebDriver;
import test.models.pages.HomePage;
import url.Urls;

import java.util.List;

public class FeaturedProductTest {

    public static void main(String[] args) {

        WebDriver driver = DriverFactory.getChromeDriver();
        driver.get(Urls.demoBaseUrl);

        try {
            testFeaturedProductHomePage(driver);
        } catch (Exception e){
            e.printStackTrace();
        }

        driver.quit();
    }

    private static void testFeaturedProductHomePage(WebDriver driver) {
        DriverFactory.getChromeDriver().get(Urls.demoBaseUrl);
        HomePage homePage = new HomePage(driver);

        List<ProductItemComponent> productItemComps =
                homePage.productGridComp().productItemComps();

        productItemComps.forEach(productItemComp -> {
            System.out.println(productItemComp.productTittleElem().getText());
        });
    }

}
