package com.example.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class InventoryPage {

    private AndroidDriver driver;

    public InventoryPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"test-Price\" and @text=\"$29.99\"]")
    private WebElement itemPrice;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Sauce Labs Bike Light']")
    private WebElement itemName;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Cart']//android.widget.TextView")
    private java.util.List<WebElement> cartBadgeList;

    @AndroidFindBy(accessibility = "test-Menu")
    private WebElement menuButton;

    @AndroidFindBy(accessibility = "test-RESET APP STATE")
    private WebElement resetAppStateButton;

    public String getItemPrice() {
        return itemPrice.getText();
    }

    public String getItemName() {
        return itemName.getText();
    }

    public void resetAppState() {
        menuButton.click();
        resetAppStateButton.click();
    }

    public void scrollToItem(String itemName) {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(" +
                        "new UiSelector().text(\"" + itemName + "\"))"));
    }

    public void addItemToCart(String itemName) {
        String xpath = String.format(
                "//android.view.ViewGroup[@content-desc='test-Item' and .//android.widget.TextView[@text='%s']]//android.view.ViewGroup[@content-desc='test-ADD TO CART']",
                itemName);
        driver.findElement(By.xpath(xpath)).click();
    }

    public String getCartBadgeCount() {
        if (cartBadgeList.isEmpty()) {
            return "0";
        }
        return cartBadgeList.get(0).getText();
    }

}
