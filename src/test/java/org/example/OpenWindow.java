package org.example;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class OpenWindow {

    @BeforeClass
    public static void setUp(){
        Driver.getInstance();
    }

    @Test
    public void verifyDriverIsWorking(){
        Driver.getInstance().get("https://www.spicejet.com/");
    }


    @AfterClass
    public static void tearDown(){
        Driver.getInstance().quit();
    }
}
