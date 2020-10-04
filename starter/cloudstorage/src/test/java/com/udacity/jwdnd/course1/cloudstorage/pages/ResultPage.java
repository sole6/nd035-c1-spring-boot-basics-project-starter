package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {
    @FindBy(id="resultSuccess")
    private WebElement resultSuccess;

    public ResultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String getResultSuccess(){
        System.out.println(resultSuccess.getText());
        return resultSuccess.getText();
    }
}
