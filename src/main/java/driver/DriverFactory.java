package driver;

import environments.Config;
import environments.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class DriverFactory {

    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (webDriver.get() == null) {
            if (isGridEnabled()) {
                createRemoteDriver();
            } else {
                createDriver();
            }
        }
        return webDriver.get();
    }

    private static boolean isGridEnabled() {
        return Boolean.parseBoolean(Config.get(Constants.SELENIUM_GRID_ENABLED));
    }

    private static void createRemoteDriver() {
        RemoteWebDriver driver = null;
        String gridUrl = "http://localhost:4444/wd/hub";

        switch (getBrowserType().toLowerCase()) {
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                // Add any desired options for Firefox here
                try {
                    driver = new RemoteWebDriver(new URL(gridUrl), options);
                } catch (MalformedURLException e) {
                    throw new RuntimeException("Invalid Grid URL: " + gridUrl, e);
                }
                break;
            }
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                // Add any desired options for Chrome here
                try {
                    driver = new RemoteWebDriver(new URL(gridUrl), options);
                } catch (MalformedURLException e) {
                    throw new RuntimeException("Invalid Grid URL: " + gridUrl, e);
                }
                break;
            }
            default -> throw new IllegalArgumentException("Browser type not supported: " + getBrowserType());
        }

        // Set the created driver in the ThreadLocal variable
        webDriver.set(driver);
        driver.manage().window().maximize();
    }

    private static void createDriver() {
        WebDriver driver = null;

        switch (getBrowserType().toLowerCase()) {
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                break;
            }
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                //options.addArguments("--headless");
                driver = new ChromeDriver(options);
                driver.manage().window().maximize();
                break;
            }
            default -> throw new IllegalArgumentException("Browser type not supported: " + getBrowserType());
        }

        // Set the created driver in the ThreadLocal variable
        webDriver.set(driver);
    }

    private static String getBrowserType() {
        String browserType = null;
        String browserTypeRemoteValue = System.getProperty("browserType");

        try {
            if (browserTypeRemoteValue == null || browserTypeRemoteValue.isEmpty()) {
                Properties properties = new Properties();
                FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/config/Config.properties");
                properties.load(file);
                browserType = properties.getProperty("browser").toLowerCase().trim();
            } else {
                browserType = browserTypeRemoteValue;
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return browserType;
    }

    public static void cleanupDriver() {
        if (webDriver.get() != null) {
            webDriver.get().quit();
            webDriver.remove();
        }
    }
}