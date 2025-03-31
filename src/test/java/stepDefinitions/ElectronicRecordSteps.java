package stepDefinitions;

import io.cucumber.java.en.When;
import pageObjects.BasePageObjects;
import pageObjects.ElectonicPageObjects;

import java.awt.*;

public class ElectronicRecordSteps extends BasePageObjects  {

    private ElectonicPageObjects electonicPageObjects;

    public ElectronicRecordSteps(ElectonicPageObjects electonicPageObjects) {
        this.electonicPageObjects = electonicPageObjects;
    }

    @When("User fill valid electronic record form in {string} and save")
    public void userFillValidElectronicRecordFormInAndSave(String foldername) throws AWTException, InterruptedException {
        electonicPageObjects.fillAndSaveElectronicForm(foldername);
        electonicPageObjects.fillKeywordSynopsis();
        electonicPageObjects.saveElectronicForm();
    }

    @When("User fill valid electronic record form in {string} and draft")
    public void userFillValidElectronicRecordFormInAndDraft(String foldername) throws AWTException, InterruptedException {
        electonicPageObjects.fillAndSaveElectronicForm(foldername);
        electonicPageObjects.fillKeywordSynopsis();
        electonicPageObjects.draftElectronicForm();
    }
}
