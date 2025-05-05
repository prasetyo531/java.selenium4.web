Feature: Failed Electronic Record

  Verify the system's ability to detect and prevent duplicate file uploads in the
  same folder.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              Feature: Add Product

  @Regression
  Scenario Outline: Upload a valid PDF file but with existing details
    Given User has accessed login page
    When User enters credentials "<username>" and "<password>"
    And User directed to homepage
    #When User fill valid electronic record form but with existing details
    #Then Display process registered failed "Record upload failure. The record already exists in the same file reference."

    Examples:
      |   username    |     password      |
      |	  rewina_EU	  |	  SqlP@ssw0rd_2023 	  |