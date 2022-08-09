package models.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPageModel01 {

    private final WebDriver webDriver;
    // Define Selectors
    private final static By usernameSelector = By.id("username");
    private final static By passwordSelector = By.cssSelector("#password");
    private final By loginBtnSelector = By.cssSelector("[type=\"submit\"]");

    public LoginPageModel01(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    // Define element getters
    public WebElement username(){
        return webDriver.findElement(usernameSelector);
    }

    public WebElement password(){
        return webDriver.findElement(passwordSelector);
    }

    public WebElement loginBtn(){
        return webDriver.findElement(loginBtnSelector);
    }


}
