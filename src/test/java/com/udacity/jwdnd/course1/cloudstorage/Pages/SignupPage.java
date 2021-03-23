package com.udacity.jwdnd.course1.cloudstorage.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public SignupPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void setInputFirstName(String firstname){
        this.inputFirstName.sendKeys(firstname);
    }

    public void setInputLastName(String lastname){
        this.inputLastName.sendKeys(lastname);
    }

    public void setInputUsername(String username){
        this.inputUsername.sendKeys(username);
    }

    public void setInputPassword(String password){
        this.inputPassword.sendKeys(password);
    }

    public void submit(){
        this.submitButton.click();
    }

    public void backToLogin(){
        this.backTologinLink.click();
    }

    public void toLoginAfterSignUpSuccess(){
        this.signupSuccessLink.click();
    }

    public String getSignUpSuccessMessage(){
        return this.signupSuccess.getText();
    }

    public String getSignUpErrorMessage(){
        this.signupError.getText();
    }
}
