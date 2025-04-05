package locatorsRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="user_name") 
	private WebElement UserNameTextField;
	
    @FindAll({@FindBy(name="user_password"), @FindBy(xpath="//input[@type='password']")})
	private WebElement passwordTextField;
    
    @FindBy(id="submitButton")
    private WebElement loginButton;

	public WebElement getUserNameTextField() {
		return UserNameTextField;
	}

	public WebElement getPasswordTextField() {
		return passwordTextField;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}

}
