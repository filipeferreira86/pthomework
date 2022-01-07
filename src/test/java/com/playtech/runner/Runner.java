package com.playtech.runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)

@CucumberOptions(
		plugin = {"pretty", 
				"html:target/cucumber-html-report.htm",
				"json:target/cucumber.json", 
				"junit:target/cucumber.xml"},
		features = {"src/test/resources/features/form.feature"},
		glue = "com.playtech.steps",
		snippets = SnippetType.CAMELCASE
		)

public class Runner {

}
