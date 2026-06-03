package com.example.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;

public class CalculatorPage {
    private AndroidDriver driver;

    // Digit Elements
    @AndroidFindBy(id = "com.google.android.calculator:id/digit_0")
    private WebElement digit0;

    @AndroidFindBy(id = "com.google.android.calculator:id/digit_1")
    private WebElement digit1;

    @AndroidFindBy(id = "com.google.android.calculator:id/digit_2")
    private WebElement digit2;

    @AndroidFindBy(id = "com.google.android.calculator:id/digit_3")
    private WebElement digit3;

    @AndroidFindBy(id = "com.google.android.calculator:id/digit_4")
    private WebElement digit4;

    @AndroidFindBy(id = "com.google.android.calculator:id/digit_5")
    private WebElement digit5;

    @AndroidFindBy(id = "com.google.android.calculator:id/digit_6")
    private WebElement digit6;

    @AndroidFindBy(id = "com.google.android.calculator:id/digit_7")
    private WebElement digit7;

    @AndroidFindBy(id = "com.google.android.calculator:id/digit_8")
    private WebElement digit8;

    @AndroidFindBy(id = "com.google.android.calculator:id/digit_9")
    private WebElement digit9;

    // Operator Elements
    @AndroidFindBy(id = "com.google.android.calculator:id/op_add")
    private WebElement opAdd;

    @AndroidFindBy(id = "com.google.android.calculator:id/op_sub")
    private WebElement opSub;

    @AndroidFindBy(id = "com.google.android.calculator:id/op_mul")
    private WebElement opMul;

    @AndroidFindBy(id = "com.google.android.calculator:id/op_div")
    private WebElement opDiv;

    @AndroidFindBy(id = "com.google.android.calculator:id/eq")
    private WebElement eq;

    // Result & Display Elements
    @AndroidFindBy(id = "com.google.android.calculator:id/result_final")
    private WebElement resultFinal;

    @AndroidFindBy(id = "com.google.android.calculator:id/result_preview")
    private WebElement resultPreview;

    @AndroidFindBy(id = "com.google.android.calculator:id/formula")
    private WebElement formula;

    // Constructor to initialize elements using Appium Page Factory
    public CalculatorPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    // Helper method to click any digit (supports single digit or multi-digit numbers like tens, hundreds, etc.)
    public void clickDigit(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("clickDigit currently only supports non-negative numbers: " + number);
        }
        String numStr = String.valueOf(number);
        for (char digitChar : numStr.toCharArray()) {
            int digit = Character.getNumericValue(digitChar);
            switch (digit) {
                case 0 -> digit0.click();
                case 1 -> digit1.click();
                case 2 -> digit2.click();
                case 3 -> digit3.click();
                case 4 -> digit4.click();
                case 5 -> digit5.click();
                case 6 -> digit6.click();
                case 7 -> digit7.click();
                case 8 -> digit8.click();
                case 9 -> digit9.click();
                default -> throw new IllegalArgumentException("Invalid digit: " + digit);
            }
        }
    }

    // Operations click actions
    public void clickAdd() {
        opAdd.click();
    }

    public void clickSubtract() {
        opSub.click();
    }

    public void clickMultiply() {
        opMul.click();
    }

    public void clickDivide() {
        opDiv.click();
    }

    public void clickEquals() {
        eq.click();
    }

    // Retrieves the final result text from the calculator screen
    public String getResult() {
        try {
            // Check if final result is displayed (e.g. after '=' is clicked)
            return resultFinal.getText();
        } catch (Exception e) {
            // Fallback to preview result if equals wasn't clicked yet
            try {
                return resultPreview.getText();
            } catch (Exception ex) {
                return "";
            }
        }
    }

    // Retrieves current formula input text
    public String getFormula() {
        try {
            return formula.getText();
        } catch (Exception e) {
            return "";
        }
    }
}
