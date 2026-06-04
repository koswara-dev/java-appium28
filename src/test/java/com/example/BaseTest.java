package com.example;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;

public class BaseTest {
    protected AndroidDriver driver;

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        // Configure options for Android UiAutomator2
        UiAutomator2Options options = new UiAutomator2Options();

        options.setDeviceName("Android Emulator");
        options.setUdid("emulator-5554");
        options.setPlatformName("Android");
        options.setPlatformVersion("10");
        options.setAutomationName("UiAutomator2");

        // Target the default Android Settings app for a generic, out-of-the-box smoke
        // test
        options.setAppPackage("com.swaglabsmobileapp");
        options.setAppActivity(".MainActivity");

        // Session settings
        options.setNoReset(true);

        // Appium Server URL (Default Appium 2.x port)
        URL appiumServerUrl = URI.create("http://127.0.0.1:4723/wd/hub").toURL();

        // Initialize AndroidDriver
        driver = new AndroidDriver(appiumServerUrl, options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
