Feature: Muffin wallet single request tests
  
  @Success
  Scenario: Get Muffin wallet by id
    Given muffin wallet exists with parameters:
      | id         | c2d46009-9f29-4613-8d03-36badd85f7c2 |
      | owner_name | alexey                               |
      | balance    | 100                                  |
    Then get muffin wallet by id c2d46009-9f29-4613-8d03-36badd85f7c2 returns data:
      | id         | c2d46009-9f29-4613-8d03-36badd85f7c2 |
      | owner_name | alexey                               |
      | balance    | 100                                  |
    Then check muffin wallet exists with parameters:
      | owner_name | alexey |
      | balance    | 100    |
