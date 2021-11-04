package pl.automation.softie.pageobjects;

import org.openqa.selenium.WebDriver;
import pl.automation.softie.AbstractPage;
import pl.automation.softie.components.LoginForm;
import pl.automation.softie.components.RegisterForm;

public class LoginPage extends AbstractPage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginForm loginForm() {
        return new LoginForm(driver);
    }

    public RegisterForm registerForm() {
        return new RegisterForm(driver);
    }

}
