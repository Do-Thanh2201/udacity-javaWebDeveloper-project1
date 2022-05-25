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

    public String getMessage() {
        try {
            WebElement e = driver.findElement(By.id("message"));
            String msg = e.getText();
            return msg;
        } catch (Exception e) {
            return null;
        }
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

    public boolean notePresent(Note n, List<Note> notes) {
        for (Note note : notes) {
            if (note.getNoteTitle().equals(n.getNoteTitle())
                    && note.getNoteDescription().equals(n.getNoteDescription()))
                return true;
        }
        return false;
    }

    public void updateNote(int noteid, String title, String desc) throws InterruptedException {
        WebElement editNoteButton = driver.findElement(By.id("edit" + noteid));
        editNoteButton.click();
        Thread.sleep(500);
        noteTitle.clear();
        noteTitle.sendKeys(title);
        noteDesc.clear();
        noteDesc.sendKeys(desc);
        noteTitle.submit();
    }

    public Integer getNoteId(String notetitle, String notedescription, List<Note> notes) {
        for (Note n : notes)
            if (n.getNoteTitle().equals(notetitle) && n.getNoteTitle().equals(notedescription))
                return n.getNoteId();
        return null;
    }

    public void deleteNote(Integer id) throws InterruptedException {
        WebElement delNoteButton = driver.findElement(By.id("delete" + id));
        delNoteButton.click();
        Thread.sleep(500);
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

    public List<Credential> getCreds() throws InterruptedException {
        Thread.sleep(500);

        List<WebElement> ids = driver.findElements(By.className("credentialId"));
        List<WebElement> urls = driver.findElements(By.className("credentialUrl"));
        List<WebElement> usernames = driver.findElements(By.className("credentialUsername"));
        List<WebElement> passwords = driver.findElements(By.className("credentialPassword"));
        List<Credential> creds = new ArrayList<>();
        for (int i = 0; i < urls.size(); ++i) {
            try {
                Integer id = Integer.parseInt(ids.get(i).getAttribute("value"));
                String url = urls.get(i).getText();
                String username = usernames.get(i).getText();
                String password = passwords.get(i).getAttribute("value");
                Credential cred = new Credential();
                cred.setCredentialId(id);
                cred.setUrl(url);
                cred.setUsername(username);
                cred.setPassword(password);
                creds.add(cred);
            } catch (Exception e) {

            }
        }
        return creds;
    }

    public boolean credPresent(Credential c, List<Credential> creds) {
        for (Credential cred : creds) {
            if (cred.getUrl().equals(c.getUrl())
                    && cred.getUsername().equals(c.getUsername()) && cred.getPassword().equals(c.getPassword()))
                return true;
        }
        return false;
    }

    public void updateCred(int credId, String url, String username, String password) throws InterruptedException {
        WebElement editCredButton = driver.findElement(By.id("upd" + credId));
        editCredButton.click();
        Thread.sleep(500);
        credUrl.clear();
        credUrl.sendKeys(url);
        credUsername.clear();
        credUsername.sendKeys(username);
        credPassword.clear();
        credPassword.sendKeys(password);
        credUrl.submit();
    }

    public Integer getCredId(String url, String username, String password, List<Credential> creds) {
        for (Credential cred : creds)
            if (cred.getUrl().equals(url)
                    && cred.getUsername().equals(username) && cred.getPassword().equals(password))
                return cred.getCredentialId();
        return null;
    }

    public void deleteCred(Integer id) throws InterruptedException {
        WebElement delCredButton = driver.findElement(By.id("deleteCrd" + id));
        delCredButton.click();
        Thread.sleep(500);
    }
}
