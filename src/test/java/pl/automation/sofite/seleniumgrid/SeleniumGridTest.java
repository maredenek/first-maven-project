package pl.automation.sofite.seleniumgrid;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import pl.automation.sofite.utils.UserDataGenerator;
import pl.automation.softie.components.Gender;
import pl.automation.softie.pageobjects.HomePage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class SeleniumGridTest {

    static RemoteWebDriver driver;

    @BeforeAll
    static void setUp() throws MalformedURLException {
        //ChromeDriverManager.chromedriver().setup();
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", "firefox");
        driver = new RemoteWebDriver(new URL("http://localhost:5555/wd/hub"), caps);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }


    @BeforeEach
    void beforeEach() {
        driver.get("http://automationpractice.com");
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }

    @Test
    // exercise 19
    public void shouldNotRegisterWithIncompliteData() {
        HomePage homePage = new HomePage(driver);
        Gender gender = UserDataGenerator.generateRandomGender();
        String firstName = UserDataGenerator.generateRandomFirstName();
        String lastName = UserDataGenerator.generateRandomLastName();
        String password = UserDataGenerator.generateRandomPassword();
        String email = UserDataGenerator.generateRandomEmail();
        boolean isErrorDisplayed = homePage.header()
                .goToLogInPage()
                .registerForm()
                .openRegisterFormUsing(email)
                .registerUser(gender, firstName, lastName, password)
                .isErrorDisplayedWithoutTryCatch();
        Assertions.assertTrue(isErrorDisplayed, "Error message is not displayed");
    }

}
