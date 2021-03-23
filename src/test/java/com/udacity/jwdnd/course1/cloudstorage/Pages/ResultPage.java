package com.udacity.jwdnd.course1.cloudstorage.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {

    @FindBy(id = "succes")
    WebElement succes;

    @FindBy(id = "succesLink")
    WebElement succesLink;

    @FindBy(id = "error")
    WebElement error;

    @FindBy(id = "firstError")
    WebElement firstError;

    @FindBy(id = "firstErrorLink")
    WebElement firstErrorLink;

    @FindBy(id = "secondError")
    WebElement secondError;

    @FindBy(id = "errorMessage")
    WebElement errorMessage;

    @FindBy(id = "secondErrorLink")
    WebElement secondErrorLink;

    public ResultPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public String getSuccessMessage(){
        return this.succes.getText();
    }

    public void clickSuccessLink(){
        this.succesLink.click();
    }

    public String getFirstError(){
        return this.firstError.getText();
    }

    public void clickFirstErrorLink(){
        this.firstErrorLink.click();
    }

    public String getSecondError(){
        return this.secondError.getText();
    }

    public String getSecondErrorMessage(){
        return this.errorMessage.getText();
    }

    public void clickSecondErrorLink(){
        this.secondErrorLink.click();
    }


}
