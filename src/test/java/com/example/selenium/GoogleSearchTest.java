package com.example.selenium;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.util.List;

import static utilities.utilities.*;

public class GoogleSearchTest {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setupReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("target/GoogleSearchReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @BeforeMethod
    public void setupDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--disable-infobars");

        driver = new ChromeDriver(options);
    }

    @Test
    public void googleSearchTest() throws InterruptedException {
        test = extent.createTest("Google Search Test", "Search Times of India and print headlines");

        driver.get("https://www.google.com");
        click(driver, "//*[@name='q']");
        sendValueToSearchField(driver,"times of india","//*[@name='q']");

        Wait(driver,10,"//*[contains(text(),'Breaking News')]");
        click(driver,"//*[contains(text(),'Breaking News')]");

        // After line 42
        List<WebElement> maybeLaterBtn = driver.findElements(By.xpath("//*[@class='boxBtnWrap']//*[contains(text(),'Maybe later')]"));
        if (!maybeLaterBtn.isEmpty()) {
            maybeLaterBtn.get(0).click();
        }

        Wait(driver, 10, "(//*[@class='CRKrj style_change edCaE'])[1]");

        List<WebElement> headlines = driver.findElements(
                By.xpath("(//*[@class='CRKrj style_change edCaE'])[position() <= 10]")
        );

// Fetch limited headlines (avoid long loads)
        test.info("<h2 style='color:#1976d2;'>Headlines of the day: " + driver.getTitle() + "</h2>");
        System.out.println("<h2 style='color:#1976d2;'>Headlines of the day: " + driver.getTitle() + "</h2>");

        for (int i = 0; i < Math.min(headlines.size(), 10); i++) {
            test.info("<div style='border:1px solid #ccc;padding:10px;margin:5px 0;border-radius:6px;background:#f9f9f9;'>"
                    + headlines.get(i).getText() + "</div>");
            System.out.println((i + 1) + ". " + headlines.get(i).getText());
        }

        test.pass("Google search headlines fetched successfully!");
    }

    @AfterMethod
    public void tearDown() {
//        if(driver != null) driver.quit();
    }

    @AfterClass
    public void flushReport() {
        extent.flush(); // generates report
    }
}
