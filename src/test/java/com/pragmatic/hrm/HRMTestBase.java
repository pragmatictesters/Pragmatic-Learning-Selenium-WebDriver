package com.pragmatic.hrm;

import com.pragmatic.hrm.pages.LandingPage;
import com.pragmatic.hrm.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.util.Collections;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class HRMTestBase {

    public WebDriver webDriver;
    public static  String BASE_URL;
    public static  String BROWSER_TYPE;



    @BeforeSuite(alwaysRun = true, groups = {"regression", "smoke"})
    public void beforeSuite(){
        initProperties();

    }

    private void initProperties() {
        try {
            Configuration config = new PropertiesConfiguration("hrm.properties");
            BASE_URL = config.getString("base.url");
            BROWSER_TYPE = config.getString("browser.type");

        } catch (ConfigurationException e) {
            e.printStackTrace();
            System.exit(-1);
        }

    }


    @BeforeMethod(groups = {"regression", "smoke"})
    public void beforeMethod(){
        webDriver = getBrowserInstance();
        webDriver.get(BASE_URL);
    }



    @AfterMethod(groups = {"regression", "smoke"})
    public void afterMethod(){

        if (webDriver!=null){
            webDriver.quit();
        }

    }


    public WebDriver getBrowserInstance() {

      if (BROWSER_TYPE.equalsIgnoreCase("chrome")){
          WebDriverManager.chromedriver().setup();
          ChromeOptions options = new ChromeOptions();
          options.addArguments("start-maximized");
          options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
          options.setExperimentalOption("useAutomationExtension", false);
          webDriver= new ChromeDriver(options);

      }else if(BROWSER_TYPE.equalsIgnoreCase("chrome-headless")){
          WebDriverManager.chromedriver().setup();
          ChromeOptions options = new ChromeOptions();
          options.addArguments("--headless");
          options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
          options.setExperimentalOption("useAutomationExtension", false);
          webDriver= new ChromeDriver(options);

      } else {
          //TODO Add a custom error message here
      }

      return webDriver;
    }



    public LandingPage login(String userName, String password){
        LoginPage loginPage= PageFactory.initElements(webDriver,LoginPage.class);
        LandingPage landingPage = loginPage.typeUsername(userName).typePassword(password).clickLoginWithSuccess(webDriver);
        return landingPage;

    }


    public void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




}