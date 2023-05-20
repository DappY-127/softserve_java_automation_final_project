package net.phptravels.tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import net.phptravels.pages.DashboardPage;
import net.phptravels.pages.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@DisplayName("Customer and Agent users login tests")
@Epic("Login")
@Feature("Customer and Agent Login")
public class LoginTest extends BaseTest{
    private LoginPage loginPage;
    private  String customerLogin;
    private  String customerPassword;
    private  String agentLogin;
    private  String agentPassword;
    private WebDriver driver;

    @BeforeEach
    public void navigateToLoginPage() {
        String url = getConfig().getProperty("url") + getConfig().getProperty("url.login");
        customerLogin = getConfig().getProperty("login.customer");
        customerPassword = getConfig().getProperty("password.customer");
        agentLogin = getConfig().getProperty("login.agent");
        agentPassword = getConfig().getProperty("password.agent");
        driver = getDriver();
        driver.get(url);
        loginPage = new LoginPage(driver);
        getWait().until(ExpectedConditions.visibilityOf(loginPage.getLoginButton()));
    }

    @Test
    @DisplayName("Customer login with valid credentials")
    public void testCustomerCanLoginWithValidCredentials() {
        Allure.addAttachment("Step 1", "Perform login with valid credentials");
        loginPage.login(customerLogin, customerPassword);
        DashboardPage dashboardPage = new DashboardPage(driver);
        // Wait for dashboard page to load
        Allure.addAttachment("Step 2", "Wait for the dashboard page to load");
        getWait().until(ExpectedConditions.visibilityOf(dashboardPage.getUserName()));
        getWait().until(ExpectedConditions.elementToBeClickable(dashboardPage.getWelcomeMessage()));
        // Verify that the username and welcome message are displayed
        Allure.addAttachment("Step 3", "Verify username and welcome message");
        Assertions.assertAll(
                () -> Assertions.assertTrue(driver.getCurrentUrl().contains("/dashboard"),
                        "URL doesn't contain '/dashboard'"),
                () -> Assertions.assertTrue(dashboardPage.isUsernameDisplayed(),
                        "Customer username is not displayed on user dashboard page after login"),
                () -> Assertions.assertTrue(dashboardPage.isWelcomeMessageDisplayed(),
                        "Welcome message is not displayed")
        );
    }

    @Test
    @DisplayName("Agent login with valid credentials")
    public void testAgentCanLoginWithValidCredentials() {
        loginPage.login(agentLogin, agentPassword);
        DashboardPage dashboardPage = new DashboardPage(driver);
        // Wait for dashboard page to load
        getWait().until(ExpectedConditions.visibilityOf(dashboardPage.getUserName()));
        getWait().until(ExpectedConditions.elementToBeClickable(dashboardPage.getWelcomeMessage()));
        // Verify that the username and welcome message are displayed
        Assertions.assertAll(
                () -> Assertions.assertTrue(driver.getCurrentUrl().contains("/dashboard"),
                        "URL doesn't contain '/dashboard'"),
                () -> Assertions.assertTrue(dashboardPage.isUsernameDisplayed(),
                        "Agent username is not displayed on user dashboard page after login"),
                () -> Assertions.assertTrue(dashboardPage.isWelcomeMessageDisplayed(),
                        "Welcome message is not displayed")
        );
    }

    @Test
    @DisplayName("Invalid login with blank email and password")
    public void testInvalidLoginWithBlankEmailAndPassword() {
        loginPage.login("", "");
        Assertions.assertFalse(driver.getCurrentUrl().contains("/dashboard"),
                "Login is successful with blank email and password");
    }

    @Test
    @DisplayName("Invalid login with incorrect email format")
    public void testInvalidLoginWithIncorrectEmailFormat() {
        loginPage.login("phptravels.com", getConfig().getProperty("password.invalid"));
        Assertions.assertFalse(driver.getCurrentUrl().contains("/dashboard"),
                "Login is successful with incorrect email format");
    }

    @Test
    @DisplayName("Invalid login with incorrect email and password")
    public void testInvalidLoginWithIncorrectEmailAndPassword() {
        loginPage.login(getConfig().getProperty("login.invalid"), getConfig().getProperty("password.invalid"));
        Assertions.assertFalse(driver.getCurrentUrl().contains("/dashboard"),
                "Login is successful with incorrect email and password");
    }

    @Test
    @DisplayName("Invalid login with blank email")
    public void testInvalidLoginWithBlankEmail() {
        loginPage.login("", getConfig().getProperty("password.invalid"));
        Assertions.assertFalse(driver.getCurrentUrl().contains("/dashboard"),
                "Login is successful with blank email");
    }

    @Test
    @DisplayName("Invalid login with blank password")
    public void testInvalidLoginWithBlankPassword() {
        loginPage.login(getConfig().getProperty("login.invalid"), "");
        Assertions.assertFalse(driver.getCurrentUrl().contains("/dashboard"),
                "Login is successful with blank password");
    }

    @Test
    @DisplayName("Verify login fields are required")
    public void testLoginFieldsRequired() {
        // Перевіряєю наявність атрибуту "required" та тип поля на "email" у полі логіну
        Assertions.assertTrue(loginPage.getUsernameField().getAttribute("required") != null);
        Assertions.assertEquals("email", loginPage.getUsernameField().getAttribute("type"));
        // Перевіряю наявність атрибуту "required" та тип поля на "password" у полі паролю
        Assertions.assertTrue(loginPage.getPasswordField().getAttribute("required") != null);
        Assertions.assertEquals("password", loginPage.getPasswordField().getAttribute("type"));
    }


/*    @Test
    public  void testUserCanNotLoginWithInvalidCredentials() {
        loginPage.login(getConfig().getProperty("login.invalid"), getConfig().getProperty("password.invalid"));
//        wait.until(ExpectedConditions.visibilityOf(loginPage.getInvalidCredentialsMessage()));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/i[text()=' Wrong credentials. try again! ']")));
        Assertions.assertTrue(loginPage.isInvalidCredentialsErrorMessageDisplayed());
    }*/

}
