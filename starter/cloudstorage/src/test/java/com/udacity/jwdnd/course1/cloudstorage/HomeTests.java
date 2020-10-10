package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.ResultPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.SignUpPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.openqa.selenium.NoSuchElementException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HomeTests {
    String firstName = "firstname";
    String lastName = "lastName";
    String userName = "userName";
    String password = "password";

    @LocalServerPort
    private int port;

    private WebDriver driver;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        this.driver = new ChromeDriver();
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }

    @Test
    @Order(1)
    public void signUp() throws InterruptedException {
        driver.get("http://localhost:" + this.port + "/signup");

        SignUpPage signUpPage=new SignUpPage(driver);
        signUpPage.signUp(firstName, lastName, userName, password);
        String signupsuccess = signUpPage.getsignUpSuccess();
        Assertions.assertEquals("You successfully signed up!", signupsuccess);

    }

    @Test
    @Order(2)
    public void logoutFromHome() throws InterruptedException {
        driver.get("http://localhost:" + this.port + "/login");
       LoginPage loginPage = new LoginPage(driver);
       loginPage.login(userName, password);
        Thread.sleep(3000);
        Assertions.assertEquals("http://localhost:" + this.port + "/home", driver.getCurrentUrl());
        HomePage homePage = new HomePage(driver);
        homePage.logout();
        Thread.sleep(3000);
        Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());

        Thread.sleep(3000);
        driver.get("http://localhost:" + this.port + "/home");
        Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());

    }
    @Test
    @Order(3)
    public void noteTests() throws InterruptedException {
        driver.get("http://localhost:" + this.port + "/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(userName, password);
        WebDriverWait wait = new WebDriverWait(driver, 1000);
        WebElement marker = wait.until(webDriver -> webDriver.findElement(By.id("home-page")));
        Assertions.assertEquals("http://localhost:" + this.port + "/home", driver.getCurrentUrl());
        HomePage homePage = new HomePage(driver);
        homePage.saveNote(driver,"Note title", "Note desc");

        WebElement marker2 = wait.until(webDriver -> webDriver.findElement(By.id("resultSuccess")));
        ResultPage resultPage=new ResultPage(driver);
        Assertions.assertTrue(resultPage.getResultSuccess().contains("Success"));

        driver.get("http://localhost:" + this.port + "/home");

        marker = wait.until(webDriver -> webDriver.findElement(By.id("home-page")));
        homePage.editNote(driver, "Edited new", "Edited desc");

        WebElement marker3 = wait.until(webDriver -> webDriver.findElement(By.id("resultSuccess")));

        Thread.sleep(3_000);
        Assertions.assertTrue(resultPage.getResultSuccess().contains("success"));

        driver.get("http://localhost:" + this.port + "/home");
        homePage.deleteNote(driver);

        Assertions.assertTrue(resultPage.getResultSuccess().contains("Success"));

    }
    @Test
    @Order(4)
    public void credentialsTests() throws InterruptedException {
        String url = "google.com";
        String urlusername = "user";
        String urlpassword = "password";

        driver.get("http://localhost:" + this.port + "/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(userName, password);
        WebDriverWait wait = new WebDriverWait(driver, 1000);
        wait.until(webDriver -> webDriver.findElement(By.id("home-page")));
        Assertions.assertEquals("http://localhost:" + this.port + "/home", driver.getCurrentUrl());

        HomePage homePage = new HomePage(driver);
        homePage.saveCredential(driver,url, urlusername, urlpassword);

        driver.get("http://localhost:" + this.port + "/home");

        wait.until(webDriver -> webDriver.findElement(By.id("home-page")));

        homePage.openCredentialsTab(driver);
        Assertions.assertEquals("google.com", homePage.getCredentailUrlTdDriver(driver));

        Assertions.assertNotEquals("pass", homePage.getCredentailPasswordTd(driver));

        driver.get("http://localhost:" + this.port + "/home");

        wait.until(webDriver -> webDriver.findElement(By.id("home-page")));
        homePage.openCredentialsTab(driver);

        wait.until(webDriver -> webDriver.findElement(By.id("credentialModal")));
        homePage.editCredential(driver, "google.com", "user", "editedpass");

        driver.get("http://localhost:" + this.port + "/home");
        homePage.openCredentialsTab(driver);
        Assertions.assertEquals("google.com", homePage.getCredentailUrlTdDriver(driver));
        Assertions.assertNotEquals("editedpass", homePage.getCredentailPasswordTd(driver));
        homePage.deleteCredential(driver);
        driver.get("http://localhost:" + this.port + "/home");
        homePage.openCredentialsTab(driver);
        Assertions.assertThrows(NoSuchElementException.class, homePage::getCredentailUrlTd);


    }


}
