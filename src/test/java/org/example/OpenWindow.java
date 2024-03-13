package org.example;

import org.assertj.core.api.SoftAssertions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;


public class OpenWindow {

    static WebDriver driver;
    private static SoftAssertions softAssertions;

    @BeforeClass
    public static void setUp(){

        driver =  Driver.getInstance();
        driver.manage().window().maximize();
        driver.get("https://www.spicejet.com/");
        //softAssertions = new SoftAssertions();

    }

    @Test
    public void verifyRadioBtnClick(){
        SpiceJetPage spiceJetPage = new SpiceJetPage(driver);
        spiceJetPage.clickOneWayRadioBtn();
    }
    @Test
    public void verfiyCityChoice() throws InterruptedException {
        SpiceJetPage spiceJetPage = new SpiceJetPage(driver);
        spiceJetPage.chooseCity();
    }
    @Test
    public void chooseDate() throws InterruptedException{
        SpiceJetPage spiceJetPage = new SpiceJetPage(driver);
        spiceJetPage.chooseDepartureDate();
        spiceJetPage.checkReturnDateisDisabled();
    }

    @Test
    public void checkNumberOfPassengers() throws InterruptedException {
        SpiceJetPage spiceJetPage = new SpiceJetPage(driver);
        //two types of operations ( plus , minus )
        //Three types of passengers ( adults , children , infants )
            spiceJetPage.chooseTypeOfOperationAndTypeAndNumberOfPassengers("plus","children",4);
        //spiceJetPage.chooseTypeOfOperationAndTypeAndNumberOfPassengers("plus","Adult",4);

    }




    @AfterClass
    public static void tearDown(){

        //softAssertions.assertAll();
        driver.quit();
    }
}
