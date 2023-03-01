package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Dashboard {
    @FindBy(className = "oxd-userdropdown-tab")
    public WebElement btnProfileTab;
    @FindBy(partialLinkText = "Logout")
    public  WebElement linkLogout;
    @FindBy(className = "oxd-main-menu-item--name")

    public List<WebElement> menus;
    @FindBy(tagName = "input")
    public List<WebElement> inputData;
    @FindBy(className = "oxd-main-menu-item--name")
    public List<WebElement> mainMenu;

    public Dashboard(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void clickOnPIM(){
        menus.get(1).click();
    }
    public void doLogout(){
        btnProfileTab.click();
        linkLogout.click();

    }
}
