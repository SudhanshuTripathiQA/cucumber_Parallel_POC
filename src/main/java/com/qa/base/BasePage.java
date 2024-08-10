package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasePage {
	public WebDriver driver;
	public Properties prop;
	long IMPLICIT_TIME = 30;

	public void setupThePropertyFile() {
		// Creating object of Properties class
		prop = new Properties();
		FileInputStream file = null;
		try {
			file = new FileInputStream("src/main/resources/config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println("Error occurred: not found property file");
		}
		try {
			// Loading property file
			prop.load(file);
			System.out.println("Loaded the properties file successfully!");
		} catch (IOException e) {
			System.err.println("Error occurred: Could not load the property file");
			e.printStackTrace();
		}
	}

	public void setupBrowser(String appUrl) {
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_TIME));
		driver.get(appUrl);
	}

	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
