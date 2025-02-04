package com.example.TwistedMountainAnimation.Tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.example.TwistedMountainAnimation.TestComponent.BaseTest;

public class HomePageTitleTest extends BaseTest {

    @Test
    public void testHomePageTitle() {
        String pageTitle = driver.getTitle();
        System.out.println("Page Title is: " + pageTitle);
        Assert.assertTrue(pageTitle.contains("HIGHLINE WARREN"));
    }


}
