package pl.automation.sofite.screenshots;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Set;

public class ScreenshotTest {

    static WebDriver driver;

    @BeforeAll
    static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.w3schools.com/tags/att_a_target.asp");
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }

    @Test
    public void shouldTakeAScreenshot() throws IOException {
        driver.findElement(By.linkText("Try it Yourself »")).click();
        Set<String> windowHandles = driver.getWindowHandles();
        takeScreenshot("first_tab.png");
        driver.switchTo().window(windowHandles.toArray()[1].toString());
        takeScreenshot("second_tab.png");
        driver.close();
        driver.switchTo().window(windowHandles.toArray()[0].toString());
        driver.findElement(By.linkText("Try it Yourself »")).click();
    }

    private void takeScreenshot(String name) throws IOException {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("C:\\screens\\" + name));
    }

}
