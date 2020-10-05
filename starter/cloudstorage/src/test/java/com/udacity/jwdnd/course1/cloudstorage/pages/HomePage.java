package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
    @FindBy(id="credential-url-td")
    private WebElement credentialUrlTd;
    @FindBy(id="credential-username-td")
    private WebElement credentialUserNameTd;
    @FindBy(id="credential_password_td")
    private WebElement credentialPasswordTd;
    //

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
        WebDriverWait wait = new WebDriverWait(driver, 1000);

        wait.until(ExpectedConditions.elementToBeClickable(By.id("credential-url")));
        credentialUrl.sendKeys(url);
        credentialUsername.sendKeys(userName);
        credentialPassword.sendKeys(password);
        credentialSaveBtn.click();
    }
    public void openEditCredentialModel(WebDriver driver) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 1000);
        openCredentialsTab(driver);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("edit-credential-btn")));
        editCredentialBtn.click();

        wait.until(webDriver -> webDriver.findElement(By.id("credentialModal")));
        try {
           // WebDriverWait wait2 = new WebDriverWait(driver, 2);
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            System.out.println(driver.switchTo().alert().getText());
            alert.accept();

            wait.until(ExpectedConditions.elementToBeClickable(By.id("credential-url")));
            wait.until(webDriver -> webDriver.findElement(By.id("credential-username")));
            wait.until(webDriver -> webDriver.findElement(By.id("credential-password")));
        } catch (Exception e) {
            //exception handling
        }
    }
    public void editCredential(WebDriver driver, String url, String userName, String password) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 1000);

        wait.until(ExpectedConditions.elementToBeClickable(By.id("credential-url"))).clear();
        credentialUrl.sendKeys(url);
        wait.until(webDriver -> webDriver.findElement(By.id("credential-username"))).clear();
        wait.until(webDriver -> webDriver.findElement(By.id("credential-password"))).clear();

        credentialUsername.sendKeys(userName);
        credentialPassword.sendKeys(password);
        credentialSaveBtn.click();
    }

    public void openCredentialsTab(WebDriver driver){
       WebDriverWait wait = new WebDriverWait(driver, 1000);
        wait.until(webDriver -> webDriver.findElement(By.id("nav-credentials-tab")));
        navCredentialsTab.click();
    }

    public String getCredentailUrlTdDriver(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, 1000);
        wait.until(webDriver -> webDriver.findElement(By.id("credential-url-td")));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("credential-url-td")));
        return getCredentailUrlTd();
    }

    public String getCredentailUrlTd(){
        return credentialUrlTd.getText();
    }
    public String getCredentailUsernameTd(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, 1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("credential-username-td")));
        return credentialUsername.getText();
    }
    public String getCredentailPasswordTd(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, 1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("credential-password-td")));
        return credentialPassword.getText();
    }
    public void deleteCredential(WebDriver driver) throws InterruptedException {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", navCredentialsTab);
        Thread.sleep(3_000);
        deleteCredentialBtn.click();
    }

}
