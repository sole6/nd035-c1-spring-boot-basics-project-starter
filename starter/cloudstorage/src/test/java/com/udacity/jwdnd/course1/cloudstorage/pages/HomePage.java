package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePage {

    @FindBy(id = "logoutBtnHM")
    private WebElement logoutBtnHM;
    @FindBy(id = "nav-notes-tab")
    private WebElement navNotesTab;

    @FindBy(id="add-note-btn")
    private WebElement addNoteBtn;

    @FindBy(id="note-title")
    private  WebElement noteTitle;

    @FindBy(id="note-description")
    private WebElement noteDescription;

    @FindBy(id="note-save-btn")
    private WebElement saveNoteBtn;

    @FindBy(id="edit-note-btn")
    private WebElement editNoteBtn;

    @FindBy(id="delete-note-btn")
    private WebElement deleteNoteBtn;

    @FindBy(id="noteTable")
    private WebElement noteTable;

    @FindBy(id="nav-credentials-tab")
    private WebElement navCredentialsTab;

    @FindBy(id="add-credential-btn")
    private WebElement addCredentialBtn;
    @FindBy(id="credential-url")
    private WebElement credentialUrl;
    @FindBy(id="credential-username")
    private WebElement credentialUsername;
    @FindBy(id="credential-password")
    private WebElement credentialPassword;
    @FindBy(id="credential-save-btn")
    private WebElement credentialSaveBtn;
    @FindBy(id="edit-credential-btn")
    private WebElement editCredentialBtn;
    @FindBy(id="delete-credential-btn")
    private WebElement deleteCredentialBtn;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void logout() {
        logoutBtnHM.click();
    }

    public void saveNote(WebDriver driver, String title, String description) throws InterruptedException {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", navNotesTab);
        Thread.sleep(3_000);
        addNoteBtn.click();
        Thread.sleep(3_000);
        noteTitle.sendKeys(title);
        noteDescription.sendKeys(description);
        saveNoteBtn.click();
    }
    public void editNote(WebDriver driver, String newTitle, String newDes) throws InterruptedException {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", navNotesTab);
        Thread.sleep(3_000);

        executor.executeScript("arguments[0].click();", editNoteBtn);
        Thread.sleep(3_000);
        noteTitle.clear();
        noteTitle.sendKeys(newTitle);
        noteDescription.clear();
        noteDescription.sendKeys(newDes);
        saveNoteBtn.click();
    }
    public void deleteNote(WebDriver driver) throws InterruptedException {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", navNotesTab);
        Thread.sleep(3_000);
        deleteNoteBtn.click();
    }

    public void saveCredential(WebDriver driver, String url, String userName, String password) throws InterruptedException {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", navCredentialsTab);
        Thread.sleep(3_000);
        addCredentialBtn.click();
        Thread.sleep(3_000);
        credentialUrl.sendKeys(url);
        credentialUsername.sendKeys(userName);
        credentialPassword.sendKeys(password);
        credentialSaveBtn.click();
    }
    public void editCredential(WebDriver driver, String url, String userName, String password) throws InterruptedException {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", navCredentialsTab);
        Thread.sleep(3_000);
        editCredentialBtn.click();
        Thread.sleep(3_000);
        credentialUrl.clear();
        credentialUrl.sendKeys(url);
        credentialUsername.clear();
        credentialUsername.sendKeys(userName);
        credentialPassword.clear();
        credentialPassword.sendKeys(password);
        credentialSaveBtn.click();
    }
    public void deleteCredential(WebDriver driver) throws InterruptedException {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", navCredentialsTab);
        Thread.sleep(3_000);
        deleteCredentialBtn.click();
    }

}
