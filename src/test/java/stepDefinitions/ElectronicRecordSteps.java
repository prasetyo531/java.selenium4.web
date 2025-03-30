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

    @When("User fill valid electronic record form and save")
    public void userFillValidElectronicRecordFormAndSave() throws AWTException, InterruptedException {
        electonicPageObjects.fillAndSaveElectronicForm();
    }
}
