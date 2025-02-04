package com.example.TwistedMountainAnimation.Tests;

import com.example.TwistedMountainAnimation.TestComponent.BaseTest;
import org.testng.annotations.Test;
import java.util.List;
import org.openqa.selenium.WebElement;
import java.net.URL;
import java.net.HttpURLConnection;
import java.awt.Window;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class FooterLinkStatusTest extends BaseTest{
	
	@Test
	public void verifyFooterLinksStatus() throws InterruptedException {
		
		homePage.closeDialogPopUp();
	    List<WebElement> footerLinks = driver.findElements(By.cssSelector("div[class='footer'] a"));
	    System.out.println("Total Footer Links Found: " + footerLinks.size());
	    
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");

	    footerLinks.stream()
	        .map(link -> link.getDomAttribute("href"))
	        .filter(url -> url != null && !url.isEmpty() && !url.startsWith("#") && !url.startsWith("javascript"))
	        .forEach(url -> {
	            int statusCode = getResponseStatus(url);
	            System.out.println("Link: " + url + " -> Status Code: " + statusCode);
	            assert statusCode == 200 : "Broken Link Found: " + url;
	        });
	}

	// Method to get HTTP response status code
	private int getResponseStatus(String urlString) {
	    try {
	        HttpURLConnection connection = (HttpURLConnection) new URL(urlString).openConnection();
	        connection.setRequestMethod("HEAD");
	        connection.connect();
	        return connection.getResponseCode();
	    } catch (IOException e) {
	        System.out.println("Error checking URL: " + urlString);
	        return 0; 
	    }
	}




}
