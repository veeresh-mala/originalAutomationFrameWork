package contactTests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import genericUtility.BaseClass;
import genericUtility.ExcelFileUtility;
import genericUtility.WebDriverUtility;
import locatorsRepository.ContactInfoPage;
import locatorsRepository.ContactsPage;
import locatorsRepository.CreateContactsDetailsPage;
import locatorsRepository.HomePage;

public class ToCreateContactWithOrgTest extends BaseClass {

	@Test(groups = "regression")
	public void toCreateContact_001() throws EncryptedDocumentException, IOException {

		// Click on contacts link
		HomePage hp = new HomePage(driver);
		hp.getContactsLink().click();

		// Click on create organization button
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateContactButton().click();

		// Enter last name and select organization from child window
		CreateContactsDetailsPage cdp = new CreateContactsDetailsPage(driver);
		ExcelFileUtility eutil = new ExcelFileUtility();
		String LASTNAME = eutil.toReadDataFromExcelFile("Contacts", 1, 2);
		cdp.getLastName().sendKeys(LASTNAME);
		cdp.getCreateOrgButton().click();
		WebDriverUtility wutil = new WebDriverUtility();
		wutil.toSwitchWindow(driver, "Accounts");
		String orgName = cdp.getSelectOrg().getText();
		cdp.getSelectOrg().click();

		// Save and verify
		wutil.toSwitchWindow(driver, "Contacts");
		cdp.getSaveButton().click();
		ContactInfoPage cip = new ContactInfoPage(driver);
		String LastName = cip.getContactInfo().getText();
		Assert.assertTrue(LastName.contains(LASTNAME) && orgName.equals("abc"));
		Reporter.log(LastName + " and " + orgName + " Pass", true);
	}
}
