Feature: Save Electronic Record

  Automate the process of uploading a valid PDF file and saving it,
  verifying that the system correctly processes the upload and provides appropriate feedback.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             Feature: Add Product

  @Scenario1
  Scenario Outline: Upload a valid PDF file and saving it
    Given User has accessed login page
    When User enters credentials "<username>" and "<password>"
    And User directed to homepage
    #When User fill valid electronic record form in "<folder>" and save
    #Then Display process registered successfully

    Examples:
      |   username    |     password      |  folder     |
      |	  rewina_EU	  |	  SqlP@ssw0rd_2023 | QA TECHNICAL TEST-ELECTRONIC FOLDER |