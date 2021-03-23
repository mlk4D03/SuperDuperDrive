package com.udacity.jwdnd.course1.cloudstorage.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

    // File begin
    @FindBy(id = "logout")
    WebElement logout;

    @FindBy(id = "nav-files-tab")
    WebElement filesTab;

    @FindBy(id = "nav-notes-tab")
    WebElement notesTab;

    @FindBy(id = "nav-credentials-tab")
    WebElement credentialsTab;

    @FindBy(id = "fileUpload")
    WebElement fileDialogButton;

    @FindBy(id = "uploadButton")
    WebElement uploadButton;

    @FindBy(id = "fileTable")
    WebElement fileTable;

    @FindBy(id = "downloadFile")
    WebElement downloadFile;

    @FindBy(id = "deleteButton")
    WebElement deleteButton;

    @FindBy(id = "deleteButtonDisabled")
    WebElement deleteButtonDisabled;
    // File end



}
