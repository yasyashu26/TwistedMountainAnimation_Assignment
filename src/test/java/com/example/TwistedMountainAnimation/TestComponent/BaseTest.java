package com.example.TwistedMountainAnimation.TestComponent;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.NoSuchElementException;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.example.TwistedMountainAnimation.pageObjects.HomePage;

public class BaseTest {

    protected WebDriver driver;
    protected HomePage homePage;

    private static final String USERNAME = "yasyashu_qfhQ5x";
    private static final String ACCESS_KEY = "DUiEFzjFsayeBYUXgW7s";
    private static final String BS_URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";

    @BeforeMethod
    @Parameters({"browser", "os", "osVersion", "resolution"})
    public void initializeDriver(String browser, String os, String osVersion, String resolution) {
        try {
            System.out.println("Initializing WebDriver for Browser: " + browser + ", OS: " + os + ", OS Version: " + osVersion + ", Resolution: " + resolution);

            HashMap<String, Object> bstackOptions = new HashMap<>();
            bstackOptions.put("os", os);
            bstackOptions.put("osVersion", osVersion);
            bstackOptions.put("resolution", resolution);
            bstackOptions.put("seleniumVersion", "4.10.0");
            bstackOptions.put("buildName", "UI Automation Build 2");
            bstackOptions.put("sessionName", "Multi-Browser Test");

            switch (browser.toLowerCase()) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.setCapability("bstack:options", bstackOptions);
                    driver = new RemoteWebDriver(new URL(BS_URL), chromeOptions);
                    break;

                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.setCapability("bstack:options", bstackOptions);
                    driver = new RemoteWebDriver(new URL(BS_URL), firefoxOptions);
                    break;

                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.setCapability("bstack:options", bstackOptions);
                    driver = new RemoteWebDriver(new URL(BS_URL), edgeOptions);
                    break;

                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }

            driver.manage().window().maximize();
            driver.get("https://www.shophighlinewarren.com/");
            homePage = new HomePage(driver);
            System.out.println("WebDriver initialized successfully!");

        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid BrowserStack URL: " + BS_URL, e);
        } catch (Exception e) {
            System.err.println("Failed to initialize WebDriver: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("WebDriver initialization failed!", e);
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("WebDriver closed successfully!");
        }
    }

    /**
     * Closes the pop-up using different methods for better cross-browser compatibility.
     */
//    public void closeDialogPopUp() {
//        try {
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//            // Try locating the SVG element normally
//            WebElement svgElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[local-name()='svg']/*[local-name()='path']")));
//            svgElement.click();
//            System.out.println("SVG element found and clicked using XPath!");
//
//        } catch (TimeoutException | NoSuchElementException e) {
//            System.out.println("SVG element not found using XPath, trying JavaScript...");
//
//            try {
//                // Use JavaScript as a fallback
//                JavascriptExecutor js = (JavascriptExecutor) driver;
//                WebElement svgElement = (WebElement) js.executeScript("return document.querySelector('svg path')");
//                
//                if (svgElement != null) {
//                    js.executeScript("arguments[0].click();", svgElement);
//                    System.out.println("SVG element clicked using JavaScript!");
//                } else {
//                    System.out.println("SVG element not found with JavaScript either.");
//                }
//
//            } catch (Exception jsException) {
//                System.out.println("JavaScript execution failed: " + jsException.getMessage());
//            }
//        }
//    }
}
