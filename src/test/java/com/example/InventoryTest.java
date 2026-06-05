package com.example;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.example.pages.InventoryPage;
import com.example.pages.LoginPage;

public class InventoryTest extends BaseTest {
    @Test(description = "Menampilkan detail harga dan nama barang pada halaman inventory")
    public void testInventoryItemDetails() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginIfNecessary("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);

        String itemName = inventoryPage.getItemName();
        String itemPrice = inventoryPage.getItemPrice();

        Assert.assertEquals(itemName, "Sauce Labs Bike Light", "Nama barang tidak sesuai");
        Assert.assertEquals(itemPrice, "$29.99", "Harga barang tidak sesuai");

        // convert tipe data price dari string ke double dan melakukan perbandingan
        double expectedPrice = 29.99;
        double actualPrice = Double.parseDouble(itemPrice.replace("$", ""));
        Assert.assertEquals(actualPrice, expectedPrice, "Harga barang tidak sesuai");

        System.out.println("Test Detail Inventory berhasil");
        System.out.println("Nama Barang: " + itemName);
        System.out.println("Harga Barang: " + itemPrice);
    }

    @Test(description = "Menambahkan 3 item ke keranjang belanja sekaligus menggunakan scroll Android")
    public void testAddToCartThreeItems() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginIfNecessary("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);

        // 1. Tambahkan item pertama yang langsung terlihat (Backpack)
        inventoryPage.addItemToCart("Sauce Labs Backpack");

        // 2. Tambahkan item kedua yang langsung terlihat (Bike Light)
        inventoryPage.addItemToCart("Sauce Labs Bike Light");

        // 3. Scroll ke item ketiga (Fleece Jacket) dan tambahkan
        inventoryPage.scrollToItem("Sauce Labs Fleece Jacket");
        inventoryPage.addItemToCart("Sauce Labs Fleece Jacket");

        // Verifikasi total item di keranjang adalah 3
        Assert.assertEquals(inventoryPage.getCartBadgeCount(), "3", "Gagal menambahkan 3 item ke keranjang");

        System.out.println("Test Add to Cart 3 items sekaligus dengan scroll Android berhasil!");
    }

    @Test(description = "Verifikasi jumlah item pada halaman inventory")
    public void testInventoryItemCount() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginIfNecessary("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);

        List<WebElement> productNames = inventoryPage.getProductNames();
        int expectedCount = 2;

        Assert.assertEquals(productNames.size(), expectedCount, "Jumlah item tidak sesuai");

        System.out.println("Test Jumlah Item pada halaman inventory berhasil!");
        System.out.println("Jumlah item: " + productNames.size());
    }

    @Test(description = "Verifikasi nama dan harga barang")
    public void testInventoryItemNamesAndPrices() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginIfNecessary("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);

        List<WebElement> productNames = inventoryPage.getProductNames();
        List<WebElement> productPrices = inventoryPage.getProductPrices();

        Assert.assertEquals(productNames.size(), 2, "Jumlah item tidak sesuai");
        Assert.assertEquals(productPrices.size(), 2, "Jumlah harga tidak sesuai");

        System.out.println("Test Nama dan Harga Barang pada halaman inventory berhasil!");
        for (WebElement productName : productNames) {
            System.out.println("Nama Barang: " + productName.getText());
        }

        for (WebElement productPrice : productPrices) {
            System.out.println("Harga Barang: " + productPrice.getText());
        }
    }
}
