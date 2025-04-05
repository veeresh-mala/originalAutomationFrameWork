package practice;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;

import genericUtility.ExcelFileUtility;
import genericUtility.PropertyFileUtility;
import genericUtility.WebDriverUtility;
import locatorsRepository.CreateOrganizationDetailsPage;
import locatorsRepository.HomePage;
import locatorsRepository.LoginPage;
import locatorsRepository.OrganizationPage;

public class CreateOrgWithDrpDwn_Pom_GU_DD {

	public static void main(String[] args) throws IOException {

		PropertyFileUtility putil = new PropertyFileUtility();
		ExcelFileUtility eutil = new ExcelFileUtility();
		WebDriverUtility wutil = new WebDriverUtility();

		String URL = putil.toReadDataFromPropertyFile("url");
		String BROWSER = putil.toReadDataFromPropertyFile("browser");
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");

		String ORGNAME = eutil.toReadDataFromExcelFile("Organization", 1, 2);

		// Step 1:- Launch the browser

		WebDriver driver = null;
		if (BROWSER.equals("Chrome")) {
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
		LoginPage lp = new LoginPage(driver);
		lp.getUserNameTextField().sendKeys(USERNAME);
		lp.getPasswordTextField().sendKeys(PASSWORD);
		lp.getLoginButton().click();

		// Step 3:- Navigate to organization link
		HomePage hp = new HomePage(driver);
		hp.getOrganizationLink().click();

		// Step 4:- Click on create organization button
		OrganizationPage op = new OrganizationPage(driver);
		op.createOrgButton().click();

		// Step 5:- Create organization with mandatory fields
		CreateOrganizationDetailsPage odp = new CreateOrganizationDetailsPage(driver);
		Random r = new Random();
		int random = r.nextInt(2000);
		odp.getCreateOrganizationButton().click();
		odp.getOrgName().sendKeys(ORGNAME + random);
		
		 

	}
}
