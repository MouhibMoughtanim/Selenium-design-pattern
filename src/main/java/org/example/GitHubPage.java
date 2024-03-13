package org.example;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GitHubPage {
    WebDriver driver;
    By bySignInBtn = By.linkText("Sign in");
    By byUserNameInput = By.id("login_field");
    By byPasswordInput = By.id("password");

    By bySignInBtnLoginPage = By.xpath("//input[@data-signin-label='Sign in']");

    By byAvatarImg = By.xpath("//img[contains(@class,'avatar')][1]");

    By byProfileBtn = By.xpath("//span[contains(text(),'profile')]/parent::a");

    By byDialog = By.xpath("//dialog");
    By byEditButton = By.xpath("//a[contains( @aria-label, 'Change your avatar')]");

    By byFinalEditButton = By.xpath("//img[contains(@class,'avatar-user')]");

    By byUploadImageInput = By.xpath("//*[@id='avatar_upload']");
    By byRemoveImageBtn = By.xpath("(//button[normalize-space()='Remove photo'])[1]");

    By byConfirmationBtn = By.xpath("//div[@class='Box-footer']/button");

    By byAlertDiv = By.xpath("//div[contains(@role,'alert')]");


    public GitHubPage(WebDriver driver){
        this.driver = driver;
    }

    public void navigateToLoginPage(){
        WebElement signInBtn = driver.findElement(bySignInBtn);
        signInBtn.click();
    }

    public void sendCredentials(String username,String password) throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        wait.until(ExpectedConditions.urlContains("login"));

        WebElement userNameInput = driver.findElement(byUserNameInput);
        WebElement passwordInput = driver.findElement(byPasswordInput);
        WebElement signInBtnLoginPage = driver.findElement(bySignInBtnLoginPage);



        userNameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        signInBtnLoginPage.click();

        Thread.sleep(3000);

    }

    public List getCredentials(String path){
        List userPasswordList = new ArrayList();

        try{
            FileReader reader=new FileReader(path);

            Scanner in= new Scanner(reader);

            while (in.hasNextLine()) {
                String line=in.nextLine();
                userPasswordList.add(line);

            }
        }
        catch (IOException exception){
            System.out.println("Error processing file:" +exception);
        }
        return userPasswordList;
    }

    public void navigateToProfilePage(String userName) throws InterruptedException {

        WebElement avatarImg = driver.findElement(byAvatarImg);
        avatarImg.click();

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(byProfileBtn));
        WebElement profileBtn = driver.findElement(byProfileBtn);
        profileBtn.click();

        wait.until(ExpectedConditions.urlToBe("https://github.com/"+userName));
        WebElement editButton = driver.findElement(byEditButton);
        editButton.click();

        wait.until(ExpectedConditions.urlContains("settings/profile"));
        WebElement finalEditbutton = driver.findElement(byFinalEditButton);
        finalEditbutton.click();

        wait.until(ExpectedConditions.elementToBeClickable(byRemoveImageBtn));
        WebElement uploadImageInput = driver.findElement(byUploadImageInput);
        uploadImageInput.sendKeys("C:\\Users\\dell\\Pictures\\profileNoBG.png");

        wait.until(ExpectedConditions.elementToBeClickable(byConfirmationBtn));
        WebElement confirmationBtn = driver.findElement(byConfirmationBtn);
        confirmationBtn.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(byAlertDiv));
        WebElement alertDiv = driver.findElement(byAlertDiv);
        String alertText = alertDiv.getText();
        Assert.assertTrue(alertText.contains("updated"));

        Thread.sleep(9000);

    }

}
