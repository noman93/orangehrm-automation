package testrunner;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Dashboard;
import pages.LoginPage;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

public class LoginTestRunner extends Setup {
    LoginPage loginPage;
    @Test(priority = 1,description = " CannotLogin with invalid username and invalid password")

    public void doLoginWithInvalidCreds(){
        driver.get("https://opensource-demo.orangehrmlive.com/");
        loginPage = new LoginPage(driver);
        String actual_msg=loginPage.doLoginWithInvalidCreds("user","password");
        String expected_msg = "Invalid credentials";
        Assert.assertTrue(actual_msg.contains(expected_msg));
    }
    @Test(priority = 2,description = " CannotLogin with invalid username and valid password")

    public void doLoginWithInvalidUsername(){
        loginPage = new LoginPage(driver);
        String actual_msg=loginPage.doLoginWithInvalidUsername("user1234","admin123");
        String expected_msg = "Invalid credentials";
        Assert.assertTrue(actual_msg.contains(expected_msg));
    }


    @Test(priority = 3,description = "User can Do login successfully")
    public void doLogin() throws IOException, ParseException {
        loginPage = new LoginPage(driver);
        //JSONObject userObject= Utils.loadJSONFile("./src/test/resources/user.json");
        //String username = (String) userObject.get("username");
        //String password = (String) userObject.get("password");

        loginPage.doLogin("Admin","admin123");
    }

}
