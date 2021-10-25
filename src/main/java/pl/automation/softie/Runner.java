package pl.automation.softie;

import org.openqa.selenium.chrome.ChromeDriver;

public class Runner {

    public static void main(String[] args) {
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://google.com");
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();
        driver.quit();
    }

}
