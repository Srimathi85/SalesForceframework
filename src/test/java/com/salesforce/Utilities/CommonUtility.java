package com.salesforce.Utilities;





import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class CommonUtility {
    Properties oProperties=new Properties();
    FileInputStream oFileInStream=null;
    Logger log=Logger.getLogger(getClass().getSimpleName());

    public static String readFileReturnInString(String sPathOfJson) throws Exception{
        byte[] encoded= Files.readAllBytes(Paths.get(sPathOfJson));
        return new String(encoded, StandardCharsets.UTF_8);

    }
    public void loadLog4jProperty(String PropertiesFilePath) throws IOException {
        log.info("Log4j Property File Path : "+PropertiesFilePath);
        oFileInStream=new FileInputStream(PropertiesFilePath);
        oProperties.load(oFileInStream);
        PropertyConfigurator.configure(oProperties);

    }
    public  String readPropertiesFile(String fileName,String keyValue) throws IOException {
        Properties properties=new Properties();
        FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\testresources\\testData\\"+fileName+".properties");
        properties.load(fis);
        return properties.getProperty(keyValue);
    }
    static void readFromExcelFile() throws IOException {
        File xlFile = new File(System.getProperty("user.dir") + "\\TestData\\SalesForcePageData.xlsx");
        FileInputStream fis = new FileInputStream(xlFile);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("Sheet1");
        int row = sheet.getLastRowNum();
        int column = sheet.getRow(row).getLastCellNum();
        String loginTitle = sheet.getRow(1).getCell(1).getStringCellValue();
    }

    }
