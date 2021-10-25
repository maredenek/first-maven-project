package pl.automation.sofite.fluentwait;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class FluentWaitTest {

    static ChromeDriver driver;

    @BeforeAll
    static void setUp() {
        ChromeDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }

    @Test
    public void waitForHelloWorld() {
        Wait<WebDriver> wait = fluentWait();
        WebElement startElement = wait.until(driver -> driver.findElement(By.tagName("button")));
        startElement.click();
        WebElement finishElement = wait.until(driver -> driver.findElement(By.id("finish")));
        String finishElementText = finishElement.getText();
        Assertions.assertEquals("Hello World!", finishElementText);
    }

    private Wait<WebDriver> fluentWait() {
        return new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoreAll(List.of(NoSuchElementException.class));
    }

    private Wait<WebDriver> webDriverWait() {
        return new WebDriverWait(driver, 5000);
    }


}
