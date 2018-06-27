Feature: Feature Sample

  Background:Background bbb
    Given this is a sample given step
    When this is a sample when step


  Scenario: Sample Scenario1
    Given this is a sample given step
    When this is a sample when step
    Then this is a sample then step


  Scenario Outline: Sample Scenario2
    Given this is a sample given step
    When this is a sample when step
    Then this is a sample then step
    Examples:
      | column1 | column2 | column3 |
      | value1  | value2  | value3  |