package net.phptravels.pages;


import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SupplierDashboardPage {
    private WebDriver driver;

    @FindBy(id = "dropdownMenuProfile")
    private WebElement supplierAccountDropdown;

    @FindBy(xpath = "//div[text()='Logout']")
    private WebElement supplierLogoutButton;

    @FindBy(css = "h1.display-4")
    private WebElement dashboardLabel;

    public SupplierDashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getSupplierAccountDropdown() {
        return supplierAccountDropdown;
    }

    public WebElement getDashboardLabel() {
        return dashboardLabel;
    }

    public boolean isDashboardLabelDisplayed() {
        try {
            return dashboardLabel.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickAccountDropdown() {
        supplierAccountDropdown.click();
    }

    public void clickLogoutButton() {
        supplierLogoutButton.click();
    }
    public void supplierLogoutFromDashboard() {
        clickAccountDropdown();
        clickLogoutButton();
    }
}
