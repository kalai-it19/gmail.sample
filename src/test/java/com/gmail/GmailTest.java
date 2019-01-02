package com.gmail;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.gmail.datasets.GmailUser;
import com.gmail.datasets.ImportExcelData;

public class GmailTest {
	WebDriver driver;

	@BeforeTest
	@Parameters("browser")
	public void setup(String browser) {
		
		if (browser.equalsIgnoreCase("chrome")) {

			ChromeOptions options = new ChromeOptions();
			options.setBinary("C:\\Program Files (x86)\\Google\\Chrome Beta\\Application\\chrome.exe");

			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\Saba\\Downloads\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver(options);

		} else if (browser.equalsIgnoreCase("ie")) {

			System.setProperty("webdriver.ie.driver", "C:\\tools\\IEDriverServer.exe");
			//DesiredCapabilities caps = DesiredCapabilities.internetExplorer(); 
			//caps.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true); 

			InternetExplorerOptions options = new InternetExplorerOptions();
			options.destructivelyEnsureCleanSession();
			driver = new InternetExplorerDriver(options);
		}
		
		
		driver.get("https://www.google.com");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}
	
	@Test(priority = 1)
	public void googleHomePage() {
		//System.out.println("------Browser----" + browser);
		GoogleHomePage homePage = PageFactory.initElements(driver, GoogleHomePage.class);
		homePage.clickGmail();
		GmailHomePage gmailHome = PageFactory.initElements(driver, GmailHomePage.class);
		gmailHome.clickSignIn();

	}

	@Test(priority = 2)
	public void gmailSignInPage() {
		GmailSignInPage loginPage = PageFactory.initElements(driver, GmailSignInPage.class);

		List<GmailUser> users = ImportExcelData.getUsersFromExcel();

		for (GmailUser user : users) {
			loginPage.loginToGmail(user.username);

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			driver.navigate().back();
		}

		driver.quit();
	}

}
