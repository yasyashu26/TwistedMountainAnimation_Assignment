package com.example.TwistedMountainAnimation.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.example.TwistedMountainAnimation.AbstractComponents.AbstractComponent;

public class SearchPage extends AbstractComponent {
	
	public SearchPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(css = "span.product-title")
    private List<WebElement> productList;
	
	@FindBy(xpath = "(//span[@class='product-title' and @itemprop='name'])[1]")
	private WebElement firstProductTitle;

    public boolean isProductListDisplayed() {
        return !productList.isEmpty();
    }

    public ProductPage clickFirstProduct() {
        clickElement(productList.get(0));
        ProductPage productPage = new ProductPage(driver);
        return productPage;
    }

    public String getFirstProductTitle() {
        return firstProductTitle.getText();
    }
}
