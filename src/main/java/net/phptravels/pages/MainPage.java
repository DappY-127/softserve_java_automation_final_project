package net.phptravels.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    private WebDriver driver;

    @FindBy(css = ".dropdown .btn-primary")
    private WebElement accountDropdown;

    @FindBy(css = ".dropdown-item[href=\"https://phptravels.net/account/logout\"]")
    private WebElement logoutButton;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
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
}
