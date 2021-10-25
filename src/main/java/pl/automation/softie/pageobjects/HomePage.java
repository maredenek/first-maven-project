package pl.automation.softie.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.automation.softie.AbstractPage;
import pl.automation.softie.components.HeaderComponent;

public class HomePage extends AbstractPage {

    @FindBy(xpath = "//a[@class='login']")
    private WebElement singInButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HeaderComponent header() {
        return new HeaderComponent(driver);
    }

}
