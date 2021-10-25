package pl.automation.sofite.pageobjecttests;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.chrome.ChromeDriver;
import pl.automation.softie.pageobjects.HomePage;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class LoginTest {

    static ChromeDriver driver;

    @BeforeAll
    static void setUp() {
        ChromeDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
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
    public void shouldNotLoginWithWrongPassword() {
        HomePage homePage = new HomePage(driver);
        String errorMessage = homePage.header()
                .goToLogInPage()
                .loginForm()
                .fillAndSendLoginForm("test@softie.pl", "wrong Password")
                .getErrorMessage();
        Assertions.assertEquals("Authentication failed.", errorMessage);
    }

    @Test
    public void shouldNotLoginWithoutPassword() {
        HomePage homePage = new HomePage(driver);
        String errorMessage = homePage.header()
                .goToLogInPage()
                .loginForm()
                .fillAndSendLoginForm("test@softie.pl", "")
                .getErrorMessage();
        Assertions.assertEquals("Password is required.", errorMessage);
    }

    @Test
    public void shouldNotLoginWithoutEmail() {
        HomePage homePage = new HomePage(driver);
        String errorMessage = homePage.header()
                .goToLogInPage()
                .loginForm()
                .fillAndSendLoginForm("", "test")
                .getErrorMessage();
        Assertions.assertEquals("An email address required..", errorMessage);
    }

    @Test
    public void shouldNotLoginWithoutProvidingCredentials() {
        HomePage homePage = new HomePage(driver);
        String errorMessage = homePage.header()
                .goToLogInPage()
                .loginForm()
                .fillAndSendLoginForm("", "")
                .getErrorMessage();
        Assertions.assertEquals("An email address required.", errorMessage);
    }

    @ParameterizedTest
    @MethodSource("loginFormData")
    public void shouldNotLoginWithIncorrectCredentials(String email, String password, String expectedErrorMessage) {
        HomePage homePage = new HomePage(driver);
        String errorMessage = homePage.header()
                .goToLogInPage()
                .loginForm()
                .fillAndSendLoginForm(email, password)
                .getErrorMessage();
        Assertions.assertEquals(expectedErrorMessage, errorMessage);
    }

    private static Stream<Arguments> loginFormData() {
        return Stream.of(
                Arguments.of("test@softie.pl", "wrong Password", "Authentication failed."),
                Arguments.of("test@softie.pl", "", "Password is required."),
                Arguments.of("", "wrong Password", "An email address required."),
                Arguments.of("", "", "An email address required.")
        );
    }

}
