package org.example;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;


public class SpiceJetPage {

    WebDriver driver;

    @FindBy(xpath = "//div[@data-testid='one-way-radio-button']")
    WebElement oneWayRadioBtn;
    @FindBy(xpath = "//div[@data-testid='one-way-radio-button']/descendant::*")
    List<WebElement> oneWayRadioBtnDescendant;
    @FindBy(xpath = "//div[@data-testid='to-testID-origin']")
    WebElement fromInputDiv;
    @FindBy(xpath = "//div[@data-testid='to-testID-origin']/descendant::input")
    WebElement fromInput;
    @FindBy(xpath = "//div[text()='International']")
    WebElement fromInputRegion;
    @FindBy(xpath = "//div[text()='Colombo']")
    WebElement fromInputCity;
    @FindBy(xpath = "//div[@data-testid='to-testID-destination']")
    WebElement toInputDiv;
    @FindBy(xpath = "//div[@data-testid='to-testID-destination']/descendant::input")
    WebElement toInput;
    @FindBy(xpath = "//div[text()='India']")
    WebElement toInputRegion;
    @FindBy(xpath = "//div[text()='Aurangabad']")
    WebElement toInputCity;
    @FindBy(xpath = "//div[@data-testid='departure-date-dropdown-label-test-id']")
    WebElement departureInputDiv;

    By byDepartureCalanderInput = By.id("//div[@data-testid='undefined-calendar-day-18']");
    @FindBy(xpath = "//div[@data-testid='departure-date-dropdown-label-test-id']/div[2]/div[1]")
    WebElement departureInput;
    @FindBy(xpath = "//div[@data-testid='return-date-dropdown-label-test-id']")
    WebElement returnInputDiv;
    @FindBy(xpath = "//div[@data-testid='undefined-calendar-picker']")
    WebElement calander;
    @FindBy(xpath = "//div[@data-testid='home-page-travellers']")
    WebElement passangersDropDown;
    @FindBy(xpath="//div[@data-testid='Adult-testID-plus-one-cta']")
    WebElement adultBtnPlus;
    @FindBy(xpath="//div[@data-testid='Adult-testID-minus-one-cta']")
    WebElement adultBtnMinus;
    @FindBy(xpath="//div[@data-testid='Children-testID-plus-one-cta']")
    WebElement childrenBtnPlus;
    @FindBy(xpath="//div[@data-testid='Children-testID-minus-one-cta']")
    WebElement childrenBtnMinus;
    @FindBy(xpath="//div[@data-testid='Infant-testID-plus-one-cta']")
    WebElement infantBtnPlus;
    @FindBy(xpath="//div[@data-testid='Infant-testID-minus-one-cta']")
    WebElement infantBtnMinus;
    @FindBy(xpath = "//div[@data-testid='home-page-travellers']/descendant::div[contains(text(),'Adult')]")
    WebElement numberOfPassengersDiv;


    public SpiceJetPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOneWayRadioBtn() {
        oneWayRadioBtn.click();
        List<String> tagNames = oneWayRadioBtnDescendant.stream()
                .map(WebElement::getTagName)
                .collect(Collectors.toList());
        Assert.assertTrue(tagNames.contains("g"));
    }

    public void chooseCity() throws InterruptedException {
        fromInputDiv.click();
        Thread.sleep(2000);
        fromInputRegion.click();
        Thread.sleep(2000);
        fromInputCity.click();
        Thread.sleep(2000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Wait for the value to be updated (adjust the wait time as needed)
        wait.until(ExpectedConditions.attributeToBeNotEmpty(fromInput, "value"));

        Assert.assertEquals(fromInput.getAttribute("value"), "Colombo (CMB)");


        toInputDiv.click();
        Thread.sleep(2000);
        toInputRegion.click();
        Thread.sleep(2000);
        toInputCity.click();
        Thread.sleep(2000);

        wait.until(ExpectedConditions.attributeToBeNotEmpty(toInput, "value"));

        Assert.assertEquals(toInput.getAttribute("value"), "Aurangabad (IXU)");

    }

    public void chooseDepartureDate() throws InterruptedException {


        departureInputDiv.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(ExpectedConditions.elementToBeClickable(byDepartureCalanderInput));

        WebElement departureCalanderInput = driver.findElement(byDepartureCalanderInput);
        departureCalanderInput.click();


        Assert.assertEquals("Thu, 18 Mar 2024", departureInput.getText());
    }

    public void checkReturnDateisDisabled() throws InterruptedException {
        String divColor = returnInputDiv.getCssValue("background-color");
        Assert.assertEquals("rgba(238, 238, 238, 1)", divColor);

    }

    public void chooseTypeOfOperationAndTypeAndNumberOfPassengers(String typeOfOperation,String typeOfPassengers ,int numberOfPassengers) throws InterruptedException {

        WebElement btnToClick = null;
        passangersDropDown.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        wait.until(ExpectedConditions.visibilityOf(adultBtnMinus));

        switch(typeOfPassengers){
            case "children":
                if(typeOfOperation == "plus") {
                    btnToClick = childrenBtnPlus;
                }else{ btnToClick = childrenBtnMinus;}

                break;

            case "adults":
                if(typeOfOperation == "plus") {
                    btnToClick = adultBtnPlus;
                }else{
                    btnToClick = adultBtnMinus;}
                break;


            case "infants":
                if(typeOfOperation == "plus") {
                    btnToClick = infantBtnPlus;
                }else{ btnToClick = infantBtnMinus;}

                break;

        }

        for (int j = 0; j < numberOfPassengers; j++) {
            btnToClick.click();
        }
        System.out.println(numberOfPassengersDiv.getText());

    }

}
