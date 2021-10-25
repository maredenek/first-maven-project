package pl.automation.softie.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.automation.softie.AbstractPage;
import pl.automation.softie.pageobjects.LoginPage;

public class HeaderComponent extends AbstractPage {

    @FindBy(xpath = "//a[@class='login']")
    private WebElement singInButton;
    public HeaderComponent(WebDriver driver) {
        super(driver);
    }

    public LoginPage goToLogInPage() {
        singInButton.click();
        return new LoginPage(driver);
    }
}
