package com.legalZoom;

import java.awt.Window;
import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;

import com.legalZoom.customutil.propertyFile;

import io.github.bonigarcia.wdm.WebDriverManager;
import listener.testListener;

public class keywordLZ extends constant   {
	private static final TimeUnit SECONDS = null;
	private static WebDriver driver;
	private static Logger log = Logger.getLogger(keywordLZ.class);
	static String url = "";

	public static void openBrowser(String browserName) {
		switch (browserName) {
		case "Chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		default:
			break;
		}

	}

	public static void launchUrl(String url) {
		driver.get(url);
		driver.manage().window().maximize();
	}

	private static WebElement getWebElement(String locator) {
		System.out.println("Spliting the locator" + locator);
		String locatorType = propertyFile.getLocator(locator)[0];
		String locatorValue = propertyFile.getLocator(locator)[1];
		System.out.println("LocatorType:" + locatorType);
		System.out.println("LocatorValue:" + locatorValue);

		WebElement element = null;

		switch (locatorType) {
		case "xpath":
			element = driver.findElement(By.xpath(locatorValue));
			break;

		case "css":
			element = driver.findElement(By.cssSelector(locatorValue));
			break;

		case "id":
			element = driver.findElement(By.id(locatorValue));
			break;

		case "class":
			element = driver.findElement(By.className(locatorValue));
			break;

		case "linktext":
			element = driver.findElement(By.linkText(locatorValue));
			break;

		case "partialLinkText":
			element = driver.findElement(By.partialLinkText(locatorValue));
			break;

		default:
			break;
		}

		return element;
	}

	public static void toCheck_Logo_Availablity() {
		log.info("Check Logo");
		propertyFile.getLocator("checkLogo");
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	}

	public static void dropdownMenu() {
		// String magnifier=propertyFile.getLocator("magnifier");
		driver.findElement(By.xpath("//*[@id=\"navbarDropdownMenuLink-ma\"]")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public static void createAccount() {
		getWebElement("createAnAccount").click();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	}

	public static void createNewAccount(String newEmailid, String newPassword) {
		getWebElement("NewEmailComponent").sendKeys(newEmailid);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

		getWebElement("newPasswordComponent").sendKeys(newPassword);

		getWebElement("createMyAccount").click();

		String msg = getWebElement("errorMessage").getText();

		if (msg != "") {
			// Account Already Exists.
			System.out.println("Test: " + msg);
			Assert.assertTrue(true);
			// Then do login
		} else {
			Assert.assertTrue(false);
		}
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	}

	public static void closePopups() {

		getWebElement("covidclosePopup").click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		getWebElement("OfferClosedPopup").click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void verify_ScheduleConsultation() {
		getWebElement("ScheduleConsultation").click();
	}

	public static void verify_ToSelectAccountOverviewButton() {
		getWebElement("SelectAccountOverviewButton").click();
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	}

	public static void verify_editAccount() {
		getWebElement("EditAccount").click();
		getWebElement("EditName").click();
		minWait();
	}

	/*public static void verify_editname(String fname, String lname) throws InterruptedException {

		getWebElement("Firstname").sendKeys(fname);
		Thread.sleep(2000);
		

		getWebElement("Lastname").sendKeys(lname);
		Thread.sleep(2000);
		
		
		getWebElement("Save").click();
		Thread.sleep(2000);
	}

	public static void tearDown(ITestResult result) {

		testListener ptc = new testListener();
	        ptc.onTestFailure(result);
	}*/
	
	public static void minWait() {
		WebDriverWait wait = new WebDriverWait(driver, 50); // Explicite wait
	}

}
