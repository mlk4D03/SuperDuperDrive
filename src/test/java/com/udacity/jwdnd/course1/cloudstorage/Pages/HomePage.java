package com.udacity.jwdnd.course1.cloudstorage.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * This class is a selenium page objects and represents the home page with it's
 * elements and interactions
 */
public class HomePage {

    @FindBy(id = "logout")
    WebElement logout;

    @FindBy(id = "nav-files-tab")
    WebElement filesTab;

    @FindBy(id = "nav-notes-tab")
    WebElement notesTab;

    @FindBy(id = "nav-credentials-tab")
    WebElement credentialsTab;

    @FindBy(id = "buttonAddNote")
    WebElement buttonAddNote;

    @FindBy(xpath = "//table[@id = 'noteTable']/tbody/tr/th")
    List<WebElement> rowsNoteTableWithFirstColumn;

    @FindBy(xpath = "//table[@id = 'noteTable']/tbody/tr")
    List<WebElement> rowsNoteTableWithSecondColumn;

    @FindBy(xpath = "//table/tbody/tr/td")
    List<WebElement> editAndDeleteButtonsNote;

    @FindBy(xpath = "//table[@id = 'credentialTable']/tbody/tr")
    List<WebElement> rowsCredentialTable;

    @FindBy(id = "noteButtonEdit")
    WebElement noteButtonEdit;

    @FindBy(id = "noteButtonEditDisabled")
    WebElement noteButtonEditDisabled;

    @FindBy(id = "noteDeleteButton")
    WebElement noteDeleteButton;

    @FindBy(id = "noteDeleteButtonDisabled")
    WebElement noteDeleteButtonDisabled;

    @FindBy(id = "noteModal")
    WebElement noteModal;

    @FindBy(id = "note-title")
    WebElement noteTitle;

    @FindBy(id = "note-description")
    WebElement noteDescription;

    @FindBy(id = "noteModalSubmit")
    WebElement noteModalSubmit;

    @FindBy(id = "credentialAddButton")
    WebElement credentialAddButton;

    @FindBy(id = "credentialTable")
    WebElement credentialTable;

    @FindBy(id = "credential-url")
    WebElement credentialUrl;

    @FindBy(id = "credential-username")
    WebElement credentialUsername;

    @FindBy(id = "credential-password")
    WebElement credentialPassword;

    @FindBy(id = "credentialSubmitButton")
    WebElement credentialSubmitButton;

    /**
     * Initializes all Elements.
     *
     * @param driver the WebDriver(Firefox)
     */
    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * Says if the Home Page is visible or not.
     *
     * @return True if the home page is visible.
     */
    public boolean isHomePageVisible() {
        return this.logout.isDisplayed();
    }

    /**
     * Clicks the Logout Button on the home page.
     */
    public void logout() {
        this.logout.click();
    }

    /**
     * Clicks on the Note Tab.
     */
    public void toNoteTab() {
        this.notesTab.click();
    }

    /**
     * Clicks on the Credential Tab.
     */
    public void toCredentialTab() {
        this.credentialsTab.click();
    }

    /**
     * Clicks the 'Add Note' Button.
     */
    public void addNote() {
        this.buttonAddNote.click();
    }

    /**
     * Clicks the 'Add Credential' Button.
     */
    public void addCredential() {
        this.credentialAddButton.click();
    }

    /**
     * Sets the URL to the input field in the Credential Modal.
     *
     * @param url the url.
     */
    public void addUrl(String url) {
        this.credentialUrl.click();
        this.credentialUrl.clear();
        this.credentialUrl.sendKeys(url);
    }

    /**
     * Types the username into the input field in the credential dialog.
     *
     * @param username the username.
     */
    public void addUsernameCredential(String username) {
        this.credentialUsername.click();
        this.credentialUsername.clear();
        this.credentialUsername.sendKeys(username);
    }

    /**
     * Types the Credential password into the input field.
     *
     * @param password the password.
     */
    public void addCredentialPassword(String password) {
        this.credentialPassword.clear();
        this.credentialPassword.clear();
        this.credentialPassword.sendKeys(password);
    }

    /**
     * Submits the Credential Inputs.
     */
    public void credentialSubmit() {
        this.credentialPassword.submit();
    }

