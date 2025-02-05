package com.example.TwistedMountainAnimation.pageObjects;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;




import com.example.TwistedMountainAnimation.AbstractComponents.AbstractComponent;

public class HomePage extends AbstractComponent {
	
	@FindBy(xpath = "//*[name()='svg']//*[name()='path']")
    private WebElement closeButton;  
	
	public HomePage(WebDriver driver)
	{
		super(driver);
	}
	
//	public void closeDialogPopUp() throws InterruptedException {
//        try {
//            if (closeButton.isDisplayed()) {
//                // Scroll into view before clicking
//                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", closeButton);
//                Thread.sleep(500);
//                clickElement(closeButton);
//                System.out.println("Pop-up closed successfully.");
//            }
//        } catch (NoSuchElementException e) {
//            System.out.println("No pop-up found, continuing execution.");
//        }
//    }
	
//	public void closeDialogPopUp() throws InterruptedException {
//	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//	    try {
//	        WebElement svgElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[local-name()='svg']/*[local-name()='path']")));
//	        System.out.println("SVG element found using XPath!");
//	    } catch (Exception e) { // Catch all exceptions instead of just TimeoutException
//	        System.out.println("XPath not working, trying JavaScript...");
//	        
//	        JavascriptExecutor js = (JavascriptExecutor) driver;
//	        WebElement svgElement = (WebElement) js.executeScript("return document.querySelector('svg path')");
//	        System.out.println("SVG element found using JavaScript!");
//	    }
//	}
	
	
	public void closeDialogPopUp() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Try locating the SVG element normally
            WebElement svgElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[local-name()='svg']/*[local-name()='path']")));
            svgElement.click();
            System.out.println("SVG element found and clicked using XPath!");

        } catch (TimeoutException | NoSuchElementException e) {
            System.out.println("SVG element not found using XPath, trying JavaScript...");

            try {
                // Use JavaScript as a fallback
                JavascriptExecutor js = (JavascriptExecutor) driver;
                WebElement svgElement = (WebElement) js.executeScript("return document.querySelector('svg path')");
                
                if (svgElement != null) {
                    js.executeScript("arguments[0].click();", svgElement);
                    System.out.println("SVG element clicked using JavaScript!");
                } else {
                    System.out.println("SVG element not found with JavaScript either.");
                }

            } catch (Exception jsException) {
                System.out.println("JavaScript execution failed: " + jsException.getMessage());
            }
        }
    }

	
	public SearchPage searchForProduct(String product) throws InterruptedException {
		closeDialogPopUp();
	    enterText(searchBox, product);
	    clickElement(searchButton);
	    return new SearchPage(driver);
	}
	
	public void clickCategory(WebElement headerLink) {
        clickElement(headerLink);
    }
	
	
	
	
	
}
