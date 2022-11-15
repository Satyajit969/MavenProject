package driverfactory;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.FunctionLibrary;
import utilities.ExcelFileUtil;

public class Driverscript {


	public static WebDriver driver;
	String inputpath="D:\\satya\\selenium work\\Maven_Project\\Testinput\\framework.xlsx";
	String outputpath="D:\\satya\\selenium work\\Maven_Project\\TestOutput\\project.xlsx";
	ExtentReports report;
	ExtentTest test;

	public void startTest()throws Throwable
	{
		String ModuleStatus="";
	
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		for(int i=1;i<=xl.rowcount("MasterTestCases");i++)
		{
			if(xl.getcelldata("MasterTestCases", i, 2).equalsIgnoreCase("Y"))
			{
				String TCModule =xl.getcelldata("MasterTestCases", i, 1);
				report=new ExtentReports("./ExtentReports/Reports/"+TCModule+FunctionLibrary.generateDate()+".html");
				for(int j=1;j<=xl.rowcount(TCModule);j++)
				{
					test=report.startTest(TCModule);
					String Description =xl.getcelldata(TCModule, j, 0);
					String ObjectType =xl.getcelldata(TCModule, j, 1);
					String LocatorType =xl.getcelldata(TCModule, j, 2);
					String LocatorValue= xl.getcelldata(TCModule, j, 3);
					String TestData =xl.getcelldata(TCModule, j, 4);
					try {
						if(ObjectType.equalsIgnoreCase("startBrowser"))
						{
							driver =FunctionLibrary.startBrowser();
							test.log(LogStatus.INFO, Description);
						}
						else if(ObjectType.equalsIgnoreCase("openUrl"))
						{
							FunctionLibrary.openUrl(driver);
							test.log(LogStatus.INFO, Description);
						}
						else if(ObjectType.equalsIgnoreCase("waitForElement"))
						{
							FunctionLibrary.waitForElement(driver, LocatorType, LocatorValue, TestData);
							test.log(LogStatus.INFO, Description);
						}
						else if(ObjectType.equalsIgnoreCase("typeAction"))
						{
							FunctionLibrary.typeAction(driver, LocatorType, LocatorValue, TestData);
							test.log(LogStatus.INFO, Description);
						}
						else if(ObjectType.equalsIgnoreCase("clickAction"))
						{
							FunctionLibrary.clickAction(driver, LocatorType, LocatorValue);
							test.log(LogStatus.INFO, Description);
						}
						else if(ObjectType.equalsIgnoreCase("validateTitle"))
						{
							FunctionLibrary.validateTitle(driver, TestData);
							test.log(LogStatus.INFO, Description);
						}
						else if(ObjectType.equalsIgnoreCase("closeBrowser"))
						{
							FunctionLibrary.closeBrowser(driver);
							test.log(LogStatus.INFO, Description);
						}
						else if(ObjectType.equalsIgnoreCase("captureData"))
						{
							FunctionLibrary.captureData(driver, LocatorType, LocatorValue);
							test.log(LogStatus.INFO, Description);
						}
						else if(ObjectType.equalsIgnoreCase("supplierTable"))
						{
							FunctionLibrary.supplierTable(driver);
							test.log(LogStatus.INFO, Description);
						}
						else if(ObjectType.equalsIgnoreCase("mouseClick"))
						{
							FunctionLibrary.mouseClick(driver, LocatorType, LocatorValue);
							test.log(LogStatus.INFO, Description);
						}
						else if(ObjectType.equalsIgnoreCase("Stocktable"))
						{
							FunctionLibrary.Stocktable(driver, TestData);
							test.log(LogStatus.INFO, Description);
						}
					
						xl.setcelldata(TCModule, j, 5, "Pass", outputpath);
						test.log(LogStatus.PASS, Description);
						ModuleStatus="true";
					}catch(Exception e)
					{
						System.out.println(e.getMessage());
						xl.setcelldata(TCModule, j, 5, "Fail", outputpath);	
						test.log(LogStatus.FAIL, Description);
						ModuleStatus="false";
					}
					if(ModuleStatus.equalsIgnoreCase("True"))
					{
						xl.setcelldata("MasterTestCases", i, 3, "Pass", outputpath);
					}
					if(ModuleStatus.equalsIgnoreCase("False"))
					{
						xl.setcelldata("MasterTestCases", i, 3, "Fail", outputpath);	
					}
				}
				report.endTest(test);
				report.flush();
			}
			else
			{
			
				xl.setcelldata("MasterTestCases", i, 3, "Blocked", outputpath);
			}
		}
	}

}







		
	




