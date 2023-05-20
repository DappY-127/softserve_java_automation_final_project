package net.phptravels.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import net.phptravels.pages.SupplierDashboardPage;
import net.phptravels.pages.SupplierLoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@DisplayName("Supplier user logout tests")
@Epic("Logout")
@Feature("Supplier Logout")
public class SupplierLogoutTest extends BaseTest{

    private SupplierLoginPage supplierLoginPage;
    private SupplierDashboardPage supplierDashboardPage;
    private WebDriver driver;

    @BeforeEach
    public void setupLoginPage() {
        String url = getConfig().getProperty("url") + getConfig().getProperty("url.supplierlogin");
        String supplierLogin = getConfig().getProperty("login.supplier");
        String supplierPassword = getConfig().getProperty("password.supplier");
        driver = getDriver();
        driver.get(url);
        supplierLoginPage = new SupplierLoginPage(driver);
        getWait().until(ExpectedConditions.visibilityOf(supplierLoginPage.getPasswordField()));
        supplierLoginPage.supplierLogin(supplierLogin, supplierPassword);
        supplierDashboardPage = new SupplierDashboardPage(driver);
        getWait().until(ExpectedConditions.visibilityOf(supplierDashboardPage.getSupplierAccountDropdown()));
        getWait().until(ExpectedConditions.visibilityOf(supplierDashboardPage.getDashboardLabel()));
    }
    @Test
    @DisplayName("Supplier logout")
    public void testSupplierCanLogoutFromDashboardAccountDropdown() {
        supplierDashboardPage.supplierLogoutFromDashboard();
        getWait().until(ExpectedConditions.visibilityOf(supplierLoginPage.getLoginButton()));
        getWait().until(ExpectedConditions.elementToBeClickable(supplierLoginPage.getLoginButton()));
     /*   Assertions.assertAll(
                () -> Assertions.assertTrue(driver.getCurrentUrl().contains("/api/supplier"),
                        "URL doesn't contain '/supplier'"),
                () -> Assertions.assertTrue(supplierLoginPage.isLoginButtonDisplayed()),
                () -> Assertions.assertTrue(supplierLoginPage.isLoginButtonClickable(),
                        "Login button not available after logout from account")
        );*/
        Assertions.assertTrue(driver.getCurrentUrl().contains("/api/supplier"),
                "URL doesn't contain '/supplier'");
        Assertions.assertTrue(supplierLoginPage.isLoginButtonClickable(),
                "Login button not available after logout from account");
    }
}
