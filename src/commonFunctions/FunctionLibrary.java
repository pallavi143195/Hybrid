package commonFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import config.Apputil;

public class FunctionLibrary extends Apputil 
{
	public static boolean verifylogin(String username,String password)
	{
		driver.findElement(By.xpath(config.getProperty("objuser"))).sendKeys(username);
		driver.findElement(By.xpath(config.getProperty("objpass"))).sendKeys(password);
		driver.findElement(By.xpath(config.getProperty("objlogin"))).click();
		String expected="adminflow";
		String actual=driver.getCurrentUrl();
		if(actual.toLowerCase().contains(expected.toLowerCase()))
		{
			Reporter.log("login success"+expected+" "+actual,true);
			return true;
		}
		else
		{
			Reporter.log("login fail"+expected+" "+actual,true);
			return false;
		}
	}
		public static void clickbranch()throws Throwable
		{
			driver.findElement(By.xpath(config.getProperty("Branch"))).click();
			Thread.sleep(4000);
	}
		
		public static boolean verifynewbranch(String BranchName,String Address1,String Address2,String Address3,String Area,
				String Zipcode,String coutry,String state,String city) throws Throwable
		{
			driver.findElement(By.xpath(config.getProperty("NewBranch"))).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath(config.getProperty("OBranchName"))).sendKeys(BranchName);
			driver.findElement(By.xpath(config.getProperty("OAddress1"))).sendKeys(Address1);
			driver.findElement(By.xpath(config.getProperty("OAddress2"))).sendKeys(Address2);
			driver.findElement(By.xpath(config.getProperty("OAddress3"))).sendKeys(Address3);
			driver.findElement(By.xpath(config.getProperty("OArea"))).sendKeys(Area);
			driver.findElement(By.xpath(config.getProperty("OZipcode"))).sendKeys(Zipcode);
			new Select(driver.findElement(By.xpath(config.getProperty("OCountry")))).selectByVisibleText(coutry);
			new Select(driver.findElement(By.xpath(config.getProperty("OState")))).selectByVisibleText(state);
			new Select(driver.findElement(By.xpath(config.getProperty("OCity")))).selectByVisibleText(city);
			driver.findElement(By.xpath(config.getProperty("OSubmit"))).click();
			Thread.sleep(4000);
			String expectedalert=driver.switchTo().alert().getText();
			Thread.sleep(1000);
			driver.switchTo().alert().accept();
			Thread.sleep(1000);
			String actualalert="New Branch with id";
			if(expectedalert.toLowerCase().contains(actualalert.toLowerCase()))
			{
				Reporter.log(expectedalert);
				return true;
			}
			else
			{
				Reporter.log("unable to create new branch",true);
				return false;
			}
			
			}
		public static boolean verifybranchupdate(String branchname,String address,String area,String zipcode) throws Throwable
		{
			driver.findElement(By.xpath(config.getProperty("Updation"))).click();
			Thread.sleep(1000);
			WebElement element1=driver.findElement(By.xpath(config.getProperty("objBranchName")));
			element1.clear();
			element1.sendKeys(branchname);
			WebElement element2=driver.findElement(By.xpath(config.getProperty("objAddress1")));
			element2.clear();
			element2.sendKeys(address);
			WebElement element3=driver.findElement(By.xpath(config.getProperty("objArea")));
			element3.clear();
			element3.sendKeys(area);
			WebElement element4=driver.findElement(By.xpath(config.getProperty("objZipcode")));
			element4.clear();
			element4.sendKeys(zipcode);
			driver.findElement(By.xpath(config.getProperty("objupdate"))).click();
			Thread.sleep(1000);
			String expectedupdatedalert=driver.switchTo().alert().getText();
			Thread.sleep(4000);
			driver.switchTo().alert().accept();
			String actualupdatealert="Branch updated";
			if(expectedupdatedalert.toLowerCase().contains(actualupdatealert.toLowerCase()))
			{
				Reporter.log(expectedupdatedalert,true);
				return true;
			}
			else
			{
				Reporter.log("branch update fail",true);
				return false;
			}
		}
		public static boolean verifylogout()throws Throwable
		{
			driver.findElement(By.xpath(config.getProperty("objlogout"))).click();
			Thread.sleep(1000);
			if(driver.findElement(By.xpath(config.getProperty("objlogin"))).isDisplayed())
			{
				Reporter.log("logout success",true);
				return true;
			}
			else
			{
				Reporter.log("logout fail",true);
				return true;
			}
			//public static void clickroles()throws Throwable
			//{
				//driver.findElement(By.xpath(config.getProperty("Roles"))).click();
				//Thread.sleep(4000);
			//}
			//public static boolean verifynewroles(String)

		
		}
}

		
	


