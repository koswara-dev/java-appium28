package com.example;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.example.pages.CalculatorPage;

public class CalculatorTest extends BaseTest {

    @Test(description = "Testing fitur penjumlahan")
    public void testAdd() {

        CalculatorPage calculatorPage = new CalculatorPage(driver);

        calculatorPage.clickDigit(4);
        calculatorPage.clickAdd();
        calculatorPage.clickDigit(5);
        calculatorPage.clickEquals();

        String result = calculatorPage.getResult();
        Assert.assertEquals(result, "9", "Test Penjumlahan Gagal");

        System.out.println("Test Penjumlahan Berhasil");
    }

    @Test(description = "Testing fitur pengurangan")
    public void testSubtract() {

        CalculatorPage calculatorPage = new CalculatorPage(driver);

        calculatorPage.clickDigit(100);
        calculatorPage.clickSubtract();
        calculatorPage.clickDigit(40);
        calculatorPage.clickEquals();

        String result = calculatorPage.getResult();
        Assert.assertEquals(result, "60", "Test Pengurangan Gagal");

        System.out.println("Test Pengurangan Berhasil");
    }
}
