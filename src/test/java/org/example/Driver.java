package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Objects;

public class Driver {

    private static WebDriver driver ;

    private Driver(){}

    public static WebDriver getInstance(){
        if(driver == null){
            if(Objects.equals(Constants.browserName, "chrome")) {
                System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
                driver= new ChromeDriver();
            }
        }

        return driver;
    }

    public static void quit(){
        driver.quit();
        driver = null;
    }




}
