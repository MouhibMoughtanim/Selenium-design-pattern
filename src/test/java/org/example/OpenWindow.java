package org.example;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;


public class OpenWindow {

    static WebDriver driver;
    @BeforeClass
    public static void setUp(){

        driver =  Driver.getInstance();
        driver.get("https://www.spicejet.com/");

    }

    @Test
    public void verifyRadioBtnClick(){
        SpiceJetPage spiceJetPage = new SpiceJetPage(driver);
        spiceJetPage.clickOneWayRadioBtn();
    }
    @Test
    public void verfiyCityChoice(){
        SpiceJetPage spiceJetPage = new SpiceJetPage(driver);
    }



    @AfterClass
    public static void tearDown(){
        Driver.getInstance().quit();
    }
}
