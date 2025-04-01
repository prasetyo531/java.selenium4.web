package stepDefinitions;

import io.cucumber.java.en.Then;
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
        electonicPageObjects.draftElectronicForm(foldername);
        electonicPageObjects.fillKeywordSynopsis();
        electonicPageObjects.draftElectronicForm();
    }

    @When("User fill valid electronic record form but with existing details")
    public void userFillValidElectronicRecordFormButWithExistingDetails() throws InterruptedException, AWTException {
        electonicPageObjects.saveExistingElectronicForm();
        electonicPageObjects.fillKeywordSynopsis();
        electonicPageObjects.saveElectronicForm();
    }

    @Then("Display process registered successfully")
    public void displayProcessRegisteredSuccessfully() {
        electonicPageObjects.saveUploadCompleted();
    }

    @Then("Display process draft record registered successfully")
    public void displayProcessDraftRecordRegisteredSuccessfully() {
        electonicPageObjects.draftUploadCompleted();
    }

    @Then("Display process registered failed {string}")
    public void displayProcessRegisteredFailed(String message) {
        electonicPageObjects.recordUploadFailed(message);
    }
}
