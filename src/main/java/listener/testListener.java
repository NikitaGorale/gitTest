/**
 * 
 */
package listener;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;



	public class testListener implements ITestListener  {
WebDriver driver;
		@Override
		public void onFinish(ITestContext arg0) {
			System.out.println("On finish");
		}

		@Override
		public void onStart(ITestContext arg0) {
			System.out.println("On Start");
		}

		@Override
		public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
			System.out.println("On test failure but within success percentage");
			
		}

		@Override
		public void onTestFailure(ITestResult result) {
			
			System.out.println("On Test failure");

			if (ITestResult.FAILURE == result.getStatus()) {
				try {

					TakesScreenshot ts = (TakesScreenshot) driver;


					File source = ts.getScreenshotAs(OutputType.FILE);

					FileUtils.copyFile(source,
							new File("C:\\Users\\Santosh\\Desktop\\screenshot\\" + result.getName() + ".png"));

					System.out.println("Screenshot taken");
				} catch (Exception e) {

					System.out.println("Exception while taking screenshot " + e.getMessage());
				}

			}
			}

		@Override
		public void onTestStart(ITestResult result) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTestSuccess(ITestResult result) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTestSkipped(ITestResult result) {
			// TODO Auto-generated method stub
			
		}

}
