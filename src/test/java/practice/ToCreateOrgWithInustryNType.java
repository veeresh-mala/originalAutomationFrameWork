package practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class ToCreateOrgWithInustryNType {
	
	public static void main(String[] args) {

		// Step 1:- Launch browser
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		// Step 2:- Login application with valid credentials
		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("password");
		driver.findElement(By.id("submitButton")).click();

		// Step 3:- Navigate to organization link
		driver.findElement(By.linkText("Organizations")).click();

		// Step 4:- Click on create organization look up image
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		// Step 5:- Create organization with mandatory fields
		driver.findElement(By.name("accountname")).sendKeys("TATA power");

		// Step 6:- Select chemicals from industry drop down
		WebElement industryDropdown = driver.findElement(By.name("industry"));
		Select dd = new Select(industryDropdown);
		dd.selectByValue("Energy");
		
		//Step 7:- Select customer from type drop down
		WebElement typeDropdown = driver.findElement(By.name("accounttype"));
		Select typedd = new Select(typeDropdown);
		typedd.selectByValue("Customer");

		// Step 8:- Save and verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String energyOrgName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		String industryType = driver.findElement(By.xpath("//font[text()='Energy']")).getText();
		String type = driver.findElement(By.xpath("//font[text()='Customer']")).getText();
		
		if (energyOrgName.contains("TATA") && industryType.equals("Energy")) {
			if(type.equals("Customer"))
			System.out.println(energyOrgName + "  " + industryType + " "+type+"   ------Passed");
		} else {
			System.out.println("Organization with industryType with type is failed");
		}

		// Step 9:- LogOut
		WebElement signOut = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action = new Actions(driver);
		action.moveToElement(signOut).perform();
		driver.findElement(By.linkText("Sign Out")).click();

		// Step 10:- Close the browser
		driver.quit();

	}

}
