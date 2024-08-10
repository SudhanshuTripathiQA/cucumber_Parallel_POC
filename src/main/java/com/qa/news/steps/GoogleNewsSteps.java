package com.qa.news.steps;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.qa.base.BasePage;
import com.qa.util.Utility;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class GoogleNewsSteps extends BasePage {
	Utility util = new Utility();

	@Given("User navigate to Google News")
	public void user_navigate_to_google_news() throws IOException {
		// Load property file
		setupThePropertyFile();

		// Setup WebDriver
		setupBrowser(prop.getProperty("url"));
		System.out.println("Application launched successfully!");
	}

	@When("User scroll to the bottom and click the second last link")
	public void user_scroll_to_bottom_and_click_second_last_link() throws InterruptedException, IOException {
		
		List<WebElement> elements = driver.findElements(By.xpath("//h2/parent::div//article"));
		// Scroll to the bottom of the page
		util.scrollToBottom(driver);

		// Click the second last link
		elements.get(elements.size() - 1).click();
		System.out.println("Clicked on the second last link");
		
		util.takeScreenshot("./srenshots/screenshot_" + util.getRandomNumber() + ".png", driver);
		
		String parentWindowID=driver.getWindowHandle();
		util.doWindowSwitch(driver);
		
		// Again Scrolling to the bottom of the page
		util.scrollToBottom(driver);
		
		// Closing the secondary opened tab
		driver.close();
		
		// Switching back to the parent window tab
		driver.switchTo().window(parentWindowID);
	}

	@When("User close the browser")
	public void user_close_the_browser() {
		tearDown();
	}
}
