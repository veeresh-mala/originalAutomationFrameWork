package practice;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;

import genericUtility.ExcelFileUtility;
import genericUtility.PropertyFileUtility;
import genericUtility.WebDriverUtility;
import locatorsRepository.ContactInfoPage;
import locatorsRepository.ContactsPage;
import locatorsRepository.CreateContactsDetailsPage;
import locatorsRepository.HomePage;
import locatorsRepository.LoginPage;

public class CreateContactWithPOM_GU_DD {

	public static void main(String[] args) throws IOException {

		PropertyFileUtility putil = new PropertyFileUtility();

		ExcelFileUtility eutil = new ExcelFileUtility();

		WebDriverUtility wutil = new WebDriverUtility();

		String URL = putil.toReadDataFromPropertyFile("url");
		String BROWSER = putil.toReadDataFromPropertyFile("browser");
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");

		String LASTNAME = eutil.toReadDataFromExcelFile("Contacts", 1, 2);

		// Step 1:- To launch browser
		WebDriver driver = null;
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("safari")) {
			driver = new SafariDriver();
		} else if (BROWSER.equals("edge")) {

		}
		wutil.toMaximize(driver);
		wutil.toUseImplicitWait(driver);

		// Step 2:- Login to application
		driver.get(URL);
		LoginPage lp = new LoginPage(driver);
		lp.getUserNameTextField().sendKeys(USERNAME); 
		lp.getPasswordTextField().sendKeys(PASSWORD);
		lp.getLoginButton().click();

		// Step 3:- Navigate to contact link
		HomePage hp = new HomePage(driver);
		hp.getContactsLink().click();

		// Step 4:- Click on create contact look up image
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateContactButton().click();

		// Step 5:- Create contact with mandatory fields
		CreateContactsDetailsPage cdp = new CreateContactsDetailsPage(driver);
		cdp.getLastName().sendKeys(LASTNAME);

		// Step 6:- Save and verify
		cdp.getSaveButton().click();

		ContactInfoPage cip = new ContactInfoPage(driver);

		String lastName = cip.getContactInfo().getText();
		if (lastName.contains(LASTNAME)) { 
			System.out.println(lastName + "-----Passed");
		} else {
			System.out.println("Failed to create contact");
		}

		// Step 7:- LogOut
		//WebElement signOut = hp.getLogOutLink();
		//wutil.toMouseHover(driver, signOut);
		
		//You can pass like this as well
		wutil.toMouseHover(driver, hp.getLogOutLink());
		hp.signOutButton().click();

		// Step 8:- Close the browser
		driver.quit();

	}

}
