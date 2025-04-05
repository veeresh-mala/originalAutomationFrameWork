 package locatorsRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateContactsDetailsPage {
	
	public CreateContactsDetailsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(name="lastname") 
	private WebElement lastName;
	
	@FindBy(xpath="//input[@name='account_name']/..//img[@title='Select']") 
	private WebElement createOrgButton;
		
	@FindBy(xpath="//input[@title='Save [Alt+S]']") 
	private WebElement saveButton;
	
	@FindBy(linkText="abc") 
	private WebElement selectOrg;

	public WebElement getSelectOrg() { 
		return selectOrg;
	}

	public WebElement getLastName() {
		return lastName;
	}

	public WebElement getCreateOrgButton() {
		return createOrgButton;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}
	
}
