package com.udacity.jwdnd.course1.cloudstorage.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class is a selenium page object and represents the login page.
 */
public class LoginPage {

    @FindBy(id = "inputUsername")
    WebElement inputUsername;

    @FindBy(id = "inputPassword")
    WebElement inputPassword;

    @FindBy(id = "signup")
    WebElement signup;

    @FindBy(id = "invalidUsername")
    WebElement invalidUsername;

    @FindBy(id = "logoutMessage")
    WebElement logoutMessage;

    @FindBy(id = "login")
    WebElement login;

    /**
     * Constructor. Initializes all elements.
     *
     * @param driver the WebDriver (Firefox)
     */
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * Types the username into the login form.
     *
     * @param inputUsername the username.
     */
    public void setInputUsername(String inputUsername) {
        this.inputUsername.click();
        this.inputUsername.sendKeys(inputUsername);
    }

    /**
     * Types the password into the login form.
     *
     * @param inputPassword the password.
     */
    public void setInputPassword(String inputPassword) {
        this.inputPassword.click();
        this.inputPassword.sendKeys(inputPassword);
    }

    /**
     * Submits the login.
     */
    public void submit() {
        this.login.click();
    }

    /**
     * Clicks the signup link.
     */
    public void signup(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("signup")));
        this.signup.click();
    }

    /**
     * Specifies whether the login page is visible or not.
     *
     * @return true, if the login page is visible. False if not.
     */
    public boolean isLoginPageVisible() {
        this.inputPassword.click();
        return this.inputPassword.isDisplayed();
    }

}
