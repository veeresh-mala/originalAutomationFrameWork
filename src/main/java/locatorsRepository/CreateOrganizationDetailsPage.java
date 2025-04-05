package locatorsRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateOrganizationDetailsPage {
	
	public CreateOrganizationDetailsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//img[@title='Create Organization...']") 
	private WebElement createOrganizationButton;

	@FindBy(name="industry") 
	private WebElement industryDropDown;
	
	@FindBy(name="accounttype") 
	private WebElement typeDropDown;
	
	@FindBy(name = "accountname") 
	private WebElement orgName;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']") 
	private WebElement saveButton;

	public WebElement getSaveButton() {
		return saveButton;
	}

	public WebElement getOrgName() {
		return orgName;
	}

	public WebElement getCreateOrganizationButton() {
		return createOrganizationButton;
	}

	public WebElement getIndustryDropDown() {
		return industryDropDown;
	}

	public WebElement getTypeDropDown() {
		return typeDropDown;
	}

}
