package com.udacity.jwdnd.course1.cloudstorage.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class is a selenium page object and represents the result page and its elements.
 */
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

    /**
     * Constructor. Initializes all elements.
     *
     * @param driver the WebDriver (Firefox)
     */
    public ResultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * Clicks the success link to get to the login page.
     *
     * @param driver the webdriver for waiting until the element appears.
     */
    public void clickSuccessLink(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("succesLink")));
        this.succesLink.click();
    }

}
