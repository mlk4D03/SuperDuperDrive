package com.udacity.jwdnd.course1.cloudstorage.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class is a selenium page object and represents the signup page and its elements.
 */
public class SignupPage {

    @FindBy(id = "backTologinLink")
    WebElement backTologinLink;

    @FindBy(id = "signupSuccess")
    WebElement signupSuccess;

    @FindBy(id = "signupError")
    WebElement signupError;

    @FindBy(id = "inputFirstName")
    WebElement inputFirstName;

    @FindBy(id = "inputLastName")
    WebElement inputLastName;

    @FindBy(id = "inputUsername")
    WebElement inputUsername;

    @FindBy(id = "inputPassword")
    WebElement inputPassword;

    @FindBy(id = "submitButton")
    WebElement submitButton;

    @FindBy(id = "signupSuccessLink")
    WebElement signupSuccessLink;

    public SignupPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * Types the first name into the input field.
     *
     * @param firstname the firstname of the user.
     */
    public void setInputFirstName(String firstname) {
        this.inputFirstName.clear();
        this.inputFirstName.click();
        this.inputFirstName.sendKeys(firstname);
    }

    /**
     * Types in the last name of the user.
     *
     * @param lastname the last name.
     */
    public void setInputLastName(String lastname) {
        this.inputLastName.clear();
        this.inputLastName.click();
        this.inputLastName.sendKeys(lastname);
    }

    /**
     * Types the username into the input field.
     *
     * @param username the username of the user.
     */
    public void setInputUsername(String username) {
        this.inputUsername.clear();
        this.inputUsername.click();
        this.inputUsername.sendKeys(username);
    }

    /**
     * Types the password into the input field.
     *
     * @param password the password.
     */
    public void setInputPassword(String password) {
        this.inputPassword.clear();
        this.inputPassword.click();
        this.inputPassword.sendKeys(password);
    }

    /**
     * Submits the Login inputs.
     */
    public void submit() {
        this.submitButton.click();
    }

    /**
     * Says whether the signup page is visible or not.
     *
     * @return true if the signup page is visible, false if not
     */
    public boolean isSignUpPageVisible() {
        return this.inputFirstName.isDisplayed();
    }


}
