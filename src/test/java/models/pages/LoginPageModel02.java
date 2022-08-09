package models.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPageModel02 {

    // Define selectors
    private final static By usernameSelector = By.id("username");
    private final static By passwordSelector = By.cssSelector("#password");
    private final static By loginBtnSelector = By.cssSelector("[type=\"submit\"]");

    private final WebDriver webDriver;

    public LoginPageModel02(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void inputUsername(String usernameText){
        webDriver.findElement(usernameSelector).sendKeys(usernameText);
    }

    public void inputPassword(String passwordText){
        webDriver.findElement(passwordSelector).sendKeys(passwordText);
    }

    public void clickOnLoginBtn(){
        webDriver.findElement(loginBtnSelector).click();
    }
}
