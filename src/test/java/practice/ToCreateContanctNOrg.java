package practice;

import java.time.Duration;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ToCreateContanctNOrg {

	public static void main(String[] args) {

		// Step 1:- Launch browser
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Step 2:- Login application with valid credentials
		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("password");
		driver.findElement(By.id("submitButton")).click();

		// step 3:- Navigate to contact links
		driver.findElement(By.linkText("Contacts")).click();

		// Step 4:- Click on create contact look up image
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// Step 5:- Create contact with mandatory fields

		Random r = new Random();
		int random = r.nextInt(1000);

		driver.findElement(By.name("lastname")).sendKeys("Ramcharan M" + random);

		String mainWindow = driver.getWindowHandle();

		// Step 6:- Create organization

		driver.findElement(By.xpath("//input[@name='account_name']/..//img[@title='Select']")).click();

		Set<String> otherWindows = driver.getWindowHandles();

		for (String handle : otherWindows) {
			if (!handle.equals(mainWindow)) {
				// Switch to the new window
				driver.switchTo().window(handle);
				System.out.println("Switched to new window: " + driver.getTitle());

				driver.findElement(By.linkText("TATA power")).click();
			}
		}
		driver.switchTo().window(mainWindow);

		// Step 6:- Save and verify
		// Save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Verify
		String lastName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		System.out.println(lastName);
		String orgName = driver.findElement(By.xpath("//td[@id='mouseArea_Organization Name']//a[text()='TATA power']"))
				.getText();
		System.out.println();

		if (lastName.contains("Ramcharan ") && orgName.equals("TATA power")) {
			System.out.println(lastName + "  " + orgName + "-----Passed");
		} else {
			System.out.println("Failed to create contact");
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
