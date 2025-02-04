package com.example.TwistedMountainAnimation.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	
	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory - way of defining the webElements
		@FindBy(name ="SearchTerm")
		protected WebElement searchBox;
		
		@FindBy(xpath = "//button[@name='search']")
		protected WebElement searchButton;
		
		@FindBy(xpath = "//a[@title='Browse By Category']")
		WebElement categoryByCategoryLink;
		
		@FindBy(css = "a[title='Parts Look Up']")
		private WebElement partsLookUpPage;
		
		@FindBy(linkText = "About")
		private WebElement aboutPage;
		
		@FindBy(xpath = "//a[@title='Contact']")
		private WebElement contactPage;
		
		@FindBy(css = "a[title='Customer Service']")
		WebElement customerServiceLink;
		
		public void goToPartsLookUpPage()
		{
			clickElement(partsLookUpPage);
		}
		
		public void goToAboutPage()
		{
			clickElement(aboutPage);
		}
		
		public void goToContactPage()
		{
			clickElement(contactPage);
		}
		
		public void clickElement(WebElement element) {
	        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	    }
		
		 public void scrollToElement(WebElement element) {
		        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		    }
		 
		 public void enterText(WebElement element, String text) {
			 	wait.until(ExpectedConditions.visibilityOf(element));
		        element.clear();
		        element.sendKeys(text);
		    }

}
