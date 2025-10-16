package com.irctcFlight.pages;

import com.irctcFlight.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.util.List;

public class SearchPage {
    private WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }


    public void open() {
        driver.get("https://www.air.irctc.co.in/");
    }

    public void selectOneWayTrip() {
        driver.findElement(By.xpath("//label[@for='One-Way']")).click();
    }

    public void selectOrigin(String city) throws InterruptedException {
        WebElement originInput = WaitUtils.waitForElementToBeClickable(driver, By.id("stationFrom"));
        originInput.click();
        originInput.sendKeys(city);
        Thread.sleep(2000);
        List<WebElement> options = driver.findElements(By.xpath("//div[text()='Hyderabad (HYD)']"));
        for (WebElement option : options) {
            if (option.isDisplayed()) {
                option.click();
                break;
            }
        }
    }

    public void selectDestination(String city) throws InterruptedException {
        WebElement destInput = WaitUtils.waitForElementToBeClickable(driver, By.id("stationTo"));
        destInput.click();
        destInput.sendKeys(city);
        Thread.sleep(2000);
        List<WebElement> options = driver.findElements(By.xpath("//div[text()='Pune (PNQ)']"));
        for (WebElement option : options) {
            if (option.isDisplayed()) {
                option.click();
                break;
            }
        }
    }

    public void selectDepartureDate() {
        driver.findElement(By.id("originDate")).click();
        int day = LocalDate.now().getDayOfMonth();
        String dateXpath = "//td[@class='date']//span[normalize-space(text())='" + day + "']";
        driver.findElement(By.xpath(dateXpath)).click();
    }

    public void setTravelDetails() throws InterruptedException {
        driver.findElement(By.id("noOfpaxEtc")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("travelClass")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//select[@id='travelClass']/option[text()='Business']")).click();
        Thread.sleep(1000);
    }

    public void clickSearch() throws InterruptedException {
        driver.findElement(By.xpath("//button[text()='Search ']")).click();
        Thread.sleep(3000);
    }
}

