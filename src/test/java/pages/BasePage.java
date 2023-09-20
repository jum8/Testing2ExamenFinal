package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;


    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofMillis(2000));
    }

    public void setup() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
        driver.manage().window().maximize();
    }

    public void url(String url) throws InterruptedException {
        driver.get(url);
        Thread.sleep(1000);
    }

    public void close() {
        driver.quit();
    }

    public WebElement findElement(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return driver.findElement(locator);
    }

    public void sendText(String imputText, By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        this.findElement(locator).clear();
        this.findElement(locator).sendKeys(imputText);
    }

    public void sendKey(CharSequence pressKeys, By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        this.findElement(locator).sendKeys(pressKeys);
    }

    public void clickear(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        this.findElement(locator).click();
    }

    public String getText(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return this.findElement(locator).getText();
    }

    public void selectByText(By locator, String text) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        Select dropdown = new Select(this.findElement(locator));
        dropdown.selectByVisibleText(text);

    }

    public void selectByIndex(By locator, int index) {
        wait.until(ExpectedConditions.presenceOfNestedElementsLocatedBy(locator, By.tagName("option")));
        Select dropdown = new Select(this.findElement(locator));
        dropdown.selectByIndex(index);

    }

}