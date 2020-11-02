package com.pragmatic.hrm.pages;

import com.pragmatic.hrm.HRMTestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class MenuPage extends HRMTestBase {


    @FindBy(xpath = "//a[@id='menu_pim_viewPimModule']")
    WebElement mnuPIM;

    @FindBy(xpath = "//*[@id='menu_pim_addEmployee']")
    WebElement mnuAddEmployee;


    public AddNewEmployeePage navigateToAddEmployeePage(WebDriver webDriver) {

        mnuPIM.click();
        sleep(5000); //TODO need to incorporate wait until method here
        mnuAddEmployee.click();
        return PageFactory.initElements(webDriver, AddNewEmployeePage.class);
    }




}