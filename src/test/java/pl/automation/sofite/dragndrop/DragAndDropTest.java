package pl.automation.sofite.dragndrop;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DragAndDropTest {

    static WebDriver driver;

    @BeforeAll
    static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/horizontal_slider");
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }

    @Test
    public void shouldElementBeDropped() {
        WebElement sliderValue = driver.findElement(By.id("range"));
        setSliderValue(4);
        Assertions.assertEquals("4", sliderValue.getText());
        setSliderValue(2);
        Assertions.assertEquals("2", sliderValue.getText());
    }

    private void setSliderValue(int expectedValue) {
        WebElement input = driver.findElement(By.tagName("input"));
        Actions builder = new Actions(driver);
        int inputSize = input.getSize().getWidth();
        int thresholdSize = inputSize/5;
        builder.moveToElement(input, -(inputSize/2),0)
                .clickAndHold()
                .moveByOffset(thresholdSize*expectedValue, 0)
                .release()
                .build().perform();
    }
}
