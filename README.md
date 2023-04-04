# Orangehrm Automation Testing
### This is a complete project where a Demo site is automated by writing test suites using selenium-webdriver and TestNg as testing framework.

## Scenerio
1. Login to orange hrm demo site
https://opensource-demo.orangehrmlive.com/

2. Create 2 new employees and save it to a JSON list
3. Now go to PIM dashboard and search by 1st user name. Assert that the user is found.
4. Now click on the user from the search table and update id by random userid
5. Now again search the user by new user id from the PIM dashboard menu and assert that the user is found
6. Now logout from admin and login with the 2nd user from your JSON list
7. Now click on My Info menu
8. Select Gender and Blood Type and save it
9. Click on contact details and input address and email
10. Logout the user 

Key test cases(total 15) are written for each module and test suites created including the positive and negative test cases.

## Technology and Tool Used
- Selenium Webdriver
- TestNG
- Java
- Gradle
- intellij idea 
- Allure

## How to run this project
- clone this project
- hit the following command into the terminal:
  - gradle clean test
- For generating Report in Allure hit
  - allure generate allure-results --clean -o allure-report
  - allure serve allure-results  
  
  
  ## Automation reports:
  
  
  ![allureOverview](https://user-images.githubusercontent.com/28690228/222207949-f9922476-ad4c-4ae4-bd26-44a336cf648f.png)

  ![allureBehavior](https://user-images.githubusercontent.com/28690228/222208006-edea37a5-c6cb-4a81-9184-6376b04d6673.png)
  
  
  ## Video Output:
  

https://user-images.githubusercontent.com/28690228/222210101-d408577b-29ac-4304-a9c6-4ba67703b88b.mp4



  
