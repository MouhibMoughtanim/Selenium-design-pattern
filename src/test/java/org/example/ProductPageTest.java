package org.example;


import org.json.JSONObject;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;

public class ProductPageTest {

    static WebDriver driver;

    @BeforeClass
    public static void setUp(){
        driver = Driver.getInstance();
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
    }

    @Test
    public void addProducts(){
        ProductsPage productsPage = new ProductsPage(driver);
        Constants constants = new Constants();
        constants.products.forEach(e -> {
            //System.out.println(((JSONObject) e).get("name")+"  "+((JSONObject) e).get("number"));
            productsPage.findProductByName((String) ((JSONObject) e).get("name"), (Integer) ((JSONObject) e).get("number"));
        });

    }

    @Test
    public void proceedToPayement() throws InterruptedException {
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.proceedToPayement();
    }

    @AfterClass
    public static void tearDown(){
        driver.quit();
    }


}
