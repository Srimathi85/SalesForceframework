package com.salesforce.pageobjects;

import com.salesforce.Utilities.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.Set;

public class POLead extends TestBase {
    WebDriver driver;
    POLoginPage oPOLoginPage;
    public POLead(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements( driver,this);
       oPOLoginPage=new POLoginPage(driver);
    }
    @FindBy(id="username")
    WebElement ph_username;
    @FindBy(id="password")
    WebElement ph_password;
    @FindBy(id="Login")
    WebElement button_login;
    @FindBy(id="home_Tab")
    WebElement tab_Home;
    @FindBy(xpath ="//a[contains(@href,'/home/showAllTabs.jsp')]" )
    WebElement allTab;
    @FindBy(xpath = "//a[@href='/00Q/o']")
    WebElement leads;
    public void openLead(String sUserName,String sPassWord) throws Exception {
     oPOLoginPage.loginToSalesForce(sUserName, sPassWord);
        oSelUtility.waitForElementVisible(tab_Home, 5,driver);
        oSelUtility.verifyStrings(driver.getTitle(),oComUtility.readPropertiesFile("Page_Title","home.title"),"Home Page");
        oSelUtility.clickOnElement(allTab,"All tab");
        oSelUtility.waitForElementVisible(leads, 5,driver);
        oSelUtility.clickOnElement(leads,"Lead");
        oSelUtility.verifyStrings(driver.getTitle(),oComUtility.readPropertiesFile("Leads_Data","leadsPage"),"Lead Page");
    }
    @FindBy(xpath = "//span[@class='fBody']//select")
    WebElement dropdown;
    public void validateLeadDropDown(String sUserName,String sPassWord) throws Exception {
        openLead(sUserName, sPassWord);
        oSelUtility.waitForElementVisible(dropdown,5,driver);
        oSelUtility.clickOnElement(dropdown,"DropDown in Lead Page");
        oSelUtility.verifyDropDown(dropdown,oComUtility.readPropertiesFile("Leads_Data","expectedDropdown"),"DropDown in Page      ");
    }
    @FindBy(xpath = "//select[@id='fcf']")
            WebElement viewTab;
    @FindBy(xpath = "//span[@id='userNavLabel']")
    WebElement userMenu;
    @FindBy(xpath = "//a[contains(text(),'Logout')]")
    WebElement logout;
    @FindBy(xpath = "//a[contains(@href,'/home/showAllTabs.jsp')]")
    WebElement allTab1;
    @FindBy(xpath = "//input[@value=' Go! ']")
    WebElement goButton;
    @FindBy(xpath = "//select[@name='fcf']")
    WebElement menu;
    public void checkDefaultView(String sUserName,String sPassWord) throws Exception {
    openLead(sUserName, sPassWord);
    oSelUtility.waitForElementVisible(viewTab,5,driver);
    oSelUtility.selectOptionInDropDown(viewTab,oComUtility.readPropertiesFile("Leads_Data","optionToSelect"));
    oSelUtility.verifySelectedItemInDropDown(viewTab,oComUtility.readPropertiesFile("Leads_Data","optionToSelect"),oComUtility.readPropertiesFile("Leads_Data","optionToSelect"));
    oSelUtility.clickOnElement(userMenu,"User Menu");
    String sCurrentWindow=driver.getWindowHandle();
    oSelUtility.clickOnElement(logout,"Logout");
    Set<String> windowsList=driver.getWindowHandles();
    String sNewWindow= oSelUtility.switchWindow(sCurrentWindow,windowsList);
    driver.switchTo().window(sNewWindow);
    openLead(sUserName, sPassWord);
    oSelUtility.waitForElementVisible(goButton,5,driver);
    oSelUtility.clickOnElement(goButton,"Go Button");
    oSelUtility.verifySelectedItemInDropDown(menu,oComUtility.readPropertiesFile("Leads_Data","optionToSelect"),oComUtility.readPropertiesFile("Leads_Data","optionToSelect"));
}
    public void openLeadsPage(String sUserName,String sPassWord) throws Exception {
        openLead(sUserName, sPassWord);
        oSelUtility.waitForElementVisible(viewTab,5,driver);
        oSelUtility.selectOptionInDropDown(viewTab,oComUtility.readPropertiesFile("Leads_Data","optionToSelect"));
        oSelUtility.verifySelectedItemInDropDown(viewTab,oComUtility.readPropertiesFile("Leads_Data","optionToSelect"),oComUtility.readPropertiesFile("Leads_Data","optionToSelect"));

    }
    @FindBy(xpath = "//input[@name='new']")
    WebElement newTab;
    @FindBy(xpath = "//input[@id='name_lastlea2']")
    WebElement lastname;
    @FindBy(xpath = "//input[@id='lea3']")
    WebElement companyName;
    @FindBy(xpath = "//input[@name='save']")
    WebElement saveButton;
    @FindBy(xpath = "//h2[@class=\"topName\"]")
    WebElement pageHeader;

    public void checkLeadsNew(String sUserName,String sPassWord) throws Exception {
        openLead(sUserName, sPassWord);
        oSelUtility.waitForElementVisible(newTab,5,driver);
        oSelUtility.clickOnElement(newTab,"New Button");
        oSelUtility.waitForElementVisible(lastname,5,driver);
        oSelUtility.verifyStrings(driver.getTitle(), oComUtility.readPropertiesFile("Leads_Data", "leadsNewPage"),"New Lead ~ Salesforce");
        oSelUtility.sendInput(lastname, oComUtility.readPropertiesFile("Leads_Data", "leads.lastName"),"LastName" );
        oSelUtility.clickOnElement(companyName,"Company Name");
        oSelUtility.sendInput(companyName, oComUtility.readPropertiesFile("Leads_Data", "leads.companyName"),oComUtility.readPropertiesFile("Leads_Data", "leads.companyName") );
        oSelUtility.clickOnElement(saveButton,"Save Button");
        oSelUtility.waitForElementVisible(pageHeader,5,driver);
        oSelUtility.verifyStrings(pageHeader.getText(), oComUtility.readPropertiesFile("Leads_Data","leads.lastName"),"New Page with "+oComUtility.readPropertiesFile("Leads_Data","leads.lastName") );



        }
    }



