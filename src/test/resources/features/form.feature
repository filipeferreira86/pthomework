#Author: Filipe Ferreira de Jesus
Feature: As a user I want to abble to my contact information through a google form.
	
  Scenario Outline: Error messages for mandatory fields without information    
    Given Im in the contact form page 
    When I select the option 2 in the first question 
    And Inform all fields except for the <mandatory>
    And click on the submit button
    Then a error message should be presented in the <mandatory>
    Examples: 
      | mandatory  |
      | "Name"     |
      | "Email"    |
      | "Address"  |
    
  Scenario Outline: Error messages when not selecting the third option in the first question    
    Given Im in the contact form page
    When I select the option <option> in the first question
    And Inform all fields 
    And click on the submit button
    Then a error message "Please select the 3rd option" should be presented
    Examples: 
      | option     |
      | 0          |
      | 1          |
      | 3          |
      | 4          |
      | 5          |

	
  Scenario Outline: Clear the selection on the first question
    Given Im in the contact form page
    When I select the option <option> in the first question
    And click on the link for clear selection
    Then no option in the first question should be selected 
    Examples: 
      | option     |
      | 0          |
      | 1          |
      | 2          |
      | 3          |
      | 4          |
      | 5          |  
      
  @test
  Scenario: Clear all the fields in the   
    Given Im in the contact form page 
    When I select the option 3 in the first question 
    And Inform all fields
    And click on the clear form 
    And click on the clear form allert
    Then no option in the first question should be selected
    And all fields should be blank 
  
  Scenario: Error messages for a invalid e-mail    
    Given Im in the contact form page 
    When I select the option 3 in the first question 
    And Inform all fields
    And Change the e-mail for "abc##"
    And click on the submit button
    Then a error message about incorrect format should be presented
  
  Scenario Outline: Sending the form with non-mandatory fields without information    
    Given Im in the contact form page 
    When I select the option 3 in the first question 
    And Inform all fields except for the <non-mandatory>
    And click on the submit button
    Then the form should be submited
    Examples: 
      | non-mandatory |
      | "Phone number"|
      | "Comments"    |

  Scenario: Sending the form with all fields filled
    Given Im in the contact form page 
    When I select the option 3 in the first question 
    And Inform all fields
    And click on the submit button
    Then the form should be submited
