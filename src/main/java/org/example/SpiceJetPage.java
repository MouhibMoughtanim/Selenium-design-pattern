package org.example;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;


public class SpiceJetPage{

    WebDriver driver ;
    @FindBy(xpath="//div[@data-testid='one-way-radio-button']")
    WebElement oneWayRadioBtn;
    @FindBy(xpath="//div[@data-testid='one-way-radio-button']/descendant::*")
    List<WebElement> oneWayRadioBtnDescendant ;
    @FindBy(xpath="//div[@data-testid='to-testID-origin']")
    WebElement fromInput;
    @FindBy(xpath ="//div[text()='International']")
    WebElement fromInputRegion;
    @FindBy(xpath ="//div[text()='Colombo']")
    WebElement fromInputCity;
    @FindBy(xpath = "//div[@data-testid='to-testID-destination']")
    WebElement toInput;

    WebElement departureInput;
    WebElement returnInput;
    WebElement passangersDropDown;
    WebElement adultBtnPlus;
    WebElement adultBtnMinus;
    WebElement childrenBtnPlus;
    WebElement childrenBtnMinus;
    WebElement infantBtnPlus;
    WebElement infantBtnMinus;

    public SpiceJetPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void clickOneWayRadioBtn(){
        oneWayRadioBtn.click();
        List<String> tagNames = oneWayRadioBtnDescendant.stream()
                .map(WebElement::getTagName)
                .collect(Collectors.toList());
        Assert.assertTrue(tagNames.contains("g"));
    }

    public void chooseCity() throws InterruptedException {
        fromInput.click();
        Thread.sleep(2000);
        fromInputRegion.click();
        Thread.sleep(2000);
        fromInputCity.click();
    }

}
