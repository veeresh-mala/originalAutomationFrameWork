package practice;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;

import genericUtility.ExcelFileUtility;
import genericUtility.PropertyFileUtility;
import genericUtility.WebDriverUtility;

public class CreateContactByGenericUtilityAndDDT {

	public static void main(String[] args) throws IOException {

		// To read data from property file
		PropertyFileUtility putil = new PropertyFileUtility();
		// To read data from excel file
		ExcelFileUtility eutil = new ExcelFileUtility();
		// To use common method
		WebDriverUtility wutil = new WebDriverUtility();

		String URL = putil.toReadDataFromPropertyFile("url");
		String BROWSER = putil.toReadDataFromPropertyFile("browser");
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");

		String LASTNAME = eutil.toReadDataFromExcelFile("Contacts", 1, 2);

		// Step 1: Launch browser
		WebDriver driver = null;
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equals("safari")) {
			driver = new SafariDriver();
		}

		wutil.toMaximize(driver);
		wutil.toUseImplicitWait(driver);

		// Step 2:- Login to application
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		// step 3:- Navigate to contact links
		driver.findElement(By.linkText("Contacts")).click();

		// Step 4:- Click on create contact look up image
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// Step 5:- Create contact with mandatory fields
		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);

		// Step 6:- Save and verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String Name = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (Name.contains(LASTNAME)) {
			System.out.println(Name + "-----Passed");
		} else {
			System.out.println("Failed to create contact");
		}

		// Step 7:- LogOut
		WebElement signOut = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wutil.toMouseHover(driver, signOut);
		driver.findElement(By.linkText("Sign Out")).click();

		// Step 8:- Close the browser
		driver.quit();
	}
}
