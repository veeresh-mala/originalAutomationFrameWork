package practice;

import java.io.IOException;
import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import genericUtility.ExcelFileUtility;
import genericUtility.PropertyFileUtility;

public class UsingGenericMethodCreateOrg {

	public static void main(String[] args) throws IOException {
		//To read data from the property file(PropertyFileUtility is a generic method)
		
				PropertyFileUtility putil = new PropertyFileUtility();
				String URL = putil.toReadDataFromPropertyFile("url");
				String BROWSER = putil.toReadDataFromPropertyFile("browser");
				String USERNAME = putil.toReadDataFromPropertyFile("username");
				String PASSWORD = putil.toReadDataFromPropertyFile("password");
				
				//To read data from the property file(ExcelFileUtility is a generic method)
				
				ExcelFileUtility eutil = new  ExcelFileUtility();
				String ORGNAME = eutil.toReadDataFromExcelFile("Orgnization", 1, 2);	
				
				// Step 1:- Launch browser  
				WebDriver driver = null;
				if (BROWSER.equals("chrome")) {
					driver = new ChromeDriver();
				} else if (BROWSER.equals("edge")) {
					driver = new EdgeDriver();
				} else if (BROWSER.equals("firefox")) {
					driver = new FirefoxDriver();
				}
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

				// Step 2:- Login to application
				driver.get(URL);
				driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				driver.findElement(By.id("submitButton")).click();

				// Step 3:- Navigate to organization link
				driver.findElement(By.linkText("Organizations")).click();

				// Step 4:- Click on create organization look up image
				driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

				// Step 5:- Create organization with mandatory fields
				Random r = new Random();
				int random = r.nextInt(2000);
				driver.findElement(By.name("accountname")).sendKeys(ORGNAME + random);

				// Step 6:- Save and verify
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				String orgName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if (orgName.contains(ORGNAME)) {
					System.out.println(orgName + "------Passed");
				} else {
					System.out.println("Organization creation failed");
				}

				// Step 7:- LogOut
				WebElement signOut = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
				Actions action = new Actions(driver);
				action.moveToElement(signOut).perform();
				driver.findElement(By.linkText("Sign Out")).click();

				// Step 8:- Close the browser
				driver.quit();
	}
}
