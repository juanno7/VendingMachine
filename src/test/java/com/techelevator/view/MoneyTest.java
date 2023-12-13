package com.techelevator.view;

import com.techelevator.Gum;
import com.techelevator.Inventory;
import com.techelevator.Money;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MoneyTest {
    private Money money;
    private Inventory inventory;
    private static String filepath = "vendingmachine.csv";
    @Before
    public void setup() {
        money = new Money();
        inventory = new Inventory();
        inventory.initializeInventory(filepath);
    }

    @Test
    public void testGetAmountOfMoney() {
        money.setAmountOfMoney(10.0);
        assertEquals(10.0, money.getAmountOfMoney(), 0.001);
    }

    @Test
    public void testSetAmountOfMoney() {
        money.setAmountOfMoney(20.0);
        assertEquals(20.0, money.getAmountOfMoney(), 0.001);
    }

    @Test
    public void testReturnChange() {
        money.setAmountOfMoney(5.0);
        Gum gum = new Gum("A1", "Trident", 1.0, "Gum", 5);
        double change = money.amountOfMoney - gum.getPrice();
        money.returnChange(change);
        assertEquals(4.0, change, 0);
    }

//    @Test
//    public void testFeedMoney() {
//        // Arrange
//        String input = "5\n";  // we will feed 5 dollars
//        InputStream in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in);
//
//        money.feedMoney(5.0);
//
//        double expected = 5.0;
//        double actual = money.getAmountOfMoney();
//        double delta = 0.0001;  // the margin of error we allow when comparing doubles
//        assertEquals("Balance should be updated after feeding money", expected, actual, delta);
//    }

    // checks if created new file
    @Test
    public void testSalesReport() {
        money.salesReport();
        File file = new File("SalesReport.txt");
        assertTrue(file.exists());
    }

    @Test
    public void testTotalSalesWithNonEmptyInventory() {
        double expectedTotalSales = (5 - 3) * 3.05 + (5 - 4) * 1.80 + (5 - 4) * 1.25 + (5 - 3) * 0.85;
        double result = money.totalSales();
        assertEquals(expectedTotalSales, result);
    }

//    @Test
//    public void testTotalSalesWithZeroQuantityProducts() {
//        // b2 - 1.5, c2 - 1.5, d3 - 0.75
//        Inventory productB2 = inventory.getProduct("B2");
//        Inventory productC2 = inventory.getProduct("C2");
//        Inventory productD3 = inventory.getProduct("D3");
//
//        productB2.setQuantity(0);
//        productC2.setQuantity(0);
//        productD3.setQuantity(0);
//
//        double expectedTotalSales = (5 - 0) * 3.05;
//        double result = money.totalSales();
//        assertEquals(expectedTotalSales, result);
//    }
}
