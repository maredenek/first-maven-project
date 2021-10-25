package pl.automation.sofite.selectors;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SelectorsTest {

    static ChromeDriver driver;

    @BeforeAll
    static void setUp() {
        ChromeDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://stackoverflow.com/");
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }

    @Test
    public void shouldFindSeveralElements() {
        WebElement leftSidebar = driver.findElement(By.className("left-sidebar-toggle"));
        WebElement topSearch = driver.findElement(By.className("s-input__search"));
        WebElement loginButton = driver.findElement(By.linkText("Log in"));
        WebElement topSingUpButton = driver.findElement(By.cssSelector("a[href='/users/signup']"));
        WebElement parentOfTopSignButton = driver.findElement(By.xpath("//div[@class='flex--item p16 sm:p12 sm:mb12']"));
        // dot before xpath locator is crucial here
        WebElement topSingUpButtonSolution2 = parentOfTopSignButton.findElement(By.xpath(".//a[@href='/users/signup']"));
        Assertions.assertEquals("Stack Overflow - Where Developers Learn, Share, & Build Careers", driver.getTitle());
    }


}
