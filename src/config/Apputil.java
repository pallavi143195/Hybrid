package config;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class Apputil {
	public static  WebDriver driver;
	public static Properties config;
	@BeforeSuite
	public static void setup() throws Throwable {
		config=new Properties();
		config.load(new FileInputStream("D:\\pallavi\\Hybrid_Framework\\PropertyFiles\\Environment.properties"));
		if(config.getProperty("Browser").equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.get(config.getProperty("url"));
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			
		}
		else if(config.getProperty("Browser").equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
			driver.manage().deleteAllCookies();
			driver.get(config.getProperty("url"));
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		}
		else
		{
			System.out.println("Browser is not matching");
		}
	}
		@AfterSuite
		public static void teardown()
		{
			driver.close();
		}
	}


