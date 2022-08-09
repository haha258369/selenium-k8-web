package pom_tests;

import driver.DriverFactory;
import models.pages.LoginPageModel02;
import org.openqa.selenium.WebDriver;
import url.Urls;

public class LoginTestModel02 implements Urls {

    public static void main(String[] args) {

        String usernameText = "tomsmith";
        String passwordText = "SuperSecretPassword!";
        
        // Get a Chrome session
        WebDriver webDriver = DriverFactory.getChromeDriver();

        try {

            // Navigate to base
            webDriver.get(baseUrl + loginSlug);

            LoginPageModel02 loginPage = new LoginPageModel02(webDriver);
            loginPage.inputUsername(usernameText);
            loginPage.inputPassword(passwordText);
            loginPage.clickOnLoginBtn();
            
            Thread.sleep(1000);

        } catch (Exception e){
            e.printStackTrace();
        }

        // Quit driver session
        webDriver.quit();
    }
}
