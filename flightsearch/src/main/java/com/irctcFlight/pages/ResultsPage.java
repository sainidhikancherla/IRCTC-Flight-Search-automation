package com.irctcFlight.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ResultsPage {
    private WebDriver driver;

    public ResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean validateRoute(String origin, String destination) {
        String pageSource = driver.getPageSource();
        return pageSource.contains(origin) && pageSource.contains(destination);
    }

    public List<WebElement> getFlightNames() {
        return driver.findElements(By.xpath("//b[text()='Air India']"));
    }
}
