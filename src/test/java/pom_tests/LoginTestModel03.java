package pom_tests;

import driver.DriverFactory;
import models.components.LoginFormComponent;
import models.pages.LoginPageModel03;
import org.openqa.selenium.WebDriver;
import url.Urls;

public class LoginTestModel03 implements Urls {

    public static void main(String[] args) {

        String usernameText = "tomsmith";
        String passwordText = "SuperSecretPassword!";
        // Get a Chrome session
        WebDriver webDriver = DriverFactory.getChromeDriver();

        try {
            // Navigate to target base
            webDriver.get(baseUrl + loginSlug);

            LoginPageModel03 loginPage = new LoginPageModel03(webDriver);
            LoginFormComponent loginFormComponent = loginPage.loginFormComponent();
            loginFormComponent.inputUsername(usernameText);
            loginFormComponent.inputPassword(passwordText);
            loginFormComponent.clickOnLoginBtn();

            Thread.sleep(1000);

        } catch (Exception e){
            e.printStackTrace();
        }

        // Quit driver session
        webDriver.quit();
    }

}
