package com.salesforce.testcases;

import com.salesforce.TestDataProvider.Dataprovider;
import com.salesforce.Utilities.TestBase;
import com.salesforce.pageobjects.POLead;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;

public class LeadPageFeatures extends TestBase {
    POLead oPOLead;
    Logger log=Logger.getLogger(getClass().getSimpleName());
    @BeforeMethod
    public void launchBrowserApplication(Method testName) throws IOException {
        driverWeb=oSelUtility.launchApp();
        oPOLead=new POLead(driverWeb);
        oSelUtility.goToWebPage(oComUtility.readPropertiesFile("Login_properties","sfdc.webUrl"));
        System.out.println("Driver****"+driverWeb.getTitle());
        log.info(driverWeb);
        log.info("App is launching");
        oSelUtility.createReport(testName.getName());
    }
    @AfterMethod
    public void closeBrowserApplication(){
        oSelUtility.closeBrowser();
    }
    @AfterTest
    public  void closeReport(){
        oSelUtility.closeReport();
    }
    @Test(dataProvider = "valid_loginData",dataProviderClass = Dataprovider.class)
    public  void openLeadsPage_TC20(String sUsername,String sPassword) throws Exception {
       // oPOLead.openLeadPage(sUsername,sPassword);
        oPOLead.openLead(sUsername, sPassword);
    }
    @Test(dataProvider = "valid_loginData",dataProviderClass = Dataprovider.class)
    public void checkLeadsDropdown_TC21(String sUsername,String sPassword) throws Exception {
    oPOLead.validateLeadDropDown(sUsername, sPassword);

    }
    @Test(dataProvider = "valid_loginData",dataProviderClass = Dataprovider.class)
    public void checkDefaultView_TC22(String sUsername,String sPassword) throws Exception {
    oPOLead.checkDefaultView(sUsername, sPassword);
    }
    @Test(dataProvider = "valid_loginData",dataProviderClass = Dataprovider.class )
    public void openLeadsPage_TC23(String sUsername,String sPassword) throws Exception {
    oPOLead.openLeadsPage(sUsername, sPassword);
    }
    @Test(dataProvider = "valid_loginData",dataProviderClass = Dataprovider.class)
    public void checkLeadsNew_TC24(String sUsername,String sPassword) throws Exception {
    oPOLead.checkLeadsNew(sUsername, sPassword);
    }
}
