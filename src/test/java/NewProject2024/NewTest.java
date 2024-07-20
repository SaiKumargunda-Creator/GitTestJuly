package NewProject2024;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NewTest {
	WebDriver driver;
	final String expectedregisterurl = "https://demo.guru99.com/insurance/v1/register.php";
	final String expectedsuccessfulregisterurl = "https://demo.guru99.com/insurance/v1/successful/register.php";
    final String expectedfailurepageurl = "https://demo.guru99.com/insurance/v1/failure/register.php";
 
	@ BeforeMethod
	public void loadbrowser() {
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--disable-notifications");
		driver = new ChromeDriver(opt);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://demo.guru99.com/insurance/v1/index.php");
		
	}
	@AfterMethod
	public void closebrowser() {
		driver.close();
	}
		@Test
		public void registerlink() {
			driver.findElement(By.xpath("//a[text() ='Register']")).click();
		}
		
		@Test (priority=0,enabled= true)
		public void Validationofregistrationspage () {
			registerlink();
			Assert.assertEquals(driver.getCurrentUrl(),expectedregisterurl );
			}
		@Test (priority=1, dependsOnMethods ="Validationofregistrationspage", enabled = true)
		public void positiveregistrationtest() {
			registerlink();
			driver.findElement(By.name("firstname")).sendKeys("sai");
			driver.findElement(By.name("lastname")).sendKeys("gunda");
			driver.findElement(By.name("email")).sendKeys("gunda.sai@gmail.com");
			driver.findElement(By.name("password")).sendKeys("test@123");
			driver.findElement(By.name("c_password")).sendKeys("test@123");
			driver.findElement(By.name("submit")).click();
			Assert.assertEquals(driver.getCurrentUrl(),expectedsuccessfulregisterurl );
			
		}
		
		@Test (priority=2, dependsOnMethods ="Validationofregistrationspage",enabled = false)
		public void negativeregistrationtest() {
			registerlink();
			driver.findElement(By.name("firstname")).sendKeys("sachin");
			driver.findElement(By.name("lastname")).sendKeys("reddy");
			driver.findElement(By.name("email")).sendKeys("sachin.sai@gmail.com");
			driver.findElement(By.name("password")).sendKeys("test@123");
			driver.findElement(By.name("c_password")).sendKeys("test@123");
			driver.findElement(By.name("submit")).click();
			Assert.assertEquals(driver.getCurrentUrl(),expectedfailurepageurl );
			
		}
		
		
		
	}