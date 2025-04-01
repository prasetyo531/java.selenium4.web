package pageObjects;

import driver.DriverFactory;
import environments.Config;
import environments.Constants;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ElectonicPageObjects extends BasePageObjects {

    private @FindBy(id = "mainFrame") WebElement electronicIframe;
    private @FindBy(id = "frmProfileFrm") WebElement mainForm;
    private @FindBy(id = "docHtmlTemplateId") WebElement selectTemplateField;

    private @FindBy(xpath = "//*[@id=\"frmProfileFrm\"]/table/tbody/tr[2]/td/table/tbody/tr[2]/td[3]/div/div[1]/div[2]") WebElement documentToBeField;
    private @FindBy(xpath = "//*[@id=\"fileRefTable\"]/tbody/tr/td[2]") WebElement folderField;
    private @FindBy(xpath = "//input[@name=\"searchCriteria\"]") WebElement searchCriteriaField;
    private @FindBy(xpath = "//*[@id=\"frmSearchFileRefsFrm\"]/div/div/table/tbody/tr[1]/td/table/tbody/tr/td[5]/input") WebElement searchCriteriaBtn;

    private @FindBy(xpath = "//*[@id=\"uniform-fileRefSysId_581329\"]") WebElement resultSearchRadioBtn;
    private @FindBy(xpath = "//*[@id=\"frmSearchFileRefsFrm\"]/div/div/table/tbody/tr[4]/td/div/input[1]") WebElement okBtn;

    private @FindBy(xpath = "//*[@id=\"keywords1\"]") WebElement keywordField;
    private @FindBy(xpath = "//*[@id=\"synopsis1\"]") WebElement synopsisField;
    private @FindBy(xpath = "//*[@id=\"frmProfileFrm\"]/table/tbody/tr[5]/td/div/a[3]") WebElement saveBtn;
    private @FindBy(xpath = "//*[@id=\"frmProfileFrm\"]/table/tbody/tr[5]/td/div/a[1]") WebElement draftBtn;

    private @FindBy(xpath = "//div[@id='dialog-message' and contains(@class, 'ui-dialog-content') and contains(@class, 'ui-widget-content')]") WebElement errorDialogMsg;

    public ElectonicPageObjects() {
        super();
    }

    public void fillKeywordSynopsis() {
        DriverFactory.getDriver().switchTo().frame(electronicIframe);
        sendKeys(keywordField, "QA TECHNICAL TEST-ELECTRONIC FOLDER");
        sendKeys(synopsisField, "QA TECHNICAL TEST-ELECTRONIC FOLDER");
        //focus back from iframe
        DriverFactory.getDriver().switchTo().defaultContent();
    }

    public void saveElectronicForm() {
        DriverFactory.getDriver().switchTo().frame(electronicIframe);
        waitForWebElementAndClick(saveBtn);
        DriverFactory.getDriver().switchTo().defaultContent();
    }

    public void draftElectronicForm() {
        DriverFactory.getDriver().switchTo().frame(electronicIframe);
        waitForWebElementAndClick(draftBtn);
        DriverFactory.getDriver().switchTo().defaultContent();
    }

    public void fillAndSaveElectronicForm(String foldername) throws AWTException, InterruptedException, IOException {
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

        Properties properties = new Properties();
        FileInputStream file = new FileInputStream(Constants.CONFIG_FILE_PATH);
        properties.load(file);

        String uploadPdfOne = readProperty("upload.pdf.one");
        StringSelection stringSelection = new StringSelection(uploadPdfOne);
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
        DriverFactory.getDriver().switchTo().defaultContent();
        //search criteria
        String originalTab =  DriverFactory.getDriver().getWindowHandle();

        // Wait for the new tab to open
        waitForNewTab(2);

        // Switch to the new tab
        for (String tab : DriverFactory.getDriver().getWindowHandles()) {
            if (!tab.equals(originalTab)) {
                DriverFactory.getDriver().switchTo().window(tab);
                break;
            }
        }

        sendKeys(searchCriteriaField, foldername);
        waitForWebElementAndClick(searchCriteriaBtn);
        waitForWebElementAndClick(resultSearchRadioBtn);
        waitForWebElementAndClick(okBtn);
        waitForNewTab(1);
        DriverFactory.getDriver().switchTo().window(originalTab);
    }

    public void draftElectronicForm(String foldername) throws AWTException, InterruptedException, IOException {
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

        String uploadPdfDraft = readProperty("upload.pdf.draftcase");
        StringSelection stringSelection = new StringSelection(uploadPdfDraft);
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
        DriverFactory.getDriver().switchTo().defaultContent();
        //search criteria
        String originalTab =  DriverFactory.getDriver().getWindowHandle();

        // Wait for the new tab to open
        waitForNewTab(2);

        // Switch to the new tab
        for (String tab : DriverFactory.getDriver().getWindowHandles()) {
            if (!tab.equals(originalTab)) {
                DriverFactory.getDriver().switchTo().window(tab);
                break;
            }
        }

        sendKeys(searchCriteriaField, foldername);
        waitForWebElementAndClick(searchCriteriaBtn);
        waitForWebElementAndClick(resultSearchRadioBtn);
        waitForWebElementAndClick(okBtn);
        waitForNewTab(1);
        DriverFactory.getDriver().switchTo().window(originalTab);
    }

    public void saveExistingElectronicForm() throws AWTException, InterruptedException, IOException {
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

        String uploadPdfExisting = readProperty("upload.pdf.existing");
        StringSelection stringSelection = new StringSelection(uploadPdfExisting);
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
        DriverFactory.getDriver().switchTo().defaultContent();
        //search criteria
        String originalTab =  DriverFactory.getDriver().getWindowHandle();

        // Wait for the new tab to open
        waitForNewTab(2);

        // Switch to the new tab
        for (String tab : DriverFactory.getDriver().getWindowHandles()) {
            if (!tab.equals(originalTab)) {
                DriverFactory.getDriver().switchTo().window(tab);
                break;
            }
        }

        sendKeys(searchCriteriaField, "QA TECHNICAL TEST-ELECTRONIC FOLDER");
        waitForWebElementAndClick(searchCriteriaBtn);
        waitForWebElementAndClick(resultSearchRadioBtn);
        waitForWebElementAndClick(okBtn);
        waitForNewTab(1);
        DriverFactory.getDriver().switchTo().window(originalTab);
    }

    public void saveUploadCompleted() {
        waitFor(errorDialogMsg);
        String errorMsg = errorDialogMsg.getText();
        Assert.assertEquals("The record is registered successfully.", errorMsg);
    }

    public void draftUploadCompleted() {
        waitFor(errorDialogMsg);
        String errorMsg = errorDialogMsg.getText();
        Assert.assertEquals("The draft record is registered successfully.", errorMsg);
    }

    public void recordUploadFailed(String msg) {
        waitFor(errorDialogMsg);
        String errorMsg = errorDialogMsg.getText();
        Assert.assertEquals(msg, errorMsg);
    }
}
