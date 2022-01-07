package com.playtech.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.playtech.support.DataC;

public class ContactPage extends Base{
	
	private By fristQuestion = By.className("appsMaterialWizToggleRadiogroupGroupContent");
	private By itensInQuestion = By.className("appsMaterialWizToggleRadiogroupOffRadio");
	
	private By nameField = By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[2]/div/div/div[2]/div/div[1]/div/div[1]/input");
	private By emailField = By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[3]/div/div/div[2]/div/div[1]/div/div[1]/input");
	private By addressField = By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[4]/div/div/div[2]/div/div[1]/div[2]/textarea");
	private By phoneNumberField = By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[5]/div/div/div[2]/div/div[1]/div/div[1]/input");
	private By commentsField = By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[6]/div/div/div[2]/div/div[1]/div[2]/textarea");
	private By submitBtn;
	private By clearSelectionBtn;
	private By clearFormBtn;
	private By nameError = By.xpath("//div[@id='i25']//span[@class='freebirdFormviewerComponentsQuestionBaseErrorText']");
	private By emailError = By.xpath("//div[@id='i29']//span[@class='freebirdFormviewerComponentsQuestionBaseErrorText']");
	private By addressError = By.xpath("//div[@id='i33']//span[@class='freebirdFormviewerComponentsQuestionBaseErrorText']");
	private By radioWrapper = By.xpath("//span[contains(@class, 'appsMaterialWizToggleRadiogroupGroupContent')]");
	private By allRadioOptions = By.xpath("//span[contains(@class, 'docssharedWizToggleLabeledLabelText')]");
	private By allertClearBtn;
		
	public ContactPage(WebDriver driver, String submitLocator, String clearSelectionLocator, String clearForm, String allertClearLocator) {
		super(driver);
		System.out.println(submitLocator);
		submitBtn = By.xpath(submitLocator);
		clearSelectionBtn = By.xpath(clearSelectionLocator);
		clearFormBtn = By.xpath(clearForm);
		allertClearBtn = By.xpath(allertClearLocator);
		
	}

	public void selectItemInFirstQuestion(int item) {
		waitForElementsToAppear(fristQuestion, itensInQuestion).get(item).click();
	}
	
	public boolean isAnyItemChecked() throws InterruptedException {
		Thread.sleep(500);
		List<WebElement> radioItems = waitForElementsToAppear(radioWrapper, allRadioOptions);
		int a = radioItems.size();
		System.out.println("------------>"+a);
		for(int i=0;i<radioItems.size();i++) {
			System.out.println("------------>"+i);
			if(radioItems.get(i).isSelected()) {
				System.out.println("------------>"+i);
				return true;
			}
		}
		return false;
	}
	
	public void informAllFields(DataC data) {
		waitForElementToAppear(nameField).sendKeys(data.getName());
		waitForElementToAppear(emailField).sendKeys(data.getEmail());
		waitForElementToAppear(addressField).sendKeys(data.getAddress());
		waitForElementToAppear(phoneNumberField).sendKeys(data.getPhone());
		waitForElementToAppear(commentsField).sendKeys(data.getComments());
		
	}
	
	public void informAllFieldsExceptOne(String except, DataC data) {
		except = except.toLowerCase();
		if (!except.equals("name")) {
			waitForElementToAppear(nameField).sendKeys(data.getName());
		}
		if (!except.equals("email")) {
		waitForElementToAppear(emailField).sendKeys(data.getEmail());
		}
		if (!except.equals("address")) {
			waitForElementToAppear(addressField).sendKeys(data.getAddress());
		}
		if (!except.equals("phone number")){
			waitForElementToAppear(phoneNumberField).sendKeys(data.getPhone());
		}
		if (!except.equals("comments")){
			waitForElementToAppear(commentsField).sendKeys(data.getComments());
		}
		
	}

	
	public void submitContactInfo() throws InterruptedException {
		waitForElementToBeClickable(submitBtn).click();
	}
	
	public void clearFristQuestionSelection() {
		waitForElementToBeClickable(clearSelectionBtn).click();
	}
	
	public void clearForm() {
		waitForElementToBeClickable(clearFormBtn).click();
	}
	
	public void changeEmail(String content) {
		waitForElementToBeClickable(emailField).sendKeys(content);
	}
	
	public List<String> getAllTexts(){
		List<String> texts = new ArrayList<String>();
		texts.add(waitForElementToBeClickable(nameField).getAttribute("data-initial-value"));
		texts.add(waitForElementToBeClickable(emailField).getAttribute("data-initial-value"));
		texts.add(waitForElementToBeClickable(addressField).getAttribute("data-initial-value"));
		texts.add(waitForElementToBeClickable(phoneNumberField).getAttribute("data-initial-value"));
		texts.add(waitForElementToBeClickable(commentsField).getAttribute("data-initial-value"));
		return texts;
	}
	
	public String getErrorMessage(String field) throws InterruptedException {
		switch(field.toLowerCase()){
		case "name":
			return waitForElementToBeClickable(nameError).getText();
		case "email":
			return  waitForElementToBeClickable(emailError).getText();
		case "address":
			return waitForElementToBeClickable(addressError).getText();
			
		}
		return null;
	}

	public void confirmClearForm() {
		waitForElementToBeClickable(allertClearBtn).click();
	}
}
