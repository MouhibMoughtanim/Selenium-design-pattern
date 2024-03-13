package org.example;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.Scanner;


public class GitHubPageTest {

    static WebDriver driver;
    @BeforeClass
    public static void setUp(){
        driver = Driver.getInstance();
        driver.get("https://github.com/");
    }

    @Test
    public void openWindow() throws InterruptedException {
        GitHubPage gitHubPage = new GitHubPage(driver);
        gitHubPage.navigateToLoginPage();
        Dotenv dotenv = Dotenv.load();

        String userName = (String) gitHubPage.getCredentials(dotenv.get("MY_SECRET")).get(0);
        String pasword = (String) gitHubPage.getCredentials(dotenv.get("MY_SECRET")).get(1);

        gitHubPage.sendCredentials(userName,pasword);
        gitHubPage.navigateToProfilePage(userName);
    }

    @AfterClass
    public static void tearDown(){
        driver.quit();
    }
}
