package com.salesforce.pageobjects;
import com.salesforce.Utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.IOException;

public class POLoginPage  extends TestBase {
    WebDriver driver;
    public POLoginPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements( driver,this);
        System.out.println("driver--------"+driver);
    }
    @FindBy(id="username")
    WebElement ph_username;
    @FindBy(id="password")
    WebElement ph_password;
    @FindBy(id="Login")
    WebElement button_login;
    @FindBy(id="rememberUn")
    WebElement ph_remember;
    @FindBy(id="home_Tab")
    WebElement tab_Home;

       public void loginToSalesForce(String sUserName,String sPassWord) throws Exception{

        oSelUtility.waitForElementVisible(ph_username, 5,driver);
        oSelUtility.sendInput(ph_username,sUserName,"UserName");
        oSelUtility.sendInput(ph_password,sPassWord,"password");
        oSelUtility.clickOnElement(button_login,"Login");
    }
    public boolean validLoginToSalesForce(String sUserName,String sPassWord) throws IOException {
        boolean bRes_Flag=false;
           loginForgetPassword(sUserName, sPassWord);
        oSelUtility.waitForElementVisible(tab_Home, 5,driver);
        oSelUtility.verifyStrings(driver.getTitle(),oComUtility.readPropertiesFile("Page_Title","home.title"),"Home Page");
        if(oSelUtility.isDisplayed(tab_Home,"Home Page"))
            bRes_Flag=true;
        return bRes_Flag;
    }
    @FindBy(xpath ="//span[@id='userNavLabel']")
    WebElement ph_usernameTag;
    @FindBy(id="idcard-identity")
    WebElement ph_userNameNew;
    @FindBy(xpath = "//input[@id='rememberUn']")
    WebElement ph_rememberBox;
    @FindBy(xpath="//a[contains(text(),'Logout')]")
    WebElement ph_logout;
    public boolean loginToSalesForceWithRemember(String sUserName,String sPassWord) throws Exception {
    boolean bRes_Flag=false;
        oSelUtility.waitForElementVisible(ph_username, 5,driver);
        oSelUtility.sendInput(ph_username,sUserName,"UserName");
        oSelUtility.sendInput(ph_password,sPassWord,"password");
        oSelUtility.clickOnElement(ph_remember,"Remember checkbox");
        oSelUtility.clickOnElement(button_login,"Login");
    oSelUtility.clickOnElement(ph_usernameTag,"UsernameTag");
    oSelUtility.clickOnElement(ph_logout,"Logout");
   driver.get(oComUtility.readPropertiesFile("Login_properties","sfdc.webUrl"));
   oSelUtility.verifyStrings(ph_userNameNew.getText(),sUserName,"Username" );
   Assert.assertTrue(oSelUtility.validateCheckBox(ph_rememberBox,"RememberCheckBox"));
        //oSelUtility.validateCheckBox(ph_rememberBox,"RememberCheckBox");
    bRes_Flag=true;
    return bRes_Flag;
}
@FindBy(xpath = "//a[@id='forgot_password_link']")
WebElement ph_forgotPassword;
@FindBy(xpath = "//h2[@id='header']")
WebElement ph_forgetPasswordHeader;
@FindBy(id="un")
WebElement ph_userName;
@FindBy(id="continue")
    WebElement button_continueButton;
@FindBy(xpath = "//h2[@id='header']")
WebElement ph_forgetPassPage;
public boolean loginForgetPassword(String sUserName,String sPassWord) throws IOException {
    boolean bRes_Flag=false;
    oSelUtility.clickOnElement(ph_forgotPassword, "ForgotPassword");
    oSelUtility.verifyStrings(ph_forgetPasswordHeader.getText(), oComUtility.readPropertiesFile("Login_properties", "forgetPassword.errorMsg"), "ForgotPage ");
    oSelUtility.sendInput(ph_userName, sUserName, "Username");
    oSelUtility.clickOnElement(button_continueButton, "Continue");
    oSelUtility.verifyStrings(ph_forgetPassPage.getText(), oComUtility.readPropertiesFile("Login_properties", "forgotPassword.alertMsg"), "Password Reset Message");
    bRes_Flag=true;
    return bRes_Flag;

}
@FindBy(xpath = "//div[@id='error']")
WebElement ph_actualErrorMsg;
public boolean invaildLogin(String sUserName,String sPassWord,String sExpectedErrorMessage) throws Exception {
    boolean bRes_Flag=false;
    loginToSalesForce(sUserName,sPassWord);
    oSelUtility.verifyStrings(ph_actualErrorMsg.getText(),sExpectedErrorMessage,"Password Error message");
    bRes_Flag=true;
    return bRes_Flag;
}

}
