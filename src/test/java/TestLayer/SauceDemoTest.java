package TestLayer;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import PageLayer.SauceDemo;
import UtilityLayer.ExcelReader;

public class SauceDemoTest {

	WebDriver driver;
	
	@BeforeMethod
	@Parameters("browser")
	public void setUp(String browser) {
		if(browser.equals("firefox")) {
			 driver = new FirefoxDriver();
		}else if(browser.equals("chrome")) {
			 driver = new ChromeDriver();
		}
	}
	
	@Test(dataProvider = "log")
	void loginTest(String uName, String passWord) {

		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		
		SauceDemo sd = new SauceDemo(driver);
		
		driver.get("https://www.saucedemo.com/");
		
		sd.enterUsername(uName);
		sd.enterPassword(passWord);
		sd.clickBtn();
		
		Assert.assertTrue(driver.findElement(By.id("shopping_cart_container")).isDisplayed());
		
		driver.quit();
	}
	
	
	@DataProvider(name="log")
	public Object[][] getData() throws Exception{
		/*
		 * Object loginData [][] = { {"standard_user","secret_sauce"},
		 * {"problem_user","secret_sauce"}, };
		 */
		// get data from xl
		
		String path = ".\\data\\info.xlsx";
		ExcelReader read = new ExcelReader(path);
		
		int rows =  read.getRowCount(path);
		int colms = read.getCellCount(path, rows);
		
		String data[][] = new String[rows][colms];
		
		for(int i=1;i<=rows;i++)
		{
			for(int j=0;j<colms;j++)
			{
	data[i-1][j] = read.getCellData("Sheet0", i, j);
			}
		}
		
		return data;
	}
	
	
}
