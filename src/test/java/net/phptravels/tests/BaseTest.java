package net.phptravels.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    private static WebDriver driver;
    private Properties config;
    private WebDriverWait wait;

    @BeforeEach
    @Step("Setup test")
    public void setUp() throws IOException {
/*        // Load config properties
        config = new Properties();
        String configPath = "src/main/resources/config.properties";
        try (FileInputStream fis = new FileInputStream(configPath)) {
            config.load(fis);
        } catch (IOException e) {
            System.err.println("Error: Unable to load config.properties from path: " + configPath);
            throw e;
        }*/

        // Load config properties v2
        config = new Properties();
        String configPath = "src/main/resources/config.properties";
        try (FileInputStream fis = new FileInputStream(configPath)) {
            config.load(fis);
        } catch (IOException e) {
            System.err.println("Error: Unable to load config.properties from path: " + configPath);
            e.printStackTrace();
        }

        // Create a new ChromeDriver instance
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        // Set Chrome options for headless mode
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--window-size=1920,1080");
//        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
//        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    @Step("Test teardown")
    public void tearDown() {
        // Close the browser window
        driver.close();
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public Properties getConfig() {
        return config;
    }

    public WebDriverWait getWait() {
        return wait;
    }

/*    public void waitForElementVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }*/

}
