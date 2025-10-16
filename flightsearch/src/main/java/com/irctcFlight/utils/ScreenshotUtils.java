package com.irctcFlight.utils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;

public class ScreenshotUtils{
    public static void takeScreenshot(WebDriver driver) throws IOException, InterruptedException {
    	File screenshotDir=new File(System.getProperty("user.dir")+"/screenshot");
		if(!screenshotDir.exists())
		{
			screenshotDir.mkdirs();
		}
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		
		long scrollHeight=(Long) js.executeScript("return document.body.scrollHeight");
		long viewportHeight=(Long) js.executeScript("return window.innerHeight");
		
		int parts=(int)Math.ceil((double) scrollHeight/viewportHeight);
		
		for(int i=0;i<parts;i++) {
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File dest=new File(screenshotDir,"flight_"+i+".png");
		FileHandler.copy(src, dest);
		
		System.out.println("Captured screenshot:" + dest.getAbsolutePath());
		
		js.executeScript("window.scrollBy(0,"+viewportHeight+")");
		Thread.sleep(1000);
		
		}
		File last=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(last,new File(screenshotDir,"flights_last.png"));
    }
}
