package com.irctcFlight.tests;

import com.irctcFlight.base.TestBase;
import com.irctcFlight.pages.SearchPage;
import com.irctcFlight.pages.ResultsPage;
import com.irctcFlight.utils.ScreenshotUtils;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class FlightSearchTest extends TestBase {

    @Test
    public void testFlightSearch() throws InterruptedException, IOException {
        SearchPage searchPage = new SearchPage(driver);
        ResultsPage resultsPage = new ResultsPage(driver);

        searchPage.open();
        searchPage.selectOneWayTrip();
        searchPage.selectOrigin("Hyderabad");
        searchPage.selectDestination("Pune");
        searchPage.selectDepartureDate();
        searchPage.setTravelDetails();
        searchPage.clickSearch();

        Assert.assertTrue(resultsPage.validateRoute("Hyderabad", "Pune"), "Route validation failed!");

        List<WebElement> flights = resultsPage.getFlightNames();
        System.out.println("Total Flights Available: " + flights.size());
        for (WebElement flight : flights) {
            System.out.println(flight.getText());
        }

        
        ScreenshotUtils.takeScreenshot(driver);
    }
}
