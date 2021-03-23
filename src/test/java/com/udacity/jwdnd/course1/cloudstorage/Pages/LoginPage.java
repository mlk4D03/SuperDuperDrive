package com.udacity.jwdnd.course1.cloudstorage.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void setInputUsername(String inputUsername) {
        this.inputUsername.sendKeys(inputUsername);
    }

    public void setInputPassword(String inputPassword) {
        this.inputPassword.sendKeys();
    }

    public void submit(){
        this.inputPassword.submit();
    }

    public void signup(){
        this.signup.click();
    }

    public String getLogoutMessage(){
        return this.logoutMessage.getText();
    }

    public String getInvalidUsernameMessage(){
        return this.invalidUsername.getText();
    }

}
