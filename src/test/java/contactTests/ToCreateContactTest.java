package contactTests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtility.BaseClass;
import genericUtility.ExcelFileUtility;
import locatorsRepository.ContactInfoPage;
import locatorsRepository.ContactsPage;
import locatorsRepository.CreateContactsDetailsPage;
import locatorsRepository.HomePage;

@Listeners(genericUtility.ListernsImplimentation.class)
public class ToCreateContactTest extends BaseClass {
	@Test(groups = "smoke")
	public void toCreateContact_001() throws EncryptedDocumentException, IOException {
		// Click on Contacts
		HomePage hp = new HomePage(driver);
		hp.getContactsLink().click();

		// Click on create contact button
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateContactButton().click();

		// Enter last name
		CreateContactsDetailsPage cdp = new CreateContactsDetailsPage(driver);
		ExcelFileUtility eutil = new ExcelFileUtility();
		String LASTNAME = eutil.toReadDataFromExcelFile("Contacts", 1, 2);
		cdp.getLastName().sendKeys(LASTNAME);

		// Save and verify
		cdp.getSaveButton().click();
		//Assert.fail();
		ContactInfoPage cip = new ContactInfoPage(driver);
		String lastName = cip.getContactInfo().getText();
		Assert.assertTrue(lastName.contains(LASTNAME));
		Reporter.log(lastName + " Created successfully", true);
	}
}