    /**
     * Types the title of the note into the input field.
     *
     * @param title note title.
     */
    public void addNoteTitle(String title) {
        this.noteTitle.click();
        this.noteTitle.clear();
        this.noteTitle.sendKeys(title);
    }

    /**
     * Types the note description into the input field.
     *
     * @param noteDescription the description.
     */
    public void addNoteDescription(String noteDescription) {
        this.noteDescription.click();
        this.noteDescription.sendKeys(noteDescription);
    }

    /**
     * Submits the note inputs.
     */
    public void submitNote() {
        this.noteTitle.submit();
    }

    /**
     * Extracts the note title from the table.
     *
     * @param row the table row.
     * @return note title.
     */
    public String getNoteTitle(int row) {
        if (row > 0) {
            return this.rowsNoteTableWithFirstColumn.get(row - 1).getText();
        }
        return "";
    }

    /**
     * Extracts the description from the note table.
     *
     * @param row the table row.
     * @return the note description.
     */
    public String getNoteDescription(int row) {
        if (row > 0) {
            return this.rowsNoteTableWithSecondColumn.get(row - 1).findElement(By.id("columnNoteDescription")).getText();
        }
        return "";
    }

    /**
     * Clicks the edit Button in a specific table row to edit a note.
     *
     * @param row the table row.
     */
    public void editNote(int row) {
        if (row > 0) {
            this.editAndDeleteButtonsNote.get(row - 1).findElement(By.id("noteButtonEdit")).click();
        }
    }

    /**
     * Deletes a specific Note.
     *
     * @param row the table row.
     */
    public void deleteNote(int row) {
        this.editAndDeleteButtonsNote.get(row - 1).findElement(By.id("noteDeleteButton")).click();
    }

    /**
     * Says if the Note table is empty or not.
     *
     * @return true if empty, false if not.
     */
    public boolean isNoteTableEmpty() {
        return this.editAndDeleteButtonsNote.isEmpty();
    }

    /**
     * Returns the number of credentials.
     *
     * @param driver the WebDriver for waiting until the table appears.
     * @return the number of rows.
     */
    public int getRowsCredentialTable(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("credentialTable")));
        return this.rowsCredentialTable.size();
    }

    /**
     * Returns the encrypted password of a submitted Credential.
     *
     * @param row the table row.
     * @return the encrypted password.
     */
    public String getCredentialPasswordEnrypted(int row) {
        if (row > 0) {
            return this.rowsCredentialTable.get(row - 1).findElement(By.id("passwordEnrypted")).getText();
        }
        return "";
    }

    /**
     * Clicks the edit Button for editing a credential.
     *
     * @param row the table row.
     */
    public void editCredential(int row) {
        if (row > 0) {
            this.rowsCredentialTable.get(row - 1).findElement(By.id("credentialEditButton")).click();
        }
    }

    /**
     * Returns the unencrypted password.
     *
     * @return the unenrypted password.
     */
    public String getUnenryptedCredentialPassword() {
        return this.credentialPassword.getAttribute("value");
    }

    /**
     * Returns the credential username.
     *
     * @param row the table row.
     * @return the username.
     */
    public String getCredentialUsername(int row) {
        if (row > 0) {
            return this.rowsCredentialTable.get(row - 1).findElement(By.id("credentialUsername")).getText();
        }
        return "";
    }

    /**
     * Returns the url.
     *
     * @param row the table row.
     * @return the url.
     */
    public String getCredentialUrl(int row) {
        if (row > 0) {
            return this.rowsCredentialTable.get(row - 1).findElement(By.id("credentialUrl")).getText();
        }
        return "";
    }

    /**
     * Deletes a credential from the table.
     *
     * @param row the table row.
     */
    public void deleteCredential(int row) {
        if (row > 0 && !this.rowsCredentialTable.isEmpty()) {
            this.rowsCredentialTable.get(row - 1).findElement(By.id("credentialDeleteButton")).click();
        }
    }

    /**
     * Finds out if the credential table is empty
     *
     * @return true for empty, false for not empty
     */
    public boolean idCredentialTableEmpty() {
        return this.rowsCredentialTable.isEmpty();
    }
}
