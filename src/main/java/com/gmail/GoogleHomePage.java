package com.gmail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleHomePage {
	WebDriver driver;

	public GoogleHomePage(WebDriver driver) {
		this.driver = driver;
	}
	//@FindBy(how = How.LINK_TEXT, using ="Gmail")
	@FindBy(linkText="Gmail")
	private WebElement gmailLink;
	
	
	public void clickGmail()
	{
		gmailLink.click();		
	}
	
	
	
	
}