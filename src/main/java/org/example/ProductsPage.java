package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.locators.RelativeLocator;
import java.time.Duration;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class ProductsPage {

    WebDriver driver;
    String productName="";
    By byProductName = By.xpath("//h4[@class='product-name'][contains(text(), "+productName+")]/parent::div");
    By byCartItem = By.xpath("//img[@alt='Cart'][parent::a[@class='cart-icon']]");
    By byCartItemPreview = By.xpath("//div[contains(@class,'cart-preview')]");
    By byActionButton = By.xpath("//div[@class='action-block']/button");

    By byPromoCodeInput = By.xpath("//input[@class='promoCode']");
    By byPromoCodeButton = By.xpath("//button[@class='promoBtn']");
    By byPromoInfo = By.xpath("//span[@class='promoInfo']");

    By byPlaceOrder = By.xpath("//button[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'place order')]");
    By bySelectCountries = By.xpath("//select");

    By byCheckTerms = By.xpath("//input[@class='chkAgree']");

    By byProceedBtn = By.xpath("//button");




    public void setProductName(String productName) {
        this.productName = productName;
        this.byProductName = By.xpath("//h4[@class='product-name'][contains(text(), '"+productName+"')]/parent::div");
    }

    public ProductsPage(WebDriver driver){
        this.driver = driver;
    }

    public void findProductByName(String name,int number){
        this.setProductName(name);
        WebElement product = driver.findElement(byProductName);
        for (int i = 0; i < number ; i++) {
            product.findElement(By.xpath(".//descendant::a[@class='increment']")).click();
        }
        product.findElement(By.xpath(".//descendant::button")).click();

    }

    public void proceedToPayement() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement cartButton = driver.findElement(byCartItem);
        WebElement actionButton = driver.findElement(byActionButton);


        cartButton.click();

        wait.until(ExpectedConditions.attributeContains(byCartItemPreview,"class","active"));

        actionButton.click();

        wait.until(ExpectedConditions.urlContains("/cart"));


        WebElement promoCodeInput = driver.findElement(byPromoCodeInput);
        WebElement promoCodeBtn = driver.findElement(byPromoCodeButton);

        promoCodeInput.sendKeys("rahulshettyacademy");

        promoCodeBtn.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(byPromoInfo));
        WebElement placeOrderBtn = driver.findElement(byPlaceOrder);
        placeOrderBtn.click();

        wait.until(ExpectedConditions.urlContains("/country"));
        WebElement selectContries = driver.findElement(bySelectCountries);
        WebElement checkTerms = driver.findElement(byCheckTerms);

        Select select = new Select(selectContries);

        select.selectByValue("Morocco");
        checkTerms.click();

        WebElement button = driver.findElement(byProceedBtn);

        button.click();



    }
}
