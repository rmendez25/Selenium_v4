package com.framework.test;

import com.framework.config.ConfigReader;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.util.Objects;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod(description = "Setup browser options")
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-extensions");
        options.addArguments("--incognito");
        options.addArguments("--headless");

        driver = new ChromeDriver(options);
    }

    @AfterMethod(description = "Close browser")
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            captureScreenshot();
        }
        if (driver != null) {
            driver.quit();
        }
    }

    private void captureScreenshot() {
        try {
            // Capture screenshot
            TakesScreenshot ts = (TakesScreenshot) driver;
            File screenshotFile =  ts.getScreenshotAs(OutputType.FILE);

            // Save screenshot to a location
            String screenshotPath = "screenshot/folder/" + System.currentTimeMillis() + ".png";
            FileUtils.copyFile(screenshotFile, new File(screenshotPath));

            // Attach screenshot to Allure report
            Allure.addAttachment("Screenshot", new FileInputStream(screenshotPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
