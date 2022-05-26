package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class HomePage {

    @FindBy(id = "logout")
    private WebElement logoutButton;

    @FindBy(id = "nav-notes-tab")
    private WebElement notesTabButton;

    @FindBy(id = "addNoteButton")
    private WebElement addNoteButton;

    @FindBy(id = "note-title")
    private WebElement noteTitle;

    @FindBy(id = "note-description")
    private WebElement noteDesc;

    @FindBy(id = "addCredentialButton")
    private WebElement addCredButton;

    @FindBy(id="credential-url")
    private WebElement credUrl;

    @FindBy(id="credential-username")
    private WebElement credUsername;

    @FindBy(id="credential-password")
    private WebElement credPassword;

    @FindBy(id = "nav-credentials-tab")
    private WebElement credsTabButton;

    @FindBy(className = "edit-credential")
    private WebElement editFirstCredentialButton;
    @FindBy(className = "delete-credential")
    private WebElement deleteFirstCredentialButton;

    @FindBy(className = "edit-note")
    private WebElement editFirstNoteButton;

    @FindBy(className = "delete-note")
    private WebElement deleteFirstNoteButton;
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void logout() {
        logoutButton.submit();
    }

    public void selectNotesWindow() throws InterruptedException {
        Thread.sleep(500);
        notesTabButton.click();
    }

    public void selectCredentialsWindow() throws InterruptedException {
        Thread.sleep(500);
        credsTabButton.click();
    }

    public void addNote(String title, String desc) throws InterruptedException {
        Thread.sleep(500);
        addNoteButton.click();
        Thread.sleep(500);
        noteTitle.sendKeys(title);
        noteDesc.sendKeys(desc);
        noteTitle.submit();
    }

    public List<Note> getNotes() throws InterruptedException {
        Thread.sleep(500);

        List<WebElement> ids = driver.findElements(By.className("noteId"));
        List<WebElement> titles = driver.findElements(By.className("noteTitle"));
        List<WebElement> descriptions = driver.findElements(By.className("noteDescription"));
        List<Note> noteList = new ArrayList<>();
        for (int i = 0; i < titles.size(); ++i) {
            try {
                Integer id = Integer.parseInt(ids.get(i).getAttribute("value"));
                String title = titles.get(i).getText();
                String desc = descriptions.get(i).getText();
                Note note = new Note();
                note.setNoteId(id);
                note.setNoteTitle(title);
                note.setNoteDescription(desc);
                noteList.add(note);
            } catch (Exception e) {

            }
        }
        return noteList;
    }

    public void updateNote(/*int noteid,*/ String title, String desc) throws InterruptedException {

        editFirstNoteButton.click();
        Thread.sleep(500);
        noteTitle.clear();
        noteTitle.sendKeys(title);
        noteDesc.clear();
        noteDesc.sendKeys(desc);
        noteTitle.submit();
    }



    public void deleteNote() {

        deleteFirstNoteButton.click();

    }


    public void addCred(String url, String username, String password) throws InterruptedException {
        Thread.sleep(500);
        addCredButton.click();
        Thread.sleep(500);
        credUrl.sendKeys(url);
        credUsername.sendKeys(username);
        credPassword.sendKeys(password);
        credUrl.submit();
    }

    public void updateCred( String url, String username, String password) throws InterruptedException {
        editFirstCredentialButton.click();
        Thread.sleep(500);
        credUrl.clear();
        credUrl.sendKeys(url);
        credUsername.clear();
        credUsername.sendKeys(username);
        credPassword.clear();
        credPassword.sendKeys(password);
        credUrl.submit();
    }

    public void deleteCred(/*Integer id*/) {

        deleteFirstCredentialButton.click();
    }
}
