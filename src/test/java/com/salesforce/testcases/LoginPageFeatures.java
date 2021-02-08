package com.salesforce.testcases;
import com.salesforce.TestDataProvider.Dataprovider;
import com.salesforce.Utilities.TestBase;
import com.salesforce.pageobjects.POLoginPage;
import org.apache.log4j.Logger;
import org.testng.annotations.*;


import java.io.IOException;
import java.lang.reflect.Method;
public class LoginPageFeatures extends TestBase {
    POLoginPage oPOLoginPage;
    Logger log=Logger.getLogger(getClass().getSimpleName());
    //@BeforeTest
    //public void settingDependency() throws IOException {
      //  oPOLoginPage=new POLoginPage(driverWeb);
    //}

   @BeforeMethod
   public void launchBrowserApplication(Method testName) throws IOException {
      // oWebDriManager.launchApp(sBrowser);
       driverWeb=oSelUtility.launchApp();
       //oSelUtility.launchApp(oComUtility.readPropertiesFile("Login_properties","sfdc.webUrl"));

       oPOLoginPage=new POLoginPage(driverWeb);
        oSelUtility.goToWebPage(oComUtility.readPropertiesFile("Login_properties","sfdc.webUrl"));
       System.out.println("Driver****"+driverWeb.getTitle());
       log.info(driverWeb);
       log.info("App is launching");
       //oSelUtility.goToWebPage(oComUtility.readPropertiesFile("Login_properties","sfdc.webUrl"));
        oSelUtility.createReport(testName.getName());

   //oCommonUtility.launchBrowser(readPropertiesFile("Login_properties","sfdc.webUrl"));

    }
    @AfterMethod
    public void closeBrowserApplication(){
        oSelUtility.closeBrowser();
    }
    @AfterTest
    public  void closeReport(){
       oSelUtility.closeReport();
    }
    @Test(dataProvider = "invalid_loginData",dataProviderClass = Dataprovider.class)
    public void loginError_Testcase1(String sUsername,String sPassword,String sExpectedErrorMessage) throws Exception {
        oPOLoginPage.invaildLogin(sUsername, sPassword, sExpectedErrorMessage);
    }
    @Test(dataProvider = "valid_loginData",dataProviderClass = Dataprovider.class)
    public void loginSFDC_Testcase2(String username,String password) throws Exception {
        oPOLoginPage.loginToSalesForce(username, password);
    }
    @Test(dataProvider = "valid_loginData",dataProviderClass = Dataprovider.class)
    public void loginSFDCWithRemember_Testcase3(String sUsername,String sPassword) throws Exception {
        oPOLoginPage.loginToSalesForceWithRemember(sUsername, sPassword);
    }
    @Test(dataProvider = "valid_loginData",dataProviderClass = Dataprovider.class)
    public void loginForgetPassword_Testcase4(String sUsername,String sPassword) throws IOException {
       oPOLoginPage.loginForgetPassword(sUsername, sPassword);
    }


}
