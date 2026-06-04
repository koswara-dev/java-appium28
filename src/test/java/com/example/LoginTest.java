package com.example;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.example.pages.LoginPage;
import com.example.utils.Utils;

public class LoginTest extends BaseTest {

    @Test(description = "Testing fitur login dengan username dan password yang valid")
    public void testLoginWithValidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        Assert.assertEquals(loginPage.getProductsText(), "PRODUCTS", "Login gagal dengan kredensial valid");

        System.out.println("Test Login dengan kredensial valid berhasil");
    }

    @DataProvider(name = "loginNegativeData")
    public Object[][] getLoginNegativeData() {
        return Utils.readCsvData("src/test/resources/login_negative_data.csv");
    }

    @Test(description = "Testing fitur login dengan username dan password yang tidak valid", dataProvider = "loginNegativeData")
    public void testLoginWithInvalidCredentials(String username, String password, String expectedErrorMessage) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        String actualErrorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);

        System.out.println("Test Login dengan username: '" + username + "' dan password: '" + password + "' berhasil memicu error: '" + expectedErrorMessage + "'");
    }

}
