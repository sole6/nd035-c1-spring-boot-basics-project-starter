package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "loginErrorMessage")
    private WebElement loginErrorMessage;

    @FindBy(id = "loginButton")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    public String loginFail() {
        return loginErrorMessage.getText();
    }

    public void login(String userName, String password) {
        inputUsername.sendKeys(userName);
        inputPassword.sendKeys(password);
        loginButton.click();
    }
}
