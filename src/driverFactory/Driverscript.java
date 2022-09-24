package driverFactory;

import org.testng.Reporter;
import org.testng.annotations.Test;

import commonFunctions.FunctionLibrary;
import config.Apputil;
import utilities.ExcelFileUtil;

public class Driverscript extends Apputil {
	String inputpath="D:\\pallavi\\Hybrid_Framework\\TestInput\\DataEngine.xlsx";
	String outputpath="D:\\pallavi\\Hybrid_Framework\\TestOutput\\HybridResults.xlsx";
	String TCsheet="TestCases";
	String TSsheet="TestSteps";
	@Test
public 	void startTest() throws Throwable
	{
		boolean res=false;
		String tcres="";
		ExcelFileUtil xl=new ExcelFileUtil(inputpath);
		int TCCount=xl.rowcount(TCsheet);
		int TSCount=xl.rowcount(TSsheet);
		Reporter.log(TCCount+" "+TSCount,true);
		for (int i = 1; i <=TCCount; i++) 
		{
		String ExecutionMode=xl.getcelldata(TCsheet, i, 2);
		if(ExecutionMode.equalsIgnoreCase("Y"))
		{
			String tcid=xl.getcelldata(TCsheet, i, 0);
			for (int j = 1; j <=TSCount; j++)
			{
				String tsid=xl.getcelldata(TSsheet, j, 0);
				if(tcid.equalsIgnoreCase(tsid))
				{
					String keyWord=xl.getcelldata(TSsheet, j, 3);
					if(keyWord.equalsIgnoreCase("AdminLogin"))
					{
						String Para1=xl.getcelldata(TSsheet, j, 5);
						String Para2=xl.getcelldata(TSsheet, j, 6);
						res=FunctionLibrary.verifylogin(Para1, Para2);
					}
					else if(keyWord.equalsIgnoreCase("NewBranch"))
					{
						String Para1=xl.getcelldata(TSsheet, j, 5);
						String Para2=xl.getcelldata(TSsheet, j, 6);
						String Para3=xl.getcelldata(TSsheet, j, 7);
						String Para4=xl.getcelldata(TSsheet, j, 8);
						String Para5=xl.getcelldata(TSsheet, j, 9);
						String Para6=xl.getcelldata(TSsheet, j, 10);
						String Para7=xl.getcelldata(TSsheet, j, 11);
						String Para8=xl.getcelldata(TSsheet, j, 12);
						String Para9=xl.getcelldata(TSsheet, j, 13);
						FunctionLibrary.clickbranch();
						res=FunctionLibrary.verifynewbranch(Para1, Para2, Para3, Para4, Para5, Para6, Para7, Para8, Para9);
						}
					else if(keyWord.equalsIgnoreCase("UpdateBranch"))
					{
						String Para1=xl.getcelldata(TSsheet, j, 5);
						String Para2=xl.getcelldata(TSsheet, j, 6);
						String Para3=xl.getcelldata(TSsheet, j, 9);
						String Para4=xl.getcelldata(TSsheet, j, 10);
						FunctionLibrary.clickbranch();
						res=FunctionLibrary.verifybranchupdate(Para1, Para2, Para3, Para4);
					}
					else if(keyWord.equalsIgnoreCase("AdminLogout"))
					{
						res=FunctionLibrary.verifylogout();
					}
					String tsres="";
					if(res)
					{
						tsres="pass";
						xl.setcelldata(TSsheet, j, 4, tsres, outputpath);		
					}
					else
					{
						tsres="fail";
						xl.setcelldata(TSsheet, j, 4, tsres, outputpath);
					}
					tcres=tsres;

				}		
			}
			xl.setcelldata(TCsheet, i, 3, tcres,outputpath);
			
		}
		else
		{
			xl.setcelldata(TCsheet, i, 3, "blocked",outputpath);
			
		}
		}
		
	}

}
