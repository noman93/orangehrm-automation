package testrunner;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesInterface;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.Dashboard;
import pages.LoginPage;
import pages.PIMPage;
import setup.Setup;
import utils.Utils;

import java.io.IOException;
import java.util.List;

public class PIMtestRunner extends Setup {
    Dashboard dashboard;
    Utils utils;
    LoginPage loginPage;
    PIMPage pimPage;
    String randomId = String.valueOf(Utils.generateRandomNumber(10000, 99999));

    @BeforeTest
    public void doLogin() throws IOException, ParseException {
        loginPage = new LoginPage(driver);
        JSONObject userObject= Utils.loadJSONFile("./src/test/resources/user.json");
        String username = (String) userObject.get("username");
        String password = (String) userObject.get("password");
        driver.get("https://opensource-demo.orangehrmlive.com/");
        loginPage.doLogin(username,password);
    }





    @Test(priority = 4,description = "Create 2 new employee")
    public void addEmployee() throws InterruptedException, IOException, ParseException {

        for (int i = 0; i < 2; i++) {

            dashboard = new Dashboard(driver);
            //Thread.sleep(5000);
            dashboard.menus.get(1).click();

            pimPage = new PIMPage(driver);

            utils = new Utils();
            Faker faker = new Faker();
            //utils.generateRandomData();
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            int randomId = Utils.generateRandomNumber(1000, 9999);
            String userName = firstName + randomId;
            String password = "P@ssword123";
            String conFirmPassword = "P@ssword123";
            //String employeeID = String.valueOf(randomId);

            pimPage.btnAddEmployee.get(2).click();
            Thread.sleep(3000);

            pimPage.toggleButton.click();

            pimPage.createEmployee(firstName, lastName, userName, password, conFirmPassword);

            List<WebElement> headerTitle = driver.findElements(By.className("orangehrm-main-title"));
            Assert.assertTrue(headerTitle.get(0).isDisplayed());
            Thread.sleep(10000);

            //utils.waitForElement(driver, headerTitle.get(0), 50);
            utils.saveJsonList(firstName,lastName,userName, password);



        }


    }

    @Test(priority = 5,description = "Search By 1st user name")

    public void searchEmployee() throws InterruptedException, IOException, ParseException {
        dashboard = new Dashboard(driver);
        pimPage = new PIMPage(driver);
        Thread.sleep(3000);
        dashboard.menus.get(1).click();
        Thread.sleep(3000);
        String fileName = "./src/test/resources/Users.json";
        JSONArray jsonArray = (JSONArray) Utils.readJSONArray(fileName);
        int indexOfFirstEmp = jsonArray.size() - 2;
        int indexOfSecondEmp = jsonArray.size() - 1;
        JSONObject firstEmp = new JSONObject();
        firstEmp = (JSONObject) jsonArray.get(indexOfFirstEmp);
        String firstName = (String) firstEmp.get("firstname");
        String lastName = (String) firstEmp.get("lastname");
        String fullName = firstName+" "+lastName;


        dashboard.inputData.get(1).sendKeys(fullName);
        Thread.sleep(1500);
        pimPage.searchBtn.get(1).click();
        Thread.sleep(3000);
        Utils.doScroll(driver,200);

        String isUserFound = driver.findElements(By.className("oxd-text--span")).get(11).getText();
        Assert.assertTrue(isUserFound.contains("Record Found"));




    }
    @Test(priority = 6,description = "Update User ID by Random ID")

