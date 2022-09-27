package test.global.footer;

import driver.DriverFactory;
import test.models.components.global.footer.*;
import test.models.pages.BasePage;
import test.models.pages.CategoryPage;
import test.models.pages.HomePage;
import test.models.pages.RegisterPage;
import test.models.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import url.Urls;

public class FooterTestLearning {

    public static void main(String[] args) {

        WebDriver driver = DriverFactory.getChromeDriver();
        driver.get(Urls.demoBaseUrl);

        try {
            testFooterHomePage(driver);
            testFooterCategoryPage(driver);
            testFooterRegisterPage(driver);
            testFooterLoginPage(driver);

        } catch (Exception e){
            e.printStackTrace();
        }

        driver.quit();
    }

    private static void testFooterHomePage(WebDriver driver) {
        System.out.println("Home Page");
        HomePage homePage = new HomePage(driver);
        testPageFooter(homePage);

    }

    private static void testFooterCategoryPage(WebDriver driver) {
        System.out.println("Category Page");
        CategoryPage categoryPage = new CategoryPage(driver);
        testPageFooter(categoryPage);
    }

    private static void testFooterRegisterPage(WebDriver driver) {
        System.out.println("Register Page");
        RegisterPage registerPage = new RegisterPage(driver);
        testPageFooter(registerPage);
    }

    private static void testFooterLoginPage(WebDriver driver) {
        System.out.println("Login Page");
        LoginPage loginPage = new LoginPage(driver);
        testPageFooter(loginPage);
    }

    private static void testFooterColumn(FooterColumnComponent footerColumnComp) {
        System.out.println(footerColumnComp.headerElem().getText());
        footerColumnComp.linksElem().forEach(link -> {
            System.out.println(link.getText());
            System.out.println(link.getAttribute("href"));
        });
    }

    private static void testPageFooter(BasePage basePage) {

        InformationColumnComponent informationColumnComp =
                basePage.footerComp().informationColumnComp();

        CustomerServiceColumnComponent customerServiceColumnComp =
                basePage.footerComp().customerServiceColumnComp();

        MyAccountColumnComponent myAccountColumnComp =
                basePage.footerComp().myAccountColumnComp();

        FollowUsColumnComponent followUsColumnComp =
                basePage.footerComp().followUsColumnComp();

        testFooterColumn(informationColumnComp);
        testFooterColumn(customerServiceColumnComp);
        testFooterColumn(myAccountColumnComp);
        testFooterColumn(followUsColumnComp);
    }

}
