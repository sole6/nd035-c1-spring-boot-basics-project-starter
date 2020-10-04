package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage {
    @FindBy(id = "inputFirstName")
    private WebElement inputFirstName;

    @FindBy(id = "inputLastName")
    private WebElement inputLastName;

    @FindBy(id = "inputUsernameSU")
    private WebElement inputUsernameSU;

    @FindBy(id = "inputPasswordSU")
    private WebElement inputPasswordSU;

    @FindBy(id = "signUpBtn")
    private WebElement signUpBtn;

    @FindBy(id = "signUpSuccess")
    private WebElement signUpSuccess;

    @FindBy(id = "goToLogin")
    private WebElement goToLogin;

    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    public String getsignUpSuccess() {
        return signUpSuccess.getText();
    }

    public void goToLogin () {
        goToLogin.click();
    }

    public void signUp(String firstName, String lastName, String userName, String password) {
        inputFirstName.sendKeys(firstName);
        inputLastName.sendKeys(lastName);
        inputUsernameSU.sendKeys(userName);
        inputPasswordSU.sendKeys(password);
        signUpBtn.click();
    }
}
