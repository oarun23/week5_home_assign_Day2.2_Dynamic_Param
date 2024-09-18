package week5.homeassignments;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DynamicParamTestNG {
	
	@Parameters({"url","username","password"})
	@Test(dataProvider = "runName")
	public void runDynamicParam(String entityName, String companyName) {
	
		
ChromeOptions options = new ChromeOptions();
		
		options.addArguments("--disable-notifications");
		
		ChromeDriver driver = new ChromeDriver(options);
		
		
		driver.get("https://login.salesforce.com");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));		
		driver.manage().window().maximize();
		
		
		driver.findElement(By.id("username")).sendKeys("gokul.sekar@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Leaf$321");
		driver.findElement(By.id("Login")).click();
		
		
		driver.findElement(By.xpath("//button[@title='App Launcher']")).click();
		driver.findElement(By.xpath("//button[@aria-label='View All Applications']")).click();
		
		WebElement element = driver.findElement(By.xpath("//p[text()='Legal Entities']"));
		
		Actions action = new Actions(driver);
		
		action.moveToElement(element).click().perform();
		
		//driver.findElement(By.xpath("(//*[name()='svg' and @class='slds-icon slds-icon-text-default slds-icon_xx-small'])[5]")).click();
		
		driver.findElement(By.xpath("(//a[@class='slds-button slds-button_reset'])[4]")).click();
		
		//driver.findElement(By.xpath("//span[text()='New Legal Entity']")).click();
		
		driver.findElement(By.xpath("(//*[name()='svg' and @data-key='add'])[2]")).click();
		
		//String name ="Salesforce Automation by Arun";
		
		driver.findElement(By.xpath("(//input[@class='slds-input'])[2]")).sendKeys(entityName);
		
		driver.findElement(By.xpath("(//input[@class='slds-input'])[3]")).sendKeys(companyName);
		
		driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
		
		String text = driver.findElement(By.xpath("//div[@title='']")).getText();
		
		System.out.println(text);
		
		if(text.equals(entityName)) {
			System.out.println("Legal Entity Name Verified");
		}else{
			System.out.println("Not Correct");
		}
		

	}
	
	@DataProvider
	public String[][] runName() {
		
		String str [] [] = new String[2][2];
		
		str [0][0] ="Salesforce Automation by Arun1";
		str [0][1] ="Arun1";
		
		str [1][0] ="Salesforce Automation by Arun2";
		str [1][1] ="Arun2";
				
		return str;
	}

}
