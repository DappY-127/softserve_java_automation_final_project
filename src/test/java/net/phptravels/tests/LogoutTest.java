package net.phptravels.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import net.phptravels.pages.DashboardPage;
import net.phptravels.pages.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@DisplayName("Customer and Agent users logout tests")
@Epic("Logout")
@Feature("Customer and Agent Logout")
public class LogoutTest extends BaseTest{
    private LoginPage loginPage;
    private String customerLogin;
    private String customerPassword;
    private String agentLogin;
    private String agentPassword;
    private WebDriver driver;

    @BeforeEach
    public void setupLoginPage() {
        String url = getConfig().getProperty("url") + getConfig().getProperty("url.login");
        customerLogin = getConfig().getProperty("login.customer");
        customerPassword = getConfig().getProperty("password.customer");
        agentLogin = getConfig().getProperty("login.agent");
        agentPassword = getConfig().getProperty("password.agent");
        driver = getDriver();
        driver.get(url);
        loginPage = new LoginPage(driver);
    }

    @Test
    @DisplayName("Customer logout from dashboard sidebar")
    public void testCustomerCanLogoutFromDashboard() {
        loginPage.login(customerLogin, customerPassword);
        DashboardPage dashboardPage = new DashboardPage(driver);
        getWait().until(ExpectedConditions.visibilityOf(dashboardPage.getUserName()));
        getWait().until(ExpectedConditions.elementToBeClickable(dashboardPage.getWelcomeMessage()));
        dashboardPage.logoutFromDashboard();
        Assertions.assertAll(
                () -> Assertions.assertTrue(driver.getCurrentUrl().contains("/login")),
                () -> Assertions.assertTrue(loginPage.isLoginButtonDisplayed())
        );
    }

    @Test
    @DisplayName("Customer logout from account dashboard")
    public void testCustomerCanLogoutFromDashboardAccountDropdown() {
        loginPage.login(customerLogin, customerPassword);
        DashboardPage dashboardPage = new DashboardPage(driver);
        getWait().until(ExpectedConditions.visibilityOf(dashboardPage.getUserName()));
        getWait().until(ExpectedConditions.elementToBeClickable(dashboardPage.getWelcomeMessage()));
        dashboardPage.logoutFromAccountDropdown();
        Assertions.assertAll(
                () -> Assertions.assertTrue(driver.getCurrentUrl().contains("/login")),
                () -> Assertions.assertTrue(loginPage.isLoginButtonDisplayed())
        );
    }

    @Test
    @DisplayName("Agent logout from dashboard sidebar")
    public void testAgentCanLogoutFromDashboard() {
        loginPage.login(agentLogin, agentPassword);
        DashboardPage dashboardPage = new DashboardPage(driver);
        getWait().until(ExpectedConditions.visibilityOf(dashboardPage.getUserName()));
        getWait().until(ExpectedConditions.elementToBeClickable(dashboardPage.getWelcomeMessage()));
        dashboardPage.logoutFromDashboard();
        Assertions.assertAll(
                () -> Assertions.assertTrue(driver.getCurrentUrl().contains("/login")),
                () -> Assertions.assertTrue(loginPage.isLoginButtonDisplayed())
        );
    }

    @Test
    @DisplayName("Agent logout from account dashboard")
    public void testAgentCanLogoutFromAccountDropdown() {
        loginPage.login(agentLogin, agentPassword);
        DashboardPage dashboardPage = new DashboardPage(driver);
        getWait().until(ExpectedConditions.visibilityOf(dashboardPage.getUserName()));
        getWait().until(ExpectedConditions.elementToBeClickable(dashboardPage.getWelcomeMessage()));
        dashboardPage.logoutFromAccountDropdown();
        Assertions.assertAll(
                () -> Assertions.assertTrue(driver.getCurrentUrl().contains("/login")),
                () -> Assertions.assertTrue(loginPage.isLoginButtonDisplayed())
        );
    }
}
