package NewProject2024;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDriventesting {
	WebDriver driver;
	@ BeforeMethod
	public void loadbrowser() {
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--disable-notifications");
		driver = new ChromeDriver(opt);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://www.firstcry.com/");
		
	}
	@AfterMethod
	public void closebrowser() {
		driver.close();
	}
	
	
	@DataProvider
	public String[] positivesearch () {
		String[]babyproducts = {"napkins", 
				                "diapers",
				                "tissue",
				                "toilets paper"};
		return babyproducts;
	}


@Test(dataProvider="positivesearch")

public void positivetesting(String products) {
	WebElement search =driver.findElement(By.id("search_box"));
	search.sendKeys(products);
WebElement searchbutton=	driver.findElement(By.xpath("//input[@ id =\"search_box\"]/following-sibling::span"));
	searchbutton.click();
	Assert.assertTrue(driver.getCurrentUrl().contains(products));
    Assert.assertTrue(driver.getTitle().contains(products));
      
		}
	
	
}




