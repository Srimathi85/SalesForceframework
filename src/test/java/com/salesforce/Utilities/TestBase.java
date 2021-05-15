package com.salesforce.Utilities;


import com.jayway.jsonpath.JsonPath;
import com.salesforce.webdrivers.WebDriManager;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {
    public static String sConfig;
    public static String sBrowser;
    public static String sAutomation;
    public WebDriver driverWeb;
    public static CommonUtility oComUtility=new CommonUtility();
    public static SelUtility oSelUtility=new SelUtility();
    public WebDriManager oWebDriManager=new WebDriManager();
    Logger log=Logger.getLogger(getClass().getSimpleName());
@BeforeSuite
 public void manageDependency() throws Exception {
    oComUtility.loadLog4jProperty(System.getProperty("user.dir") + "/testresources/testdata/log4j.properties");
    sConfig = CommonUtility.readFileReturnInString(System.getProperty("user.dir") + "/resources/testdata/config.json");
    sAutomation = JsonPath.read(sConfig, "automation");
    BasicConfigurator.configure();
    if (sAutomation.toLowerCase().equals("web"))
        sBrowser=JsonPath.read(sConfig,"browser");
       // driverWeb=oSelUtility.launchApp();


}
@AfterSuite
    public void terminateApp() {
    if(sAutomation.toLowerCase().equals("web")){
        oSelUtility.quitApp();
    }
}
}
