package org.example.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebElement;

@DefaultUrl("http://localhost:8100/login")
public class LoginPage extends PageObject {

    @FindBy(xpath = "//ion-input[@id='username-input']/input")
    private WebElement usernameInput;

    @FindBy(xpath = "//ion-input[@id='password-input']/input")
    private WebElement passwordInput;

    @FindBy(id = "login-submit")
    private WebElement loginButton;

    public void inputUsername(String username) {
        this.usernameInput.sendKeys(username);
    }

    public void inputPassword(String password) {
        this.passwordInput.sendKeys(password);
    }

    public void clickLogin() {
        this.loginButton.click();
    }
}
