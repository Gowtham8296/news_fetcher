package com.example.selenium;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

import static utilities.utilities.*;

class GoogleSearchTest {

    public static void main(String[] args) throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.google.com");
        click(driver, "//*[@name='q']");
        sendValueToSearchField(driver,"times of india","//*[@name='q']");
        Wait(driver,10,"//*[contains(text(),'Times of India: News - Breaking News')]");
        click(driver,"//*[contains(text(),'Times of India: News - Breaking News')]");
        System.out.println("Page title is: " + driver.getTitle());
        System.out.println("Times of India news headlines:");
        String news = "//*[@class='CRKrj style_change edCaE']";
        Wait(driver,5,news);
        for (WebElement element : driver.findElements(By.xpath(news))) {
            System.out.println(element.getText());
        }

    }
}