package utils;

import com.github.javafaker.Faker;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.time.Duration;
import java.util.List;

public class Utils {
    public static void doScroll(WebDriver driver, int heightPixel) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0," + heightPixel + ")");

    }


    public static JSONObject loadJSONFile(String fileLocation) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(fileLocation));
        JSONObject jsonObject = (JSONObject) obj;

        return jsonObject;
    }
    public static void waitForElement(WebDriver driver, WebElement webElement, int timeInsec){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInsec));
        wait.until(ExpectedConditions.visibilityOf(webElement));

    }
    public static int generateRandomNumber(int max, int min){
        int number = (int) Math.floor(Math.random()*(max - min) + min);
        return number;
    }


    public void saveJsonList(String firstname,String lastname, String username, String password) throws IOException, ParseException {
        String fileName="./src/test/resources/Users.json";
        JSONParser parser=new JSONParser();
        Object obj= parser.parse(new FileReader(fileName));

        JSONArray jsonArray= (JSONArray) obj;

        JSONObject userObject=new JSONObject();
        userObject.put("firstname",firstname);
        userObject.put("lastname",lastname);
        userObject.put("userName",username);
        userObject.put("password",password);
        //userObject.put("employeeId",empId);

        jsonArray.add(userObject);

        FileWriter file=new FileWriter(fileName);
        file.write(jsonArray.toJSONString());
        file.flush();
        file.close();
        //System.out.println("Saved data");

    }

    public static List readJSONArray(String filename) throws IOException, ParseException {
        JSONParser parser=new JSONParser();
        Object object= parser.parse(new FileReader(filename));
        JSONArray jsonArray= (JSONArray) object;
        return jsonArray;
    }


}