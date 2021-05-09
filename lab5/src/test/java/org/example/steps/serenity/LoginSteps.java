package org.example.steps.serenity;

import net.thucydides.core.annotations.Step;
import org.example.pages.LoginPage;

public class LoginSteps {

    private LoginPage loginPage;

    @Step
    public void openPage() {
        this.loginPage.open();
    }

    @Step
    public void inputUsername(String username) {
        this.loginPage.inputUsername(username);
    }

    @Step
    public void inputPassword(String password) {
        this.loginPage.inputPassword(password);
    }

    @Step
    public void clickLogin() {
        this.loginPage.clickLogin();
    }

    @Step
    public void login(String username, String password) {
        this.openPage();
        this.inputUsername(username);
        this.inputPassword(password);
        this.clickLogin();
    }
}
