package organizationTests;

import java.io.IOException;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import genericUtility.BaseClass;
import genericUtility.ExcelFileUtility;
import genericUtility.WebDriverUtility;
import locatorsRepository.CreateOrganizationDetailsPage;
import locatorsRepository.HomePage;
import locatorsRepository.OrganizationPage;
import locatorsRepository.OrganizationPageInfo;

public class ToCreateOrgWithIndustryTest extends BaseClass {

	@Test(groups = "regression")
	public void toCreateOrgWithIndusty() throws EncryptedDocumentException, IOException {
		HomePage hp = new HomePage(driver);
		hp.getOrganizationLink().click();
		OrganizationPage op = new OrganizationPage(driver);
		op.createOrgButton().click();
		ExcelFileUtility eutil = new ExcelFileUtility();
		String ORGNAME = eutil.toReadDataFromExcelFile("Organization", 1, 2);
		Random r = new Random();
		int random = r.nextInt(2000);
		CreateOrganizationDetailsPage codp = new CreateOrganizationDetailsPage(driver);
		codp.getOrgName().sendKeys(ORGNAME + random);
		WebElement industryDropDown = codp.getIndustryDropDown();
		WebDriverUtility wutil = new WebDriverUtility();
		wutil.toHandleDropDown(industryDropDown, "Energy");
		codp.getSaveButton().click();
		OrganizationPageInfo opi = new OrganizationPageInfo(driver);
		String orgName = opi.getOrgInfo().getText();
		Assert.assertTrue(orgName.contains(ORGNAME));
		Reporter.log(orgName + " -----Passed", true);
	}
}
