package com.mbtroads;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.model.Media;
import org.apache.commons.io.FileUtils;
import org.graphwalker.websocket.WebSocketServer;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;

public class BaseClass extends BasePage implements ISystemProperties{
    protected static WebDriver driver;

    @Rule
    public TestName testName = new TestName();

    @Before
    public void beforeEachTest() {
        if(ExtentReport.test.getModel().getName().equalsIgnoreCase("test environment preparation")) {
            if(!ExtentReport.test.getModel().hasException()) {
                ExtentReport.removeTestFromReport(ExtentReport.test);
           }
       }

       ExtentReport.createTestInstance(testName.getMethodName());





    }

    @BeforeClass
    public static void beforeTestClass() {
     /*   String driverName = "chromedriver";
        if(OS.contains("Windows")) {
            driverName = "chromedriver.exe";
        }
       */
        ExtentReport.createReportInstance(currentDir + pathSeperator + "Reports" + pathSeperator + "Report.html");
        ExtentReport.createTestInstance("Test Environment Preparation");

        try {

            FileUtils.cleanDirectory(new File(currentDir + pathSeperator + "Reports" + pathSeperator));
            HttpClient.setValues();
            String headless = System.getProperty("Headless");

         //   System.setProperty("webdriver.chrome.driver", currentDir + pathSeperator + "Drivers" + pathSeperator + driverName);

            //ChromeOptions options = new ChromeOptions();
            //if(String.valueOf(headless).equalsIgnoreCase("true")) {
              //  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                //int h = screenSize.height;
                //int w = screenSize.width;

             //   options.setHeadless(true);
               // options.addArguments("window-size=" + w +"," + h);


            //}


            //driver = new ChromeDriver(options);
            //driver.manage().window().maximize();

        } catch(Exception e) {
            e.printStackTrace();
            ExtentReport.test.fail(e);
        }
    }


    public static String getScreenshotAsString() throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        byte[] fileContent = FileUtils.readFileToByteArray(src);
        return Base64.getEncoder().encodeToString(fileContent);
    }

    protected static void delayInMillis(long milliSeconds) {
        try {
            Thread.currentThread().sleep(milliSeconds);
        } catch(Exception e) {
           // ExtentReport.reportError(e);
        }
    }

    public static Media addBase64ScreenShotToReport() throws IOException {
        return MediaEntityBuilder.createScreenCaptureFromBase64String(getScreenshotAsString()).build();
    }

    @AfterClass
    public static void afterTestClass() {
        ExtentReport.flushReport();
       //driver.quit();
    }


}