    public void updateEmplyeeId() throws InterruptedException {
        pimPage = new PIMPage(driver);
        pimPage.userInfo.get(1).click();
        Thread.sleep(2000);
        Utils.doScroll(driver,100);
        //int randomId = Utils.generateRandomNumber(10000, 99999);
        pimPage.txtUserID.get(5).sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE);
        String employeeID = String.valueOf(randomId);
        pimPage.txtUserID.get(5).sendKeys(employeeID);
        Thread.sleep(2000);
        Utils.doScroll(driver,400);
        pimPage.empinfoSubmit.get(0).click();
        Thread.sleep(4000);
        Utils.doScroll(driver,-4000);






    }
    @Test(priority = 7,description = "Search User by New ID")

    public void searchByNewID() throws InterruptedException {
        pimPage = new PIMPage(driver);
        dashboard = new Dashboard(driver);
        dashboard.menus.get(1).click();
        Thread.sleep(3000);
        dashboard.inputData.get(2).sendKeys(randomId);
        pimPage.searchBtn.get(1).click();
        Thread.sleep(3000);
        Utils.doScroll(driver,200);
        String isUserFound = driver.findElements(By.className("oxd-text--span")).get(11).getText();
        Assert.assertTrue(isUserFound.contains("Record Found"));




    }
    @Test(priority = 8,description = "Logout and Login with Second User")
    public void logoutAndLoginWithSecondUser() throws IOException, ParseException {
        dashboard = new Dashboard(driver);
        dashboard.doLogout();
        loginPage = new LoginPage(driver);
        String fileName = "./src/test/resources/Users.json";
        JSONArray jsonArray = (JSONArray) Utils.readJSONArray(fileName);
        int indexOfSecondEmp = jsonArray.size() - 1;
        JSONObject secondEmp = new JSONObject();
        secondEmp = (JSONObject) jsonArray.get(indexOfSecondEmp);
        String username = (String) secondEmp.get("userName");
        String password = (String) secondEmp.get("password");
        //String username = (String) userObject.get("username");
        //String password = (String) userObject.get("password");
        driver.get("https://opensource-demo.orangehrmlive.com/");
        loginPage.doLogin(username,password);

    }
    @Test(priority = 9,description = "Click On My Info Menu")

    public void clickOnMyInfo() throws InterruptedException {
        dashboard = new Dashboard(driver);
        dashboard.mainMenu.get(2).click();
        Thread.sleep(3000);



    }
@Test(priority = 10,description = "Update Gender And Blood Group")
    public void updateInfo() throws InterruptedException {
        pimPage = new PIMPage(driver);
        Utils.doScroll(driver,300);
        pimPage.genderButton.get(1).click();
        Utils.doScroll(driver,300);
        pimPage.selectMenu.get(2).click();
        Thread.sleep(1000);
        Actions actions=new Actions(driver);
        actions.keyDown(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();


        Thread.sleep(1500);
        pimPage.empinfoSubmit.get(1).click();
        Thread.sleep(3000);


    }
    @Test(priority = 11,description ="Could not Save  User address and email if email is invalid" )

    public void updateContactInfoWithInvalidEmail() throws InterruptedException {
        pimPage = new PIMPage(driver);
        Utils.doScroll(driver,-400);
        pimPage.userDetailBtn.click();
        Thread.sleep(3000);
        String street1 = "3513 Beulah";
        String street2 = "Crossroad";
        String city = "Crossroad";
        String state = "RI";
        String zipCode = "05724";
        String workEmail = "dwintheiser@bradtke.org";
        String otherEmail = "dwintheisertest.com";



        pimPage.txtUserID.get(1).sendKeys(street1);
        pimPage.txtUserID.get(2).sendKeys(street2);
        pimPage.txtUserID.get(3).sendKeys(city);
        pimPage.txtUserID.get(4).sendKeys(state);
        pimPage.txtUserID.get(5).sendKeys(zipCode);
        pimPage.txtUserID.get(9).sendKeys(workEmail);
        pimPage.txtUserID.get(10).sendKeys(otherEmail);

        //Thread.sleep(3000);
        //pimPage.empinfoSubmit.get(0).click();
        //Thread.sleep(3000);
        String errorMsg=pimPage.errorMsg.get(13).getText();
        Assert.assertTrue(errorMsg.contains("Expected format: admin@example.com"));




    }

    @Test(priority = 12,description ="Input User address and email" )

    public void updateContactInfo() throws InterruptedException {
        pimPage = new PIMPage(driver);
        Utils.doScroll(driver,-400);
        pimPage.userDetailBtn.click();
        Thread.sleep(3000);
        String street1 = "3513 Beulah";
        String street2 = "Crossroad";
        String city = "Crossroad";
        String state = "RI";
        String zipCode = "05724";
        String workEmail = "dwintheiser@bradtke.org";
        String otherEmail = "dwintheiser@test.com";



        pimPage.txtUserID.get(1).sendKeys(street1);
        pimPage.txtUserID.get(2).sendKeys(street2);
        pimPage.txtUserID.get(3).sendKeys(city);
        pimPage.txtUserID.get(4).sendKeys(state);
        pimPage.txtUserID.get(5).sendKeys(zipCode);
        pimPage.txtUserID.get(9).sendKeys(workEmail);
        pimPage.txtUserID.get(10).sendKeys(otherEmail);

        Thread.sleep(3000);
        pimPage.empinfoSubmit.get(0).click();
        Thread.sleep(3000);



    }
    @Test(priority = 13,description = "User Can Logout")
    public void logOut(){
        dashboard = new Dashboard(driver);
        dashboard.doLogout();
    }











}
