package com.gmail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GmailSignInPage {

	WebDriver driver;

	public GmailSignInPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(id = "identifierId")
	WebElement emailPhone;
	
	@FindBy(xpath = "//*[@id=\"identifierNext\"]/content/span")
	WebElement next;

	public void loginToGmail(String usrName) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		emailPhone.clear();
		emailPhone.sendKeys(usrName);
		next.click();
	}
}
