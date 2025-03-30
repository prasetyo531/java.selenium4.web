package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageObjects extends BasePageObjects {

    private @FindBy(id = "userId") WebElement userField;
    private @FindBy(id = "password") WebElement passField;
    private @FindBy(id = "submitButton") WebElement loginBtn;

    public LoginPageObjects() {
        super();
    }

    public void navigateToKrisdemoLoginPage() {
        navigateTo_URL();
    }

    public void setUser(String username) {
        sendKeys(userField, username);
    }

    public void setPass(String password) {
        sendKeys(passField, password);
    }

    public void clickLoginBtn() {
        waitForWebElementAndClick(loginBtn);
    }

    public void validateSuccessfulLoginMessage() {
        waitForAlertAndValidateText("validation succeeded");
    }

    public void validateUnsuccessfulLoginMessage() {
        waitForAlertAndValidateText("validation failed");
    }
}
