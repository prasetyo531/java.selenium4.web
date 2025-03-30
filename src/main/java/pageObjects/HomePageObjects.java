package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HomePageObjects extends BasePageObjects {

    private @FindBy(id = "mainFrame") WebElement dashboardIframe;
    private @FindBy(xpath = "//div[@name='ESubmissionId']") WebElement dashboardMenu;

    private @FindBy(id = "treeViewId") WebElement sideTableMenu;
    //private @FindBy(className = "brand-Red") WebElement newBtn;

    By newBtn=By.cssSelector("button.brand-Red[onclick='showNewFileDiv();']");

    public HomePageObjects() {
        super();
    }

    public void waitHomepage() {
        switchToIframe(dashboardIframe, dashboardMenu);
    }

    public void clickNewBtnHomepage() {
        clickButtonInTable(0, sideTableMenu, newBtn);
    }
}
