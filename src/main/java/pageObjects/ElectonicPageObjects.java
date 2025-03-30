package pageObjects;

import driver.DriverFactory;
import environments.Config;
import environments.Constants;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class ElectonicPageObjects extends BasePageObjects {

    private @FindBy(id = "mainFrame") WebElement electronicIframe;
    private @FindBy(id = "frmProfileFrm") WebElement mainForm;
    private @FindBy(id = "docHtmlTemplateId") WebElement selectTemplateField;

    private @FindBy(xpath = "//*[@id=\"frmProfileFrm\"]/table/tbody/tr[2]/td/table/tbody/tr[2]/td[3]/div/div[1]/div[2]") WebElement documentToBeField;
    private @FindBy(xpath = "//*[@id=\"fileRefTable\"]/tbody/tr/td[2]") WebElement folderField;
    private @FindBy(xpath = "//*[@id=\"frmSearchFileRefsFrm\"]/div/div/table/tbody/tr[1]/td/table/tbody/tr/td[4]/input") WebElement searchCriteriaField;
    private @FindBy(xpath = "//*[@id=\"frmSearchFileRefsFrm\"]/div/div/table/tbody/tr[1]/td/table/tbody/tr/td[5]/input") WebElement searchCriteriaBtn;

    public ElectonicPageObjects() {
        super();
    }

    public void fillAndSaveElectronicForm() throws AWTException, InterruptedException {

        waitFor(electronicIframe);
        // Switch to the iframe
        DriverFactory.getDriver().switchTo().frame(electronicIframe);
        waitFor(mainForm);

        // Wait for the dropdown to be visible
        Select select = new Select(selectTemplateField);
        select.selectByValue("1");

        //upload pdf
        Thread.sleep(2000);
        clickElementBeforeUpload(documentToBeField);

        Robot rb = new Robot();
        Thread.sleep(2000);

        StringSelection stringSelection = new StringSelection(Config.get(Constants.UPLOAD_PDF_ONE));
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

        // Cmd + Tab is needed since it launches a Java app and the browser looses focus
        Thread.sleep(2000);
        rb.keyPress(KeyEvent.VK_META);
        rb.keyPress(KeyEvent.VK_TAB);
        rb.keyRelease(KeyEvent.VK_META);
        rb.keyRelease(KeyEvent.VK_TAB);
        rb.delay(800);
        //Open Goto window
        rb.keyPress(KeyEvent.VK_META);
        rb.keyPress(KeyEvent.VK_SHIFT);
        rb.keyPress(KeyEvent.VK_G);
        rb.keyRelease(KeyEvent.VK_META);
        rb.keyRelease(KeyEvent.VK_SHIFT);
        rb.keyRelease(KeyEvent.VK_G);
        //Paste the clipboard value
        rb.keyPress(KeyEvent.VK_META);
        rb.keyPress(KeyEvent.VK_V);
        rb.keyRelease(KeyEvent.VK_META);
        rb.keyRelease(KeyEvent.VK_V);
        //Press Enter key to close the Goto window and Upload window
        rb.delay(1000);
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
        rb.delay(1000);
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);

        waitForWebElementAndClick(folderField);

        //search criteria
        sendKeys(searchCriteriaField, "QA TECHNICAL TEST-ELECTRONIC FOLDER");
        waitForWebElementAndClick(searchCriteriaBtn);

        DriverFactory.getDriver().switchTo().defaultContent();
    }
}
