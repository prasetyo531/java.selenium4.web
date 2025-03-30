package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ElectonicPageObjects extends BasePageObjects {

    private @FindBy(xpath = "//a[contains(text(),'Electronic Record')]") WebElement electroRecordMenu;

    public ElectonicPageObjects() {
        super();
    }

    public void clickElectroRecordMenu() {
        waitForWebElementAndClick(electroRecordMenu);
    }
}
