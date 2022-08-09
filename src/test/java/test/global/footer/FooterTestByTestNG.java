package test.global.footer;

import models.components.global.footer.*;
import models.pages.BasePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class FooterTestByTestNG {

    @Test(priority = 2)
    public void testFooterHomePage() {
        String actualResult = "aaa";
        String expectedResult = "bbb";
//        Verifier.verifyEquals(actualResult, expectedResult);

        Assert.assertTrue(actualResult.equals(expectedResult), "[ERR] They are different!");
        Assert.assertFalse(actualResult.equals(expectedResult), "[ERR] They are different");
        Assert.assertEquals(actualResult, expectedResult, "[ERR] Message is incorrect!");
        Assert.fail();
        Assert.fail("[ERR]");
    }

    @Test(priority = 1)
    public void testFooterCategoryPage() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(1,2);
        softAssert.assertEquals(2,2);
        softAssert.assertEquals(2,3);

//        softAssert.assertAll();

        System.out.println("Hello");
    }

    @Test
    public void testFooterRegisterPage() {
    }

    @Test
    public void testFooterLoginPage() {
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
