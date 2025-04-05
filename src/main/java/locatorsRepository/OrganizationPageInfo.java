package locatorsRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPageInfo {
	
	public OrganizationPageInfo(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[@class='dvHeaderText']") 
	private WebElement orgInfo;

	public WebElement getOrgInfo() {
		return orgInfo;
	}

}
