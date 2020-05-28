package com.legalZoomTests;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.legalZoom.keywordLZ;

import listener.testListener;

@Listeners({testListener.class })
public class signInTestCases extends keywordLZ  {

	@Test
	public void verify_MyAccount() {
		keywordLZ.openBrowser("Chrome");
		keywordLZ.launchUrl("https://www.legalzoom.com/country/in");
		minWait();
		toCheck_Logo_Availablity();

		dropdownMenu();

		keywordLZ.createAccount();
		keywordLZ.createNewAccount("leagalzoom@mail.com", "mytest");
		minWait();
		keywordLZ.closePopups();
		minWait();
		keywordLZ.verify_ScheduleConsultation();
		minWait();
		keywordLZ.verify_ToSelectAccountOverviewButton();
		minWait();
		keywordLZ.verify_editAccount();
		minWait();
		
			//keywordLZ.verify_editname("Test", "Mytest");
		
			
		
		Assert.assertTrue(true);

	}

	/*public void closeBrowser(ITestResult ITestResult) {
		keywordLZ.tearDown(ITestResult);
	}*/
}
