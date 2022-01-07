package com.playtech.support;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Supports {

	public byte[] capturar(WebDriver driver) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		byte[] source = ts.getScreenshotAs(OutputType.BYTES);
		return source;
	}

}
