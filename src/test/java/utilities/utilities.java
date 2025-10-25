package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.management.StringValueExp;
import java.time.Duration;

public class utilities {

    public static void click(WebDriver driver, String locator) {
        driver.findElement(By.xpath(locator)).click();
    }
    public static void click(WebDriver driver, WebElement element) {
        element.click();
    }

    public static void Wait(WebDriver driver, int seconds, String locator) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
    }
    public static void Wait(WebDriver driver, int seconds, WebElement locator) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOf(locator));
    }
    public static void sendValueToSearchField(WebDriver driver, String searchValue, String locator) {
        driver.findElement(By.xpath(locator)).sendKeys(searchValue);
        driver.findElement(By.xpath(locator)).sendKeys(Keys.ENTER);

    }

}
