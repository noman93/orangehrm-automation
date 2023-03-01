package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pages.Dashboard;
import utils.Utils;

import java.util.List;

public class PIMPage {
    Dashboard dashboard;
    @FindBy(className = "oxd-button--secondary")
    static
    List<WebElement> button;
    WebDriver driver;
    @FindBy(className = "oxd-button")
    public List<WebElement> btnAddEmployee;

    @FindBy(name = "firstName")
    static
    WebElement txtFirstName;

    @FindBy(name = "lastName")
    static
    WebElement txtLastName;

    @FindBy(className = "oxd-switch-input")
    public WebElement toggleButton;

    @FindBy(className = "oxd-input")
    public static List <WebElement> txtUserCreds;

    @FindBy(css = "[type = submit]")
    static
    WebElement btnSubmit;
    @FindBy(className = "oxd-button")
    public List<WebElement> searchBtn;
    @FindBy(className = "oxd-table-row")
    public List<WebElement> userInfo;
    @FindBy(className = "oxd-input")
    public List<WebElement> txtUserID;
    @FindBy(className = "oxd-button")
    public List<WebElement> empinfoSubmit;
    @FindBy(className = "oxd-radio-input")
    public List<WebElement> genderButton;
    @FindBy(className = "oxd-select-text")

    public List<WebElement> selectMenu;
    @FindBy(partialLinkText = "Contact Details")

    public WebElement userDetailBtn;
    @FindBy(className = "oxd-text")
    public List<WebElement> errorMsg;













    public PIMPage(WebDriver driver){
        PageFactory.initElements(driver,this);

    }
    public static void createEmployee(String firstName, String lastName, String userName, String password, String confirmPassword) throws InterruptedException {
        txtFirstName.sendKeys(firstName);
        txtLastName.sendKeys(lastName);

        Thread.sleep(2000);
       // String username = "test"+Utils.generateRandomNumber(1000,9999);
        txtUserCreds.get(5).sendKeys(userName);

        txtUserCreds.get(6).sendKeys(password);
        txtUserCreds.get(7).sendKeys(confirmPassword);
        btnSubmit.click();


    }


}
