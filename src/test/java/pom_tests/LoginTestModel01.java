package pom_tests;

import driver.DriverFactory;
import models.pages.LoginPageModel01;
import org.openqa.selenium.WebDriver;
import url.Urls;

public class LoginTestModel01 implements Urls {

    public static void main(String[] args) {

        // Get a Chrome session
        WebDriver webDriver = DriverFactory.getChromeDriver();

        try {
            // Navigate to base
            webDriver.get(baseUrl + loginSlug);

            LoginPageModel01 loginPage = new LoginPageModel01(webDriver);
            loginPage.username().sendKeys("tomsmith");
            loginPage.password().sendKeys("SuperSecretPassword!");
            loginPage.loginBtn().click();

            Thread.sleep(1000);

        } catch (Exception e){
            e.printStackTrace();
        }

        // Quit driver session
        webDriver.quit();
    }


}
