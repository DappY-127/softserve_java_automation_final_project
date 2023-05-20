package net.phptravels.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {

    private WebDriver driver;

    @FindBy(css = ".breadcrumb-content .sec__title")
    private WebElement welcomeMessage;

    @FindBy(css = ".author-bio h4")  //".author__title strong"
    private WebElement userName;

    @FindBy(css = ".sidebar-menu [href=\"https://phptravels.net/account/logout\"]")
    private WebElement sidebarLogoutButton;

    @FindBy(css = ".dropdown .btn-primary")
    private WebElement accountDropdown;

    @FindBy(css = ".dropdown-menu [href='https://phptravels.net/account/logout']")
    private WebElement logoutButton;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getWelcomeMessage() {
        return welcomeMessage;
    }

    public WebElement getUserName() {
        return userName;
    }

    public boolean isUsernameDisplayed() {
        try {
            return userName.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isWelcomeMessageDisplayed() {
        try {
            return welcomeMessage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void logoutFromAccountDropdown() {
        clickAccountDropdown();
        clickLogoutButton();
    }

    private void clickLogoutButton() {
        logoutButton.click();
    }

    private void clickAccountDropdown() {
        accountDropdown.click();
    }

    public void logoutFromDashboard() {
        sidebarLogoutButton.click();
    }

}
