package practice;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;

import genericUtility.ExcelFileUtility;
import genericUtility.PropertyFileUtility;
import genericUtility.WebDriverUtility;
import locatorsRepository.CreateOrganizationDetailsPage;
import locatorsRepository.HomePage;
import locatorsRepository.LoginPage;
import locatorsRepository.OrganizationPageInfo;

public class CreateOrgWithPOM {

	public static void main(String[] args) throws IOException {
		PropertyFileUtility putil = new PropertyFileUtility();

		ExcelFileUtility eutil = new ExcelFileUtility();

		WebDriverUtility wutil = new WebDriverUtility();

		String URL = putil.toReadDataFromPropertyFile("url");
		String BROWSER = putil.toReadDataFromPropertyFile("browser");
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");

		String ORGNAME = eutil.toReadDataFromExcelFile("Organization", 1, 2);

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
		LoginPage lp = new LoginPage(driver);
		driver.get(URL);
		lp.getUserNameTextField().sendKeys(USERNAME);
		lp.getPasswordTextField().sendKeys(PASSWORD);
		lp.getLoginButton().click();

		// Step 3:- Navigate to organization link
		HomePage hp = new HomePage(driver);
		hp.getOrganizationLink().click();

		// Step 4:- Click on create organization look up image
		CreateOrganizationDetailsPage odp = new CreateOrganizationDetailsPage(driver);
		odp.getCreateOrganizationButton().click();

		// Step 5:- Create organization with mandatory fields
		Random r = new Random();
		int random = r.nextInt(2000);
		odp.getCreateOrganizationButton().click();
		odp.getOrgName().sendKeys(ORGNAME + random);

		// Step 6:- Save and verify
		odp.getSaveButton().click();
		OrganizationPageInfo opi = new OrganizationPageInfo(driver);
		String orgName = opi.getOrgInfo().getText();

		if (orgName.contains("GlobalLogic")) {
			System.out.println(orgName + "------Passed");
		} else {
			System.out.println("Organization creation failed");
		}

		// Step 7:- LogOut
		WebElement signOut = hp.getLogOutLink();
		wutil.toMouseHover(driver, signOut);
		// You can also pass the element in this way
		// wutil.toMouseHover(driver, hp.getLogOutLink());
		hp.signOutButton().click();

		// Step 8:- Close the browser
		driver.quit();
	}

}
