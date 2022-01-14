package com.playtech.steps;

import java.util.List;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.playtech.pages.ContactPage;
import com.playtech.pages.FormSentPage;
import com.playtech.support.Conn;
import com.playtech.support.DataC;
import com.playtech.support.Supports;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Steps {

	public WebDriver driver;
	public Scenario scenario;

	public Conn conn;
	public ContactPage contactPage;
	public FormSentPage formSentPage;
	public DataC data;
	public Supports support = new Supports();

	@Before
	public void bfore(Scenario c) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		this.scenario = c;
	}

	@AfterStep
	public void afterStep() {
		this.scenario.attach(support.capturar(driver), "image/png", "prt");
	}

	@Given("Im in the contact form page")
	public void imInTheContactFormPage() {
		driver.get("https://docs.google.com/forms/d/e/"
				+ "1FAIpQLScVG7idLWR8sxNQygSnLuhehUNVFti0FnVviWCSjDh-JNhsMA/viewform");
		String xmlLang = driver.findElement(By.tagName("html")).getAttribute("lang");
		conn = new Conn();
		data = conn.getData(xmlLang);
		contactPage = new ContactPage(driver, data.getSubmitLocator(), data.getClearSelectionLocator(),
				data.getClearFormLocator(), data.getAllertClearLocator());

	}

	@When("I select the option {int} in the first question")
	public void iSelectTheOptionInTheFirstQuestion(Integer item) {
		contactPage.selectItemInFirstQuestion(item);
	}

	@When("Inform all fields")
	public void informAllFields() {
		contactPage.informAllFields(data);
	}

	@When("Inform all fields except for the {string}")
	public void informAllFieldsExceptForThe(String field) {
		contactPage.informAllFieldsExceptOne(field, data);
	}

	@When("click on the submit button")
	public void clickOnTheSubmitButton() throws InterruptedException {
		contactPage.submitContactInfo();
	}

	@When("click on the link for clear selection")
	public void clickOnTheLinkForClearSelection() {
		contactPage.clearFristQuestionSelection();
	}

	@When("click on the clear form allert")
	public void clickOnTheClearFormAllert() {
		contactPage.confirmClearForm();
	}

	@When("click on the clear form")
	public void clickOnTheClearForm() {
		contactPage.clearForm();
	}

	@When("Change the e-mail for {string}")
	public void changeTheEmailFor(String email) {
		contactPage.changeEmail(email);
	}

	@Then("all fields should be blank")
	public void allFieldsShouldBeBlank() throws InterruptedException {
		List<String> fieldsContents = contactPage.getAllTexts();
		for (int i = 0; fieldsContents.size() > i; i++) {
			Assert.assertEquals("The item " + i + " is not blank", "", fieldsContents.get(i));
		}
	}
	
	@Then("a error message {string} should be presented")
	public void aErrorMessageShouldBePresented(String content) {
		if(driver.getPageSource().contains(content)){
			Assert.assertTrue(true);
		}

		else{
			Assert.assertTrue("The error "+ content +" is not present in the page", false);
		}
	}

	@Then("the form should be submited")
	public void the_form_should_be_submited() {
		formSentPage = new FormSentPage(driver);
		Assert.assertEquals("The confirmation messagem is not ok", "Thanks for submitting your contact info!",
				formSentPage.getConfirmationMessage());
	}

	@Then("a error message should be presented in the {string}")
	public void aErrorMessageShouldBePresentedInThe(String field) throws InterruptedException {
		Assert.assertEquals("The error message is no shown as expected", data.getMessage(),
				contactPage.getErrorMessage(field));
	}

	@Then("a error message about incorrect format should be presented")
	public void aErrorMessageShouldBePresentedInThe() throws InterruptedException {
		Assert.assertEquals("The error message is no shown as expected", data.getEmailMessage(),
				contactPage.getErrorMessage("email"));
	}

	@Then("no option in the first question should be selected")
	public void no_option_in_the_first_question_should_be_selected() throws InterruptedException {
		Assert.assertTrue("", !contactPage.isAnyItemChecked());
	}

	@After
	public void after() {
		driver.quit();
	}
}
