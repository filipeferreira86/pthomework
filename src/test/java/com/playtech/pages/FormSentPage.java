package com.playtech.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormSentPage extends Base {
	
	private By confirmationMessage = By.xpath("//div[@class='freebirdFormviewerViewResponseConfirmationMessage']");

	public FormSentPage(WebDriver driver) {
		super(driver);
	}
	
	public String getConfirmationMessage() {
		return waitForElementToAppear(confirmationMessage).getText();
	}

}
