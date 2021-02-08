package com.salesforce.TestDataProvider;

import org.testng.annotations.DataProvider;

public class Dataprovider {
    @DataProvider(name="invalid_loginData")
    public Object[][] data_loginInvalid(){

        return new String[][]{{"srir@abc.com","","Please enter your password."},{"123","12345","Please check your username and password. If you still can't log in, contact your Salesforce administrator."}};
    }
    @DataProvider(name="valid_loginData")
    public Object[][] data_login(){
        return new String[][]{{"srimathi@abc.com","sana2021"}} ;
    }
    @DataProvider(name="valid_loginDataForRandom")
    public  Object[][] data_loginRandom(){
        return new String[][]{{"srir@abc.com","mathi2020","SRI Raja","Raja"}} ;
    }
}

