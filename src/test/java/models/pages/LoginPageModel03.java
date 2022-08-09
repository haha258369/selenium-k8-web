package models.pages;

import models.components.LoginFormComponent;
import org.openqa.selenium.WebDriver;

public class LoginPageModel03 {

    private final WebDriver webDriver;

    public LoginPageModel03(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public LoginFormComponent loginFormComponent() {
        return new LoginFormComponent(webDriver);
    }
}
