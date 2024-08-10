package com.qa.util;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.base.BasePage;

public class Utility extends BasePage {
	private static final Logger LOGGER = Logger.getLogger(Utility.class);


	public void scrollToBottom(WebDriver driver) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void takeScreenshot(String filePath, WebDriver driver) throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File(filePath));
	}

	public int getRandomNumber() {
		Random random = new Random();
		return random.nextInt(1000);
	}

	public void doWindowSwitch(WebDriver driver) {
		Set<String> winTabs = driver.getWindowHandles();
		ArrayList<String> tabs = new ArrayList<String>(winTabs);
		driver.switchTo().window(tabs.get(1));
	}
	
	public boolean isCustomWait(By byLocatorText, WebDriver driver) throws InterruptedException {
		boolean flag = false;
		// 2 minutes in milliseconds
		int timeout = 120000; 
		// It will poll every 500 milliseconds
		int timeIntervalPolling = 500; 
		long endTime = System.currentTimeMillis() + timeout;

		// Disabling the implicit wait to rely only on custom wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

		while (!flag && System.currentTimeMillis() < endTime) {
			try {
				WebElement element = driver.findElement(byLocatorText);
				if (element.isDisplayed()) {
					return flag = true;
				}
			} catch (NoSuchElementException e) {
				Thread.sleep(timeIntervalPolling);
				LOGGER.info("This WebElement is not visible on the page: " + byLocatorText);
				LOGGER.info("Time left is: " + (endTime - System.currentTimeMillis()) + " milliseconds");
			}
		}
		LOGGER.error("Could not find the WebElement within the given timeout.");
		return flag;
	}
}
