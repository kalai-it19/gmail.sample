package com.gmail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GmailHomePage {

	WebDriver driver;

	public GmailHomePage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(className="gmail-nav__nav-link__sign-in")
	private WebElement signin;

	public void clickSignIn() {
		signin.click();
	}
}
