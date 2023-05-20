package net.phptravels.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver driver;

    @FindBy(css = ".form-group [name='email']")
    private WebElement usernameField;

    public WebElement getUsernameField() {
        return usernameField;
    }

    @FindBy(css = ".form-group [name='password']")
    private WebElement passwordField;

    public WebElement getPasswordField() {
        return passwordField;
    }

    @FindBy(xpath = "//span[text()='Login']")
    private WebElement loginButton;

    @FindBy(xpath = "//div/i[text()=' Wrong credentials. try again! ']")
    private  WebElement invalidCredentialsMessage;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getInvalidCredentialsMessage() {
        return invalidCredentialsMessage;
    }

    public WebElement getLoginButton() {
        return loginButton;
    }

    public boolean isInvalidCredentialsErrorMessageDisplayed() {
        try {
            return invalidCredentialsMessage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isLoginButtonDisplayed() {
        try {
            return loginButton.isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void setUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void setPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void login(String username, String password) {
        setUsername(username);
        setPassword(password);
        clickLoginButton();
    }

}
