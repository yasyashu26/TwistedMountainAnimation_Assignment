package com.example.TwistedMountainAnimation.TestComponent;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.example.TwistedMountainAnimation.pageObjects.HomePage;
import com.github.dockerjava.api.model.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.HashMap;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeOptions;

public class BaseTest {
	
	protected WebDriver driver;
	protected HomePage homePage;
	
	
	//Without multi-browser and resolution code.
//	@BeforeMethod
//    public void initializeDriver() throws MalformedURLException {
//        String USERNAME = "yasyashu_aCoR8D";
//        String ACCESS_KEY = "qQ3pdVvcCEyjrqQW8BFE";
//        String BS_URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";
//
//        // Set W3C-compliant capabilities
//        ChromeOptions options = new ChromeOptions();
//        HashMap<String, Object> browserstackOptions = new HashMap<>();
//
//        browserstackOptions.put("browserName", "Chrome");
//        browserstackOptions.put("browserVersion", "latest");
//        browserstackOptions.put("os", "Windows");
//        browserstackOptions.put("osVersion", "11");
//        browserstackOptions.put("resolution", "1920x1080");
//        browserstackOptions.put("buildName", "UI Automation Build 1");
//        browserstackOptions.put("sessionName", "Product Search & Footer Test");
//        
//        options.setCapability("bstack:options", browserstackOptions);
//
//        // Initialize BrowserStack Remote WebDriver
//        driver = new RemoteWebDriver(new URL(BS_URL), options);
//        driver.manage().window().maximize();
//        driver.get("https://www.shophighlinewarren.com/");
//        homePage = new HomePage(driver);
//        PageFactory.initElements(driver, this);
//    }
	
	//with multi-browser and resolution code
	private static final String USERNAME = "yasyashu_aCoR8D";
	private static final String ACCESS_KEY = "qQ3pdVvcCEyjrqQW8BFE";
	private static final String BS_URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";
    

	
    @BeforeMethod
    @Parameters({"browser", "os", "resolution"})
    public void initializeDriver(String browser, String os, String resolution) {
        try {
            System.out.println("Initializing WebDriver for Browser: " + browser + ", OS: " + os + ", Resolution: " + resolution);

            HashMap<String, Object> bstackOptions = new HashMap<>();
            bstackOptions.put("os", os);
            bstackOptions.put("osVersion", "11");
            bstackOptions.put("resolution", resolution);
            bstackOptions.put("seleniumVersion", "4.10.0");  // Ensures compatibility
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
            throw new RuntimeException("Failed to initialize WebDriver: " + e.getMessage(), e);
        }
    }
    
    
	@AfterMethod
	public void tearDown()
	{
		if(driver!=null)
		{
			driver.quit();
		}
	}

}
