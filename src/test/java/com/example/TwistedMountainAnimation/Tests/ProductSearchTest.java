package com.example.TwistedMountainAnimation.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.example.TwistedMountainAnimation.TestComponent.BaseTest;
import com.example.TwistedMountainAnimation.pageObjects.ProductPage;
import com.example.TwistedMountainAnimation.pageObjects.SearchPage;

public class ProductSearchTest extends BaseTest {
	
	@Test
    public void verifyProductSearchAndDetails() throws InterruptedException {
        String productName = "Car"; 

        SearchPage searchPage = homePage.searchForProduct(productName);
        Assert.assertTrue(searchPage.getFirstProductTitle().contains(productName), "Search did not return correct results");
        System.out.println("Search functionality works correctly");

        ProductPage productPage = searchPage.clickFirstProduct();
        Assert.assertTrue(productPage.isProductTitleDisplayed(), "Product title not visible");
        System.out.println("Product details are displayed correctly");
    }
}


