package pl.automation.softie.pageobjects;

import org.openqa.selenium.WebDriver;
import pl.automation.softie.AbstractPage;
import pl.automation.softie.components.LoginForm;

public class LoginPage extends AbstractPage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginForm loginForm() {
        return new LoginForm(driver);
    }
}
