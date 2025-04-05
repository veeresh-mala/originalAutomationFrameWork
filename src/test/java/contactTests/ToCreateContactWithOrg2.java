package contactTests;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import genericUtility.BaseClass;
import genericUtility.ExcelFileUtility;
import genericUtility.WebDriverUtility;
import locatorsRepository.ContactInfoPage;
import locatorsRepository.ContactsPage;
import locatorsRepository.CreateContactsDetailsPage;
import locatorsRepository.CreateOrganizationDetailsPage;
import locatorsRepository.HomePage;
import locatorsRepository.OrganizationPage;
import locatorsRepository.OrganizationPageInfo;

public class ToCreateContactWithOrg2 extends BaseClass {
	@Test
	public void createOrg() throws EncryptedDocumentException, IOException {
		HomePage hp = new HomePage(driver);
		hp.getOrganizationLink().click();
		OrganizationPage op = new OrganizationPage(driver);
		op.createOrgButton().click();
		ExcelFileUtility eutil = new ExcelFileUtility();
		Random r = new Random();
		int random = r.nextInt(2000);
		String ORGNAME = eutil.toReadDataFromExcelFile("Organization", 1, 2) + random;
		CreateOrganizationDetailsPage codp = new CreateOrganizationDetailsPage(driver);
		codp.getOrgName().sendKeys(ORGNAME);
		codp.getSaveButton().click();
		OrganizationPageInfo opi = new OrganizationPageInfo(driver);
		String orgName = opi.getOrgInfo().getText();
		if (orgName.contains(ORGNAME)) {
			System.out.println(orgName + " ----Passed");
		} else {
			System.out.println(orgName + " ----Failed");
		}

		// Click on contacts link
		hp.getContactsLink().click();

		// Click on create organization button
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateContactButton().click();

		// Enter last name and select organization from child window
		CreateContactsDetailsPage cdp = new CreateContactsDetailsPage(driver);
		ExcelFileUtility eutil1 = new ExcelFileUtility();
		String LASTNAME = eutil1.toReadDataFromExcelFile("Contacts", 1, 2);
		cdp.getLastName().sendKeys(LASTNAME);
		cdp.getCreateOrgButton().click();
		WebDriverUtility wutil = new WebDriverUtility();
		wutil.toSwitchWindow(driver, "Accounts");
		driver.findElement(By.xpath("//a[text()='+"+ORGNAME+"+']")).click();
		
		 WebElement table = driver.findElement(By.xpath("//table[@class='small']/..//div[@style='overflow:auto;height:348px;']")); // Modify locator as needed
         
         // Get all rows
         List<WebElement> rows = table.findElements(By.tagName("tr"));
         
         if (!rows.isEmpty()) {
             // Get the last row
             WebElement lastRow = rows.get(rows.size() - 2);
             
             // Find the element to click inside the last row (e.g., a button or link)
             WebElement lastRowElement = lastRow.findElement(By.tagName("td")).findElement(By.tagName("a")); // Modify as needed
             
             // Click the element
             lastRowElement.click();
         } else {
             System.out.println("No rows found in the table.");
         }

		////table[@class='small']/..//div[@style='overflow:auto;height:348px;']
		// driver.findElement(By.xpath("//a[text()='+"+ORGNAME+"+']")).click();
		// System.out.println(elementname);
		// String orgName1 = cdp.getSelectOrg().getText();
		//cdp.getSelectOrg().click();

		// Save and verify
		wutil.toSwitchWindow(driver, "Contacts");
		cdp.getSaveButton().click();
		ContactInfoPage cip = new ContactInfoPage(driver);
		String name = cip.getContactInfo().getText();
		Assert.assertTrue(name.contains(LASTNAME));
	}
	
}
