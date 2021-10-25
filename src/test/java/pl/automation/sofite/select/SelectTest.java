package pl.automation.sofite.select;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class SelectTest {

    static ChromeDriver driver;

    @BeforeAll
    static void setUp() {
        ChromeDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }

    @Test
    void register() throws InterruptedException {
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");

        driver.findElement(By.name("email_create")).sendKeys("fake@fakemail.pl");
        driver.findElement(By.name("email_create")).sendKeys(Keys.ENTER);
        driver.findElement(By.id("customer_firstname")).sendKeys("Automation");
        Select selectDay = new Select(driver.findElement(By.id("days")));
        Select selectMonth = new Select(driver.findElement(By.id("months")));
        Select selectYear = new Select(driver.findElement(By.id("years")));
        selectDay.selectByValue("12");
        selectMonth.selectByValue("12");
        selectYear.selectByValue("2020");
    }


}
