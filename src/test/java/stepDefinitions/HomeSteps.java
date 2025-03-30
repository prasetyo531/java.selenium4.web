package stepDefinitions;

import io.cucumber.java.en.And;
import pageObjects.BasePageObjects;
import pageObjects.HomePageObjects;

public class HomeSteps extends BasePageObjects {

    private HomePageObjects homePageObjects;

    public HomeSteps(HomePageObjects homePageObjects) {
        this.homePageObjects = homePageObjects;
    }

    @And("User directed to homepage")
    public void userDirectedToHomepage() {
        homePageObjects.waitHomepage();
        homePageObjects.clickNewBtnHomepage();
    }
}
