package com.example.TwistedMountainAnimation.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.example.TwistedMountainAnimation.AbstractComponents.AbstractComponent;

public class ProductPage extends AbstractComponent{
	
	public ProductPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(css = "h1.prod-name") 
    private WebElement productTitle;

    public boolean isProductTitleDisplayed() {
        return productTitle.isDisplayed();
    }

}
