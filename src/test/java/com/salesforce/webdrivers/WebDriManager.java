package com.salesforce.webdrivers;

import com.salesforce.driverutilities.IDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriManager implements IDriverManager,IWebDriver {
   public WebDriver driver;
    public void launchApp(String sBrowser) {
        if (sBrowser.toLowerCase().equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

        }
        driver.manage().window().maximize();
    }
    public void quitApp() {
driver.quit();
    }

    public WebDriver getWebDriver() {

        return driver;
    }
}
