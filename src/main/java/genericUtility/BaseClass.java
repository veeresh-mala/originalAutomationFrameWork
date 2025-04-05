package genericUtility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import locatorsRepository.HomePage;
import locatorsRepository.LoginPage;

public class BaseClass {

	PropertyFileUtility putil = new PropertyFileUtility();
	WebDriverUtility wutil = new WebDriverUtility();
	public WebDriver driver = null;
	public static WebDriver sdriver; //It is for listeners to take screenShot

	@BeforeSuite(groups = { "smoke", "regression" })
	public void beforeSuiteConfiguarjion() {
		Reporter.log("Data base connection established", true);
	}

	// To perform the cross browser testing uncomment the parameters, BeforeTest and
	// Uncomment method parameter String BROWSER.

	// @Parameters("browser")
	// @BeforeTest
	@BeforeClass(groups = { "smoke", "regression" })
	public void beforeClassConfiguration(/* String BROWSER */) throws IOException {
		String BROWSER = putil.toReadDataFromPropertyFile("browser");
		String URL = putil.toReadDataFromPropertyFile("url");

		if (BROWSER.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		Reporter.log("Browser Launced Successfully", true);
		sdriver = driver; //It is for listeners to take screenShot
		wutil.toMaximize(driver);
		wutil.toUseImplicitWait(driver);
		driver.get(URL);
	}

	@BeforeMethod(groups = { "smoke", "regression" })
	public void beforeMethodConfiguration() throws IOException {
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");
		LoginPage lp = new LoginPage(driver);
		lp.getUserNameTextField().sendKeys(USERNAME);
		lp.getPasswordTextField().sendKeys(PASSWORD);
		lp.getLoginButton().click();
		Reporter.log("Logged in suceessfully", true);
	}

	@AfterMethod(groups = { "smoke", "regression" })
	public void afterMethodConfiguration() {
		HomePage hp = new HomePage(driver);
		wutil.toMouseHover(driver, hp.getLogOutLink());
		hp.signOutButton().click();
		Reporter.log("Logged out successfully", true);
	}

	@AfterClass(groups = { "smoke", "regression" })
	public void afterClassConfiguration() {
		Reporter.log("Browser got closed successfully", true);
		driver.quit();
	}

	@AfterSuite(groups = { "smoke", "regression" })
	public void afterSuiteConfiguration() {
		Reporter.log("Data base disconnected", true);
	}
}
